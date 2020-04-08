package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Constants {

    static int gameWidth = 1600;
    static int gameHight = 1000;


    static Font f2 = new Font("Serif", Font.BOLD, 25);
    static Font f1 = new Font("Serif", Font.BOLD, 20);
    static Font Title = new Font("Serif", Font.BOLD, 90);
    static Font designer = new Font("Serif", Font.BOLD, 12);


    static Icon mageIcon = new ImageIcon("resources\\pics\\hero\\mage.png");
    static Icon rogueIcon = new ImageIcon("resources\\pics\\hero\\rogue.png");
    static Icon warlockIcon = new ImageIcon("resources\\pics\\hero\\warlock.png");


    static BufferedImage heroBackgroung;


    private static BufferedImage login;
    private static BufferedImage main;
    static private BufferedImage shop;
    static BufferedImage status;
    static BufferedImage playpanel;

    static BufferedImage mage;
    static BufferedImage rogue;
    static BufferedImage hunter;
    static BufferedImage priest;
    static BufferedImage warlock;


    static BufferedImage magePower;
    static BufferedImage roguePower;
    static BufferedImage warlockPower;


    static BufferedImage hp;

    static BufferedImage arcanitereaper;
    static BufferedImage ashbringer;
    static BufferedImage blessingoftheancients;
    static BufferedImage bloodfury;
    static BufferedImage bookofspecters;
    static BufferedImage cobaltspellkin;
    static BufferedImage curiocollector;
    static BufferedImage darkskies;
    static BufferedImage deadlyshot;
    static BufferedImage deathwing;
    static BufferedImage depthcharge;
    static BufferedImage divinehymn;
    static BufferedImage dreadscale;
    static BufferedImage evasivewyrm;
    static BufferedImage fierywaraxe;
    static BufferedImage flamestrike;
    static BufferedImage friendlysmith;
    static BufferedImage gearblade;
    static BufferedImage highpriestamet;
    static BufferedImage holylight;
    static BufferedImage koboldstickyfinger;
    static BufferedImage learndraconic;
    static BufferedImage lightforgedblessing;
    static BufferedImage pharaohblessing;
    static BufferedImage polymorph;
    static BufferedImage sandbreath;
    static BufferedImage sathrovarr;
    static BufferedImage securityrover;
    static BufferedImage silversword;
    static BufferedImage sprint;
    static BufferedImage strengthinnumbers;
    static BufferedImage swampkingdred;
    static BufferedImage swarmoflocusts;
    static BufferedImage tastyflyfish;
    static BufferedImage tombwarden;
    static BufferedImage truesilverchampion;
    static BufferedImage umbralskulker;


    static HashMap<String, BufferedImage> cardPics;
    static HashMap<String, BufferedImage> gamePics;
    static HashMap<String, BufferedImage> heroPics;

    static void pictureLoader() {
        cardPics = new HashMap<>();
        try {
            arcanitereaper = ImageIO.read(new File("resources\\pics\\cards\\arcanitereaper.png"));
            ashbringer = ImageIO.read(new File("resources\\pics\\cards\\ashbringer.png"));
            blessingoftheancients = ImageIO.read(new File("resources\\pics\\cards\\blessingoftheancients.png"));
            bloodfury = ImageIO.read(new File("resources\\pics\\cards\\bloodfury.png"));
            bookofspecters = ImageIO.read(new File("resources\\pics\\cards\\bookofspecters.png"));
            cobaltspellkin = ImageIO.read(new File("resources\\pics\\cards\\cobaltspellkin.png"));
            curiocollector = ImageIO.read(new File("resources\\pics\\cards\\curiocollector.png"));
            darkskies = ImageIO.read(new File("resources\\pics\\cards\\darkskies.png"));
            deadlyshot = ImageIO.read(new File("resources\\pics\\cards\\deadlyshot.png"));
            deathwing = ImageIO.read(new File("resources\\pics\\cards\\deathwing.png"));
            depthcharge = ImageIO.read(new File("resources\\pics\\cards\\depthcharge.png"));
            divinehymn = ImageIO.read(new File("resources\\pics\\cards\\divinehymn.png"));
            dreadscale = ImageIO.read(new File("resources\\pics\\cards\\dreadscale.png"));
            evasivewyrm = ImageIO.read(new File("resources\\pics\\cards\\evasivewyrm.png"));
            fierywaraxe = ImageIO.read(new File("resources\\pics\\cards\\fierywaraxe.png"));
            flamestrike = ImageIO.read(new File("resources\\pics\\cards\\flamestrike.png"));
            friendlysmith = ImageIO.read(new File("resources\\pics\\cards\\friendlysmith.png"));
            gearblade = ImageIO.read(new File("resources\\pics\\cards\\gearblade.png"));
            highpriestamet = ImageIO.read(new File("resources\\pics\\cards\\highpriestamet.png"));
            holylight = ImageIO.read(new File("resources\\pics\\cards\\holylight.png"));
            koboldstickyfinger = ImageIO.read(new File("resources\\pics\\cards\\koboldstickyfinger.png"));
            learndraconic = ImageIO.read(new File("resources\\pics\\cards\\learndraconic.png"));
            lightforgedblessing = ImageIO.read(new File("resources\\pics\\cards\\lightforgedblessing.png"));
            pharaohblessing = ImageIO.read(new File("resources\\pics\\cards\\pharaohblessing.png"));
            polymorph = ImageIO.read(new File("resources\\pics\\cards\\polymorph.png"));
            sandbreath = ImageIO.read(new File("resources\\pics\\cards\\sandbreath.png"));
            sathrovarr = ImageIO.read(new File("resources\\pics\\cards\\sathrovarr.png"));
            securityrover = ImageIO.read(new File("resources\\pics\\cards\\securityrover.png"));
            silversword = ImageIO.read(new File("resources\\pics\\cards\\silversword.png"));
            sprint = ImageIO.read(new File("resources\\pics\\cards\\sprint.png"));
            strengthinnumbers = ImageIO.read(new File("resources\\pics\\cards\\strengthinnumbers.png"));
            swampkingdred = ImageIO.read(new File("resources\\pics\\cards\\swampkingdred.png"));
            swarmoflocusts = ImageIO.read(new File("resources\\pics\\cards\\swarmoflocusts.png"));
            tastyflyfish = ImageIO.read(new File("resources\\pics\\cards\\tastyflyfish.png"));
            tombwarden = ImageIO.read(new File("resources\\pics\\cards\\tombwarden.png"));
            truesilverchampion = ImageIO.read(new File("resources\\pics\\cards\\truesilverchampion.png"));
            umbralskulker = ImageIO.read(new File("resources\\pics\\cards\\umbralskulker.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        try {
            mage = ImageIO.read(new File("resources\\pics\\hero\\mage.png"));
            rogue = ImageIO.read(new File("resources\\pics\\hero\\rogue.png"));
            warlock = ImageIO.read(new File("resources\\pics\\hero\\warlock.png"));
            hunter = ImageIO.read(new File("resources\\pics\\hero\\hunter.png"));
            priest = ImageIO.read(new File("resources\\pics\\hero\\priest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        heroPics.put("mage", mage);
        heroPics.put("rogue", rogue);
        heroPics.put("warlock", warlock);
        heroPics.put("hunter", hunter);
        heroPics.put("priest", priest);

        gamePics = new HashMap<>();
        try {
            login = ImageIO.read(new File("resources\\pics\\game\\login.jpg"));
            main = ImageIO.read(new File("resources\\pics\\game\\main.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gamePics.put("login", login);
        gamePics.put("main", main);


        try {
            hp = ImageIO.read(new File("resources\\pics\\1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            heroBackgroung = ImageIO.read(new File("resources\\pics\\2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {

            magePower = ImageIO.read(new File("resources\\pics\\mage.png"));
            roguePower = ImageIO.read(new File("resources\\pics\\rogue.png"));
            warlockPower = ImageIO.read(new File("resources\\pics\\warlock.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
