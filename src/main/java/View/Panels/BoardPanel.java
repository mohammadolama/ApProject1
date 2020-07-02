package View.Panels;

import Controller.Admin;
import Model.Enums.Attribute;
import Model.Enums.Type;
import Model.CardModelView;
import Model.Images;
import Controller.RequestHandler;
import View.Configs.ConfigsLoader;
import View.Configs.BoardConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import static View.Panels.Constants.*;

public class BoardPanel extends JPanel implements MouseMotionListener, MouseListener, ActionListener {


    private int mouseStartX = -1000;
    private int mouseStartY = -1000;
    private int mouseDesX;
    private int mouseDesY;
    private boolean cardSelected = false;
    private boolean playedCardSelected = false;
    private String handCardSelectedName;
    private String playedCardSelectedName;
    private int playedIndex;
    private BoardConfig config;
    private JButton nextTurnButton;
    private ArrayList<Images> handImages;
    private ArrayList<Images> friendlyPlayedImages;
    private ArrayList<Images> enemyPlayedImages;

    private JButton back;
    private JButton exit;
    private CardPreview cardPreview;

    private int selectedTargetIndex;


    private Timer toMiddleTimer;
    private Timer toHandTimer;
    private Timer playTimer;
    private Timer sleepTimer;
    private int X;
    private int Y;
    private int XA;
    private int YA;

    private Boolean ourTurn = true;
    private int deckIndex;

    private int index = 0;
    private float size = 20;
    private Font font = new Font("Serif", Font.PLAIN, 20);
    private ArrayList<JButton> buttons;

    private boolean disabled;

    private void initConfig() {
        config = ConfigsLoader.getInstance().getBoardConfig();
        X = config.getDeckX();
        Y = config.getDeckY();
        XA = (config.getMiddleX() - config.getDeckX()) / config.getFps();
        YA = (config.getMiddleY() - config.getDeckY()) / config.getFps();
    }


    public BoardPanel() {
        initConfig();
        setLayout(null);
        addMouseMotionListener(this);
        addMouseListener(this);
//        admin = Admin.getInstance();
        handImages = new ArrayList<>();
        friendlyPlayedImages = new ArrayList<>();
        enemyPlayedImages = new ArrayList<>();
        buttons = new ArrayList<>();



        toMiddleTimer = new Timer(1000 / 60, middleTimerListener);
        toHandTimer = new Timer(1000 / 60, handTimerListener);
        playTimer = new Timer(1000 / 60, playTimerListener);
        sleepTimer = new Timer(300, sleepTimerListener);
        sleepTimer.start();

        cardPreview = CardPreview.getInstance();
        cardPreview.setBounds(0, 0, 600, 600);
        cardPreview.setLayout(null);
        cardPreview.setVisible(false);
        cardPreview.setOpaque(false);
        add(cardPreview);

//        friendlyHero = new JLabel();
//        friendlyHero.setBounds(710, 615, 175, 279);
//        String name = RequestHandler.SendRequest.FriendlyHeroName.response(null);
//        friendlyHero.setIcon(heroPortraits.get(name.toLowerCase()));
//        friendlyHero.setOpaque(false);
//        add(friendlyHero);


//        enemyHero = new JLabel();
//        enemyHero.setBounds(710, 35, 175, 279);
//        name = RequestHandler.SendRequest.EnemyHeroName.response(null);
//        enemyHero.setIcon(heroPortraits.get(name.toLowerCase()));
////        enemyHero.setIgnoreRepaint(true);
//        enemyHero.addMouseListener(enemyMouseListener);
//        add(enemyHero);


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

    }


    @Override
    protected void paintComponent(Graphics gx) {
        long time = System.nanoTime();
        Graphics2D g = (Graphics2D) gx;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.clearRect(0, 0, 1600, 1000);
        BufferedImage temp1 = gamePics.get("playbackground");
        BufferedImage temp2 = gamePics.get("playboard");
        g.drawImage(temp1, 0, 0, 1600, 1000, null);
        g.drawImage(temp2, config.getLeftLineX(), config.getUpLineY(), config.getRightLineX() - config.getLeftLineX(), config.getBottomLineY() - config.getUpLineY(), null);

        g.setFont(fantasy.deriveFont(30.f));
        g.setColor(Color.yellow);

        drawUserInfo(g);

        drawLog(g);

        drawHeroPortraits(g);

        drawHeroPower(g);

        drawHeroInfo(g);

        drawWeapon(g);

        drawMana(g);

        cardnumber(g);

        handCards(g);

        playedCard(g);

        playCardAnimation(g);

        drawDeckAnimation(g);

        drawSelectionStatus(g);
//        System.out.println((System.nanoTime()-time)/1000);
    }

    private void drawHeroPortraits(Graphics2D g) {
        String name = RequestHandler.SendRequest.EnemyHeroName.response(null);
        g.drawImage(heroPortraits.get(name.toLowerCase()), 710, 80, 175, 255, null);
        name = RequestHandler.SendRequest.FriendlyHeroName.response(null);
        g.drawImage(heroPortraits.get(name.toLowerCase()), 710, 660, 175, 255, null);

    }


    private void drawUserInfo(Graphics2D g) {
        String down = RequestHandler.SendRequest.FriendlyName.response(null);
        String up = RequestHandler.SendRequest.EnemyName.response(null);
        g.drawString(down, 30, 920);
        g.drawString(up, 30, 50);
    }

    private void playCardAnimation(Graphics2D g) {
        if (config.isPlayAnimation()) {
            BufferedImage image = cardPics.get(handCardSelectedName.toLowerCase());
            g.drawImage(image, X, Y, config.getCardWidth(), config.getCardHeight(), null);
        }
    }

    private void drawDeckAnimation(Graphics2D g) {
        if (config.isAnimated()) {
            BufferedImage card = RequestHandler.SendRequest.DeckAnimationPicture.response(null);
            if (config.isToMiddle()) {
                Color color = g.getColor();
                g.setColor(new Color(222, 222, 222, config.getBlur()));
                g.fillRect(0, 0, 1600, 1000);
                g.setColor(color);
                config.setMaxWidth(config.getCardWidth() + config.getBlur());
                config.setMaxHeigth(config.getCardHeight() + config.getBlur());
                g.drawImage(card, X, Y, config.getCardWidth() + config.getBlur(), config.getCardHeight() + config.getBlur(), null);
                config.setBlur(config.getBlur() + 2);
            } else {
                g.drawImage(card, X, Y, config.getMaxWidth() + config.getBlur(), config.getMaxHeigth() + config.getBlur(), null);
                config.setBlur(config.getBlur() - 5);
            }
        }
        if (!config.isPlayAnimation() && config.isDeckAnimationFinished()) {
            config.setDeckAnimationFinished(true);
            X = config.getDeckX();
            Y = config.getDeckY();
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


    private void drawHeroPower(Graphics2D g) {
        g.setFont(fantasy.deriveFont(24.0f));
        int down = RequestHandler.SendRequest.DownPowerUsage.response(null);
        int up = RequestHandler.SendRequest.UpPowerUsage.response(null);
        if (down > 0) {
            String name = RequestHandler.SendRequest.FriendlyHeroName.response(null);
            BufferedImage temp = powerPics.get(name);
            String mana = RequestHandler.SendRequest.DownPowerMana.response(null);
            g.drawImage(temp, config.getPlayerHeroPowerX(), config.getPlayerHeroPowerY(), config.getHeroPoerWidth(), config.getHeroPowerHeight(), null);
            g.drawString(mana, config.getPlayerHeroPowerX() + (config.getHeroPoerWidth() / 2) - 5, config.getPlayerHeroPowerY() + 25);
        }
        if (up > 0) {
            String name = RequestHandler.SendRequest.EnemyHeroName.response(null);
            BufferedImage temp = powerPics.get(name);
            String mana = RequestHandler.SendRequest.UpPowerMana.response(null);
            g.drawImage(temp, config.getPlayerHeroPowerX(), config.getOpponentHeroPowerY(), config.getHeroPoerWidth(), config.getHeroPowerHeight(), null);
            g.drawString(mana, config.getPlayerHeroPowerX() + (config.getHeroPoerWidth() / 2) - 5, (config.getOpponentHeroPowerY()) + 25);
        }
    }


    private void drawLog(Graphics2D g) {
        g.setColor(new Color(0, 182, 222, 100));
        g.fillRoundRect(10, 80, config.getInfoWidth(), 790, 50, 50);
        g.setColor(Color.white);
        g.setFont(fantasy.deriveFont(20.0f));
        int size = RequestHandler.SendRequest.GameLogSize.response(null);
        if (size > 0) {
            ArrayList<String> log = RequestHandler.SendRequest.GameLog.response(null);
            for (String s : log) {
                g.drawString(s, config.getLogX(), config.getLogY());
                config.setLogY(config.getLogY() + config.getLogSpace());
            }
        }
        config.setLogY(120);
    }

    private void cardnumber(Graphics2D g) {
        g.setFont(fantasy.deriveFont(50.0f));
        String down = RequestHandler.SendRequest.DownDeckSize.response(null);
        String up = RequestHandler.SendRequest.UpDeckSize.response(null);
        g.drawString(down, 1430, 620);
        g.drawString(up, 1430, 320);
    }

    private void drawWeapon(Graphics2D g) {
        if (RequestHandler.SendRequest.DownHasWeapon.response(0, null)) {
            CardModelView view = RequestHandler.SendRequest.DownWeapon.response(null);
            g.drawImage(view.getImage(), 615, 680, config.getCardWidth() + 50, config.getCardHeight() + 50, null);
        }
        if (RequestHandler.SendRequest.UpHasWeapon.response(0, null)) {
            CardModelView view = RequestHandler.SendRequest.UpWeapon.response(null);
            g.drawImage(view.getImage(), 615, 95, config.getCardWidth() + 50, config.getCardHeight() + 50, null);
        }
        drawWeaponInfo(g);
    }


    private void drawWeaponInfo(Graphics2D g) {
        g.setFont(fantasy.deriveFont(28.0f));
        g.setColor(Color.white);
        if (RequestHandler.SendRequest.DownHasWeapon.response(0, null)) {
            CardModelView view = RequestHandler.SendRequest.DownWeapon.response(null);
            g.drawString(view.getManaCost() + "", 625, 700);
            g.drawString(view.getDamage() + "", 625, 805);
            g.drawString(view.getHp() + "", 695, 805);
        }
        if (RequestHandler.SendRequest.UpHasWeapon.response(0, null)) {
            CardModelView view = RequestHandler.SendRequest.UpWeapon.response(null);
            g.drawString(view.getManaCost() + "", 625, 115);
            g.drawString(view.getDamage() + "", 625, 220);
            g.drawString(view.getHp() + "", 695, 220);
        }
    }


    private void drawMana(Graphics2D g) {
        g.setFont(f2.deriveFont(30.0f));
        int notUsed = RequestHandler.SendRequest.FriendlyNotUsedMana.response(null);
        int total = RequestHandler.SendRequest.FriendlyTotalMana.response(null);
        g.drawString(notUsed + "/" + total, 1035, 935);
        int x = 1110;
        BufferedImage mana = gamePics.get("mana");
        for (int i = 0; i < notUsed; i++) {
            g.drawImage(mana, x, 917, 30, 30, null);
            x += 26;
        }
    }


    private void handCards(Graphics2D g) {
        g.setFont(f2.deriveFont(20.0f));
        handImages.clear();
        int downSize = RequestHandler.SendRequest.FrinedlyHandSize.response(null);
        int upSize = RequestHandler.SendRequest.EnemyHandSize.response(null);
        if (downSize > 0) {
            int i = 0;
            if (toHandTimer.isRunning() || toMiddleTimer.isRunning()) {
                downSize--;
            }
            while (i < downSize) {
                String name = RequestHandler.SendRequest.FriendlyHandCradName.response(null, i);
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

    private void playedCard(Graphics2D g) {
        friendlyPlayedImages = new ArrayList<>();
        int downSize = RequestHandler.SendRequest.FriendlyPlayedSize.response(null);
        int upSize = RequestHandler.SendRequest.EnemyPlayedSize.response(null);
        if (downSize > 0) {
            int i = 0;
            int Spacing = 5;
            if (cardSelected) {
                config.setPlayerPlayedCardX(config.getPlayerPlayedCardX() - 30);
                Spacing = 30;
            }
            while (i < downSize) {
                drawPlayedCard(g, i, config.getPlayerPlayedCardX(), config.getPlayerPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2);
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

    private void addButtons(String name, int x, int y) {

    }

    void removeButton() {
        for (JButton button : buttons) {
            remove(button);
        }
        buttons.clear();
    }

    ActionListener playTimerListener = new ActionListener() {
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
                System.out.println("timer stop");
                RequestHandler.SendRequest.PlayCard.response(handCardSelectedName, deckIndex, selectedTargetIndex);
                disabled = false;
                selectedTargetIndex = -1;
                playTimer.stop();
            }
        }
    };

    private void drawPlayedCard(Graphics2D g, int i, int x, int y, int width, int height) {
        CardModelView view = RequestHandler.SendRequest.ModelView.response("friendly", i);
        g.drawImage(view.getImage(), config.getPlayerPlayedCardX(), config.getPlayerPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2 - 30, null);
        if (view.getType().equals(Type.Minion))
            drawSpecialInfo(g, view);
        if (view.getSleep())
            drawZzz(g, view, config.getPlayerPlayedCardX(), config.getPlayerPlayedCardY());
        friendlyPlayedImages.add(new Images(view.getName(), config.getPlayerPlayedCardX(), config.getPlayerPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2, i, view.getSleep()));
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
        CardModelView view = RequestHandler.SendRequest.ModelView.response("enemy", i);
        String name = RequestHandler.SendRequest.EnemyPlayedCardName.response(null, i);
        g.drawImage(view.getImage(), config.getPlayerPlayedCardX(), config.getOpponentPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2 - 30, null);
        synchronized (enemyPlayedImages) {
            enemyPlayedImages.add(new Images(view.getName(), config.getPlayerPlayedCardX(), config.getOpponentPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2, i, view.getSleep()));
        }
        drawEnemySpecialInfo(g, view);
        if (playedCardSelected) {
            if (view.isCanBeAttacked()) {
                g.drawImage(gamePics.get("target"), (config.getPlayerPlayedCardX() + config.getCardWidth()), (config.getOpponentPlayedCardY() + config.getCardHeight()), null);
                addButtons(view.getName(), (config.getPlayerPlayedCardX() + config.getCardWidth() / 2), (config.getOpponentPlayedCardY() + config.getCardHeight() / 2));
            }
        }
    }

    private void drawZzz(Graphics2D g, CardModelView view, int x, int y) {
        g.setColor(Color.GREEN);
        g.setFont(font.deriveFont(size));
//        y=y+config.getCardHeight();
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

    private void drawHandCard(Graphics2D g, int i, int x, int y, int width, int height) {
        BufferedImage image = RequestHandler.SendRequest.FriendlyHandCard.response(null, i);
        g.drawImage(image, config.getPlayerHandX(), config.getPlayerHandY(), config.getCardWidth(), config.getCardHeight(), null);
    }

    private void drawEnemyCard(Graphics2D g, int x, int y, int width, int height) {
        BufferedImage image = gamePics.get("enemycard");
        g.drawImage(image, x, y, width, height, null);
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

    private void drawHeroInfo(Graphics2D g) {
        g.setFont(fantasy.deriveFont(28.0f));
        g.setColor(Color.white);
        BufferedImage blood = gamePics.get("blood");
        BufferedImage defence = gamePics.get("defence");
        g.drawImage(blood, 860, 800, 70, 70, null);
        g.drawImage(defence, 670, 815, 45, 45, null);
        g.drawImage(blood, 860, 210, 70, 70, null);
        g.drawImage(defence, 670, 225, 45, 45, null);
        String downHp = RequestHandler.SendRequest.DownHp.response(null);
        String upHp = RequestHandler.SendRequest.UpHp.response(null);
        String downDefence = RequestHandler.SendRequest.DownDefence.response(null);
        String upDefence = RequestHandler.SendRequest.UpDefence.response(null);
        g.drawString(downHp, 882, 855);
        g.drawString(upHp, 882, 265);
        g.drawString(downDefence, 685, 845);
        g.drawString(upDefence, 685, 255);
    }

    private void friendlyPlayedView(int x, int y) {
        int i = -1;
        for (Images images : friendlyPlayedImages) {
            if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                i = images.getIndex();
                break;
            }
        }
        if (i == (-1)) {
            return;
        }
        CardModelView view = RequestHandler.SendRequest.ModelView.response("friendly", i);
        cardPreview.setCardModelView(view);
        cardPreview.setBounds(x - 50, y - 360, 260, 370);
        cardPreview.setVisible(true);
    }

    private void enemyPlayedView(int x, int y) {
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
        CardModelView view = RequestHandler.SendRequest.ModelView.response("enemy", i);
        cardPreview.setCardModelView(view);
        cardPreview.setBounds(x - 50, y, 260, 370);
        cardPreview.setVisible(true);
    }

    private void friendlyHandView(int x, int y) {
        String st = null;
        for (Images images : handImages) {
            if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                st = images.getName();
                break;
            }
        }
        if (st == null || st.equals("")) {
            return;
        }
        CardModelView model = RequestHandler.SendRequest.PureModelView.response(st.toLowerCase());
        cardPreview.setCardModelView(model);
        cardPreview.setBounds(x - 50, y - 360, 260, 370);
        cardPreview.setVisible(true);

    }

    void clear() {
        handImages = new ArrayList<>();
        friendlyPlayedImages = new ArrayList<>();
        synchronized (enemyPlayedImages) {
            enemyPlayedImages = new ArrayList<>();
        }
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
            Boolean flag = RequestHandler.SendRequest.CanBePlayed.response(0, handCardSelectedName);
            CardModelView view = RequestHandler.SendRequest.PureModelView.response(handCardSelectedName);
            boolean s = false;
            if (view.isNeedEnemyTarget()) {
                selectEnemyTarget();
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

    private void selectFriendlyTarget() {
        int i = 0;
        for (Images image : friendlyPlayedImages) {
            JButton button = new JButton();
            button.setIcon(gameIcon.get("greentarget"));
            button.setName("1" + i);
            button.setBorderPainted(false);
            button.setRolloverEnabled(false);
            button.setContentAreaFilled(false);
            button.setBounds(image.getX() + 50, image.getY(), 75, 75);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(button.getName() + " Has Clicked.");
                    selectedTargetIndex = Integer.parseInt(button.getName());
                    synchronized (config) {
                        config.notify();
                    }
                    removeButton();
                }
            });
            buttons.add(button);
            add(button);
            i++;
        }
    }

    private void selectCard(int x, int y) {
        String st = null;
        for (Images images : handImages) {
            if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                st = images.getName();
                break;
            }
        }
        if (st == null || st.equals("")) {
            return;
        }
        Boolean flag = RequestHandler.SendRequest.CheckMana.response(0, st);
        if (flag) {
            handCardSelectedName = st;
            cardSelected = true;
        }
        revalidate();
        repaint();
    }

    private void selectPlayedCard(int x, int y) {
        String st = null;
        for (Images images : handImages) {
            if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                st = images.getName();
                break;
            }
        }
        if (st == null || st.equals("")) {
            return;
        }
        Boolean flag = RequestHandler.SendRequest.CheckMana.response(0, st);
        if (flag) {
            handCardSelectedName = st;
            cardSelected = true;
        }
        revalidate();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (disabled) {
            return;
        }
        cardPreview.setVisible(false);
        int x = e.getX();
        int y = e.getY();
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
            if (x >= config.getPlayerHeroPowerX() && x <= (config.getPlayerHeroPowerX() + config.getHeroPoerWidth()) && y >= config.getPlayerHeroPowerY() && y <= (config.getPlayerHeroPowerY() + config.getHeroPowerHeight())) {
                RequestHandler.SendRequest.PlayHeroPower.response(null);
            }
            if (x >= config.getLeftLineX() && x <= config.getRightLineX() && y >= config.getPlayerHandY()) {
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
                return;
            } else {
                for (Images image : friendlyPlayedImages) {
                    if (x >= image.getX() && x <= image.getX() + image.getWidth() && y >= image.getY() && y <= image.getY() + image.getHeigth()) {
                        boolean flag = RequestHandler.SendRequest.CanDoAction.response(image.getIndex(), null);
                        if (flag) {
                            playedCardSelected = true;
                            playedCardSelectedName = image.getName();
                            playedIndex = image.getIndex();
                            return;
                        }
                    }
                }
                playedCardSelected = false;
            }

        } else if (y < config.getMiddleLineY() && y >= 200) {
            if (playedCardSelected) {
                for (Images image : enemyPlayedImages) {
                    if (x >= image.getX() && x <= image.getX() + image.getWidth() && y >= image.getY() && y <= image.getY() + image.getHeigth()) {
                        Admin.getInstance().Attack(playedIndex,image.getIndex());
                        RequestHandler.SendRequest.SetSleep.response(null, playedIndex);
                        playedCardSelected = false;
                        playedCardSelectedName = null;
                        playedIndex = -1;
                        break;
                    }
                }
            }
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
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src == exit) {
            RequestHandler.SendRequest.Log.response("Click_Button : Exit Button");
            RequestHandler.SendRequest.Exit.response(null);
        } else if (src == back) {
            RequestHandler.SendRequest.Log.response("Click_Button : Back Button");
            RequestHandler.SendRequest.VisiblePanel.response("menu");
        } else if (src == nextTurnButton) {
            RequestHandler.SendRequest.Log.response("Click_Button : NextTurn Button");
            ourTurn = !ourTurn;
//            if (ourTurn) {
//                enemyHero.setBounds(710, 35, 175, 279);
//                friendlyHero.setBounds(710, 615, 175, 279);
//            } else {
//                enemyHero.setBounds(710, 615, 175, 279);
//                friendlyHero.setBounds(710, 35, 175, 279);
//            }
            clear();
            Boolean flag = RequestHandler.SendRequest.DeckHasNext.response(0, null);
            RequestHandler.SendRequest.EndTurn.response(null);
            if (flag) {
                config.setToMiddle(true);
                config.setBlur(0);
                config.setAnimated(true);
                toMiddleTimer.start();
            }
            playedCardSelected = false;
            removeButton();
            revalidate();
            repaint();
        }
    }


    ActionListener middleTimerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            disabled = true;
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
        }
    };

    ActionListener handTimerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
            int desX = 0;
            if (handImages.size() > 0)
                desX = (700 - (handImages.get(handImages.size() - 1).getX() + handImages.get(handImages.size() - 1).getWidth()) + 15) / 30;
            Y += 20;
            X -= desX;
            if (Y > 950) {
                config.setDeckAnimationFinished(true);
                config.setAnimated(false);
                disabled = false;
                toHandTimer.stop();
            }
        }
    };

    private void selectEnemyTarget() {
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
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(button.getName() + " Has Clicked.");
                        selectedTargetIndex = Integer.parseInt(button.getName());
                        synchronized (config) {
                            config.notify();
                        }
                        removeButton();
                    }
                });
                buttons.add(button);
                add(button);
                i++;
            }
        }
    }

    ActionListener sleepTimerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            size += 10;
            if (size >= 45) {
                size = 20;
            }
            index++;
            if (index == 3) {
                index = 0;
            }
            repaint();
        }
    };

    MouseListener enemyMouseListener = new MouseAdapter() {
        int i = 0;

        @Override
        public void mouseClicked(MouseEvent e) {
            i++;
        }
    };
}