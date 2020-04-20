package Util;

import Enums.Heroes;
import GUI.*;
import G_L_Interface.Update;
import Main.Gamestate;
import Main.JsonBuilders;
import Main.LOGGER;
import Main.LogInSignUp;
import Sounds.SoundAdmin;


import java.util.ArrayList;

public class Admin {
    private static Admin admin;

    private Admin() {
        }

    public static Admin getInstance() {
        if (admin == null) {
            admin = new Admin();
        }
        return admin;
    }


    private void frameRender() {
        MyFrame.getInstance().revalidate();
        MyFrame.getInstance().repaint();
    }

    private void addPanels(){
        ShopPanel shopPanel=ShopPanel.getInstance();
        StatusPanel statusPanel=StatusPanel.getInstance();
        CollectionPanel collectionPanel=CollectionPanel.getInstance();
        MyFrame.getPanel().add(collectionPanel,"collection");
        MyFrame.getPanel().add(shopPanel,"shop");
        MyFrame.getPanel().add(statusPanel,"status");
    }

    public void logIn(String username, String password) {
        String st = LogInSignUp.check(username, password);
        switch (st) {
            case "ok":
                if (Gamestate.getPlayer().getNewToGame()) {
                    FirstHeroSelector firstHeroSelector = new FirstHeroSelector();
                    MyFrame.getPanel().add(firstHeroSelector, "hero");
                    MyFrame.getInstance().changePanel("hero");
                } else {
                    SoundAdmin.clip.stop();
                    SoundAdmin.play("resources\\Sounds\\3.wav");
                    MyFrame.getInstance().changePanel("menu");
                    MenuPanel.getInstance().setFocusable(true);
                    MenuPanel.getInstance().grabFocus();
                    addPanels();
                }
                LoginPanel.getInstance().getUserField().setText("");
                LoginPanel.getInstance().getPassField().setText("");
                break;
            case "wrong password":
                LoginPanel.getInstance().getError().setText("Wrong Password");
                break;
            case "user not found":
                LoginPanel.getInstance().getError().setText("Username not Found.");
                break;
        }
        frameRender();
    }

    public void signUp(String username , String password){
        String st=LogInSignUp.create(username , password);
        switch (st){
            case "ok":
                LoginPanel.getInstance().getError().setText("Account Created.");
                break;
            case "user already exist":
                LoginPanel.getInstance().getError().setText("User already exists.");
                break;
        }
        frameRender();
    }

    public void selectFirstHero(String string){
        Update.updateFirstHero(string);
        playerFirstUpdate(string);
        playMusic("3");
        addPanels();
        MyFrame.getInstance().changePanel("menu");
    }
    private void playerFirstUpdate(String string){
        LOGGER.playerlog(Gamestate.getPlayer(), String.format("Select : %s as first selected hero", string.toUpperCase()));
        Gamestate.getPlayer().setNewToGame(false);
        ArrayList<Heroes> ar1 = Gamestate.getPlayer().getPlayerHeroes();
        if (Gamestate.getPlayer().getPlayerHeroes() == null) ar1 = new ArrayList<>();
        ar1.add(Heroes.valueOf(string));
        Gamestate.getPlayer().setPlayerHeroes(ar1);
        JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername(), Gamestate.getPlayer());
        }

    private void playMusic(String track){
        String st=String.format("resources\\Sounds\\%s.wav" , track);
        SoundAdmin.clip.stop();
        SoundAdmin.play(st);
    }

    public void setVisiblePanel(String panel){
        MyFrame.getInstance().changePanel(panel);
        frameRender();
    }

    public void logOut(){
        LOGGER.playerlog(Gamestate.getPlayer(),"Sign out ");
        JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername() , Gamestate.getPlayer());
        playMusic("2");
        setVisiblePanel("login");
    }
    public void exit(){
        JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername() , Gamestate.getPlayer());
        System.exit(0);
    }


}