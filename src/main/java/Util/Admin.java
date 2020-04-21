package Util;

import AllCards.Cards;
import Enums.Carts;
import Enums.Heroes;
import GUI.*;
import G_L_Interface.Update;
import Main.*;
import Sounds.SoundAdmin;
import com.sun.org.apache.xpath.internal.operations.Bool;


import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

public class Admin {
    private static Admin admin;
    private LoginPanel login;
    private MenuPanel menu;
    private ShopPanel shop;
    private CollectionPanel collection;
    private CollectionDrawingPanel collectionDrawing;
    private Col_Change col_change;
    private StatusPanel status;


    private Admin() {
//        login = LoginPanel.getInstance();
//        menu = MenuPanel.getInstance();
//        shop = ShopPanel.getInstance();
//        collection = CollectionPanel.getInstance();
//        collectionDrawing = CollectionDrawingPanel.getInstance();
//        col_change = Col_Change.getInstance();
//        status = StatusPanel.getInstance();
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

    private void addPanels() {
        ShopPanel shop = ShopPanel.getInstance();
        CollectionPanel collection = CollectionPanel.getInstance();
        Col_Change col_change = Col_Change.getInstance();
        StatusPanel status = StatusPanel.getInstance();

        MyFrame.getPanel().add(collection, "collection");
        MyFrame.getPanel().add(shop, "shop");
        MyFrame.getPanel().add(status, "status");
        MyFrame.getPanel().add(col_change, "col");
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

    public void signUp(String username, String password) {
        String st = LogInSignUp.create(username, password);
        switch (st) {
            case "ok":
                LoginPanel.getInstance().getError().setText("Account Created.");
                break;
            case "user already exist":
                LoginPanel.getInstance().getError().setText("User already exists.");
                break;
        }
        frameRender();
    }

    public void selectFirstHero(String string) {
        Update.updateFirstHero(string);
        playerFirstUpdate(string);
        playMusic("3");
        addPanels();
        MyFrame.getInstance().changePanel("menu");
    }

    private void playerFirstUpdate(String string) {
        LOGGER.playerlog(Gamestate.getPlayer(), String.format("Select : %s as first selected hero", string.toUpperCase()));
        Gamestate.getPlayer().setNewToGame(false);
        ArrayList<Heroes> ar1 = Gamestate.getPlayer().getPlayerHeroes();
        if (Gamestate.getPlayer().getPlayerHeroes() == null) ar1 = new ArrayList<>();
        ar1.add(Heroes.valueOf(string));
        Gamestate.getPlayer().setPlayerHeroes(ar1);
        JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername(), Gamestate.getPlayer());
    }

    private void playMusic(String track) {
        String st = String.format("resources\\Sounds\\%s.wav", track);
        SoundAdmin.clip.stop();
        SoundAdmin.play(st);
    }

    public void setVisiblePanel(String panel) {
        MyFrame.getInstance().changePanel(panel);
        frameRender();
    }

    public void createNewDeck() {
        if (Gamestate.getPlayer().getAllDecks().size() < 10)
            admin.setVisiblePanel("col");
        else {
            CollectionPanel.getInstance().getErrorLabel().setText("Can not create more than 10 decks");
        }
    }

    public void updateDrawingPanel(String name) {
        CollectionDrawingPanel.getInstance().setSpecialSelected(false);
        CollectionPanel.getInstance().getChangeButton().setEnabled(false);
        ArrayList<Cards> cards=new ArrayList<>();
        if (name.equalsIgnoreCase("all"))
            cards = Cards.allCards();
        else if (name.equalsIgnoreCase("locked"))
            cards = Cards.lockedCards();
        else if (name.equalsIgnoreCase("unlocked"))
            cards = Cards.purchasedCards();
        else if (name.equalsIgnoreCase("neutral"))
            cards = Cards.neutralCardsFilter();
        else if (name.equalsIgnoreCase("special"))
            Cards.specialCardsFilter();
        else {
            Deck selectedDeck = Deck.changeSelectedDeck(Gamestate.getPlayer().getAllDecks().get(name));
            cards = Deck.UpdateDeck(selectedDeck.getDeck());
        }
        CollectionDrawingPanel.getInstance().updateContent(cards);
    }

    public void saveAndUpdate() {
        Update.saveAndUpdate();
    }

    public void buyCard(String name) {
        Shop.Buy(name.toLowerCase());
        saveAndUpdate();
    }

    public void sellCard(String name) {
        Shop.Sell(name.toLowerCase());
        saveAndUpdate();
    }

    public ArrayList<Cards> properCards(int i) {
        ArrayList<Cards> ar;
        if (i == 1) {
            ar = Shop.Buyable();
        } else if (i == 2) {
            ar = Shop.Sellable();
        } else {
            ar = Cards.allCards();
        }
        return ar;
    }

    public long price(String name) {
        return Shop.Price((name.toLowerCase()));
    }

    public boolean canBeSold(String name) {
        return Shop.CanBeSold(name.toLowerCase());
    }

    public void logOut() {
        LOGGER.playerlog(Gamestate.getPlayer(), "Sign out ");
        JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername(), Gamestate.getPlayer());
        playMusic("2");
        setVisiblePanel("login");
    }

    public void exit() {
        JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername(), Gamestate.getPlayer());
        System.exit(0);
    }

    public void setSelectedDeck(Deck deck){
        System.out.println(Gamestate.getPlayer().getSelectedDeck());
        Gamestate.getPlayer().setSelectedDeck(deck);
        System.out.println(Gamestate.getPlayer().getSelectedDeck());
        saveAndUpdate();
    }

    public void removeDeck(Deck selectedDeck){
        Gamestate.getPlayer().getAllDecks().remove(selectedDeck.getName());
        if (selectedDeck.getName().equalsIgnoreCase(Gamestate.getPlayer().getSelectedDeck().getName())){
            Gamestate.getPlayer().setSelectedDeck(null);
            CollectionPanel.getInstance().setSelectedDeck(null);
        }
        saveAndUpdate();
        setVisiblePanel("collection");
    }


}