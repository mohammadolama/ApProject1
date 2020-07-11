package View.Panels;

//import View.View.GUI.DrawingPanels.TestPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

public class Constants {

    private static String cardPicsPath = "resources/pics/cards/";
    private static String heroPicsPath = "resources/pics/hero/";
    private static String gamePicsPath = "resources/pics/game/";
    private static String heroPowerPath = "resources/pics/heropower/";
    private static String heroPortraitsPath = "resources/pics/hero/portrait/";
    public static HashMap<String, BufferedImage> heroPortraits;

    static int gameWidth = 1600;
    static int gameHight = 1000;

    static Font f2 = new Font("Serif", Font.BOLD, 25);
    static Font designer = new Font("Serif", Font.BOLD, 12);
    static Font fantasy;

    static Icon mageIcon;
    static Icon rogueIcon;
    static Icon warlockIcon;

    private static BufferedImage login;
    private static BufferedImage main;
    private static BufferedImage collection;
    private static BufferedImage status;
    private static BufferedImage status2;
    private static BufferedImage playBoard;
    private static BufferedImage playBackground;
    private static BufferedImage store;
    private static BufferedImage setting;
    private static BufferedImage info;
    private static BufferedImage heroBackground;
    private static BufferedImage tick;
    private static BufferedImage mana;
    private static BufferedImage blood;
    private static BufferedImage defence;
    private static BufferedImage enemycard;
    private static BufferedImage redTarget;
    private static BufferedImage damage;

    private static BufferedImage mage;
    private static BufferedImage rogue;
    private static BufferedImage hunter;
    private static BufferedImage priest;
    private static BufferedImage warlock;


    private static BufferedImage magePower;
    private static BufferedImage roguePower;
    private static BufferedImage warlockPower;
    private static BufferedImage priestPower;
    private static BufferedImage hunterPower;

    private static BufferedImage aghahaghi;
    private static BufferedImage arcanitereaper;
    private static BufferedImage ashbringer;
    private static BufferedImage ali;
    private static BufferedImage aylar;
    private static BufferedImage blessingoftheancients;
    private static BufferedImage bloodfury;
    private static BufferedImage bookofspecters;
    private static BufferedImage benyamin;
    private static BufferedImage cat;
    private static BufferedImage darkskies;
    private static BufferedImage cookie;
    private static BufferedImage fierywaraxe;
    private static BufferedImage flamestrike;
    private static BufferedImage gearblade;
    private static BufferedImage faeze;
    private static BufferedImage holylight;
    private static BufferedImage highmastersaman;
    private static BufferedImage hossein;
    private static BufferedImage hosseinhima;
    private static BufferedImage javad;
    private static BufferedImage khashayar;
    private static BufferedImage lachin;
    private static BufferedImage lightforgedblessing;
    private static BufferedImage learnjavadonic;
    private static BufferedImage matin;
    private static BufferedImage mobin;
    private static BufferedImage polymorph;
    private static BufferedImage nima;
    private static BufferedImage sandbreath;
    private static BufferedImage quiz;
    private static BufferedImage silversword;
    private static BufferedImage shahryar;
    private static BufferedImage sprint;
    private static BufferedImage strengthinnumbers;
    private static BufferedImage strengthinnumbersdr;
    private static BufferedImage soroush;
    private static BufferedImage truesilverchampion;
    private static BufferedImage swarmofcats;
    private static BufferedImage yasaman;

    private static ImageIcon exitIcon;
    private static ImageIcon backIcon;
    private static ImageIcon selectIcon;
    private static ImageIcon nextTurn;
    private static ImageIcon deleteAccoun;
    private static ImageIcon greenTargetIcon;
    private static ImageIcon redTargetIcon;

    private static BufferedImage magePortrait;
    private static BufferedImage roguePortrait;
    private static BufferedImage warlockPortrait;
    private static BufferedImage priestPortrait;
    private static BufferedImage hunterPortrait;

    public static HashMap<String, BufferedImage> cardPics;
    public static HashMap<String, BufferedImage> gamePics;
    public static HashMap<String, BufferedImage> heroPics;
    public static HashMap<String, BufferedImage> powerPics;
    public static HashMap<String, ImageIcon> gameIcon;

    public static void pictureLoader() {

        try {
            fantasy = Font.createFont(Font.TRUETYPE_FONT, new File("resources/font/Ace.ttf")).deriveFont(40.0f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fantasy);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        mageIcon = new ImageIcon(heroPicsPath + "mage.png");
        rogueIcon = new ImageIcon(heroPicsPath + "rogue.png");
        warlockIcon = new ImageIcon(heroPicsPath + "warlock.png");

        try {
            cardPics = new HashMap<>();
            aghahaghi = ImageIO.read(new File(cardPicsPath + "aghahaghi.png"));
            ali = ImageIO.read(new File(cardPicsPath + "ali.png"));
            arcanitereaper = ImageIO.read(new File(cardPicsPath + "arcanitereaper.png"));
            ashbringer = ImageIO.read(new File(cardPicsPath + "ashbringer.png"));
            aylar = ImageIO.read(new File(cardPicsPath + "aylar.png"));
            benyamin = ImageIO.read(new File(cardPicsPath + "benyamin.png"));
            blessingoftheancients = ImageIO.read(new File(cardPicsPath + "blessingoftheancients.png"));
            bloodfury = ImageIO.read(new File(cardPicsPath + "bloodfury.png"));
            bookofspecters = ImageIO.read(new File(cardPicsPath + "bookofspecters.png"));
            cat = ImageIO.read(new File(cardPicsPath + "cat.png"));
            cookie = ImageIO.read(new File(cardPicsPath + "cookie.png"));
            darkskies = ImageIO.read(new File(cardPicsPath + "darkskies.png"));
            faeze = ImageIO.read(new File(cardPicsPath + "faeze.png"));
            fierywaraxe = ImageIO.read(new File(cardPicsPath + "fierywaraxe.png"));
            flamestrike = ImageIO.read(new File(cardPicsPath + "flamestrike.png"));
            gearblade = ImageIO.read(new File(cardPicsPath + "gearblade.png"));
            highmastersaman = ImageIO.read(new File(cardPicsPath + "highmastersaman.png"));
            holylight = ImageIO.read(new File(cardPicsPath + "holylight.png"));
            hossein = ImageIO.read(new File(cardPicsPath + "hossein.png"));
            hosseinhima = ImageIO.read(new File(cardPicsPath + "hosseinhima.png"));
            javad = ImageIO.read(new File(cardPicsPath + "javad.png"));
            khashayar = ImageIO.read(new File(cardPicsPath + "khashayar.png"));
            lachin = ImageIO.read(new File(cardPicsPath + "lachin.png"));
            learnjavadonic = ImageIO.read(new File(cardPicsPath + "learnjavadonic.png"));
            lightforgedblessing = ImageIO.read(new File(cardPicsPath + "lightforgedblessing.png"));
            matin = ImageIO.read(new File(cardPicsPath + "matin.png"));
            mobin = ImageIO.read(new File(cardPicsPath + "mobin.png"));
            nima = ImageIO.read(new File(cardPicsPath + "nima.png"));
            polymorph = ImageIO.read(new File(cardPicsPath + "polymorph.png"));
            quiz = ImageIO.read(new File(cardPicsPath + "quiz.png"));
            sandbreath = ImageIO.read(new File(cardPicsPath + "sandbreath.png"));
            shahryar = ImageIO.read(new File(cardPicsPath + "shahryar.png"));
            silversword = ImageIO.read(new File(cardPicsPath + "silversword.png"));
            soroush = ImageIO.read(new File(cardPicsPath + "soroush.png"));
            sprint = ImageIO.read(new File(cardPicsPath + "sprint.png"));
            strengthinnumbers = ImageIO.read(new File(cardPicsPath + "strengthinnumbers.png"));
            strengthinnumbersdr = ImageIO.read(new File(cardPicsPath + "strengthinnumbersdr.png"));
            swarmofcats = ImageIO.read(new File(cardPicsPath + "swarmofcats.png"));
            truesilverchampion = ImageIO.read(new File(cardPicsPath + "truesilverchampion.png"));
            yasaman = ImageIO.read(new File(cardPicsPath + "yasaman.png"));
            cardPics.put("aghahaghi", aghahaghi);
            cardPics.put("ali", ali);
            cardPics.put("arcanitereaper", arcanitereaper);
            cardPics.put("ashbringer", ashbringer);
            cardPics.put("aylar", aylar);
            cardPics.put("benyamin", benyamin);
            cardPics.put("blessingoftheancients", blessingoftheancients);
            cardPics.put("bloodfury", bloodfury);
            cardPics.put("bookofspecters", bookofspecters);
            cardPics.put("cat", cat);
            cardPics.put("cookie", cookie);
            cardPics.put("darkskies", darkskies);
            cardPics.put("faeze", faeze);
            cardPics.put("fierywaraxe", fierywaraxe);
            cardPics.put("flamestrike", flamestrike);
            cardPics.put("gearblade", gearblade);
            cardPics.put("highmastersaman", highmastersaman);
            cardPics.put("holylight", holylight);
            cardPics.put("hossein", hossein);
            cardPics.put("hosseinhima", hosseinhima);
            cardPics.put("javad", javad);
            cardPics.put("khashayar", khashayar);
            cardPics.put("lachin", lachin);
            cardPics.put("learnjavadonic", learnjavadonic);
            cardPics.put("lightforgedblessing", lightforgedblessing);
            cardPics.put("matin", matin);
            cardPics.put("mobin", mobin);
            cardPics.put("nima", nima);
            cardPics.put("polymorph", polymorph);
            cardPics.put("quiz", quiz);
            cardPics.put("sandbreath", sandbreath);
            cardPics.put("shahryar", shahryar);
            cardPics.put("silversword", silversword);
            cardPics.put("soroush", soroush);
            cardPics.put("sprint", sprint);
            cardPics.put("strengthinnumbers", strengthinnumbers);
            cardPics.put("strengthinnumbersdr", strengthinnumbersdr);
            cardPics.put("swarmofcats", swarmofcats);
            cardPics.put("truesilverchampion", truesilverchampion);
            cardPics.put("yasaman", yasaman);


            heroPics = new HashMap<>();
            mage = ImageIO.read(new File(heroPicsPath + "mage.png"));
            rogue = ImageIO.read(new File(heroPicsPath + "rogue.png"));
            warlock = ImageIO.read(new File(heroPicsPath + "warlock.png"));
            hunter = ImageIO.read(new File(heroPicsPath + "hunter.png"));
            priest = ImageIO.read(new File(heroPicsPath + "priest.png"));
            heroPics.put("mage", mage);
            heroPics.put("rogue", rogue);
            heroPics.put("warlock", warlock);
            heroPics.put("hunter", hunter);
            heroPics.put("priest", priest);


            gamePics = new HashMap<>();
            login = ImageIO.read(new File(gamePicsPath + "login.jpg"));
            main = ImageIO.read(new File(gamePicsPath + "main.jpg"));
            status = ImageIO.read(new File(gamePicsPath + "status.jpg"));
            collection = ImageIO.read(new File(gamePicsPath + "collection.png"));
            heroBackground = ImageIO.read(new File(gamePicsPath + "hero.jpg"));
            info = ImageIO.read(new File(gamePicsPath + "info.jpg"));
            playBoard = ImageIO.read(new File(gamePicsPath + "play.png"));
            playBackground = ImageIO.read(new File(gamePicsPath + "playback.png"));
            setting = ImageIO.read(new File(gamePicsPath + "setting.jpg"));
            status2 = ImageIO.read(new File(gamePicsPath + "status2.png"));
            store = ImageIO.read(new File(gamePicsPath + "store.jpg"));
            mana = ImageIO.read(new File(gamePicsPath + "mana.png"));
            blood = ImageIO.read(new File(gamePicsPath + "blood.png"));
            defence = ImageIO.read(new File(gamePicsPath + "defence.png"));
            tick = ImageIO.read(new File(gamePicsPath + "tick.png"));
            enemycard = ImageIO.read(new File(gamePicsPath + "enemycard.png"));
            redTarget = ImageIO.read(new File(gamePicsPath + "redtarget.png"));
            damage = ImageIO.read(new File(gamePicsPath + "damage.png"));
            gamePics.put("login", login);
            gamePics.put("main", main);
            gamePics.put("collection", collection);
            gamePics.put("status", status);
            gamePics.put("status2", status2);
            gamePics.put("playboard", playBoard);
            gamePics.put("playbackground", playBackground);
            gamePics.put("store", store);
            gamePics.put("setting", setting);
            gamePics.put("info", info);
            gamePics.put("herobackground", heroBackground);
            gamePics.put("tick", tick);
            gamePics.put("mana", mana);
            gamePics.put("blood", blood);
            gamePics.put("defence", defence);
            gamePics.put("enemycard", enemycard);
            gamePics.put("target", redTarget);
            gamePics.put("damage", damage);

            gameIcon = new HashMap<>();
            exitIcon = new ImageIcon(gamePicsPath + "exit.png");
            backIcon = new ImageIcon(gamePicsPath + "back.png");
            selectIcon = new ImageIcon(gamePicsPath + "select.png");
            nextTurn = new ImageIcon(gamePicsPath + "nextturn.png");
            deleteAccoun = new ImageIcon(gamePicsPath + "delete.png");
            redTargetIcon = new ImageIcon(gamePicsPath + "redtarget.png");
            greenTargetIcon = new ImageIcon(gamePicsPath + "greentarget.png");

            gameIcon.put("exit", exitIcon);
            gameIcon.put("back", backIcon);
            gameIcon.put("select", selectIcon);
            gameIcon.put("next", nextTurn);
            gameIcon.put("delete", deleteAccoun);
            gameIcon.put("redtarget", redTargetIcon);
            gameIcon.put("greentarget", greenTargetIcon);

            powerPics = new HashMap<>();
            magePower = ImageIO.read(new File(heroPowerPath + "mage.png"));
            roguePower = ImageIO.read(new File(heroPowerPath + "rogue.png"));
            warlockPower = ImageIO.read(new File(heroPowerPath + "warlock.png"));
            priestPower = ImageIO.read(new File(heroPowerPath + "priest.png"));
            hunterPower = ImageIO.read(new File(heroPowerPath + "hunter.png"));
            powerPics.put("mage", magePower);
            powerPics.put("warlock", warlockPower);
            powerPics.put("rogue", roguePower);
            powerPics.put("priest", priestPower);
            powerPics.put("hunter", hunterPower);


            heroPortraits = new HashMap<>();
            magePortrait = ImageIO.read(new File(heroPortraitsPath + "mage.png"));
            roguePortrait = ImageIO.read(new File(heroPortraitsPath + "rogue.png"));
            warlockPortrait = ImageIO.read(new File(heroPortraitsPath + "warlock.png"));
            priestPortrait = ImageIO.read(new File(heroPortraitsPath + "priest.png"));
            hunterPortrait = ImageIO.read(new File(heroPortraitsPath + "hunter.png"));
            heroPortraits.put("mage", magePortrait);
            heroPortraits.put("rogue", roguePortrait);
            heroPortraits.put("warlock", warlockPortrait);
            heroPortraits.put("priest", priestPortrait);
            heroPortraits.put("hunter", hunterPortrait);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Hashtable<Integer, JComponent> getTable() {
        Hashtable<Integer, JComponent> table =
                new Hashtable<>();
        table.put(1, new JLabel("1"));
        table.put(2, new JLabel("2"));
        table.put(3, new JLabel("3"));
        table.put(4, new JLabel("4"));
        table.put(5, new JLabel("5"));
        table.put(6, new JLabel("6"));
        table.put(7, new JLabel("7"));
        table.put(8, new JLabel("8"));
        table.put(9, new JLabel("9"));
        table.put(10, new JLabel("10"));
        table.put(11, new JLabel("All"));
        return table;
    }

}
