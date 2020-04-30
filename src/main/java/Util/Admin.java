package Util;

import AllCards.Cards;
import AllCards.Minions;
import AllCards.Spell;
import AllCards.Weapon;
import Enums.Carts;
import Enums.Heroes;
import GUI.Panels.*;
import G_L_Interface.Update;
import Heros.Hero;
import Main.*;
import Sounds.SoundAdmin;


import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static GUI.Panels.Constants.powerPics;

public class Admin {
    private static Admin admin;
    private static GameManager gameManager;
    private BoardPanel boardPanel;

    private LogInSignUp logInSignUp;

    private Admin() {
        logInSignUp = new LogInSignUp();
    }

    public static Admin getInstance() {
        if (admin == null) {
            admin = new Admin();
        }
        return admin;
    }


    public Cards getCardOf(String name) {
        return Cards.getCardOf(name.toLowerCase());
    }

    public Cards getCardOf(String name, ArrayList<Cards> arrayList) {
        for (Cards cards : arrayList) {
            if (cards.getName().equalsIgnoreCase(name)) {
                return cards;
            }
        }
        return null;
    }

    public void levelUp() {
        player().setLevel(player().getLevel() + 1);
        frameRender();
    }

    public void unlockHero() {
        Hero.HeroAdder(player());
        frameRender();
        Col_Change.getInstance().update();
    }

    private void frameRender() {
        MyFrame.getInstance().revalidate();
        MyFrame.getInstance().repaint();

    }

    public void Log(String log) {
        LOGGER.playerlog(player(), log);
    }

    private void addPanels() {
        new Thread(() -> {
            ShopPanel shop = ShopPanel.getInstance();
            CollectionPanel collection = CollectionPanel.getInstance();
            MyFrame.getPanel().add(collection, "collection");
            MyFrame.getPanel().add(shop, "shop");
        }).start();
        new Thread(() -> {
            Col_Change col_change = Col_Change.getInstance();
            StatusPanel status = StatusPanel.getInstance();
            SettingPanel settingPanel = SettingPanel.getInstance();
            MyFrame.getPanel().add(settingPanel, "setting");
            MyFrame.getPanel().add(status, "status");
            MyFrame.getPanel().add(col_change, "col");
        }).start();

    }

    public void deleteAccount() {
        int i = JOptionPane.showConfirmDialog(MyFrame.getInstance(), "You are about to delete your account.\n Are you sure?", "Delete Account", JOptionPane.YES_NO_OPTION);
        if (i == 0) {
            logInSignUp.DeleteAccount(player());
            playMusic("login");
            setVisiblePanel("login");
        }
    }

    public void logIn(String username, String password) {
        String st = logInSignUp.check(username, password);
        switch (st) {
            case "ok":
                if (Gamestate.getPlayer().getNewToGame()) {
                    frameRender();
                    FirstHeroSelector firstHeroSelector = new FirstHeroSelector();
                    MyFrame.getPanel().add(firstHeroSelector, "hero");
                    MyFrame.getInstance().changePanel("hero");
                } else {
                    new Thread(this::addPanels).start();
                    frameRender();
                    SoundAdmin.clip.stop();
                    SoundAdmin.play1("resources\\Sounds\\menu.wav");
                    playSound("welcome");
                    MyFrame.getInstance().changePanel("menu");
                    MenuPanel.getInstance().setFocusable(true);
                    MenuPanel.getInstance().grabFocus();
                }
                LoginPanel.getInstance().getUserField().setText("");
                LoginPanel.getInstance().getPassField().setText("");
                LoginPanel.getInstance().getError().setText("");
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
        String st = logInSignUp.create(username, password);
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
        playMusic("menu");
        addPanels();
        playSound("welcome");
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
        SoundAdmin.play1(st);
    }

    public void setVisiblePanel(String panel) {
        MyFrame.getInstance().changePanel(panel);
        frameRender();
    }

    public void createNewDeck() {
        if (Gamestate.getPlayer().getAllDecks().size() < 12) {
            Col_Change.getInstance().setCreateMode(true);
            admin.setVisiblePanel("col");
        } else {
            JOptionPane.showMessageDialog(CollectionPanel.getInstance(), "Can not create more than 12 decks");
        }
    }

    public void updateDrawingPanel(String name) {
        CollectionDrawingPanel.getInstance().setSpecialSelected(false);
        CollectionPanel.getInstance().getChangeButton().setEnabled(false);
        ArrayList<Cards> cards;
        if (name.equalsIgnoreCase("all"))
            cards = Cards.allCards();
        else if (name.equalsIgnoreCase("locked"))
            cards = Cards.lockedCards();
        else if (name.equalsIgnoreCase("unlocked"))
            cards = Cards.purchasedCards();
        else if (name.equalsIgnoreCase("neutral"))
            cards = Cards.neutralCardsFilter();
        else if (name.equalsIgnoreCase("special")) {
            cards = Cards.specialCardsFilter();
            CollectionDrawingPanel.getInstance().setSpecialSelected(true);
        } else {
            Deck selectedDeck = Deck.changeSelectedDeck(Gamestate.getPlayer().getAllDecks().get(name));
            cards = Deck.UpdateDeck(selectedDeck.getDeck());
        }
        CollectionDrawingPanel.getInstance().updateContent(cards);
    }

    public BufferedImage powerPicuterOf(String name) {
        return powerPics.get(name.toLowerCase());
    }

    public void saveAndUpdate() {
        Update.saveAndUpdate();
    }

    private void updateGameLog(String log) {
        gameManager.updateGameLog(log);
    }


    public void buyCard(String name) {
        Shop.Buy(name.toLowerCase());
        playSound("buy");
        Log(String.format("Buy : %s  is added to purchased cards .", name));
        saveAndUpdate();
    }

    public void sellCard(String name) {
        Shop.Sell(name.toLowerCase());
        playSound("sell");
        Log(String.format("Sell : %s  is removed from  purchased cards .", name));
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
        Log("Sign out ");
        JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername(), Gamestate.getPlayer());
        playMusic("login");
        setVisiblePanel("login");
    }

    public void exit() {
        Log("Sign out ");
        JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername(), Gamestate.getPlayer());
        System.exit(0);
    }

    public void setSelectedDeck(Deck deck) {
        Gamestate.getPlayer().setSelectedDeck(deck);
        saveAndUpdate();
    }

    public void removeDeck(Deck selectedDeck) {
        Gamestate.getPlayer().getAllDecks().remove(selectedDeck.getName());
        if (selectedDeck.getName().equalsIgnoreCase(Gamestate.getPlayer().getSelectedDeck().getName())) {
            Gamestate.getPlayer().setSelectedDeck(null);
            CollectionPanel.getInstance().setSelectedDeck(null);
        }
        Log(String.format("Delete : deck %s is deleted.", selectedDeck.getName()));
        saveAndUpdate();
        setVisiblePanel("collection");
    }

    public ArrayList<InfoPassive> generatePassive() {

        return InfoPassive.randomGenerate(InfoPassive.infoPassiveCreator());
    }

    private void showError(String error) {
        if (error.equalsIgnoreCase("emptyDeck")) {
            JOptionPane.showMessageDialog(MenuPanel.getInstance(), "You must choose a deck, first");
        } else if (error.equalsIgnoreCase("notEnoughCard.Please go to Collection.")) {
            JOptionPane.showMessageDialog(MenuPanel.getInstance(), "notEnoughCard.Please go to Collection.");
        }
    }

    private boolean checkNecessary() {
        if (Gamestate.getPlayer().getSelectedDeck() == null) {
            showError("emptyDeck");
            return false;
        }
        if (Gamestate.getPlayer().getSelectedDeck().getDeck().size() < 15 || Gamestate.getPlayer().getSelectedDeck().getDeck().size() > 30) {
            showError("notEnoughCard.Please go to Collection.");
            return false;
        }

        return true;
    }

    public void enterGame() {
        if (checkNecessary()) {
            InfoPassivePanel infoPassivePanel = new InfoPassivePanel();

            MyFrame.getPanel().add(infoPassivePanel, "info");
            setVisiblePanel("info");
        }
    }

    private void createGameManager(InfoPassive infoPassive) {
        gameManager = new GameManager(Gamestate.getPlayer(), infoPassive, Deck.UpdateDeck(player().getSelectedDeck().getDeck()));
    }


    public void createDeck(String name, ArrayList<Carts> selectedCards, String heroName) {
        Deck deck = new Deck(0, 0, name);
        deck.setDeck(selectedCards);
        deck.setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), heroName));
        deck.setUsedTimes(Deck.resetUsedTimes(selectedCards, deck));
        deck.setMostUsedCard(Deck.mostUsedCard(deck));
        Gamestate.getPlayer().getAllDecks().put(deck.getName(), deck);
        Admin.getInstance().Log(String.format("Create : deck %s is created.", deck.getName()));
        JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername(), Gamestate.getPlayer());
    }

    public void changeDeck(Deck selectedDeck, ArrayList<Carts> selectedCards, String heroName) {
        Deck deck = selectedDeck;
        deck.setDeck(selectedCards);
        deck.setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), heroName));
        deck.setUsedTimes(Deck.resetUsedTimes(selectedCards, deck));
        deck.setMostUsedCard(Deck.mostUsedCard(deck));
        Gamestate.getPlayer().getAllDecks().replace(selectedDeck.getName(), selectedDeck, deck);
        Admin.getInstance().Log(String.format("Change : deck %s has been changed.", selectedDeck.getName()));
        JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername(), Gamestate.getPlayer());
    }

    public void createPlayBoard(InfoPassive infoPassive) {
        createGameManager(infoPassive);
        boardPanel = new BoardPanel();
        boardPanel.setBounds(0, 0, 1600, 1000);
        MyFrame.getPanel().add(boardPanel, "play");
        setVisiblePanel("play");
    }

    public Player player() {
        return Gamestate.getPlayer();
    }

    public ArrayList<Cards> deckCards() {
        return gameManager.getDeckCards();
    }

    public ArrayList<Cards> handCards() {
        return gameManager.getHandCards();
    }

    public ArrayList<Cards> playedCards() {
        return gameManager.getPlayedCards();
    }

    public ArrayList<Cards> cardsOfPlayer() {
        return gameManager.getCardsofPlayer();
    }

    public void endTurn() {
        gameManager.refillMana();
        gameManager.nextCard();
        playSound("nextturn");
        updateGameLog(String.format("%s  EndTurn .", player().getUsername()));
    }

    public int notUsedmana() {
        return gameManager.getNotUsedMana();
    }

    public int totalMana() {
        return gameManager.getTotalMana();
    }

    public Hero playerHero() {
        return gameManager.getPlayerHero();
    }

    public String playerHeroName() {
        return player().getSelectedDeck().getHero().getName().toLowerCase();
    }

    public BufferedImage pictureOf(String name) {
        return Constants.cardPics.get(name.toLowerCase());
    }

    public ImageIcon gifOf(String name) {
        return Constants.heroGifs.get(name.toLowerCase());
    }

    public boolean cardCanbePlayed(String name) {
        int i = 0;
        while (i < admin.handCards().size()) {
            if (handCards().get(i).getName().equalsIgnoreCase(name)) {
                if (handCards().get(i).getManaCost() - gameManager.getManaDecrease() > gameManager.getNotUsedMana()) {
                    playSound("mana");
                    return false;
                }
            }
            i++;
        }
        return true;
    }


    private void playMinion(Minions minions) {
        if (playedCards().size() < 7) {
            playSound("minion");
            gameManager.setNotUsedMana(gameManager.getNotUsedMana() - (minions.getManaCost() - gameManager.getManaDecrease()));
            playedCards().add(minions);
            handCards().remove(minions);
        } else {
            playSound("error");
            JOptionPane.showMessageDialog(boardPanel, "you have already played 7 Cards.");
        }
    }

    private void playSpell(Spell spell) {
        playSound("spell");
        gameManager.setNotUsedMana(gameManager.getNotUsedMana() - (spell.getManaCost() - gameManager.getManaDecrease()));
        handCards().remove(spell);
    }

    private void playWeapon(Weapon weapon) {
        playSound("weapon");
        gameManager.setNotUsedMana(gameManager.getNotUsedMana() - (weapon.getManaCost() - gameManager.getManaDecrease()));
        handCards().remove(weapon);
        gameManager.setWeapon(weapon);
    }

    public Weapon playerWeapon() {
        return gameManager.getWeapon();
    }

    public void playHeroPower(Hero hero) {
        if (gameManager.getHeroPowerUsageTime() > 0) {
            if (gameManager.getNotUsedMana() >= hero.getHeroPower().getManaCost() - gameManager.getHeroPowerManaDecrease()) {
                playSound("heropower");
                gameManager.updateGameLog("HeroPower played");
                gameManager.setHeroPowerUsageTime(gameManager.getHeroPowerUsageTime() - 1);
                gameManager.setNotUsedMana(gameManager.getNotUsedMana() - (hero.getHeroPower().getManaCost() - gameManager.getHeroPowerManaDecrease()));
            } else {
                playSound("mana");
            }
        }
    }

    public int heroPowerusedTimes() {
        return gameManager.getHeroPowerUsageTime();
    }


    public void playCard(String string) {
        for (Cards cards : handCards()) {
            if (cards.getName().equalsIgnoreCase(string)) {
                System.out.println("not mana");
                if (gameManager.getNotUsedMana() >= cards.getManaCost() - gameManager.getManaDecrease()) {
                    if (getCardOf(string) instanceof Minions) {
                        playMinion((Minions) cards);
                    } else if (getCardOf(string) instanceof Spell) {
                        playSpell((Spell) cards);
                    } else if (getCardOf(string) instanceof Weapon) {
                        playWeapon((Weapon) cards);
                    }
                    updateGameLog(String.format("%s played", cards.getName().toLowerCase()));
                    String log = String.format("Play : played card \"%s\"", cards.getName());
                    Log(log);
                    break;
                } else {
                    System.out.println("not mana");
                    playSound("mana");
                }
            }
        }
    }

    public ArrayList<String> gameLog() {
        return gameManager.getGameLog();
    }

    public int playerPowerMana() {
        return (playerHero().getHeroPower().getManaCost() - gameManager.getHeroPowerManaDecrease());
    }

    public void decreaseVolume() {
        SoundAdmin.decreaseSound();
    }

    public void increaseVolume() {
        SoundAdmin.increaseSound();
    }

    public int cardMana(Cards cards) {
        return cards.getManaCost() - gameManager.getManaDecrease();
    }

    public int defaultCardMana(Cards cards) {
        return cards.getManaCost();
    }

    public int cardHp(Minions minions) {
        return minions.getHealth();
    }

    public int cardMAttack(Minions minions) {
        return minions.getAttack();
    }

    public int cardWAttack(Weapon weapon) {
        return weapon.getAtt();
    }

    public int cardDurability(Weapon weapon) {
        return weapon.getDurability();
    }

    public void playSound(String name) {
        new Thread(() -> SoundAdmin.playSound(name)).start();
    }

}