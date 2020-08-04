package Client.View.View.Panels;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.*;
import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.Model.Enums.Attribute;
import Client.Model.Enums.Type;
import Client.Model.Images;
import Client.View.Configs.ConfigsLoader;
import Client.View.Configs.BoardConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import static Client.View.View.Panels.Constants.*;

public class BoardPanel extends JPanel implements MouseMotionListener, MouseListener, ActionListener {

    private BoardConfig config;

    private MyTimer myTimer;

    private Timer toMiddleTimer, toHandTimer, playTimer, sleepTimer;

    private int X, Y, AiX, AiY, XA, YA, playedIndex, selectedTargetIndex, deckIndex,
            mouseDesX, mouseDesY, mouseStartX = -1000, mouseStartY = -1000, index = 0;

    private boolean flag, disabled, practiceMode, playedCardSelected = false,
            cardSelected = false, AiTurn = false;

    private String handCardSelectedName, playedCardSelectedName;

    private ArrayList<Images> handImages, friendlyPlayedImages, enemyPlayedImages;
    private JButton nextTurnButton, back, exit;
    private CardPreview cardPreview;

    private ActionChartPanel actionChartPanel;

    private BufferedImage temp1 = gamePics.get("playbackground");
    private BufferedImage temp2 = gamePics.get("playboard");

    private RequestHandler rh = RequestHandler.getInstance();
    private Responses res = Responses.getInstance();

    private float size = 20;
    private Font font = new Font("Serif", Font.PLAIN, 20);
    private ArrayList<JButton> actionTargetButton, attackTargetButton;
    private SummonedCardPanel summonedCardPanel;
    private JLabel label;

    public BoardPanel(boolean practiceMode) {
        this.practiceMode = practiceMode;
        initConfig();
        setLayout(null);
        addMouseMotionListener(this);
        addMouseListener(this);
        handImages = new ArrayList<>();
        friendlyPlayedImages = new ArrayList<>();
        enemyPlayedImages = new ArrayList<>();
        actionTargetButton = new ArrayList<>();
        attackTargetButton = new ArrayList<>();

        toMiddleTimer = new Timer(1000 / 60, middleTimerListener);
        toHandTimer = new Timer(1000 / 60, handTimerListener);
        if (practiceMode) {
            toMiddleTimer = new Timer(1000 / 60, AiMiddleTimerListener);
            toHandTimer = new Timer(1000 / 60, AiHandTimerListener);
        }
        playTimer = new Timer(1000 / 60, playTimerListener);
        sleepTimer = new Timer(300, sleepTimerListener);
        sleepTimer.start();


        cardPreview = CardPreview.getInstance();
        cardPreview.setBounds(0, 0, 600, 600);
        cardPreview.setLayout(null);
        cardPreview.setVisible(false);
        cardPreview.setOpaque(false);
        add(cardPreview);

        actionChartPanel = new ActionChartPanel();
        actionChartPanel.setBounds(1000, 50, 500, 400);
        actionChartPanel.setLayout(null);
        actionChartPanel.setVisible(false);
        actionChartPanel.setOpaque(false);
        add(actionChartPanel);

        summonedCardPanel = new SummonedCardPanel();
        summonedCardPanel.setBounds(100, 250, 350, 400);
        summonedCardPanel.setLayout(null);
        summonedCardPanel.setVisible(false);
        summonedCardPanel.setOpaque(false);
        add(summonedCardPanel);

        nextTurnButton = new JButton();
        nextTurnButton.setBounds(1250, 435, 130, 50);
        nextTurnButton.setIcon(gameIcon.get("next"));
        nextTurnButton.setContentAreaFilled(false);
        nextTurnButton.setRolloverEnabled(false);
        nextTurnButton.setFocusable(false);
        nextTurnButton.setBorderPainted(false);
        nextTurnButton.addActionListener(this);
        add(nextTurnButton);

        back = new JButton();
        back.setIcon(gameIcon.get("back"));
        back.setFocusable(false);
        back.addActionListener(this);
        back.setBounds(1430, 890, 60, 60);
        back.setContentAreaFilled(false);
        back.setRolloverEnabled(false);
        back.setBorderPainted(false);
        add(back);

        exit = new JButton();
        exit.addActionListener(this);
        exit.setIcon(gameIcon.get("exit"));
        exit.setBounds(1500, 890, 60, 60);
        exit.setFocusable(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverEnabled(false);
        exit.setBorderPainted(false);
        add(exit);

        label = new JLabel();
        label.setSize(new Dimension(150, 60));
        label.setBounds(1450, 10, 150, 60);
        label.setFont(f2.deriveFont(30.0f));
        label.setForeground(Color.WHITE);
        label.setBackground(Color.ORANGE);
        add(label);

        myTimer = new MyTimer(label);
        myTimer.start();

    }

    @Override
    protected void paintComponent(Graphics gx) {
        Graphics2D g = (Graphics2D) gx;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.clearRect(0, 0, 1600, 1000);
        g.drawImage(temp1, 0, 0, 1600, 1000, null);
        g.drawImage(temp2, config.getLeftLineX(), config.getUpLineY(), config.getRightLineX() - config.getLeftLineX(), config.getBottomLineY() - config.getUpLineY(), null);
        g.setFont(fantasy.deriveFont(30.f));
        g.setColor(Color.yellow);

        requests();

        clear();


        drawUserInfo(g);

        drawLog(g);

        drawHeroPortraits(g);

        drawHeroPower(g);

        drawHeroInfo(g);

        drawWeapon(g);

        drawMana(g);

        cardNumber(g);

        handCards(g);

        playedCard(g);

        playCardAnimation(g);

        drawDeckAnimation(g);

        drawSelectionStatus(g);

    }

    private void requests() {
        RequestHandler.getInstance().sendRequest(new BoardPanelRequest());
        label.setText(res.board.getTime() + "");

    }


    private void drawUserInfo(Graphics2D g) {
        String down = res.board.getFriendlyUser();
        String up = res.board.getEnemyUser();
        g.drawString(down, 30, 920);
        g.drawString(up, 30, 50);
    }

    private void drawHeroPortraits(Graphics2D g) {
        String name = res.board.getEnemyHero();
        g.drawImage(heroPortraits.get(name.toLowerCase()), 710, 80, 175, 255, null);
        name = res.board.getFriendlyHero();
        g.drawImage(heroPortraits.get(name.toLowerCase()), 710, 660, 175, 255, null);

    }

    private void drawHeroPower(Graphics2D g) {
        g.setFont(fantasy.deriveFont(24.0f));
        int down = res.board.getDownPowerUsage();
        int up = res.board.getUpPowerUsage();
        if (down > 0) {
            String name = res.board.getFriendlyHero();
            BufferedImage temp = powerPics.get(name);
            String mana = res.board.getDownPowerMana() + "";
            g.drawImage(temp, config.getPlayerHeroPowerX(), config.getPlayerHeroPowerY(), config.getHeroPoerWidth(), config.getHeroPowerHeight(), null);
            g.drawString(mana, config.getPlayerHeroPowerX() + (config.getHeroPoerWidth() / 2) - 5, config.getPlayerHeroPowerY() + 25);
        }
        if (up > 0) {
            String name = res.board.getEnemyHero();
            BufferedImage temp = powerPics.get(name);
            String mana = res.board.getUpPowerMana() + "";
            g.drawImage(temp, config.getPlayerHeroPowerX(), config.getOpponentHeroPowerY(), config.getHeroPoerWidth(), config.getHeroPowerHeight(), null);
            g.drawString(mana, config.getPlayerHeroPowerX() + (config.getHeroPoerWidth() / 2) - 5, (config.getOpponentHeroPowerY()) + 25);
        }
    }

    private void drawWeaponInfo(Graphics2D g) {
        g.setFont(fantasy.deriveFont(28.0f));
        g.setColor(Color.white);
        if (res.board.isDownHasWeapon()) {
            CardModelView view = res.board.getDownWeapon();
            g.drawString(view.getDamage() + "", 625, 805);
            g.drawString(view.getHp() + "", 695, 805);
        }
        if (res.board.isUpHasWeapon()) {
            CardModelView view = res.board.getUpWeapon();
            g.drawString(view.getDamage() + "", 625, 220);
            g.drawString(view.getHp() + "", 695, 220);
        }
    }

    private void drawMana(Graphics2D g) {
        g.setFont(f2.deriveFont(30.0f));
        int notUsed = res.board.getNotUsedMana();
        int total = res.board.getTotalMana();
        g.drawString(notUsed + "/" + total, 1035, 935);
        int x = 1110;
        BufferedImage mana = gamePics.get("mana");
        for (int i = 0; i < notUsed; i++) {
            g.drawImage(mana, x, 917, 30, 30, null);
            x += 26;
        }
    }

    private void drawHeroInfo(Graphics2D g) {
        g.setFont(fantasy.deriveFont(28.0f));
        g.setColor(Color.white);
        BufferedImage blood = gamePics.get("blood");
        BufferedImage defence = gamePics.get("defence");
        g.drawImage(blood, 860, 800, 70, 70, null);
        g.drawImage(defence, 670, 815, 45, 45, null);
        g.drawImage(blood, 860, 210, 70, 70, null);
        g.drawImage(defence, 670, 225, 45, 45, null);
        String downHp = res.board.getDownHP() + "";
        String upHp = res.board.getUpHP() + "";
        String downDefence = res.board.getDownDefence() + "";
        String upDefence = res.board.getUpDefence() + "";
        g.drawString(downHp, 882, 855);
        g.drawString(upHp, 882, 265);
        g.drawString(downDefence, 685, 845);
        g.drawString(upDefence, 685, 255);
    }

    private void handCards(Graphics2D g) {
        g.setFont(f2.deriveFont(20.0f));
        handImages.clear();

        int downSize = res.board.getDownHandSize();
        int upSize = res.board.getUpHandSize();
        if (downSize > 0) {
            int i = 0;
            if (toHandTimer.isRunning() || toMiddleTimer.isRunning()) {
                downSize--;
            }
            while (i < downSize) {
                String name = res.board.getHandCards().get(i).getName().toLowerCase();
                handImages.add(new Images(name, config.getPlayerHandX(), config.getPlayerHandY(), config.getCardWidth(), config.getCardHeight(), i));
                drawHandCard(g, i, config.getPlayerHandX(), config.getPlayerHandY(), config.getCardWidth(), config.getCardHeight());
                config.setPlayerHandX(config.getPlayerHandX() + config.getCardWidth() - 15);
                i++;
            }
            config.setPlayerHandX(570);
        }
        if (upSize > 0) {
            int i = 0;
            while (i < upSize) {
                drawEnemyCard(g, config.getPlayerHandX(), config.getOpponentHandY(), config.getCardWidth(), config.getCardHeight());
                config.setPlayerHandX(config.getPlayerHandX() + config.getCardWidth() - 15);
                i++;
            }
            config.setPlayerHandX(570);
        }
    }

    private void drawEnemyCard(Graphics2D g, int x, int y, int width, int height) {
        BufferedImage image = gamePics.get("enemycard");
        g.drawImage(image, x, y, width, height, null);
    }

    private void drawHandCard(Graphics2D g, int i, int x, int y, int width, int height) {
        BufferedImage image = cardPics.get(res.board.getHandCards().get(i).getName().toLowerCase());
        g.drawImage(image, config.getPlayerHandX(), config.getPlayerHandY(), config.getCardWidth(), config.getCardHeight(), null);
    }

    private void playedCard(Graphics2D g) {
        friendlyPlayedImages = new ArrayList<>();
        int downSize = res.board.getDownPlayedCards().size();
        int upSize = res.board.getUpPlayedCards().size();
        if (downSize > 0) {
            int i = 0;
            int Spacing = 5;
            if (cardSelected) {
                config.setPlayerPlayedCardX(config.getPlayerPlayedCardX() - 30);
                Spacing = 30;
            }
            while (i < downSize) {
                drawPlayedCard(g, i);
                config.setPlayerPlayedCardX(config.getPlayerPlayedCardX() + config.getCardWidth() * 2 + Spacing);
                i++;
            }
            config.setPlayerPlayedCardX(430);
        }
        if (upSize > 0) {
            int i = 0;
            int Spacing = 5;
            while (i < upSize) {
                drawEnemyPlayedCard(g, i);
                config.setPlayerPlayedCardX(config.getPlayerPlayedCardX() + config.getCardWidth() * 2 + Spacing);
                i++;
            }
            config.setPlayerPlayedCardX(430);
        }

    }

    private void drawPlayedCard(Graphics2D g, int i) {
        CardModelView view = res.board.getDownPlayedCards().get(i);
        g.drawImage(view.getImage(), config.getPlayerPlayedCardX(), config.getPlayerPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2 - 30, null);
        if (view.getType().equals(Type.Minion))
            drawSpecialInfo(g, view);
        if (view.getSleep())
            drawZzz(g, view, config.getPlayerPlayedCardX(), config.getPlayerPlayedCardY());
        friendlyPlayedImages.add(new Images(view.getName(), config.getPlayerPlayedCardX(), config.getPlayerPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2, i, view.getSleep()));
    }

    private void drawZzz(Graphics2D g, CardModelView view, int x, int y) {
        g.setColor(Color.GREEN);
        g.setFont(font.deriveFont(size));
        int xa, ya;
        if (index == 0) {
            xa = x + 5;
            ya = y;
        } else if (index == 1) {
            xa = x + 6;
            ya = y - 3;
        } else {
            xa = x + 7;
            ya = y - 6;
        }
        g.drawString("Z", xa, ya);
    }

    private void drawSpecialInfo(Graphics2D g, CardModelView view) {
        if (view.getAttributes() != null && view.getAttributes().contains(Attribute.DivineShield)) {
            g.setColor(Color.RED);
            g.setFont(f2.deriveFont(30.0f));
            int x = config.getPlayerPlayedCardX();
            int y = config.getPlayerPlayedCardY() + (config.getCardHeight() * 2);
            g.drawString("S", x, y);
        }
        if (view.getAttributes() != null && view.getAttributes().contains(Attribute.Taunt)) {
            g.setColor(Color.BLUE);
            g.setFont(f2.deriveFont(30.0f));
            int x = config.getPlayerPlayedCardX() + (config.getCardWidth() * 2 - 30);
            int y = config.getPlayerPlayedCardY() + (config.getCardHeight() * 2);
            g.drawString("T", x, y);
        }
    }

    private void drawEnemyPlayedCard(Graphics2D g, int i) {
        CardModelView view = res.board.getUpPlayedCards().get(i);
        g.drawImage(view.getImage(), config.getPlayerPlayedCardX(), config.getOpponentPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2 - 30, null);
        synchronized (enemyPlayedImages) {
            enemyPlayedImages.add(new Images(view.getName(), config.getPlayerPlayedCardX(), config.getOpponentPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2, i, view.getSleep()));
        }
        drawEnemySpecialInfo(g, view);
    }

    private void drawEnemySpecialInfo(Graphics2D g, CardModelView view) {
        if (view.getAttributes() != null && view.getAttributes().contains(Attribute.DivineShield)) {
            g.setColor(Color.RED);
            g.setFont(f2.deriveFont(30.0f));
            int x = config.getPlayerPlayedCardX();
            int y = config.getOpponentPlayedCardY();
            g.drawString("S", x, y);
        }
        if (view.getAttributes() != null && view.getAttributes().contains(Attribute.Taunt)) {
            g.setColor(Color.BLUE);
            g.setFont(f2.deriveFont(30.0f));
            int x = config.getPlayerPlayedCardX() + (config.getCardWidth() * 2 - 30);
            int y = config.getOpponentPlayedCardY();
            g.drawString("T", x, y);
        }
    }

    private void playCardAnimation(Graphics2D g) {
        if (config.isPlayAnimation()) {
            BufferedImage image = cardPics.get(handCardSelectedName.toLowerCase());
            g.drawImage(image, X, Y, config.getCardWidth(), config.getCardHeight(), null);
        }
    }

    private void drawSelectionStatus(Graphics2D g) {
        g.setColor(new Color(255, 255, 255, 120));
        g.fillRoundRect(1405, 650, 170, 240, 20, 20);
        g.setFont(fantasy.deriveFont(30.f));
        g.setColor(Color.cyan);
        g.drawString("Choosed", 1440, 680);
        if (cardSelected) {
            BufferedImage image = cardPics.get(handCardSelectedName.toLowerCase());
            g.drawImage(image, 1420, 690, 150, 200, null);
        }
    }

    private void friendlyHandView(int x, int y) {
        int k = -2;
        int i = 0;
        for (Images images : handImages) {
            if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                k = i;
                break;
            }
            i++;
        }
        if (k == -2) {
            return;
        }
        CardModelView model = res.board.getHandCards().get(k);
        cardPreview.setCardModelView(model);
        cardPreview.setBounds(x - 50, y - 360, 260, 370);
        cardPreview.setVisible(true);
    }

    private void drawLog(Graphics2D g) {
        g.setColor(new Color(0, 182, 222, 100));
        g.fillRoundRect(10, 80, config.getInfoWidth(), 790, 50, 50);
        g.setColor(Color.white);
        g.setFont(fantasy.deriveFont(16.0f));
        int size = res.board.getLogs().size();
        if (size > 0) {
            ArrayList<String> log = res.board.getLogs();
            for (String s : log) {
                g.drawString(s, config.getLogX(), config.getLogY());
                config.setLogY(config.getLogY() + config.getLogSpace());
            }
        }
        config.setLogY(120);
    }

    private void cardNumber(Graphics2D g) {
        g.setFont(fantasy.deriveFont(50.0f));
        String down = res.board.getDownDeckSize() + "";
        String up = res.board.getUpDeckSize() + "";
        g.drawString(down, 1430, 620);
        g.drawString(up, 1430, 400);
    }

    public synchronized void summonedMinion(CardModelView view, int mode, int damage, int hp) {
        new Thread(() -> {
            summonedCardPanel.setView(view);
            summonedCardPanel.setMode(0);
            if (mode != 0) {
                summonedCardPanel.setMode(mode);
                summonedCardPanel.setDamage(damage);
                summonedCardPanel.setHP(hp);
            }
            summonedCardPanel.setVisible(true);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            summonedCardPanel.setVisible(false);
        }).start();
    }

    private void enemyPlayedView(int x, int y) {
        if (!flag) {
            int i = -1;
            for (Images images : enemyPlayedImages) {
                if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                    i = images.getIndex();
                    break;
                }
            }
            if (i == (-1)) {
                return;
            }
            CardModelView view = res.board.getUpPlayedCards().get(i);
            cardPreview.setCardModelView(view);
            cardPreview.setBounds(x - 50, y, 260, 370);
            cardPreview.setVisible(true);
        }
    }


    public void drawPracticeDamage(int i, int j, int attack1, int attack2) {
        Graphics2D g = (Graphics2D) getGraphics();
        g.setFont(fantasy.deriveFont(40.0f));
        g.setColor(Color.white);
        int x1, y1, x2, y2;
        if (i >= 0 && j >= 0) {
            x1 = enemyPlayedImages.get(i).getX();
            y1 = enemyPlayedImages.get(i).getY();
            x2 = friendlyPlayedImages.get(j).getX();
            y2 = friendlyPlayedImages.get(j).getY();
            g.drawImage(gamePics.get("damage"), x1, y1, null);
            g.drawString(attack2 + "", x1 + 50, y1 + 80);
            g.drawImage(gamePics.get("damage"), x2, y2, null);
            g.drawString(attack1 + "", x2 + 50, y2 + 80);
        } else if (i >= 0) {
            x2 = 750;
            y2 = 700;
            g.drawImage(gamePics.get("damage"), x2, y2, null);
            g.drawString(attack1 + "", x2 + 50, y2 + 80);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void drawTargetsForAttack(ArrayList<Integer> targets) {
        for (Integer i : targets) {
            JButton button = new JButton();
            button.setName(i + "");
            button.setIcon(gameIcon.get("redtarget"));
            button.setBorderPainted(false);
            button.setFocusable(false);
            button.setContentAreaFilled(false);
            button.addMouseListener(BoardPanel.this);
            button.addActionListener(new AttackListener(button));
            if (i >= 0) {
                button.setBounds(enemyPlayedImages.get(i).getX(), enemyPlayedImages.get(i).getY(), 75, 75);
            } else if (i == -1) {
                button.setBounds(750, 150, 75, 75);
            }
            add(button);
            attackTargetButton.add(button);
        }
    }

    private void selectCard(int x, int y) {
        int k = -2;
        int i = 0;
        String st = null;
        for (Images images : handImages) {
            if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                st = images.getName();
                k = i;
                break;
            }
            i++;
        }
        if (k == -2) {
            return;
        }
        int mana = res.board.getNotUsedMana();
        CardModelView view = res.board.getHandCards().get(k);
        if (mana >= view.getManaCost()) {
            handCardSelectedName = st;
            cardSelected = true;
        }
        revalidate();
        repaint();
    }

    private void cardSelectedAction(int x, int y) {
        new Thread(() -> {
            deckIndex = 0;
            if (friendlyPlayedImages.size() > 0) {
                if (x <= friendlyPlayedImages.get(0).getX()) {
                    deckIndex = 0;
                } else if (x >= friendlyPlayedImages.get(friendlyPlayedImages.size() - 1).getX()) {
                    deckIndex = friendlyPlayedImages.size();
                } else {
                    for (int j = 0; j < friendlyPlayedImages.size() - 1; j++) {

                        if ((x > (friendlyPlayedImages.get(j).getX() + friendlyPlayedImages.get(j).getWidth() - 10)) && (x < (friendlyPlayedImages.get(j + 1).getX() + 15))) {
                            deckIndex = j + 1;
                            break;
                        }
                    }
                }
            }
            RequestHandler.getInstance().sendRequest(new CanBePlayedRequest(handCardSelectedName));
            boolean flag = res.isCanBePlayed();
            RequestHandler.getInstance().sendRequest(new PureModelViewRequest(handCardSelectedName));
            CardModelView view = res.getCardModelView();
            boolean s = false;
            if (view.isNeedEnemyTarget()) {
                selectEnemyTargetForCardActions();
                s = true;
            }
            if (view.isNeedFriendlyTarget()) {
                selectFriendlyTarget();
                s = true;
            }
            if (s) {
                synchronized (config) {
                    try {
                        config.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (flag) {
                config.setPlayAnimation(true);
                X = mouseStartX;
                Y = mouseStartY;
                playTimer.start();
            }
        }).start();

    }

    private void drawFriendlyTargetsForHeroPower() {
        for (int i = 0; i < friendlyPlayedImages.size() + 1; i++) {
            JButton button = new JButton();
            button.setName("1" + i);
            if (i == friendlyPlayedImages.size()) {
                button.setName("1");
            }
            button.setIcon(gameIcon.get("greentarget"));
            button.setBorderPainted(false);
            button.setFocusable(false);
            button.setContentAreaFilled(false);
            button.addMouseListener(BoardPanel.this);
            button.addActionListener(new HeroLestener(button));
            if (i < friendlyPlayedImages.size()) {
                button.setBounds(friendlyPlayedImages.get(i).getX(), friendlyPlayedImages.get(i).getY(), 75, 75);
            } else {
                button.setBounds(750, 700, 75, 75);
            }
            add(button);
            attackTargetButton.add(button);
        }
    }

    private void drawEnemmyTargetForHeroPower() {
        for (int i = 0; i < enemyPlayedImages.size() + 1; i++) {
            JButton button = new JButton();
            button.setName("2" + i);
            if (i == enemyPlayedImages.size()) {
                button.setName("2");
            }
            button.setIcon(gameIcon.get("redtarget"));
            button.setBorderPainted(false);
            button.setFocusable(false);
            button.setContentAreaFilled(false);
            button.addMouseListener(BoardPanel.this);
            button.addActionListener(new HeroLestener(button));
            if (i < enemyPlayedImages.size()) {
                button.setBounds(enemyPlayedImages.get(i).getX(), enemyPlayedImages.get(i).getY(), 75, 75);
            } else {
                button.setBounds(750, 150, 75, 75);
            }
            add(button);
            attackTargetButton.add(button);
        }
    }

    private void initConfig() {
        config = ConfigsLoader.getInstance().getBoardConfig();
        X = config.getDeckX();
        Y = config.getDeckY();
        AiX = config.getAiDeckX();
        AiY = config.getDeckY();
        XA = (config.getMiddleX() - config.getDeckX()) / config.getFps();
        YA = (config.getMiddleY() - config.getDeckY()) / config.getFps();
    }

    private void playHeroPower() {
        RequestHandler.getInstance().sendRequest(new HeroPowerCanBePlayedRequest());
        int i = res.getHeroPowerCanBePlayed();
        switch (i) {
            case 1:
                drawFriendlyTargetsForHeroPower();
                break;
            case 2:
                drawEnemmyTargetForHeroPower();
                break;
            case 3:
                RequestHandler.getInstance().sendRequest(new PlayHeroPowerRequest());
                break;
            case 0:
                break;
        }
    }

    private void drawWeapon(Graphics2D g) {
        if (res.board.isDownHasWeapon()) {
            CardModelView view = res.board.getDownWeapon();
            g.drawImage(view.getImage(), 615, 680, config.getCardWidth() + 50, config.getCardHeight() + 50, null);
            if (!res.board.isHeroCanAttack()) {
                g.setColor(new Color(57, 57, 57, 230));
                g.fillRect(615, 680, config.getCardWidth() + 50, config.getCardHeight() + 50);
            }
        }
        if (res.board.isUpHasWeapon()) {
            CardModelView view = res.board.getUpWeapon();
            g.drawImage(view.getImage(), 615, 95, config.getCardWidth() + 50, config.getCardHeight() + 50, null);
        }
        drawWeaponInfo(g);
    }

    private void drawDeckAnimation(Graphics2D g) {
        if (config.isAnimated()) {
            BufferedImage card;
            if (practiceMode && AiTurn) {
                card = gamePics.get("enemycard");
            } else {
                card = cardPics.get(res.board.getHandCards().get(res.board.getHandCards().size() - 1).getName().toLowerCase());
            }
            if (config.isToMiddle()) {
                Color color = g.getColor();
                g.setColor(new Color(222, 222, 222, config.getBlur()));
                g.fillRect(0, 0, 1600, 1000);
                g.setColor(color);
                config.setMaxWidth(config.getCardWidth() + config.getBlur());
                config.setMaxHeigth(config.getCardHeight() + config.getBlur());
                if (practiceMode && AiTurn) {
                    g.drawImage(card, AiX, AiY, config.getCardWidth() + config.getBlur(), config.getCardHeight() + config.getBlur(), null);
                } else {
                    g.drawImage(card, X, Y, config.getCardWidth() + config.getBlur(), config.getCardHeight() + config.getBlur(), null);
                }
                config.setBlur(config.getBlur() + 2);
            } else {
                if (practiceMode && AiTurn) {
                    g.drawImage(card, AiX, AiY, config.getMaxWidth() + config.getBlur(), config.getMaxHeigth() + config.getBlur(), null);

                } else {
                    g.drawImage(card, X, Y, config.getMaxWidth() + config.getBlur(), config.getMaxHeigth() + config.getBlur(), null);

                }
                config.setBlur(config.getBlur() - 5);
            }
        }
        if (!config.isPlayAnimation() && config.isDeckAnimationFinished()) {
            config.setDeckAnimationFinished(true);
            AiX = config.getAiDeckX();
            AiY = config.getAiDeckY();
            X = config.getDeckX();
            Y = config.getDeckY();
        }
    }


    public void drawDamages(int i, int j, int attack1, int attack2) {
        repaint();
        Graphics2D g = (Graphics2D) getGraphics();
        g.setFont(fantasy.deriveFont(40.0f));
        g.setColor(Color.white);
        int x1, y1, x2, y2;
        if (i >= 0 && j >= 0) {
            x1 = friendlyPlayedImages.get(i).getX();
            y1 = friendlyPlayedImages.get(i).getY();
            x2 = enemyPlayedImages.get(j).getX();
            y2 = enemyPlayedImages.get(j).getY();
            g.drawImage(gamePics.get("damage"), x1, y1, null);
            g.drawString(attack2 + "", x1 + 50, y1 + 80);
            g.drawImage(gamePics.get("damage"), x2, y2, null);
            g.drawString(attack1 + "", x2 + 50, y2 + 80);
        } else if (i >= 0) {
            x2 = 750;
            y2 = 100;
            g.drawImage(gamePics.get("damage"), x2, y2, null);
            g.drawString(attack1 + "", x2 + 50, y2 + 80);
        } else if (j >= 0) {
            x1 = 750;
            y1 = 700;
            x2 = enemyPlayedImages.get(j).getX();
            y2 = enemyPlayedImages.get(j).getY();
            g.drawImage(gamePics.get("damage"), x1, y1, null);
            g.drawString(attack2 + "", x1 + 50, y1 + 80);
            g.drawImage(gamePics.get("damage"), x2, y2, null);
            g.drawString(attack1 + "", x2 + 50, y2 + 80);
        } else {
            x2 = 750;
            y2 = 100;
            g.drawImage(gamePics.get("damage"), x2, y2, null);
            g.drawString(attack1 + "", x2 + 50, y2 + 80);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void selectEnemyTargetForCardActions() {
        int i = 0;
        synchronized (enemyPlayedImages) {
            for (Images image : enemyPlayedImages) {
                JButton button = new JButton();
                button.setIcon(gameIcon.get("redtarget"));
                button.setName("2" + i);
                button.setBorderPainted(false);
                button.setRolloverEnabled(false);
                button.setContentAreaFilled(false);
                button.setBounds(image.getX() + 50, image.getY(), 75, 75);
                button.addMouseListener(BoardPanel.this);
                button.addActionListener(e -> {
                    selectedTargetIndex = Integer.parseInt(button.getName());
                    synchronized (config) {
                        config.notify();
                    }
                    removeButton();
                });
                actionTargetButton.add(button);
                add(button);
                i++;
            }
        }
    }

    private void selectFriendlyTarget() {
        int i = 0;
        for (Images image : friendlyPlayedImages) {
            JButton button = new JButton();
            button.setIcon(gameIcon.get("greentarget"));
            button.setName("1" + i);
            button.setBorderPainted(false);
            button.setRolloverEnabled(false);
            button.setContentAreaFilled(false);
            button.addMouseListener(BoardPanel.this);
            button.setBounds(image.getX() + 50, image.getY(), 75, 75);
            button.addActionListener(e -> {
                selectedTargetIndex = Integer.parseInt(button.getName());
                synchronized (config) {
                    config.notify();
                }
                removeButton();
            });
            actionTargetButton.add(button);
            add(button);
            i++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src == exit) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Exit Button"));
            RequestHandler.getInstance().sendRequest(new ExitRequest());
        } else if (src == back) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Back Button"));
            RequestHandler.getInstance().sendRequest(new VisiblePanelRequest("menu"));
        } else if (src == nextTurnButton) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : NextTurn Button"));
            clear();
            change();
            boolean flag = res.board.getDownDeckSize() > 0;
            RequestHandler.getInstance().sendRequest(new EndTurnRequest());
            if (flag) {
                config.setToMiddle(true);
                config.setBlur(0);
                config.setAnimated(true);
                if (!practiceMode || !AiTurn) {
                    toMiddleTimer.start();
                }
            }
            myTimer.flag1 = false;
            playedCardSelected = false;
            removeButton();
            revalidate();
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (disabled) {
            return;
        }
        cardPreview.setVisible(false);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (disabled) {
            return;
        }
        cardPreview.setVisible(false);
        int x = e.getX();
        int y = e.getY();
        if (x >= 1500 && y >= 50 && y <= 150) {
            actionChartPanel.setVisible(true);
        } else {
            actionChartPanel.setVisible(false);
            if (y >= config.getPlayerHandY()) {
                friendlyHandView(x, y);
            } else if (y < config.getPlayerHandY()) {
                if (y > config.getMiddleLineY()) {
                    friendlyPlayedView(x, y);
                } else {
                    enemyPlayedView(x, y);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (disabled) {
            return;
        }
        int x = e.getX();
        int y = e.getY();
        if (y >= config.getPlayerHeroPowerY()) {
            if (!cardSelected) {
                mouseStartX = x;
                mouseStartY = y;
            }
            if (x >= 750 && x <= 850 && y >= 700 && y <= 850) {
                RequestHandler.getInstance().sendRequest(new HeroCanAttackRequest());
                if (res.isHeroCanAttack()) {
                    playedIndex = -1;
                    RequestHandler.getInstance().sendRequest(new TargetListRequest(this));
                }
            } else if (x >= config.getPlayerHeroPowerX() && x <= (config.getPlayerHeroPowerX() + config.getHeroPoerWidth()) && y >= config.getPlayerHeroPowerY() && y <= (config.getPlayerHeroPowerY() + config.getHeroPowerHeight())) {
                playHeroPower();
            } else if (y >= config.getPlayerHandY()) {
                selectCard(x, y);
            }
        } else if (y >= config.getMiddleLineY() && y < config.getPlayerHeroPowerY()) {
            if (cardSelected) {
                mouseDesX = x;
                mouseDesY = y;
                if (x >= config.getLeftLineX() && x <= config.getRightLineX() && y >= config.getMiddleLineY() && y <= config.getPlayAreaY()) {
                    cardSelectedAction(x, y);
                }
                cardSelected = false;
            } else {
                for (Images image : friendlyPlayedImages) {
                    if (x >= image.getX() && x <= image.getX() + image.getWidth() && y >= image.getY() && y <= image.getY() + image.getHeigth()) {
                        RequestHandler.getInstance().sendRequest(new CanDoActionRequest(image.getIndex()));
                        boolean flag = res.isCanDoAction();
                        if (flag) {
                            playedCardSelected = true;
                            RequestHandler.getInstance().sendRequest(new TargetListRequest(this));
                            playedCardSelectedName = image.getName();
                            playedIndex = image.getIndex();
                            return;
                        }
                    }
                }
                removeButton();
                playedCardSelected = false;
            }

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (disabled) {
            return;
        }
        revalidate();
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (disabled) {
            return;
        }
        cardPreview.setVisible(false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (disabled) {
            return;
        }
        if (e.getSource() instanceof JButton) {
            flag = true;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (disabled) {
            return;
        }
        if (e.getSource() instanceof JButton) {
            flag = false;
        }
    }

    private void clear() {
        handImages = new ArrayList<>();
        synchronized (enemyPlayedImages) {
            friendlyPlayedImages = new ArrayList<>();
            enemyPlayedImages = new ArrayList<>();
        }
    }

    private void change() {
        AiTurn = !AiTurn;
        if (practiceMode && AiTurn) {
            toMiddleTimer.removeActionListener(middleTimerListener);
            toMiddleTimer.addActionListener(AiMiddleTimerListener);
            toHandTimer.removeActionListener(handTimerListener);
            toHandTimer.addActionListener(AiHandTimerListener);
        } else {
            if (practiceMode) {
                toMiddleTimer.removeActionListener(AiMiddleTimerListener);
                toMiddleTimer.addActionListener(middleTimerListener);
                toHandTimer.removeActionListener(AiHandTimerListener);
                toHandTimer.addActionListener(handTimerListener);
            }
        }
    }

    public void request(Object object) {
        toMiddleTimer.start();
        while (toMiddleTimer.isRunning() || toHandTimer.isRunning()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void friendlyPlayedView(int x, int y) {
        if (!flag) {
            int i = -1;
            if (x >= 900 && x <= 1000 && y >= 700 && y <= 800) {
                i = -2;
            }
            if (i != -2) {
                for (Images images : friendlyPlayedImages) {
                    if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                        i = images.getIndex();
                        break;
                    }
                }
            }
            if (i == (-1)) {
                return;
            }
            CardModelView view;
            if (i == -2) {
                view = new CardModelView(powerPics.get(Responses.getInstance().board.getFriendlyHero().toLowerCase()));
            } else {
                view = res.board.getDownPlayedCards().get(i);
            }
            cardPreview.setCardModelView(view);
            cardPreview.setBounds(x - 50, y - 360, 260, 370);
            cardPreview.setVisible(true);
        }
    }

    private void removeButton() {
        for (JButton button : actionTargetButton) {
            remove(button);
        }
        for (JButton button : attackTargetButton) {
            remove(button);
        }
        actionTargetButton.clear();
        attackTargetButton.clear();
        flag = false;
    }


    public void AiTurn(boolean aiTurn) {
        if (aiTurn) {
            disabled = true;
            nextTurnButton.setEnabled(false);
        } else {
            change();
            myTimer.aiTurn = false;
            config.setBlur(0);
            toMiddleTimer.start();
            disabled = false;
            nextTurnButton.setEnabled(true);
        }
    }

    private ActionListener sleepTimerListener = e -> {
        size += 10;
        if (size >= 45) {
            size = 20;
        }
        index++;
        if (index == 3) {
            index = 0;
        }
        repaint();

    };
    private ActionListener middleTimerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            disabled = true;
            config.setToMiddle(true);
            config.setDeckAnimationFinished(false);
            config.setAnimated(true);
            repaint();
            X += XA;
            Y += YA;
            if (X < 700) {
                config.setToMiddle(false);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                config.setBlur(0);
                toHandTimer.start();
                toMiddleTimer.stop();
            }
            repaint();
        }
    };
    private ActionListener handTimerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int desX = 0;
            if (handImages.size() > 0) {
                desX = (700 - (handImages.get(handImages.size() - 1).getX() + handImages.get(handImages.size() - 1).getWidth()) + 15) / 30;
            }
            Y += 20;
            X -= desX;
            if (Y > 950) {
                config.setDeckAnimationFinished(true);
                config.setAnimated(false);
                disabled = false;
                toHandTimer.stop();
            }
            repaint();
        }
    };
    private ActionListener AiHandTimerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
            AiY -= 20;
            if (AiY < -100) {
                config.setDeckAnimationFinished(true);
                config.setAnimated(false);
                disabled = false;
                toHandTimer.stop();
            }
            repaint();
        }
    };
    private ActionListener AiMiddleTimerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            disabled = true;
            config.setDeckAnimationFinished(false);
            config.setAnimated(true);
            repaint();
            AiX += XA;
            AiX -= YA;
            if (AiX < 700) {
                config.setToMiddle(false);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                config.setBlur(0);
                toHandTimer.start();
                toMiddleTimer.stop();
            }
            repaint();
        }
    };
    private ActionListener playTimerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            disabled = true;
            int desX = (mouseDesX - mouseStartX) / 30;
            int desY = (mouseDesY - mouseStartY) / 30;
            X += desX;
            Y += desY;
            repaint();
            if (Y < mouseDesY) {
                config.setPlayAnimation(false);


                RequestHandler.getInstance().sendRequest(new PlayCardRequest(handCardSelectedName, deckIndex, selectedTargetIndex));
                disabled = false;
                selectedTargetIndex = -1;
                playTimer.stop();
            }
        }
    };


    class AttackListener implements ActionListener {
        JButton button;

        public AttackListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            RequestHandler.getInstance().sendRequest(new AttackRequest(playedIndex, Integer.parseInt(button.getName()), BoardPanel.this));
            removeButton();
            playedCardSelected = false;
            playedCardSelectedName = null;
            playedIndex = -3;
        }
    }

    class HeroLestener implements ActionListener {

        private JButton button;

        public HeroLestener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            RequestHandler.getInstance().sendRequest(new PlayHeroPowerRequest(Integer.parseInt(button.getName())));
            removeButton();
            playedCardSelected = false;
            playedCardSelectedName = null;
            playedIndex = -3;
        }
    }

    class MyTimer extends Thread {
        private final java.text.SimpleDateFormat timerFormat = new java.text.SimpleDateFormat(" ss");
        private JLabel label;
        private long startTime;
        private boolean flag1;
        private boolean isClicked;
        private boolean aiTurn;

        public MyTimer(JLabel label) {
            this.label = label;
        }

        public void run() {
            while (true) {
                isClicked = true;
                flag1 = true;
                aiTurn = true;
                label.setForeground(Color.WHITE);
                startTime = 60 * 1000 + System.currentTimeMillis();
                while (flag1 && aiTurn && startTime - System.currentTimeMillis() > 0) {
                    long time = startTime - System.currentTimeMillis();
                    label.setText(timerFormat.format(time));
                    if (time < 20 * 1000) label.setForeground(Color.RED);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (flag1) {
                    isClicked = false;
                }
                label.setText("finished");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!isClicked && aiTurn) {
                    nextTurnButton.doClick();
                }
            }
        }
    }

}


