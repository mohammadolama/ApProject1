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
import Client.View.View.ActionListeners.*;
import Client.View.View.MouseListeners.BoardMouseListener;
import Client.View.View.MouseListeners.BoardMouseMotionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import static Client.View.View.Panels.Constants.*;


public class BoardPanel extends JPanel {

    private BoardConfig config;

//    private MyTimer myTimer;

    private Timer toMiddleTimer, toHandTimer, playTimer, sleepTimer, requests;

    private int X1, Y1, P2X, P2Y, XA, YA, playedIndex, selectedTargetIndex, deckIndex,
            mouseDesX, mouseDesY, mouseStartX = -1000, mouseStartY = -1000, index = 0,
            attacker, target, damage1, damage2;

    private boolean flag, disabled, multiplayer, playedCardSelected = false,
            cardSelected = false, p2Turn = false;

    private String handCardSelectedName, playedCardSelectedName;

    private ArrayList<Images> handImages, friendlyPlayedImages, enemyPlayedImages;
    private JButton nextTurnButton, back, exit;
    private CardPreview cardPreview;

    private ActionChartPanel actionChartPanel;

    private BufferedImage temp1 = gamePics.get("playbackground");
    private BufferedImage temp2 = gamePics.get("playboard");

    private RequestHandler rh = RequestHandler.getInstance();
    private Responses res = Responses.getInstance();

    private float sizeX = 20;
    private final Font font = new Font("Serif", Font.PLAIN, 20);
    private ArrayList<JButton> actionTargetButton, attackTargetButton;
    private SummonedCardPanel summonedCardPanel;
    private JLabel label;
    private boolean attacked;

    public BoardPanel(boolean multiplayer) {
        this.multiplayer = multiplayer;
        initConfig();
        setLayout(null);
        addMouseMotionListener(new BoardMouseMotionListener(this));
        addMouseListener(new BoardMouseListener(this));
        handImages = new ArrayList<>();
        friendlyPlayedImages = new ArrayList<>();
        enemyPlayedImages = new ArrayList<>();
        actionTargetButton = new ArrayList<>();
        attackTargetButton = new ArrayList<>();
        if (this.multiplayer) {
            toMiddleTimer = new Timer(1000 / 60, new P2MiddleTimerListener(this));
            toHandTimer = new Timer(1000 / 60, new P2HandTimerListener(this));
        } else {
            toMiddleTimer = new Timer(1000 / 60, new MiddleTimerListener(this));
            toHandTimer = new Timer(1000 / 60, new HandTimerListener(this));
        }
        requests = new Timer(300, requestsListener);
        playTimer = new Timer(1000 / 60, new PlayTimerListener(this));
        sleepTimer = new Timer(300, new SleepTimerListener(this));
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
        nextTurnButton.addActionListener(new BoardActionListener(this));
        add(nextTurnButton);

        back = new JButton();
        back.setIcon(gameIcon.get("back"));
        back.setFocusable(false);
        back.addActionListener(new BoardActionListener(this));
        back.setBounds(1430, 890, 60, 60);
        back.setContentAreaFilled(false);
        back.setRolloverEnabled(false);
        back.setBorderPainted(false);
        add(back);

        exit = new JButton();
        exit.addActionListener(new BoardActionListener(this));
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

        requests.start();
//        myTimer = new MyTimer(label);
//        myTimer.start();

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

//        requests();

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

        drawDamage(g);

        playCardAnimation(g);

        drawDeckAnimation(g);

        drawSelectionStatus(g);

    }


    private void requests() {
        RequestHandler.getInstance().sendRequest(new BoardPanelRequest());
        label.setText(res.board.getTime() + "");
        if (res.board.isP2Turn()) {
            disabled = true;
            nextTurnButton.setEnabled(false);
        } else {
            disabled = false;
            nextTurnButton.setEnabled(true);
        }
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
            BufferedImage temp = Constants.cardPics.get(name.toLowerCase());
            String mana = res.board.getDownPowerMana() + "";
            g.drawImage(temp, config.getPlayerHeroPowerX(), config.getPlayerHeroPowerY(), config.getHeroPoerWidth(), config.getHeroPowerHeight(), null);
            g.drawString(mana, config.getPlayerHeroPowerX() + (config.getHeroPoerWidth() / 2) - 5, config.getPlayerHeroPowerY() + 25);
        }
        if (up > 0) {
            String name = res.board.getEnemyHero();
            BufferedImage temp = Constants.cardPics.get(name.toLowerCase());
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
        g.drawImage(cardPics.get(view.getName().toLowerCase()), config.getPlayerPlayedCardX(), config.getPlayerPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2 - 30, null);
        if (view.getType().equals(Type.Minion))
            drawSpecialInfo(g, view);
        if (view.isSleep())
            drawZzz(g, view, config.getPlayerPlayedCardX(), config.getPlayerPlayedCardY());
        friendlyPlayedImages.add(new Images(view.getName(), config.getPlayerPlayedCardX(), config.getPlayerPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2, i, view.isSleep()));
    }

    private void drawZzz(Graphics2D g, CardModelView view, int x, int y) {
        g.setColor(Color.GREEN);
        g.setFont(font.deriveFont(sizeX));
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
        g.drawImage(cardPics.get(view.getName().toLowerCase()), config.getPlayerPlayedCardX(), config.getOpponentPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2 - 30, null);
        synchronized (enemyPlayedImages) {
            enemyPlayedImages.add(new Images(view.getName(), config.getPlayerPlayedCardX(), config.getOpponentPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2, i, view.isSleep()));
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
            g.drawImage(image, X1, Y1, config.getCardWidth(), config.getCardHeight(), null);
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
            button.addMouseListener(new BoardMouseListener(this));
            button.addActionListener(new AttackListener(button, this));
            if (i >= 0) {
                button.setBounds(enemyPlayedImages.get(i).getX(), enemyPlayedImages.get(i).getY(), 75, 75);
            } else if (i == -1) {
                button.setBounds(750, 150, 75, 75);
            }
            add(button);
            attackTargetButton.add(button);
        }
    }


    public void drawFriendlyTargetsForHeroPower() {
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
            button.addMouseListener(new BoardMouseListener(this));
            button.addActionListener(new HeroListener(button, this));
            if (i < friendlyPlayedImages.size()) {
                button.setBounds(friendlyPlayedImages.get(i).getX(), friendlyPlayedImages.get(i).getY(), 75, 75);
            } else {
                button.setBounds(750, 700, 75, 75);
            }
            add(button);
            attackTargetButton.add(button);
        }
    }

    public void drawEnemmyTargetForHeroPower() {
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
            button.addMouseListener(new BoardMouseListener(this));
            button.addActionListener(new HeroListener(button, this));
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
        X1 = config.getDeckX();
        Y1 = config.getDeckY();
        P2X = config.getAiDeckX();
        P2Y = config.getDeckY();
        XA = (config.getMiddleX() - config.getDeckX()) / config.getFps();
        YA = (config.getMiddleY() - config.getDeckY()) / config.getFps();
    }

    private void drawWeapon(Graphics2D g) {
        if (res.board.isDownHasWeapon()) {
            CardModelView view = res.board.getDownWeapon();
            g.drawImage(cardPics.get(view.getName().toLowerCase()), 615, 680, config.getCardWidth() + 50, config.getCardHeight() + 50, null);
            System.out.println(res.board.isHeroCanAttack());
            if (!res.board.isHeroCanAttack()) {
                g.setColor(new Color(57, 57, 57, 230));
                g.fillRect(615, 680, config.getCardWidth() + 50, config.getCardHeight() + 50);
            }
        }
        if (res.board.isUpHasWeapon()) {
            CardModelView view = res.board.getUpWeapon();
            g.drawImage(cardPics.get(view.getName().toLowerCase()), 615, 95, config.getCardWidth() + 50, config.getCardHeight() + 50, null);
        }
        drawWeaponInfo(g);
    }

    private void drawDeckAnimation(Graphics2D g) {
        if (config.isAnimated()) {
            BufferedImage card;
            if (multiplayer && p2Turn) {
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
                if (multiplayer && p2Turn) {
                    System.out.println(P2X + " : " + P2Y);
                    g.drawImage(card, P2X, P2Y, config.getCardWidth() + config.getBlur(), config.getCardHeight() + config.getBlur(), null);
                } else {
                    g.drawImage(card, X1, Y1, config.getCardWidth() + config.getBlur(), config.getCardHeight() + config.getBlur(), null);
                }
                config.setBlur(config.getBlur() + 2);
            } else {
                if (multiplayer && p2Turn) {
                    g.drawImage(card, P2X, P2Y, config.getMaxWidth() + config.getBlur(), config.getMaxHeigth() + config.getBlur(), null);

                } else {
                    g.drawImage(card, X1, Y1, config.getMaxWidth() + config.getBlur(), config.getMaxHeigth() + config.getBlur(), null);
                }
                config.setBlur(config.getBlur() - 5);
            }
        }
        if (!config.isPlayAnimation() && config.isDeckAnimationFinished()) {
            System.out.println("here++++++++++");
            config.setDeckAnimationFinished(true);
            P2X = config.getAiDeckX();
            P2Y = config.getAiDeckY();
            X1 = config.getDeckX();
            Y1 = config.getDeckY();
        }
    }


    public void drawDamages(int i, int j, int attack1, int attack2) {
        new Thread(() -> {
            attacker = i;
            target = j;
            damage1 = attack1;
            damage2 = attack2;
            attacked = true;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            attacked = false;
        }).start();
    }

    private void drawDamage(Graphics2D g) {
        g.setFont(f2.deriveFont(30.f));
        g.setColor(Color.white);
        if (attacked) {
            int x1, y1, x2, y2;
            if (attacker >= 0 && target >= 0) {
                x1 = friendlyPlayedImages.get(attacker).getX();
                y1 = friendlyPlayedImages.get(attacker).getY();
                x2 = enemyPlayedImages.get(target).getX();
                y2 = enemyPlayedImages.get(target).getY();
                g.drawImage(gamePics.get("damage"), x1, y1, null);
                g.drawString(damage2 + "", x1 + 50, y1 + 80);
                g.drawImage(gamePics.get("damage"), x2, y2, null);
                g.drawString(damage1 + "", x2 + 50, y2 + 80);
            } else if (attacker >= 0) {
                x2 = 750;
                y2 = 100;
                g.drawImage(gamePics.get("damage"), x2, y2, null);
                g.drawString(damage1 + "", x2 + 50, y2 + 80);
            } else if (target >= 0) {
                x1 = 750;
                y1 = 700;
                x2 = enemyPlayedImages.get(target).getX();
                y2 = enemyPlayedImages.get(target).getY();
                g.drawImage(gamePics.get("damage"), x1, y1, null);
                g.drawString(damage2 + "", x1 + 50, y1 + 80);
                g.drawImage(gamePics.get("damage"), x2, y2, null);
                g.drawString(damage1 + "", x2 + 50, y2 + 80);
            } else {
                x2 = 750;
                y2 = 100;
                g.drawImage(gamePics.get("damage"), x2, y2, null);
                g.drawString(damage1 + "", x2 + 50, y2 + 80);
            }
        }
    }


    public void selectEnemyTargetForCardActions() {
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
                button.addMouseListener(new BoardMouseListener(this));
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

    public void selectFriendlyTarget() {
        int i = 0;
        for (Images image : friendlyPlayedImages) {
            JButton button = new JButton();
            button.setIcon(gameIcon.get("greentarget"));
            button.setName("1" + i);
            button.setBorderPainted(false);
            button.setRolloverEnabled(false);
            button.setContentAreaFilled(false);
            button.addMouseListener(new BoardMouseListener(this));
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


    public void clear() {
        handImages = new ArrayList<>();
        synchronized (enemyPlayedImages) {
            friendlyPlayedImages = new ArrayList<>();
            enemyPlayedImages = new ArrayList<>();
        }
    }

    public void change() {
        p2Turn = !p2Turn;
//        System.out.println(p2Turn+"****************");
//        if (multiplayer && p2Turn) {
//            toMiddleTimer.removeActionListener(new MiddleTimerListener(this));
//            toMiddleTimer.addActionListener(new P2MiddleTimerListener(this));
//            toHandTimer.removeActionListener(new HandTimerListener(this));
//            toHandTimer.addActionListener(new P2HandTimerListener(this));
//        } else {
//            if (multiplayer) {
//                toMiddleTimer.removeActionListener(new P2MiddleTimerListener(this));
//                toMiddleTimer.addActionListener(new MiddleTimerListener(this));
//                toHandTimer.removeActionListener(new P2HandTimerListener(this));
//                toHandTimer.addActionListener(new HandTimerListener(this));
//            }
//        }
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


    public void removeButton() {
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
//            change();
//            myTimer.aiTurn = false;
            config.setBlur(0);
            toMiddleTimer.start();
            disabled = false;
            nextTurnButton.setEnabled(true);
        }
    }

    private ActionListener requestsListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            requests();
        }
    };

    //
    public BoardConfig getConfig() {
        return config;
    }

    public void setConfig(BoardConfig config) {
        this.config = config;
    }

    public Timer getToMiddleTimer() {
        return toMiddleTimer;
    }

    public Timer getToHandTimer() {
        return toHandTimer;
    }

    public Timer getPlayTimer() {
        return playTimer;
    }

    public Timer getRequests() {
        return requests;
    }

    public void setRequests(Timer requests) {
        this.requests = requests;
    }

    public int getX1() {
        return X1;
    }

    public void setX1(int x1) {
        X1 = x1;
    }

    public int getY1() {
        return Y1;
    }

    public void setY1(int y1) {
        Y1 = y1;
    }

    public int getP2X() {
        return P2X;
    }

    public void setP2X(int p2X) {
        P2X = p2X;
    }

    public int getP2Y() {
        return P2Y;
    }

    public void setP2Y(int p2Y) {
        P2Y = p2Y;
    }

    public int getXA() {
        return XA;
    }

    public int getYA() {
        return YA;
    }

    public int getPlayedIndex() {
        return playedIndex;
    }

    public void setPlayedIndex(int playedIndex) {
        this.playedIndex = playedIndex;
    }

    public int getSelectedTargetIndex() {
        return selectedTargetIndex;
    }

    public void setSelectedTargetIndex(int selectedTargetIndex) {
        this.selectedTargetIndex = selectedTargetIndex;
    }

    public int getDeckIndex() {
        return deckIndex;
    }

    public void setDeckIndex(int deckIndex) {
        this.deckIndex = deckIndex;
    }

    public int getMouseDesX() {
        return mouseDesX;
    }

    public void setMouseDesX(int mouseDesX) {
        this.mouseDesX = mouseDesX;
    }

    public int getMouseDesY() {
        return mouseDesY;
    }

    public void setMouseDesY(int mouseDesY) {
        this.mouseDesY = mouseDesY;
    }

    public int getMouseStartX() {
        return mouseStartX;
    }

    public void setMouseStartX(int mouseStartX) {
        this.mouseStartX = mouseStartX;
    }

    public int getMouseStartY() {
        return mouseStartY;
    }

    public void setMouseStartY(int mouseStartY) {
        this.mouseStartY = mouseStartY;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public void setPlayedCardSelected(boolean playedCardSelected) {
        this.playedCardSelected = playedCardSelected;
    }

    public boolean isCardSelected() {
        return cardSelected;
    }

    public void setCardSelected(boolean cardSelected) {
        this.cardSelected = cardSelected;
    }

    public boolean isP2Turn() {
        return p2Turn;
    }

    public String getHandCardSelectedName() {
        return handCardSelectedName;
    }

    public void setHandCardSelectedName(String handCardSelectedName) {
        this.handCardSelectedName = handCardSelectedName;
    }

    public void setPlayedCardSelectedName(String playedCardSelectedName) {
        this.playedCardSelectedName = playedCardSelectedName;
    }

    public ArrayList<Images> getHandImages() {
        return handImages;
    }

    public ArrayList<Images> getFriendlyPlayedImages() {
        return friendlyPlayedImages;
    }

    public ArrayList<Images> getEnemyPlayedImages() {
        return enemyPlayedImages;
    }

    public JButton getNextTurnButton() {
        return nextTurnButton;
    }

    public JButton getBack() {
        return back;
    }

    public JButton getExit() {
        return exit;
    }

    public void setExit(JButton exit) {
        this.exit = exit;
    }

    public CardPreview getCardPreview() {
        return cardPreview;
    }

    public ActionChartPanel getActionChartPanel() {
        return actionChartPanel;
    }

    public Responses getRes() {
        return res;
    }

    public void setRes(Responses res) {
        this.res = res;
    }

    public float getSizeX() {
        return sizeX;
    }

    public void setSizeX(float sizeX) {
        this.sizeX = sizeX;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void forceEndTurn() {
        nextTurnButton.doClick();
    }
}


