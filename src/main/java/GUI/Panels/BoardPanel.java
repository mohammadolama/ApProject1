package GUI.Panels;

import GUI.Configs.ConfigsLoader;
import Util.Admin;
import GUI.Configs.BoardConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


import static GUI.Panels.Constants.*;

public class BoardPanel extends JPanel implements MouseMotionListener, MouseListener, ActionListener {


    private int mouseX = -1000;
    private int mouseY = -1000;


    private String name;

    private BoardConfig config;
    private JButton nextTurnButton;
    private ArrayList<Images> images;
    private ArrayList<Point> points = new ArrayList<>();
    private Admin admin;

    private JButton back;
    private JButton exit;
    private CardPreview cardPreview;

    private void initConfig() {
        config = ConfigsLoader.getInstance().getBoardConfig();
    }


    public BoardPanel() {
        initConfig();
        setLayout(null);
        addMouseMotionListener(this);
        addMouseListener(this);
        admin = Admin.getInstance();
        images = new ArrayList<>();


        cardPreview = CardPreview.getInstance();
        cardPreview.setBounds(0, 0, 600, 600);
        cardPreview.setLayout(null);
        cardPreview.setVisible(false);
        cardPreview.setOpaque(false);
        add(cardPreview);


        JLabel our = new JLabel();
        our.setBounds(710, 615, 175, 279);
        our.setIcon(admin.gifOf(admin.playerHeroName()));
        our.setOpaque(false);
        add(our);


        JLabel enemy = new JLabel();
        enemy.setBounds(710, 35, 175, 279);
        enemy.setIcon(admin.gifOf("hunter"));
        enemy.setOpaque(false);
        add(enemy);


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
        g.drawString(admin.player().getUsername(), 30, 920);
        g.drawString("Rexxar", 30, 50);


        drawLog(g);

        drawHeroPower(g);

        drawHeroInfo(g);

        drawWeapon(g);

        drawMana(g);

        cardnumber(g);

        handCards(g);

        playedCard(g);

        dragedCard(g);


    }

    private void drawEnemyCard(Graphics2D g, int x, int y, int width, int height) {
        g.drawImage(gamePics.get("enemycard"), x, y, width, height, null);
    }

    private void drawHeroPower(Graphics2D g) {
        g.setFont(fantasy.deriveFont(24.0f));
        g.drawImage(admin.powerPicuterOf(admin.player().getSelectedDeck().getHero().getName()), config.getPlayerHeroPowerX(), config.getPlayerHeroPowerY(), config.getHeroPoerWidth(), config.getHeroPowerHeight(), null);
        g.drawString(admin.playerPowerMana() + "", config.getPlayerHeroPowerX() + (config.getHeroPoerWidth() / 2) - 5, config.getPlayerHeroPowerY() + 25);
        g.drawImage(admin.powerPicuterOf("hunter"), config.getPlayerHeroPowerX(), config.getOpponentHeroPowerY(), config.getHeroPoerWidth(), config.getHeroPowerHeight(), null);
        if (admin.heroPowerusedTimes() == 0) {
            g.setColor(new Color(112, 112, 112, 180));
            g.fillRect(config.getPlayerHeroPowerX(), config.getPlayerHeroPowerY(), config.getHeroPoerWidth(), config.getHeroPowerHeight());
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
        g.drawString(admin.deckCards().size() + "", 1430, 620);

    }

    private void drawWeapon(Graphics2D g) {
        if (admin.playerWeapon() != null) {
            g.drawImage(admin.pictureOf(admin.playerWeapon().getName().toLowerCase()), 570, 665, config.getCardWidth() + 50, config.getCardHeight() + 80, null);
        }
    }

    private void drawMana(Graphics2D g) {
        g.setFont(f2.deriveFont(30.0f));
        g.drawString(admin.notUsedmana() + "/" + admin.totalMana(), 1035, 935);
        int x = 1110;
        for (int i = 0; i < admin.notUsedmana(); i++) {
            g.drawImage(gamePics.get("mana"), x, 917, 30, 30, null);
            x += 26;
        }
    }

    private void dragedCard(Graphics2D g) {
        if (name == null || name.equalsIgnoreCase("")) {
            return;
        }
        g.drawImage(cardPics.get(name.toLowerCase()), mouseX, mouseY, config.getCardWidth(), config.getCardHeight(), null);
    }

    private void handCards(Graphics2D g) {
        g.setFont(f2.deriveFont(20.0f));
        images.clear();
        if (admin.handCards().size() > 0) {
            int i = 0;
            while (i < admin.handCards().size()) {
                images.add(new Images(admin.handCards().get(i).getName(), config.getPlayerHandX(), config.getPlayerHandY(), config.getCardWidth(), config.getCardHeight()));
                drawHandCard(g, i, config.getPlayerHandX(), config.getPlayerHandY(), config.getCardWidth(), config.getCardHeight());
                drawEnemyCard(g, config.getPlayerHandX(), config.getOpponentHandY(), config.getCardWidth(), config.getCardHeight());
                config.setPlayerHandX(config.getPlayerHandX() + config.getCardWidth() - 15);
                i++;
            }
            config.setPlayerHandX(570);
        }
    }

    private void playedCard(Graphics2D g) {
        if (admin.playedCards().size() > 0) {
            int i = 0;
            while (i < admin.playedCards().size()) {
                drawPlayedCard(g, i, config.getPlayerPlayedCardX(), config.getPlayerPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2);
                config.setPlayerPlayedCardX(config.getPlayerPlayedCardX() + config.getCardWidth() * 2 + 5);
                i++;
            }
            config.setPlayerPlayedCardX(430);
        }

    }

    private void drawPlayedCard(Graphics2D g, int i, int x, int y, int width, int height) {
        g.drawImage(cardPics.get(admin.playedCards().get(i).getName().toLowerCase()), config.getPlayerPlayedCardX(), config.getPlayerPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2, null);
        images.add(new Images(admin.playedCards().get(i).getName(), config.getPlayerPlayedCardX(), config.getPlayerPlayedCardY(), config.getCardWidth() * 2, config.getCardHeight() * 2));

    }

    private void drawHandCard(Graphics2D g, int i, int x, int y, int width, int height) {
        g.drawImage(cardPics.get(admin.handCards().get(i).getName().toLowerCase()), config.getPlayerHandX(), config.getPlayerHandY(), config.getCardWidth(), config.getCardHeight(), null);
        images.add(new Images(admin.handCards().get(i).getName(), config.getPlayerHandX(), config.getPlayerHandY(), config.getCardWidth(), config.getCardHeight()));
    }


    private void drawHeroInfo(Graphics2D g) {
        g.setFont(fantasy.deriveFont(28.0f));
        g.setColor(Color.white);
        g.drawImage(gamePics.get("blood"), 860, 800, 70, 70, null);
        g.drawImage(gamePics.get("defence"), 670, 815, 45, 45, null);
        g.drawImage(gamePics.get("blood"), 860, 210, 70, 70, null);
        g.drawImage(gamePics.get("defence"), 670, 225, 45, 45, null);
        if (admin.player() != null) {
            g.drawString(admin.playerHero().getHp() + "", 882, 855);
        }
        g.drawString("30", 882, 265);

    }


    @Override
    public void mouseDragged(MouseEvent e) {
        cardPreview.setVisible(false);
        points.add(e.getPoint());
        int x = e.getX();
        int y = e.getY();
        mouseX = x;
        mouseY = y;
        revalidate();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        cardPreview.setVisible(false);
        int x = e.getX();
        int y = e.getY();

        String st = null;
        for (Images images : images) {
            if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                st = images.getName();
                break;
            }
        }
        if (st == null || st.equals("")) {
            return;
        }
        cardPreview.setCards(st.toLowerCase());
        cardPreview.setBufferedImage(admin.pictureOf(st));
        cardPreview.setBounds(x - 50, y - 360, 260, 370);
        cardPreview.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println(x + "\t" + y);
        if (x >= config.getPlayerHeroPowerX() && x <= (config.getPlayerHeroPowerX() + config.getHeroPoerWidth()) && y >= config.getPlayerHeroPowerY() && y <= (config.getPlayerHeroPowerY() + config.getHeroPowerHeight())) {
            System.out.println("here");
            admin.playHeroPower(admin.playerHero());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        cardPreview.setVisible(false);
        int x = e.getX();
        int y = e.getY();
        if (x >= config.getLeftLineX() && x <= config.getRightLineX() && y >= config.getPlayerHandY()) {
            String st = null;
            for (Images images : images) {
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
            }
            revalidate();
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (x >= config.getLeftLineX() && x <= config.getRightLineX() && y >= config.getMiddleLineY() && y <= config.getPlayAreaY()) {
            admin.playCard(name);
        }
        mouseX = (-1000);
        mouseY = -1000;
        name = null;
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
        JButton src = (JButton) e.getSource();
        if (src == exit) {
            admin.Log("Click_Button : Exit Button");
            admin.exit();
        } else if (src == back) {
            admin.Log("Click_Button : Back Button");
            admin.setVisiblePanel("menu");
        } else if (src == nextTurnButton) {
            admin.Log("Click_Button : NextTurn Button");
            images.clear();
            admin.endTurn();
            revalidate();
            repaint();
        }
    }
}
