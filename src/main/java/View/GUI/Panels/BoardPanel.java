package View.GUI.Panels;

import View.GUI.Configs.ConfigsLoader;
import Util.Admin;
import View.GUI.Configs.BoardConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import static View.GUI.Panels.Constants.*;

public class BoardPanel extends JPanel implements MouseMotionListener, MouseListener, ActionListener {


    private int mouseStartX = -1000;
    private int mouseStartY = -1000;
    private int mouseDesX;
    private int mouseDesY;
    private boolean cardSelected = false;


    private String name;

    private BoardConfig config;
    private JButton nextTurnButton;
    private ArrayList<Images> handImages;
    private ArrayList<Images> playedImages;
    private Admin admin;

    private JButton back;
    private JButton exit;
    private CardPreview cardPreview;


    private Timer toMiddleTimer;
    private Timer toHandTimer;
    private Timer playTimer;

    private int X;
    private int Y;
    private int XA;
    private int YA;

    private JLabel friendlyHero;
    private JLabel enemyHero;
    private Boolean ourTurn = true;
    private int deckIndex;

    JButton button;
    private boolean flag;

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
        admin = Admin.getInstance();
        handImages = new ArrayList<>();
        playedImages = new ArrayList<>();

        toMiddleTimer = new Timer(1000 / 60, this);
        toHandTimer = new Timer(1000 / 60, this);
        playTimer = new Timer(1000 / 60, this);

        cardPreview = CardPreview.getInstance();
        cardPreview.setBounds(0, 0, 600, 600);
        cardPreview.setLayout(null);
        cardPreview.setVisible(false);
        cardPreview.setOpaque(false);
        add(cardPreview);

        friendlyHero=new JLabel();
        friendlyHero.setBounds(710, 615, 175, 279);
        friendlyHero.setIcon(admin.gifOf(admin.playerHeroName()));
        friendlyHero.setOpaque(false);
        add(friendlyHero);


        enemyHero=new JLabel();
        enemyHero.setBounds(710, 35, 175, 279);
        enemyHero.setIcon(admin.gifOf(admin.enemyPlayer().getSelectedDeck().getHero().getName().toLowerCase()));
        enemyHero.setIgnoreRepaint(true);
//        enemy.set
//        enemy.setOpaque(false);
        add(enemyHero);

        button = new JButton("Att");
        button.setBounds(780, 150, 50, 50);
        button.setText("Att");
//        button.setVisible(false);
//        button.addActionListener();
        add(button);

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
        Graphics2D g = (Graphics2D) gx;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.clearRect(0, 0, 1600, 1000);
        g.drawImage(gamePics.get("playBackground"), 0, 0, 1600, 1000, null);
        g.drawImage(gamePics.get("playBoard"), config.getLeftLineX(), config.getUpLineY(), config.getRightLineX() - config.getLeftLineX(), config.getBottomLineY() - config.getUpLineY(), null);

        g.setFont(fantasy.deriveFont(30.f));
        g.setColor(Color.yellow);

        drawUserInfo(g);

        drawLog(g);

        drawHeroPower(g);

        drawHeroInfo(g);

        drawWeapon(g);

        drawMana(g);

        cardnumber(g);

        handCards(g);

        playedCard(g);

        drawCardSelectedArea(g);
        playCardAnimation(g);

        drawDeckAnimation(g);

//        dragedCard(g);

        drawSelectionStatus(g);


    }

    private void drawUserInfo(Graphics2D g) {
        g.drawString(admin.friendlyPlayer().getUsername(), 30, 920);
        g.drawString(admin.enemyPlayer().getUsername(), 30, 50);
    }

    private void playCardAnimation(Graphics2D g) {
        if (config.isPlayAnimation()) {
//            System.out.println("play card : " +name);
            BufferedImage image = admin.pictureOf(name.toLowerCase());
            g.drawImage(image, X, Y, config.getCardWidth(), config.getCardHeight(), null);
        }
    }

    private void drawDeckAnimation(Graphics2D g) {
        if (config.isAnimated()) {
            BufferedImage card = cardPics.get(admin.friendlyHandCards().get(admin.friendlyHandCards().size() - 1).getName().toLowerCase());
            if (config.isToMiddle()) {
                Color color = g.getColor();
                g.setColor(new Color(222, 222, 222, 0 + config.getBlur()));
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
        g.setColor(Color.white);
        g.fillRoundRect(1400, 800, 150, 50, 20, 20);
        g.setColor(Color.black);
        g.drawString("Choosed : ", 1410, 833);
        g.setColor(Color.green);
        if (cardSelected) {
            g.fillOval(1500, 810, 30, 30);
        } else {
            g.drawOval(1500, 810, 30, 30);
        }
    }

    private void drawCardSelectedArea(Graphics2D g) {
//        if (cardSelected) {
//            int width = config.getRightLineX() - config.getLeftLineX();
//            int height = config.getPlayAreaY() - config.getMiddleLineY();
//            g.setColor(Color.RED);
//            Stroke s = g.getStroke();
//            g.setStroke(new BasicStroke(10));
//            g.drawRect(config.getLeftLineX(), config.getMiddleLineY(), width, height);
//            g.setStroke(s);
//        }
    }


    private void drawHeroPower(Graphics2D g) {
        g.setFont(fantasy.deriveFont(24.0f));
        if (admin.friendlyHeroPowerusedTimes() > 0) {
            g.drawImage(admin.powerPicuterOf(admin.friendlyPlayer().getSelectedDeck().getHero().getName()), config.getPlayerHeroPowerX(), config.getPlayerHeroPowerY(), config.getHeroPoerWidth(), config.getHeroPowerHeight(), null);
            g.drawString(admin.friendlyPowerMana() + "", config.getPlayerHeroPowerX() + (config.getHeroPoerWidth() / 2) - 5, config.getPlayerHeroPowerY() + 25);
        }
        if (admin.enemyHeroPowerusedTimes() > 0){
            g.drawImage(admin.powerPicuterOf(admin.enemyPlayer().getSelectedDeck().getHero().getName()), config.getPlayerHeroPowerX(), config.getOpponentHeroPowerY(), config.getHeroPoerWidth(), config.getHeroPowerHeight(), null);
            g.drawString(admin.enemyPowerMana()+"" , config.getPlayerHeroPowerX() + (config.getHeroPoerWidth()/2) -5 , ( config.getOpponentHeroPowerY()) +25);
        }
    }


    private void drawLog(Graphics2D g) {
        g.setColor(new Color(0, 182, 222, 100));
        g.fillRoundRect(10, 80, config.getInfoWidth(), 790, 50, 50);
        g.setColor(Color.white);
        g.setFont(fantasy.deriveFont(20.0f));
        if (admin.gameLog().size() > 0) {
            for (String s : admin.gameLog()) {
                g.drawString(s, config.getLogX(), config.getLogY());
                config.setLogY(config.getLogY() + config.getLogSpace());
            }
        }
        config.setLogY(120);
    }

    private void cardnumber(Graphics2D g) {
        g.setFont(fantasy.deriveFont(50.0f));
        g.drawString(admin.friendlyDeckCards().size() + "", 1430, 620);
        g.drawString(admin.enemyDeckCards().size() + "", 1430, 320);
    }

    private void drawWeapon(Graphics2D g) {
        if (admin.friendlyWeapon() != null) {
            g.drawImage(admin.pictureOf(admin.friendlyWeapon().getName().toLowerCase()), 615, 680, config.getCardWidth() + 50, config.getCardHeight() + 50, null);
        }
        if (admin.enemyWeapon() !=null){
            g.drawImage(admin.pictureOf(admin.enemyWeapon().getName().toLowerCase()) , 615 , 95 , config.getCardWidth() + 50, config.getCardHeight() + 50, null);
        }
    }

    private void drawMana(Graphics2D g) {
        g.setFont(f2.deriveFont(30.0f));
        g.drawString(admin.notUsedmana() + "/" + admin.friendlyTotalMana(), 1035, 935);
        int x = 1110;
        for (int i = 0; i < admin.notUsedmana(); i++) {
            g.drawImage(gamePics.get("mana"), x, 917, 30, 30, null);
            x += 26;
        }
    }



    private void handCards(Graphics2D g) {
        g.setFont(f2.deriveFont(20.0f));
        handImages.clear();
        if (admin.friendlyHandCards().size() > 0) {
            int i = 0;
            int j1 = admin.friendlyHandCards().size();
            if (toHandTimer.isRunning() || toMiddleTimer.isRunning()) {
                j1--;
            }
            while (i < j1) {
                handImages.add(new Images(admin.friendlyHandCards().get(i).getName(), config.getPlayerHandX(), config.getPlayerHandY(), config.getCardWidth(), config.getCardHeight()));
                drawHandCard(g, i, config.getPlayerHandX(), config.getPlayerHandY(), config.getCardWidth(), config.getCardHeight());
                config.setPlayerHandX(config.getPlayerHandX() + config.getCardWidth() - 15);
                i++;
            }
            config.setPlayerHandX(570);
            i=0;
            int j2=admin.enemyHandCards().size();
            while (i<j2){
                drawEnemyCard(g, config.getPlayerHandX(), config.getOpponentHandY(), config.getCardWidth(), config.getCardHeight());
                config.setPlayerHandX(config.getPlayerHandX() + config.getCardWidth() - 15);
                i++;
            }
            config.setPlayerHandX(570);
        }
    }

    private void playedCard(Graphics2D g) {
        playedImages = new ArrayList<>();
        if (admin.friendlyPlayedCards().size() > 0) {
            int i = 0;
            int Spacing = 5;
            if (cardSelected) {
                config.setPlayerPlayedCardX(config.getPlayerPlayedCardX() - 30);
                Spacing = 30;
            }
            while (i < admin.friendlyPlayedCards().size()) {
                drawPlayedCard(g, i, config.getPlayerPlayedCardX(), config.getPlayerPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2);
                config.setPlayerPlayedCardX(config.getPlayerPlayedCardX() + config.getCardWidth() * 2 + Spacing);
                i++;
            }
            config.setPlayerPlayedCardX(430);
        }


    }

    private void drawPlayedCard(Graphics2D g, int i, int x, int y, int width, int height) {
        g.drawImage(cardPics.get(admin.friendlyPlayedCards().get(i).getName().toLowerCase()), config.getPlayerPlayedCardX(), config.getPlayerPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2 - 30, null);
        playedImages.add(new Images(admin.friendlyPlayedCards().get(i).getName(), config.getPlayerPlayedCardX(), config.getPlayerPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2));

    }

    private void drawHandCard(Graphics2D g, int i, int x, int y, int width, int height) {
        g.drawImage(cardPics.get(admin.friendlyHandCards().get(i).getName().toLowerCase()), config.getPlayerHandX(), config.getPlayerHandY(), config.getCardWidth(), config.getCardHeight(), null);
    }

    private void drawEnemyCard(Graphics2D g, int x, int y, int width, int height) {
        g.drawImage(gamePics.get("enemycard"), x, y, width, height, null);
    }


    private void drawHeroInfo(Graphics2D g) {
        g.setFont(fantasy.deriveFont(28.0f));
        g.setColor(Color.white);
        g.drawImage(gamePics.get("blood"), 860, 800, 70, 70, null);
        g.drawImage(gamePics.get("defence"), 670, 815, 45, 45, null);
        g.drawImage(gamePics.get("blood"), 860, 210, 70, 70, null);
        g.drawImage(gamePics.get("defence"), 670, 225, 45, 45, null);
        if (admin.player() != null) {
            g.drawString(admin.friendlyHero().getHp() + "", 882, 855);
        }
        g.drawString("30", 882, 265);

    }


    @Override
    public void mouseDragged(MouseEvent e) {
        cardPreview.setVisible(false);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        cardPreview.setVisible(false);
        int x = e.getX();
        int y = e.getY();

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
        cardPreview.setCardModelView(admin.getViewModelOf(st.toLowerCase()));
        cardPreview.setBounds(x - 50, y - 360, 260, 370);
        cardPreview.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (!cardSelected) {
            mouseStartX = x;
            mouseStartY = y;
        }
        if (x >= config.getPlayerHeroPowerX() && x <= (config.getPlayerHeroPowerX() + config.getHeroPoerWidth()) && y >= config.getPlayerHeroPowerY() && y <= (config.getPlayerHeroPowerY() + config.getHeroPowerHeight())) {
            admin.playHeroPower(admin.friendlyHero());
        }
        if (cardSelected) {
            mouseDesX = x;
            mouseDesY = y;
            if (x >= config.getLeftLineX() && x <= config.getRightLineX() && y >= config.getMiddleLineY() && y <= config.getPlayAreaY()) {
                deckIndex = 0;
                if (playedImages.size() > 0) {
                    if (x <= playedImages.get(0).getX()) {
                        deckIndex = 0;
                    } else if (x >= playedImages.get(playedImages.size() - 1).getX()) {
                        deckIndex = playedImages.size();
                    } else {
                        for (int j = 0; j < playedImages.size() - 1; j++) {

                            if ((x > (playedImages.get(j).getX() + playedImages.get(j).getWidth() - 10)) && (x < (playedImages.get(j + 1).getX() + 15))) {
                                deckIndex = j + 1;

                                break;
                            }
                        }
                    }
                }
                if (admin.canBePlayed(name)) {
                    config.setPlayAnimation(true);
                    X = mouseStartX;
                    Y = mouseStartY;
                    playTimer.start();
                }
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException interruptedException) {
//                    interruptedException.printStackTrace();
//                }
            }
            cardSelected = false;
            return;
        }
        if (x >= config.getLeftLineX() && x <= config.getRightLineX() && y >= config.getPlayerHandY()) {
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
            if (admin.cardCanbePlayed(st)) {
                name = st;
                cardSelected = true;
                System.out.println(name + " selected");
//                admin.playCard(name);
            }
            revalidate();
            repaint();
        }

    }

    void clear() {
        handImages = new ArrayList<>();
        playedImages = new ArrayList<>();
    }


    @Override
    public void mousePressed(MouseEvent e) {
        cardPreview.setVisible(false);
//        if (x >= config.getLeftLineX() && x <= config.getRightLineX() && y >= config.getPlayerHandY()) {
//            String st = null;
//            for (Images images : images) {
//                if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
//                    st = images.getName();
//                    break;
//                }
//            }
//            if (st == null || st.equals("")) {
//                return;
//            }
//            if (admin.cardCanbePlayed(st)) {
//                name = st;
//            }
//            revalidate();
//            repaint();
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

//        if (x >= config.getLeftLineX() && x <= config.getRightLineX() && y >= config.getMiddleLineY() && y <= config.getPlayAreaY()) {
//            admin.playCard(name);
//        }
//        mouseX = (-1000);
//        mouseY = -1000;
//        name = null;
        revalidate();
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toMiddleTimer) {
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

        } else if (e.getSource() == toHandTimer) {
            repaint();
            int desX = 0;
            if (handImages.size() > 0)
                desX = (700 - (handImages.get(handImages.size() - 1).getX() + handImages.get(handImages.size() - 1).getWidth()) + 15) / 30;
            Y += 20;
            X -= desX;
            if (Y > 950) {
                config.setDeckAnimationFinished(true);
                config.setAnimated(false);
                toHandTimer.stop();
            }
        } else if (e.getSource() == playTimer) {
            int desX = (mouseDesX - mouseStartX) / 30;
            int desY = (mouseDesY - mouseStartY) / 30;
            X += desX;
            Y += desY;
            repaint();
            if (Y < mouseDesY) {
                config.setPlayAnimation(false);
                System.out.println("timer stop");
                admin.playCard(name, deckIndex);
                playTimer.stop();
            }
        } else {
            JButton src = (JButton) e.getSource();
            if (src == exit) {
                admin.Log("Click_Button : Exit Button");
                admin.exit();
            } else if (src == back) {
                admin.Log("Click_Button : Back Button");
                admin.setVisiblePanel("menu");
            } else if (src == nextTurnButton) {
                admin.Log("Click_Button : NextTurn Button");
                ourTurn= !ourTurn;
                if (ourTurn){
                    enemyHero.setBounds(710, 35, 175, 279);
                    friendlyHero.setBounds(710, 615, 175, 279);

                }else {
                    enemyHero.setBounds(710, 615, 175, 279);
                    friendlyHero.setBounds(710, 35, 175, 279);

                }
                clear();
                admin.endTurn();
                config.setToMiddle(true);
                config.setBlur(0);
                config.setAnimated(true);
                toMiddleTimer.start();
                revalidate();
                repaint();
            }
        }


    }
}
