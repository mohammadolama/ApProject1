package Controller;

import Controller.Actions.ActionHandler;
import Controller.Actions.ActionVisitor;
import Controller.Actions.BattlecryVisitor;
import Model.Cards.Card;
import Model.Cards.Minion;
import Model.Cards.Spell;
import Model.Cards.Weapon;
import Model.Enums.Attribute;
import Model.Enums.Carts;
import Model.Enums.Heroes;
import Model.Enums.Type;
import Model.CardModelView;
import Model.Interface.Character;
import View.Panels.*;
import View.Update.Update;
import Model.Heros.Hero;
import Main.*;
import View.Sounds.SoundAdmin;


import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static View.Panels.Constants.powerPics;

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

    public BufferedImage deckAnimationCard() {
        return pictureOf(friendlyHandCards().get(friendlyHandCards().size() - 1).getName().toLowerCase());
    }

    public Card getCardOf(String name) {
        return Card.getCardOf(name.toLowerCase());
    }

    public Card getCardOf(String name, ArrayList<Card> arrayList) {
        for (Card cards : arrayList) {
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

    public void frameRender() {
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
                    SoundAdmin.play1("resources/Sounds/menu.wav");
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
        String st = String.format("resources/Sounds/%s.wav", track);
        SoundAdmin.play1(st);
    }

    public void setVisiblePanel(String panel) {
        if (panel.equalsIgnoreCase("menu")) {
            gameManager = null;
        }
        MyFrame.getInstance().changePanel(panel);
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
        ArrayList<Card> cards;
        if (name.equalsIgnoreCase("all"))
            cards = Card.allCards();
        else if (name.equalsIgnoreCase("locked"))
            cards = Card.lockedCards();
        else if (name.equalsIgnoreCase("unlocked"))
            cards = Card.purchasedCards();
        else if (name.equalsIgnoreCase("neutral"))
            cards = Card.neutralCardsFilter();
        else if (name.equalsIgnoreCase("special")) {
            cards = Card.specialCardsFilter();
            CollectionDrawingPanel.getInstance().setSpecialSelected(true);
        } else {
            Deck selectedDeck = Deck.cloneDeck(Gamestate.getPlayer().getAllDecks().get(name));
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

    public long wallet() {
        return player().getMoney();
    }

    public ArrayList<Card> properCards(int i) {
        ArrayList<Card> ar;
        if (i == 1) {
            ar = Shop.Buyable();
        } else if (i == 2) {
            ar = Shop.Sellable();
        } else {
            ar = Card.allCards();
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

    private void createGameManager(InfoPassive infoPassive, String card1, String card2, String card3) {
        gameManager = new GameManager(Gamestate.getPlayer(), infoPassive, Deck.UpdateDeck(player().getSelectedDeck().getDeck()), card1, card2, card3);
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
        MyFrame.getPanel().add("play", boardPanel);
        setVisiblePanel("play");
    }

    public void createPlayBoard(InfoPassive infoPassive, String card1, String card2, String card3) {
        createGameManager(infoPassive, card1, card2, card3);
        boardPanel = new BoardPanel();
        boardPanel.setBounds(0, 0, 1600, 1000);
        MyFrame.getPanel().add(boardPanel, "play");
        setVisiblePanel("play");
    }


    public Player player() {
        return Gamestate.getPlayer();
    }
//    public Player gamePlayer(){
//        return gameManager.getFriendlyPlayer();
//    }
//
//    public ArrayList<Model.Cards> deckCards() {
//        return gameManager.getFriendlyDeckCards();
//    }
//
//    public ArrayList<Model.Cards> handCards() {
//        return gameManager.getFriendLyHandCards();
//    }
//
//    public ArrayList<Model.Cards> playedCards() {
//        return gameManager.getFriendlyPlayedCards();
//    }

    public Player friendlyPlayer() {
        return gameManager.getFriendlyPlayer();
    }

    public Player enemyPlayer() {
        return gameManager.getEnemyPlayer();
    }

    public ArrayList<Card> friendlyDeckCards() {
        return gameManager.getFriendlyDeckCards();
    }

    public ArrayList<Card> enemyDeckCards() {
        return gameManager.getEnemyDeckCards();
    }

    //
    public ArrayList<Card> friendlyHandCards() {
        return gameManager.getFriendLyHandCards();
    }

    public ArrayList<Card> enemyHandCards() {
        return gameManager.getEnemyHandCards();
    }


    //
    public ArrayList<Card> friendlyPlayedCards() {
        return gameManager.getFriendlyPlayedCards();
    }

    public ArrayList<Card> enemyPlayedCards() {
        return gameManager.getEnemyPlayedCards();
    }


    public void endTurn() {
//        gameManager.refillMana();
//        gameManager.nextCard();
        gameManager.endTurn();

        playSound("nextturn");
        updateGameLog(String.format("%s  EndTurn .", enemyPlayer().getUsername()));
    }

    public int friendlNotUsedmana() {
        return gameManager.getFriendlyNotUsedMana();
    }

    public int friendlyTotalMana() {
        return gameManager.getFriendlyTotalMana();
    }

    public int enemyTotalMana() {
        return gameManager.getEnemyTotalMana();
    }

    public Hero friendlyHero() {
        return gameManager.getFriendlyPlayerHero();
    }

    public Hero enemyHero() {
        return gameManager.getEnemyPlayerHero();
    }

    public String friendlyHeroName() {
        return gameManager.getFriendlyPlayerHero().getName().toLowerCase();
    }

    public String enemyHeroName() {
        return gameManager.getEnemyPlayerHero().getName().toLowerCase();
    }

    public BufferedImage pictureOf(String name) {
        return Constants.cardPics.get(name.toLowerCase());
    }

    public BufferedImage gamePictureOf(String name) {
        return Constants.gamePics.get(name.toLowerCase());
    }


    public ImageIcon gameIconOf(String name) {
        return Constants.gameIcon.get(name);
    }

    public boolean ManaChecker(String name) {
        int i = 0;
        while (i < friendlyHandCards().size()) {
            if (friendlyHandCards().get(i).getName().equalsIgnoreCase(name)) {
                if (friendlyHandCards().get(i).getManaCost() - gameManager.getFriendlyManaDecrease() > gameManager.getFriendlyNotUsedMana()) {
                    playSound("mana");
                    return false;
                }
            }
            i++;
        }
        return true;
    }


    private void playMinion(Minion minions, int i, Character target) {
        if (friendlyPlayedCards().size() < 7) {
            playSound("minion");
            gameManager.setFriendlyNotUsedMana(gameManager.getFriendlyNotUsedMana() - (minions.getManaCost() - gameManager.getFriendlyManaDecrease()));
            minions.accept(new ActionVisitor(), target, friendlyDeckCards(), friendlyHandCards(), friendlyPlayedCards(), enemyDeckCards(), enemyHandCards(), enemyPlayedCards(), friendlyHero(), enemyHero());
            if (minions.getAttributes() != null && (minions.getAttributes().contains(Attribute.Charge) || minions.getAttributes().contains(Attribute.Rush))) {
                minions.setSleep(false);
            }
            friendlyPlayedCards().add(i, minions);
            friendlyHandCards().remove(minions);
            updateGameLog(String.format("%s played", minions.getName().toLowerCase()));
        } else {
            playSound("error");
            frameRender();
//            JOptionPane.showMessageDialog(boardPanel, "you have already played 7 Model.Cards.");
        }
    }

    private void playSpell(Spell spell, Character target) {
        playSound("spell");
        gameManager.setFriendlyNotUsedMana(gameManager.getFriendlyNotUsedMana() - (spell.getManaCost() - gameManager.getFriendlyManaDecrease()));
        friendlyHandCards().remove(spell);
        spell.accept(new ActionVisitor(), target, friendlyDeckCards(), friendlyHandCards(), friendlyPlayedCards(), enemyDeckCards(), enemyHandCards(), enemyPlayedCards(), friendlyHero(), enemyHero());
        updateGameLog(String.format("%s played", spell.getName().toLowerCase()));
    }

    private void playWeapon(Weapon weapon) {
        playSound("weapon");
        gameManager.setFriendlyNotUsedMana(gameManager.getFriendlyNotUsedMana() - (weapon.getManaCost() - gameManager.getFriendlyManaDecrease()));
        friendlyHandCards().remove(weapon);
        gameManager.setFriendlyWeapon(weapon);
        updateGameLog(String.format("%s played", weapon.getName().toLowerCase()));
    }


    public Weapon friendlyWeapon() {
        return gameManager.getFriendlyWeapon();
    }

    public Weapon enemyWeapon() {
        return gameManager.getEnemyWeapon();
    }

    public void playHeroPower(Hero hero) {
        if (gameManager.getFriendlyHeroPowerUsageTime() > 0) {
            if (gameManager.getFriendlyNotUsedMana() >= hero.getHeroPower().getManaCost() - gameManager.getFriendlyPowerManaDecrease()) {
                playSound("heropower");
                gameManager.updateGameLog("HeroPower played");
                gameManager.setFriendlyHeroPowerUsageTime(gameManager.getFriendlyHeroPowerUsageTime() - 1);
                gameManager.setFriendlyNotUsedMana(gameManager.getFriendlyNotUsedMana() - (hero.getHeroPower().getManaCost() - gameManager.getFriendlyPowerManaDecrease()));
            } else {
                playSound("mana");
            }
        }
    }

    public CardModelView getWeaponViewModel(String string) {
        if (string.equalsIgnoreCase("friendly")) {
            Weapon weapon = friendlyWeapon();
            return new CardModelView(pictureOf(weapon.getName().toLowerCase()), weapon.getName(), weapon.getManaCost(), weapon.getDamage(), weapon.getDurability(), Type.Weapon, null, false, false, false, false);
        } else {
            Weapon weapon = enemyWeapon();
            return new CardModelView(pictureOf(weapon.getName().toLowerCase()), weapon.getName(), weapon.getManaCost(), weapon.getDamage(), weapon.getDurability(), Type.Weapon, null, false, false, false, false);
        }
    }

    public CardModelView getViewModelOf(String list, int index) {
        if (list.equalsIgnoreCase("friendly")) {
            Card cards = gameManager.getFriendlyPlayedCards().get(index);
            String string = cards.getName().toLowerCase();
            BufferedImage image = pictureOf(string);
            if (cards instanceof Minion) {
                Minion minions = (Minion) cards;
                CardModelView modelView = new CardModelView(image, string, minions.getManaCost(), minions.getDamage(), minions.getHealth(), minions.getType(), minions.getAttributes(), minions.isSleep(), minions.isCanBeAttacked(), minions.isNeedFriendlyTarget(), minions.isNeedEnemyTarget());
                return modelView;
            }
            return null;
        } else {
            Card cards = gameManager.getEnemyPlayedCards().get(index);
            String string = cards.getName().toLowerCase();
            BufferedImage image = pictureOf(string);
            if (cards instanceof Minion) {
                Minion minions = (Minion) cards;
                CardModelView modelView = new CardModelView(image, string, minions.getManaCost(), minions.getDamage(), minions.getHealth(), minions.getType(), minions.getAttributes(), minions.isSleep(), minions.isCanBeAttacked(), minions.isNeedFriendlyTarget(), minions.isNeedEnemyTarget());
                return modelView;
            }
            return null;
        }
    }

    public boolean canDoAction(int i) {
        Minion minion = (Minion) friendlyPlayedCards().get(i);
        return !minion.isSleep();
    }

    public ArrayList<String> bestDecks() {
        return Deck.bestDeck(player());
    }

    public Deck CloneDeck(String value) {
        return Deck.cloneDeck(player().getAllDecks().get(value));
    }

    public CardModelView getPureViewModelOf(String string) {
        Card cards = getCardOf(string.toLowerCase());
        BufferedImage image = pictureOf(string.toLowerCase());
        int mana = cards.getManaCost();
        if (gameManager != null) {
            mana -= gameManager.getFriendlyManaDecrease();
        }
        if (cards instanceof Minion) {
            Minion minions = (Minion) cards;
            CardModelView modelView = new CardModelView(image, string, mana, minions.getDamage(), minions.getHealth(), minions.getType(), minions.getAttributes(), minions.isSleep(), minions.isCanBeAttacked(), minions.isNeedFriendlyTarget(), minions.isNeedEnemyTarget());
            return modelView;
        } else if (cards instanceof Weapon) {
            Weapon weapon = (Weapon) cards;
            CardModelView modelView = new CardModelView(image, string, mana, weapon.getDamage(), weapon.getDurability(), weapon.getType(), null, false, false, false, false);
            return modelView;
        } else {
            Spell spell = (Spell) cards;
            CardModelView modelView = new CardModelView(image, string, mana, spell.getType(), spell.isNeedFriendlyTarget(), spell.isNeedEnemyTarget());
            return modelView;
        }

    }

    public void setSleep(int i) {
        ((Minion) friendlyPlayedCards().get(i)).setSleep(true);
    }

    public int friendlyHeroPowerusedTimes() {
        return gameManager.getFriendlyHeroPowerUsageTime();
    }

    public int enemyHeroPowerusedTimes() {
        return gameManager.getEnemyHeroPowerUsageTime();
    }


    private Character createTarget(int target) {
        if (target >= 10 && target < 20) {
            return friendlyPlayedCards().get(target - 10);
        } else if (target >= 20 && target < 30) {
            return enemyPlayedCards().get(target - 20);
        } else if (target == 1) {
            return friendlyHero();
        } else if (target == 2) {
            return enemyHero();
        } else {
            return null;
        }
    }


    public void playCard(String string, int i, int target) {

        Minion targeted = (Minion) createTarget(target);
        System.out.println(target + "\t" + targeted);

        for (Card cards : friendlyHandCards()) {
            if (cards.getName().equalsIgnoreCase(string)) {
                if (gameManager.getFriendlyNotUsedMana() >= cards.getManaCost() - gameManager.getFriendlyManaDecrease()) {
                    cards.accept(new BattlecryVisitor(), targeted, friendlyDeckCards(), friendlyHandCards(), friendlyPlayedCards(), enemyDeckCards(), enemyHandCards(), enemyPlayedCards(), friendlyHero(), enemyHero());
                    if (cards instanceof Minion) {
                        playMinion((Minion) cards, i, targeted);
                    } else if (cards instanceof Spell) {
                        playSpell((Spell) cards, targeted);
                    } else if (cards instanceof Weapon) {
                        playWeapon((Weapon) cards);
                    }
                    String log = String.format("Play : played card \"%s\"", cards.getName());
                    Log(log);
                    break;
                } else {
                    playSound("mana");
                }
            }
        }
        gameManager.checkDestroyMinion();
    }

    public void aylarAction(String weapon) {
        Weapon weapon1 = (Weapon) getCardOf(weapon);
        friendlyDeckCards().add(weapon1);
        setVisiblePanel("play");
    }


    public boolean canBePlayed(String string) {
        for (Card cards : friendlyHandCards()) {
            if (cards.getName().equalsIgnoreCase(string)) {
                if (gameManager.getFriendlyNotUsedMana() >= cards.getManaCost() - gameManager.getFriendlyManaDecrease()) {
                    if (getCardOf(string) instanceof Minion) {
                        if (friendlyPlayedCards().size() < 7) {
                            return true;
                        } else {
                            playSound("error");
                            return false;
                        }
                    } else if (getCardOf(string) instanceof Spell) {
                        return true;
                    } else if (getCardOf(string) instanceof Weapon) {
                        return true;
                    }
                    break;
                } else {
                    return false;
                }
            }
        }
        throw new RuntimeException();
    }


    public ArrayList<String> gameLog() {
        return gameManager.getGameLog();
    }

    public int friendlyPowerMana() {
        return (friendlyHero().getHeroPower().getManaCost() - gameManager.getFriendlyPowerManaDecrease());
    }

    public int enemyPowerMana() {
        return (enemyHero().getHeroPower().getManaCost() - gameManager.getEnemyPowerManaDecrease());
    }

    public void decreaseVolume() {
        SoundAdmin.decreaseSound();
    }

    public void increaseVolume() {
        SoundAdmin.increaseSound();
    }

    public int cardMana(Card cards) {
        return cards.getManaCost() - gameManager.getFriendlyManaDecrease();
    }

    public int defaultCardMana(Card cards) {
        return cards.getManaCost();
    }

    public int cardHp(Minion minions) {
        return minions.getHealth();
    }

    public int cardMAttack(Minion minions) {
        return minions.getDamage();
    }

    public int cardWAttack(Weapon weapon) {
        return weapon.getDamage();
    }

    public int cardDurability(Weapon weapon) {
        return weapon.getDurability();
    }

    public void playSound(String name) {
        new Thread(() -> SoundAdmin.playSound(name)).start();
    }

    public void Attack(int attacker, int target, BoardPanel panel) {
        ActionHandler actionHandler = new ActionHandler();
        if (attacker >= 0 && target >= 0) {
            Minion attacker1 = (Minion) friendlyPlayedCards().get(attacker);
            Minion target1 = (Minion) enemyPlayedCards().get(target);
            actionHandler.Attack(attacker1, target1, enemyPlayedCards());
            setSleep(attacker);
            panel.draw(attacker, target, attacker1.getAttack(), target1.getAttack());
        } else if (attacker >= 0 && target < 0) {
            Minion attacker1 = (Minion) friendlyPlayedCards().get(attacker);
            Hero target1 = enemyHero();
            actionHandler.Attack(attacker1, target1, enemyPlayedCards());
            setSleep(attacker);
            panel.draw(attacker, target, attacker1.getAttack(), target1.getAttack());
        } else if (attacker < 0 && target >= 0) {
            Hero attacker1 = friendlyHero();
            Minion target1 = (Minion) enemyPlayedCards().get(attacker);
            actionHandler.Attack(attacker1, target1, enemyPlayedCards());
        } else if (attacker < 0 && target < 0) {
            Hero attacker1 = friendlyHero();
            Hero target1 = enemyHero();
            actionHandler.Attack(attacker1, target1, enemyPlayedCards());
        }
        gameManager.checkDestroyMinion();
    }

    public void threeCardChoose(InfoPassive infoPassive) {
        ArrayList<Card> list = Deck.UpdateDeck(player().getSelectedDeck().getDeck());
        Collections.shuffle(list);
        ThreeCardChooseMenu th = new ThreeCardChooseMenu(false);
        th.setModel1(getPureViewModelOf(list.get(0).getName().toLowerCase()));
        th.setModel2(getPureViewModelOf(list.get(1).getName().toLowerCase()));
        th.setModel3(getPureViewModelOf(list.get(2).getName().toLowerCase()));
        th.setInfoPassive(infoPassive);
        th.setEnabled(true);
        MyFrame.getPanel().add("three", th);
        MyFrame.getInstance().changePanel("three");
    }

    public void changeCard(int i, ThreeCardChooseMenu th) {
        ArrayList<Card> list = Deck.UpdateDeck(player().getSelectedDeck().getDeck());
        Collections.shuffle(list);
        for (Card card : list) {
            if (i == 1) {
                if (!card.getName().equalsIgnoreCase(th.getModel2().getName()) && !card.getName().equalsIgnoreCase(th.getModel3().getName())) {
                    th.setModel1(getPureViewModelOf(card.getName().toLowerCase()));
                    break;
                }
            } else if (i == 2) {
                if (!card.getName().equalsIgnoreCase(th.getModel1().getName()) && !card.getName().equalsIgnoreCase(th.getModel3().getName())) {
                    th.setModel2(getPureViewModelOf(card.getName().toLowerCase()));
                    break;
                }
            } else if (i == 3) {
                if (!card.getName().equalsIgnoreCase(th.getModel1().getName()) && !card.getName().equalsIgnoreCase(th.getModel2().getName())) {
                    th.setModel3(getPureViewModelOf(card.getName().toLowerCase()));
                    break;
                }
            }
        }
    }

    public void discoverMode(String card1, String card2, String card3) {
        ThreeCardChooseMenu th = new ThreeCardChooseMenu(true);
        th.setModel1(getPureViewModelOf(card1.toLowerCase()));
        th.setModel2(getPureViewModelOf(card2.toLowerCase()));
        th.setModel3(getPureViewModelOf(card3.toLowerCase()));
        th.setEnabled(true);
        MyFrame.getPanel().add("three", th);
        MyFrame.getInstance().changePanel("three");
    }


    public void create(InfoPassive infoPassive, String card1, String card2, String card3) {
        createPlayBoard(infoPassive, card1, card2, card3);
    }

    public void listOfTargets(BoardPanel boardPanel) {
        ArrayList<Integer> targets = new ArrayList<>();
        int i = 0;
        for (Card card : enemyPlayedCards()) {
            if (((Minion) card).getAttributes().contains(Attribute.Taunt)) {
                targets.add(i);
            }
            i++;
        }
        if (targets.size() == 0) {
            for (int j = 0; j < enemyPlayedCards().size(); j++) {
                targets.add(j);
            }
            targets.add(-1);
        }
        boardPanel.drawTargets(targets);
    }
}


