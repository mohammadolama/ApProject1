package GUI;

import Enums.Carts;
import Enums.Heroes;
import Heros.Hero;
import Main.Gamestate;
import Main.JsonBuilders;
import Main.JsonReaders;
import Main.LOGGER;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static GUI.Constants.*;

public class FirstHeroSelector extends JPanel implements ActionListener {

    private JButton mage;
    private JButton rogue;
    private JButton warlock;
    private JButton src;
    private JButton choose;
    private String hero;

    private int size=350;
    private int spacing=400;
    private int startX=340;
    private int startY=10;
    private int width=210;
    private int height=300;


    public FirstHeroSelector(){
        repaint();
        setLayout(null);

        JButton mage=new JButton();
        mage.setIcon(mageIcon);
        mage.setContentAreaFilled(false);
        mage.setBorderPainted(false);
        mage.setBounds(startX,startY,size,size);
        mage.setFont(f2);
        mage.addActionListener(this);
        add(mage);


        System.out.println("helloo");

        JButton rogue =new JButton();
        rogue.setIcon(rogueIcon);
        rogue.setContentAreaFilled(false);
        rogue.setBorderPainted(false);
        rogue.setBounds(startX+spacing,startY,size,size);
        rogue.setFont(f2);
        rogue.addActionListener(this);
        add(rogue);

        JButton warlock = new JButton();
        warlock.setIcon(warlockIcon);
        warlock.setContentAreaFilled(false);
        warlock.setBorderPainted(false);
        warlock.setBounds(startX+2*spacing,startY,size,size);
        warlock.setFont(f2);
        warlock.addActionListener(this);
        add(warlock);
        System.out.println("hello");
        JButton choose=new JButton("Choose");
        choose.setFocusable(false);
        choose.setBounds(120 , gameHight-120,120,30);
        choose.setFont(f2);
        add(choose);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(heroBackgroung,0,0,1600,1000,null);

        Graphics2D g2d=(Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(f2.deriveFont(35.0f));
        FontMetrics fontMetrics=g2d.getFontMetrics(f2);
        g2d.setColor(Color.BLUE);
        g2d.drawString("Mage" , (startX+120),startY+size+15);
        g2d.drawString("Rouge" , (startX+120)+spacing,startY+size+15);
        g2d.drawString("warlock" , (startX+120)+2*spacing,startY+size+15);

        g2d.setFont(f2.deriveFont(40.0f));
        g2d.setColor(Color.YELLOW);
        g2d.drawString("     Hero    ",20,startY+size+15);
        g2d.drawString("  Hero Power ",20,startY+500);
        g2d.drawString("Special Cards" , 20 ,startY+750);


        g2d.drawImage(magePower , startX+70 , startY+370,180,250,null);
        g2d.drawImage(magePower , (startX+70)+spacing+10 , startY+370,180,250,null);
        g2d.drawImage(magePower , (startX+70)+2*spacing+10 , startY+370,180,250,null);


        g.drawImage(cardPics.get("polymorph") ,startX-50,startY+610,width,height,null);
        g.drawImage(cardPics.get("flamestrike"),startX+150,startY+610,width,height,null);
        g2d.drawImage(cardPics.get("friendlysmith") , (startX-50)+spacing +10, startY+610,width,height,null);
        g2d.drawImage(cardPics.get("umbralskulker") , (startX+150)+spacing +10, startY+610,width,height,null);
        g2d.drawImage(cardPics.get("dreadscale") , (startX-50)+2*spacing +20, startY+610,width,height,null);
        g2d.drawImage(cardPics.get("darkskies") , (startX+150)+2*spacing +20, startY+610,width,height,null);


        g2d.setColor(Color.RED);
        g2d.setFont(designer);
        g2d.drawString("Designed by Ghaffari" , 5,940);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        src= (JButton) e.getSource();
        if (src==mage){
            hero="mage";
        }
        else if (src==rogue){
            hero="rogue";
        }
        else if (src==warlock){
            hero="warlock";
        }
        else if (src==choose){
            if (hero==null || hero=="")
                return;
            switch (hero){
                case "mage":
                    ArrayList<Carts> ar= Gamestate.getPlayer().getPlayerCarts();
                    ar.add(Carts.polymorph);
                    Gamestate.getPlayer().setPlayerCarts(ar);
                    try {
                        Gamestate.getPlayer().setSelectedHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(),"mage"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "rouge":
                    ArrayList<Carts> ar1=Gamestate.getPlayer().getPlayerCarts();
                    ar1.add(Carts.friendlysmith);
                    Gamestate.getPlayer().setPlayerCarts(ar1);
                    try {
                        Gamestate.getPlayer().setSelectedHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(),"rouge"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "warlock":
                    ArrayList<Carts> ar2=Gamestate.getPlayer().getPlayerCarts();
                    ar2.add(Carts.dreadscale);
                    Gamestate.getPlayer().setPlayerCarts(ar2);
                    try {
                        Gamestate.getPlayer().setSelectedHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(),"warlock"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;
            }
            LOGGER.playerlog(Gamestate.getPlayer(), String.format("Select : %s as first selected hero", hero.toUpperCase()));
            Gamestate.getPlayer().setNewToGame(false);
            ArrayList<Heroes> ar1=Gamestate.getPlayer().getPlayerHeroes();
            if (Gamestate.getPlayer().getPlayerHeroes()==null)  ar1=new ArrayList<Heroes>();
            ar1.add(Heroes.valueOf(hero));
            Gamestate.getPlayer().setPlayerHeroes(ar1);
            try {
                JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername(), Gamestate.getPlayer());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            MyFrame.getInstance().changePanel("menu");
        }

    }
}
