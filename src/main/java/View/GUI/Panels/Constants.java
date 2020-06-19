package View.GUI.Panels;

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
    private static String heroGifsPath = "resources/pics/hero/gifs/";

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
    private static BufferedImage target;

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


    private static BufferedImage arcanitereaper;
    private static BufferedImage ashbringer;
    private static BufferedImage blessingoftheancients;
    private static BufferedImage bloodfury;
    private static BufferedImage bookofspecters;
    private static BufferedImage cobaltspellkin;
    private static BufferedImage curiocollector;
    private static BufferedImage darkskies;
    private static BufferedImage deadlyshot;
    private static BufferedImage deathwing;
    private static BufferedImage depthcharge;
    private static BufferedImage divinehymn;
    private static BufferedImage dreadscale;
    private static BufferedImage evasivewyrm;
    private static BufferedImage fierywaraxe;
    private static BufferedImage flamestrike;
    private static BufferedImage friendlysmith;
    private static BufferedImage gearblade;
    private static BufferedImage highpriestamet;
    private static BufferedImage holylight;
    private static BufferedImage koboldstickyfinger;
    private static BufferedImage learndraconic;
    private static BufferedImage lightforgedblessing;
    private static BufferedImage pharaohblessing;
    private static BufferedImage polymorph;
    private static BufferedImage sandbreath;
    private static BufferedImage sathrovarr;
    private static BufferedImage securityrover;
    private static BufferedImage silversword;
    private static BufferedImage sprint;
    private static BufferedImage strengthinnumbers;
    private static BufferedImage swampkingdred;
    private static BufferedImage swarmoflocusts;
    private static BufferedImage tastyflyfish;
    private static BufferedImage tombwarden;
    private static BufferedImage truesilverchampion;
    private static BufferedImage umbralskulker;


    private static ImageIcon exitIcon;
    private static ImageIcon backIcon;
    private static ImageIcon selectIcon;
    private static ImageIcon nextTurn;
    private static ImageIcon deleteAccoun;


    private static ImageIcon mageGif;
    private static ImageIcon rogueGif;
    private static ImageIcon warlockGif;
    private static ImageIcon priestGif;
    private static ImageIcon hunterGif;


    public static HashMap<String, BufferedImage> cardPics;
    public static HashMap<String, BufferedImage> gamePics;
    public static HashMap<String, BufferedImage> heroPics;
    public static HashMap<String, BufferedImage> powerPics;
    public static HashMap<String, ImageIcon> heroGifs;
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
            arcanitereaper = ImageIO.read(new File(cardPicsPath + "arcanitereaper.png"));
            ashbringer = ImageIO.read(new File(cardPicsPath + "ashbringer.png"));
            blessingoftheancients = ImageIO.read(new File(cardPicsPath + "blessingoftheancients.png"));
            bloodfury = ImageIO.read(new File(cardPicsPath + "bloodfury.png"));
            bookofspecters = ImageIO.read(new File(cardPicsPath + "bookofspecters.png"));
            cobaltspellkin = ImageIO.read(new File(cardPicsPath + "cobaltspellkin.png"));
            curiocollector = ImageIO.read(new File(cardPicsPath + "curiocollector.png"));
            darkskies = ImageIO.read(new File(cardPicsPath + "darkskies.png"));
            deadlyshot = ImageIO.read(new File(cardPicsPath + "deadlyshot.png"));
            deathwing = ImageIO.read(new File(cardPicsPath + "deathwing.png"));
            depthcharge = ImageIO.read(new File(cardPicsPath + "depthcharge.png"));
            divinehymn = ImageIO.read(new File(cardPicsPath + "divinehymn.png"));
            dreadscale = ImageIO.read(new File(cardPicsPath + "dreadscale.png"));
            evasivewyrm = ImageIO.read(new File(cardPicsPath + "evasivewyrm.png"));
            fierywaraxe = ImageIO.read(new File(cardPicsPath + "fierywaraxe.png"));
            flamestrike = ImageIO.read(new File(cardPicsPath + "flamestrike.png"));
            friendlysmith = ImageIO.read(new File(cardPicsPath + "friendlysmith.png"));
            gearblade = ImageIO.read(new File(cardPicsPath + "gearblade.png"));
            highpriestamet = ImageIO.read(new File(cardPicsPath + "highpriestamet.png"));
            holylight = ImageIO.read(new File(cardPicsPath + "holylight.png"));
            koboldstickyfinger = ImageIO.read(new File(cardPicsPath + "koboldstickyfinger.png"));
            learndraconic = ImageIO.read(new File(cardPicsPath + "learndraconic.png"));
            lightforgedblessing = ImageIO.read(new File(cardPicsPath + "lightforgedblessing.png"));
            pharaohblessing = ImageIO.read(new File(cardPicsPath + "pharaohblessing.png"));
            polymorph = ImageIO.read(new File(cardPicsPath + "polymorph.png"));
            sandbreath = ImageIO.read(new File(cardPicsPath + "sandbreath.png"));
            sathrovarr = ImageIO.read(new File(cardPicsPath + "sathrovarr.png"));
            securityrover = ImageIO.read(new File(cardPicsPath + "securityrover.png"));
            silversword = ImageIO.read(new File(cardPicsPath + "silversword.png"));
            sprint = ImageIO.read(new File(cardPicsPath + "sprint.png"));
            strengthinnumbers = ImageIO.read(new File(cardPicsPath + "strengthinnumbers.png"));
            swampkingdred = ImageIO.read(new File(cardPicsPath + "swampkingdred.png"));
            swarmoflocusts = ImageIO.read(new File(cardPicsPath + "swarmoflocusts.png"));
            tastyflyfish = ImageIO.read(new File(cardPicsPath + "tastyflyfish.png"));
            tombwarden = ImageIO.read(new File(cardPicsPath + "tombwarden.png"));
            truesilverchampion = ImageIO.read(new File(cardPicsPath + "truesilverchampion.png"));
            umbralskulker = ImageIO.read(new File(cardPicsPath + "umbralskulker.png"));
            cardPics.put("arcanitereaper", arcanitereaper);
            cardPics.put("ashbringer", ashbringer);
            cardPics.put("blessingoftheancients", blessingoftheancients);
            cardPics.put("bloodfury", bloodfury);
            cardPics.put("bookofspecters", bookofspecters);
            cardPics.put("cobaltspellkin", cobaltspellkin);
            cardPics.put("curiocollector", curiocollector);
            cardPics.put("darkskies", darkskies);
            cardPics.put("deadlyshot", deadlyshot);
            cardPics.put("deathwing", deathwing);
            cardPics.put("depthcharge", depthcharge);
            cardPics.put("divinehymn", divinehymn);
            cardPics.put("dreadscale", dreadscale);
            cardPics.put("evasivewyrm", evasivewyrm);
            cardPics.put("fierywaraxe", fierywaraxe);
            cardPics.put("flamestrike", flamestrike);
            cardPics.put("friendlysmith", friendlysmith);
            cardPics.put("gearblade", gearblade);
            cardPics.put("highpriestamet", highpriestamet);
            cardPics.put("holylight", holylight);
            cardPics.put("koboldstickyfinger", koboldstickyfinger);
            cardPics.put("learndraconic", learndraconic);
            cardPics.put("lightforgedblessing", lightforgedblessing);
            cardPics.put("pharaohblessing", pharaohblessing);
            cardPics.put("polymorph", polymorph);
            cardPics.put("sandbreath", sandbreath);
            cardPics.put("sathrovarr", sathrovarr);
            cardPics.put("securityrover", securityrover);
            cardPics.put("silversword", silversword);
            cardPics.put("sprint", sprint);
            cardPics.put("strengthinnumbers", strengthinnumbers);
            cardPics.put("swampkingdred", swampkingdred);
            cardPics.put("swarmoflocusts", swarmoflocusts);
            cardPics.put("tastyflyfish", tastyflyfish);
            cardPics.put("tombwarden", tombwarden);
            cardPics.put("truesilverchampion", truesilverchampion);
            cardPics.put("umbralskulker", umbralskulker);


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
            target=ImageIO.read(new File(gamePicsPath+"target.png"));
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
            gamePics.put("target" , target);

            gameIcon = new HashMap<>();
            exitIcon = new ImageIcon(gamePicsPath + "exit.png");
            backIcon = new ImageIcon(gamePicsPath + "back.png");
            selectIcon = new ImageIcon(gamePicsPath + "select.png");
            nextTurn = new ImageIcon(gamePicsPath + "nextturn.png");
            deleteAccoun = new ImageIcon(gamePicsPath + "delete.png");
            gameIcon.put("exit", exitIcon);
            gameIcon.put("back", backIcon);
            gameIcon.put("select", selectIcon);
            gameIcon.put("next", nextTurn);
            gameIcon.put("delete", deleteAccoun);


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


            heroGifs = new HashMap<>();
            mageGif = new ImageIcon(heroGifsPath + "mage.gif");
            rogueGif = new ImageIcon(heroGifsPath + "rogue.gif");
            warlockGif = new ImageIcon(heroGifsPath + "warlock.gif");
            priestGif = new ImageIcon(heroGifsPath + "priest.gif");
            hunterGif = new ImageIcon(heroGifsPath + "hunter.gif");
            heroGifs.put("mage", mageGif);
            heroGifs.put("rogue", rogueGif);
            heroGifs.put("warlock", warlockGif);
            heroGifs.put("priest", priestGif);
            heroGifs.put("hunter", hunterGif);
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
