package Server.Controller.MainLogic;

import Client.View.View.Panels.*;
import Client.View.View.Sounds.SoundAdmin;
import Client.View.View.Update.Update;
import Server.Controller.Actions.ActionHandler;
import Server.Controller.Manager.DeckReaderManager;
import Server.Controller.Manager.Managers;
import Server.Controller.Manager.NormalModeManager;
import Server.Controller.Manager.PracticeManager;
import Server.Model.Heros.*;
import Server.Model.*;
import Server.Model.Cards.*;
import Server.Model.Enums.*;
import Server.Model.Interface.Character;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Admin {
    private static Admin admin;
    private static Managers gameManager;
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

    void levelUp() {
        player().setLevel(player().getLevel() + 1);
        frameRender();
    }

    void unlockHero() {
        Hero.HeroAdder(player());
        frameRender();
        Col_Change.getInstance().update();
    }

    public void frameRender() {
        MyFrame.getInstance().revalidate();
        MyFrame.getInstance().repaint();
    }

    public void Log(String log) {
//        LOGGER.playerlog(player(), log);
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


    public String signUp(String username, String password) {
        return logInSignUp.create(username, password);
    }


    public String logIn(String username, String password, ClientHandler clientHandler) {
        return logInSignUp.check(username, password, clientHandler);
//        switch (st) {
//            case "ok":
//                if (clientHandler.getPlayer().getNewToGame()) {
//                    frameRender();
//                    FirstHeroSelector firstHeroSelector = new FirstHeroSelector();
//                    MyFrame.getPanel().add(firstHeroSelector, "hero");
//                    MyFrame.getInstance().changePanel("hero");
//                } else {
//                    new Thread(this::addPanels).start();
//                    frameRender();
//                    SoundAdmin.clip.stop();
//                    SoundAdmin.play1("resources/Sounds/menu.wav");
//                    playSound("welcome");
//                    MyFrame.getInstance().changePanel("menu");
//                    MenuPanel.getInstance().setFocusable(true);
//                    MenuPanel.getInstance().grabFocus();
//                }
//                LoginPanel.getInstance().getUserField().setText("");
//                LoginPanel.getInstance().getPassField().setText("");
//                LoginPanel.getInstance().getError().setText("");
//                break;
//            case "wrong password":
//                LoginPanel.getInstance().getError().setText("Wrong Password");
//                break;
//            case "user not found":
//                LoginPanel.getInstance().getError().setText("Username not Found.");
//                break;
//        }
    }


    void deleteAccount() {
        int i = JOptionPane.showConfirmDialog(MyFrame.getInstance(), "You are about to delete your account.\n Are you sure?", "Delete Account", JOptionPane.YES_NO_OPTION);
        if (i == 0) {
            logInSignUp.DeleteAccount(player());
            playMusic("login");
            setVisiblePanel("login");
        }
    }

    void logOut(Player player) {
        Log("Sign out ");
        saveAndUpdate(player);
        playMusic("login");
        setVisiblePanel("login");
    }

    public void exit(Player player) {
        Log("Sign out ");
        saveAndUpdate(player);
        System.exit(0);
    }

    public void selectFirstHero(String string, Player player) {
        updateFirstHero(string, player);
        playerFirstUpdate(string, player);
        playMusic("menu");
        addPanels();
        playSound("welcome");
        MyFrame.getInstance().changePanel("menu");
    }

    private void playerFirstUpdate(String string, Player player) {
//        LOGGER.playerlog(player, String.format("Select : %s as first selected hero", string.toUpperCase()));
        player.setNewToGame(false);
        List<Heroes> ar1 = player.getPlayerHeroes();
        if (player.getPlayerHeroes() == null) ar1 = new ArrayList<>();
        ar1.add(Heroes.valueOf(string));
        player.setPlayerHeroes(ar1);
        JsonBuilders.PlayerJsonBuilder(player.getUsername(), player);
    }

    private void playMusic(String track) {
        String st = String.format("resources/Sounds/%s.wav", track);
        SoundAdmin.play1(st);
    }

    void setVisiblePanel(String panel) {
        if (panel.equalsIgnoreCase("menu")) {
            gameManager = null;
        }
        MyFrame.getInstance().changePanel(panel);
    }

    void createNewDeck(Player player) {
        if (player.getAllDecks().size() < 12) {
            Col_Change.getInstance().setCreateMode(true);
            admin.setVisiblePanel("col");
        } else {
            JOptionPane.showMessageDialog(CollectionPanel.getInstance(), "Can not create more than 12 decks");
        }
    }

    void updateDrawingPanel(String name, Player player) {
//        CollectionDrawingPanel.getInstance().setSpecialSelected(false);
//        CollectionPanel.getInstance().getChangeButton().setEnabled(false);
//        ArrayList<Card> cards;
//        if (name.equalsIgnoreCase("all"))
//            cards = Card.allCards();
//        else if (name.equalsIgnoreCase("locked"))
//            cards = Card.lockedCards(player);
//        else if (name.equalsIgnoreCase("unlocked"))
//            cards = Card.purchasedCards(player);
//        else if (name.equalsIgnoreCase("neutral"))
//            cards = Card.neutralCardsFilter();
//        else if (name.equalsIgnoreCase("special")) {
//            cards = Card.specialCardsFilter();
//            CollectionDrawingPanel.getInstance().setSpecialSelected(true);
//        } else {
//            Deck selectedDeck = Deck.cloneDeck(player.getAllDecks().get(name));
//            cards = DeckLogic.UpdateDeck(selectedDeck.getDeck());
//        }
//        CollectionDrawingPanel.getInstance().updateContent(cards);
    }

    void saveAndUpdate(Player player) {
        JsonBuilders.PlayerJsonBuilder(player.getUsername(), player);
    }

    public static void updateFirstHero(String hero, Player player) {
        switch (hero) {
            case "mage":
                List<Carts> ar = player.getPlayerCarts();
                ar.add(Carts.polymorph);
                player.setPlayerCarts(ar);
                ar = player.getSelectedDeck().getDeck();
                ar.add(Carts.polymorph);
                System.out.println(player);
                System.out.println(player.getAllDecks().toString());
                Map map = player.getAllDecks();
                for (Object o : map.entrySet()) {
                    System.out.println(ThreadColor.ANSI_BLUE + o.toString() + ThreadColor.ANSI_RESET);
                }
                System.out.println(player.getAllDecks().get("Default Deck").toString());
                System.out.println(player.getAllDecks().get("Default Deck").getUsedTimes().toString());
                player.getAllDecks().get("Default Deck").getUsedTimes().put("polymorph", 0);
                player.getSelectedDeck().getUsedTimes().put("polymorph", 0);
                player.getSelectedDeck().setDeck(ar);
                player.getSelectedDeck().setMostUsedCard(player.getSelectedDeck().mostUsedCard());
                player.getSelectedDeck().setHero(JsonReaders.HeroJsonReader(player, "mage"));
                player.getAllDecks().replace(player.getSelectedDeck().getName(), player.getSelectedDeck());
                break;
            case "rogue":
                List<Carts> ar1 = player.getPlayerCarts();
                ar1.add(Carts.aylar);
                player.setPlayerCarts(ar1);
                ar = player.getSelectedDeck().getDeck();
                ar.add(Carts.aylar);
                player.getAllDecks().get("Default Deck").getUsedTimes().put("aylar", 0);
                player.getSelectedDeck().getUsedTimes().put("aylar", 0);
                player.getSelectedDeck().setDeck(ar);
                player.getSelectedDeck().setMostUsedCard(player.getSelectedDeck().mostUsedCard());
                player.getAllDecks().replace(player.getSelectedDeck().getName(), player.getSelectedDeck());
                player.getSelectedDeck().setHero(JsonReaders.HeroJsonReader(player, "rogue"));
                break;
            case "warlock":
                List<Carts> ar2 = player.getPlayerCarts();
                ar2.add(Carts.benyamin);
                player.setPlayerCarts(ar2);
                ar = player.getSelectedDeck().getDeck();
                ar.add(Carts.benyamin);
                player.getAllDecks().get("Default Deck").getUsedTimes().put("benyamin", 0);
                player.getSelectedDeck().getUsedTimes().put("benyamin", 0);
                player.getSelectedDeck().setDeck(ar);
                player.getSelectedDeck().setMostUsedCard(player.getSelectedDeck().mostUsedCard());
                player.getAllDecks().replace(player.getSelectedDeck().getName(), player.getSelectedDeck());
                player.getSelectedDeck().setHero(JsonReaders.HeroJsonReader(player, "warlock"));
                break;
            case "priest":
                List<Carts> ar3 = player.getPlayerCarts();
                ar3.add(Carts.shahryar);
                player.setPlayerCarts(ar3);
                ar = player.getSelectedDeck().getDeck();
                ar.add(Carts.shahryar);
                player.getAllDecks().get("Default Deck").getUsedTimes().put("shahryar", 0);
                player.getSelectedDeck().getUsedTimes().put("shahryar", 0);
                player.getSelectedDeck().setDeck(ar);
                player.getSelectedDeck().setMostUsedCard(player.getSelectedDeck().mostUsedCard());
                player.getAllDecks().replace(player.getSelectedDeck().getName(), player.getSelectedDeck());
                player.getSelectedDeck().setHero(JsonReaders.HeroJsonReader(player, "priest"));
                break;
            case "hunter":
                List<Carts> ar4 = player.getPlayerCarts();
                ar4.add(Carts.faeze);
                player.setPlayerCarts(ar4);
                ar = player.getSelectedDeck().getDeck();
                ar.add(Carts.faeze);
                player.getAllDecks().get("Default Deck").getUsedTimes().put("faeze", 0);
                player.getSelectedDeck().getUsedTimes().put("faeze", 0);
                player.getSelectedDeck().setDeck(ar);
                player.getSelectedDeck().setMostUsedCard(player.getSelectedDeck().mostUsedCard());
                player.getAllDecks().replace(player.getSelectedDeck().getName(), player.getSelectedDeck());
                player.getSelectedDeck().setHero(JsonReaders.HeroJsonReader(player, "hunter"));
                break;
        }
        player.getSelectedDeck().setMostUsedCard(player.getSelectedDeck().mostUsedCard());
        player.getAllDecks().get(player.getSelectedDeck().getName()).setMostUsedCard(player.getAllDecks().get(player.getSelectedDeck().getName()).mostUsedCard());
    }


    public void updateGameLog(String log) {
        gameManager.updateGameLog(log);
        Log(log);
    }

    void buyCard(String name, Player player) {
        Shop.Buy(name.toLowerCase(), player);
        playSound("buy");
        Log(String.format("Buy : %s  is added to purchased cards .", name));
        saveAndUpdate(player);
    }

    void sellCard(String name, Player player) {
        Shop.Sell(name.toLowerCase(), player);
        playSound("sell");
        Log(String.format("Sell : %s  is removed from  purchased cards .", name));
        saveAndUpdate(player);
    }

    long wallet() {
        return player().getMoney();
    }

    ArrayList<Card> properCards(int i, Player player) {
        ArrayList<Card> ar;
        if (i == 1) {
            ar = Shop.Buyable(player);
        } else if (i == 2) {
            ar = Shop.Sellable(player);
        } else {
            ar = Card.allCards();
        }
        return ar;
    }

    long price(String name) {
        return Shop.Price((name.toLowerCase()));
    }

    public boolean canBeSold(String name, Player player) {
        return Shop.CanBeSold(name.toLowerCase(), player);
    }

    public void createDeck(String name, ArrayList<Carts> selectedCards, String heroName, Player player) {
        Deck deck = new Deck(0, 0, name);
        deck.setDeck(selectedCards);
        deck.setHero(JsonReaders.HeroJsonReader(player, heroName));
        deck.setUsedTimes(Deck.resetUsedTimes(selectedCards, deck));
        deck.setMostUsedCard(deck.mostUsedCard());
        player.getAllDecks().put(deck.getName(), deck);
        Admin.getInstance().Log(String.format("Create : deck %s is created.", deck.getName()));
        JsonBuilders.PlayerJsonBuilder(player.getUsername(), player);
    }

    public void changeDeck(Deck selectedDeck, ArrayList<Carts> selectedCards, String heroName, Player player) {
        selectedDeck.setDeck(selectedCards);
        selectedDeck.setHero(JsonReaders.HeroJsonReader(player, heroName));
        selectedDeck.setUsedTimes(Deck.resetUsedTimes(selectedCards, selectedDeck));
        selectedDeck.setMostUsedCard(selectedDeck.mostUsedCard());
        player.getAllDecks().replace(selectedDeck.getName(), selectedDeck, selectedDeck);
        Admin.getInstance().Log(String.format("Change : deck %s has been changed.", selectedDeck.getName()));
        JsonBuilders.PlayerJsonBuilder(player.getUsername(), player);
    }

    public void setSelectedDeck(Deck deck, Player player) {
        player.setSelectedDeck(deck);
        saveAndUpdate(player);
    }

    public void removeDeck(Deck selectedDeck, Player player) {
        player.getAllDecks().remove(selectedDeck.getName());
        if (selectedDeck.getName().equalsIgnoreCase(player.getSelectedDeck().getName())) {
            player.setSelectedDeck(null);
            CollectionPanel.getInstance().setSelectedDeck(null);
        }
        Log(String.format("Delete : deck %s is deleted.", selectedDeck.getName()));
        saveAndUpdate(player);
        setVisiblePanel("collection");
    }

    ArrayList<String> bestDecks() {
        return DeckLogic.bestDeck(player());
    }

    Deck CloneDeck(String value) {
        return Deck.cloneDeck(player().getAllDecks().get(value));
    }

    ArrayList<InfoPassive> generatePassive() {
        return InfoPassive.randomGenerate(InfoPassive.infoPassiveCreator());
    }

    private void showError(String error) {
        if (error.equalsIgnoreCase("emptyDeck")) {
            JOptionPane.showMessageDialog(MenuPanel.getInstance(), "You must choose a deck, first");
        } else if (error.equalsIgnoreCase("notEnoughCard.Please go to Collection.")) {
            JOptionPane.showMessageDialog(MenuPanel.getInstance(), "notEnoughCard.Please go to Collection.");
        }
    }

    private boolean checkNecessary(Player player) {
        if (player.getSelectedDeck() == null) {
            showError("emptyDeck");
            return false;
        }
        if (player.getSelectedDeck().getDeck().size() < 15 || player.getSelectedDeck().getDeck().size() > 30) {
            showError("notEnoughCard.Please go to Collection.");
            return false;
        }
        return true;
    }

    public void enterGame(Player player) {
        if (checkNecessary(player)) {
            InfoPassivePanel infoPassivePanel = new InfoPassivePanel();
            MyFrame.getPanel().add(infoPassivePanel, "info");
            setVisiblePanel("info");
        }
    }

    public void create(InfoPassive infoPassive, String card1, String card2, String card3, Player player) {
        createPlayBoard(infoPassive, card1, card2, card3, player);
    }

    private void createGameManager(InfoPassive infoPassive, String card1, String card2, String card3, Player player) {
        gameManager = new NormalModeManager(player, infoPassive, DeckLogic.UpdateDeck(player().getSelectedDeck().getDeck()), card1, card2, card3);
    }

    private void createPlayBoard(InfoPassive infoPassive, String card1, String card2, String card3, Player player) {
        createGameManager(infoPassive, card1, card2, card3, player);
        boardPanel = new BoardPanel(false);
        boardPanel.setBounds(0, 0, 1600, 1000);
        MyFrame.getPanel().add(boardPanel, "play");
        setVisiblePanel("play");
    }

    public void createDeckReaderMode(InfoPassive infoPassive) {
        gameManager = new DeckReaderManager(infoPassive);
        boardPanel = new BoardPanel(false);
        boardPanel.setBounds(0, 0, 1600, 1000);
        MyFrame.getPanel().add(boardPanel, "play");
        setVisiblePanel("play");
    }

    public void createPracticeMode(InfoPassive infoPassive) {
//        createGameManger(infoPassive, true);
        gameManager = new PracticeManager(player(), infoPassive, DeckLogic.UpdateDeck(player().getSelectedDeck().getDeck()));
        boardPanel = new BoardPanel(true);
        boardPanel.setBounds(0, 0, 1600, 1000);
        MyFrame.getPanel().add(boardPanel, "play");
        setVisiblePanel("play");
    }

    void endTurn() {
        gameManager.endTurn();
        playSound("nextturn");
    }

    boolean ManaChecker(String name) {
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


    int heroPowerCanBePlayed() {
        return gameManager.heroPowerCanBePlayed();
    }

    public void playHeroPower(int target) {
        Character target1 = createTarget(target);
        if (gameManager.playHeroPower(target1))
            playSound("heropower");
        else
            playSound("mana");
    }

    CardModelView getWeaponViewModel(String string) {
        CardModelViewGetter cd = new CardModelViewGetter();
        return cd.getWeaponViewModel(string, gameManager);
    }

    CardModelView getViewModelOf(String list, int index) {
        CardModelViewGetter cd = new CardModelViewGetter();
        return cd.getViewModelOf(list, index, gameManager);
    }

    public void drawCard(int i, String mode, ArrayList<Card> deck, ArrayList<Card> hand) {
        gameManager.drawCard(i, mode, deck, hand);
    }

    boolean canDoAction(int i) {
        Minion minion = (Minion) friendlyPlayedCards().get(i);
        return !minion.isSleep();
    }

    public CardModelView getPureViewModelOf(String string) {
        CardModelViewGetter cd = new CardModelViewGetter();
        Card cards = getCardOf(string.toLowerCase());
        return cd.getPureViewModelOf(string, cards, gameManager);
    }

    public void setSleep(int i) {
        ((Minion) friendlyPlayedCards().get(i)).setSleep(true);
    }

    public void setSleep(Minion minion) {
        gameManager.setSleep(minion);
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

    void playCard(String string, int i, int target) {
        ActionHandler actionHandler = new ActionHandler();
        actionHandler.playCard(string, i, target, gameManager);
    }


    public void summonMinion(Minion minions, int i) {
        ActionHandler actionHandler = new ActionHandler();
        actionHandler.summonMinion(minions, i, gameManager);
    }


    boolean canBePlayed(String string) {
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

    public void playSound(String name) {
        new Thread(() -> SoundAdmin.playSound(name)).start();
    }

    public boolean heroCanAttack() {
        return friendlyHero().getCanAttack();
    }

    public void Attack(int attacker, int target, BoardPanel panel) {
        gameManager.Attack(attacker, target, panel);
    }

    public void drawPracticeAttack(int i, int j, int attack1, int attack2) {
        boardPanel.drawPracticeDamage(i, j, attack1, attack2);
    }


    public void threeCardChoose(InfoPassive infoPassive) {
//        ArrayList<Card> list = DeckLogic.UpdateDeck(player().getSelectedDeck().getDeck());
//        Collections.shuffle(list);
//        AlternativePanel th = new AlternativePanel(false);
//        th.setModel1(getPureViewModelOf(list.get(0).getName().toLowerCase()));
//        th.setModel2(getPureViewModelOf(list.get(1).getName().toLowerCase()));
//        th.setModel3(getPureViewModelOf(list.get(2).getName().toLowerCase()));
//        th.setInfoPassive(infoPassive);
//        th.setEnabled(true);
//        MyFrame.getPanel().add("three", th);
//        MyFrame.getInstance().changePanel("three");
    }

    public void changeCard(int i, AlternativePanel th) {
//        ArrayList<Card> list = DeckLogic.UpdateDeck(player().getSelectedDeck().getDeck());
//        Collections.shuffle(list);
//        for (Card card : list) {
//            if (i == 1) {
//                if (!card.getName().equalsIgnoreCase(th.getModel2().getName()) && !card.getName().equalsIgnoreCase(th.getModel3().getName())) {
//                    th.setModel1(getPureViewModelOf(card.getName().toLowerCase()));
//                    break;
//                }
//            } else if (i == 2) {
//                if (!card.getName().equalsIgnoreCase(th.getModel1().getName()) && !card.getName().equalsIgnoreCase(th.getModel3().getName())) {
//                    th.setModel2(getPureViewModelOf(card.getName().toLowerCase()));
//                    break;
//                }
//            } else if (i == 3) {
//                if (!card.getName().equalsIgnoreCase(th.getModel1().getName()) && !card.getName().equalsIgnoreCase(th.getModel2().getName())) {
//                    th.setModel3(getPureViewModelOf(card.getName().toLowerCase()));
//                    break;
//                }
//            }
//        }
    }

    public void discoverMode(String card1, String card2, String card3) {
//        AlternativePanel th = new AlternativePanel(true);
//        th.setModel1(getPureViewModelOf(card1.toLowerCase()));
//        th.setModel2(getPureViewModelOf(card2.toLowerCase()));
//        th.setModel3(getPureViewModelOf(card3.toLowerCase()));
//        th.setEnabled(true);
//        MyFrame.getPanel().add("three", th);
//        MyFrame.getInstance().changePanel("three");
    }

    public void listOfTargets(BoardPanel boardPanel) {
        ArrayList<Integer> targets = new ArrayList<>();
        int i = 0;
        for (Card card : enemyPlayedCards()) {
            if (card.getAttributes().contains(Attribute.Taunt)) {
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
        boardPanel.drawTargetsForAttack(targets);
    }

    CardModelView getHeroPowerViewModelOf(int i) {
//        if (i == 1) {
//            return new CardModelView(Constants.powerPics.get(friendlyHero().getName().toLowerCase()));
//        } else
//            return new CardModelView(Constants.powerPics.get(enemyHero().getName().toLowerCase()));
        return null;
    }

    ArrayList<ActionModel> friendlyActionModels() {
        return gameManager.friendlyContiniousModel();
    }

    ArrayList<ActionModel> enemyActionModels() {
        return gameManager.enemyContiniousModel();
    }

    public void finishAction(Card card) {
        gameManager.finishAction(card);
    }

    public synchronized void summonedMinion(Card card, int mode, int damage, int hp) {
//        CardModelView view = new CardModelView(Constants.cardPics.get(card.getName().toLowerCase()));
//        boardPanel.summonedMinion(view, mode, damage, hp);
    }

    public void winGame(String name) {
        AlternativePanel alternativePanel = new AlternativePanel(false);
        alternativePanel.setEnabled(true);
        alternativePanel.setWinner(Constants.heroPortraits.get(name));
        alternativePanel.setWinningMode(true);
        MyFrame.getPanel().add("three", alternativePanel);
        MyFrame.getInstance().changePanel("three");
    }

    public void updateDeckStates(int i) {
        player().getAllDecks().get(player().getSelectedDeck().getName()).setTotalPlays(player().getSelectedDeck().getTotalPlays() + 1);
        if (i == 1) {
            player().getAllDecks().get(player().getSelectedDeck().getName()).setTotalWins(player().getSelectedDeck().getTotalWins() + 1);
        }
    }

    void finishGame(Player player) {
        saveAndUpdate(player);
        setVisiblePanel("menu");
    }

    public void updateCardUsageTime(String name) {
        Map<String, Integer> usedTimes = player().getSelectedDeck().getUsedTimes();
        if (usedTimes.containsKey(name.toLowerCase())) {
            usedTimes.replace(name, usedTimes.get(name) + 1);
        }
        Map<String, Deck> allDecks = player().getAllDecks();
        Deck deck = allDecks.get(player().getSelectedDeck().getName());
        Map<String, Integer> usedTimes1 = deck.getUsedTimes();
        if (usedTimes1.containsKey(name.toLowerCase())) {
            usedTimes1.replace(name, usedTimes1.get(name) + 1);
        }
    }

    void aylarAction(String weapon) {
        Weapon weapon1 = (Weapon) getCardOf(weapon);
        weapon1.setDurability(weapon1.getDurability() + 2);
        weapon1.setDamage(weapon1.getDamage() + 2);
        friendlyDeckCards().add(weapon1);
        setVisiblePanel("play");
        updateGameLog(String.format("%s Choosed %s", friendlyPlayerName(), weapon1.getName()));
    }

    BufferedImage deckAnimationCard(String value) {
        if (value.equalsIgnoreCase("ai")) {
            return Constants.gamePics.get("enemycard");
        } else {
            return pictureOf(friendlyHandCards().get(friendlyHandCards().size() - 1).getName().toLowerCase());
        }
    }

    Card getCardOf(String name) {
        Card card = Card.getCardOf(name.toLowerCase());
        System.out.println(card);
        return card;
    }

    public Player player() {
//        return Gamestate.getPlayer();
        return null;
    }


    String friendlyPlayerName() {
        return gameManager.getFriendlyPlayer().getUsername();
    }

    String enemyPlayerName() {
        return gameManager.getEnemyPlayer().getUsername();
    }

    ArrayList<Card> friendlyDeckCards() {
        return gameManager.getFriendlyDeckCards();
    }

    ArrayList<Card> enemyDeckCards() {
        return gameManager.getEnemyDeckCards();
    }

    ArrayList<Card> friendlyHandCards() {
        return gameManager.getFriendLyHandCards();
    }

    ArrayList<Card> enemyHandCards() {
        return gameManager.getEnemyHandCards();
    }

    ArrayList<Card> friendlyPlayedCards() {
        return gameManager.getFriendlyPlayedCards();
    }

    ArrayList<Card> enemyPlayedCards() {
        return gameManager.getEnemyPlayedCards();
    }

    int friendlNotUsedmana() {
        return gameManager.getFriendlyNotUsedMana();
    }

    int friendlyTotalMana() {
        return gameManager.getFriendlyTotalMana();
    }

    Hero friendlyHero() {
        return gameManager.getFriendlyPlayerHero();
    }

    Hero enemyHero() {
        return gameManager.getEnemyPlayerHero();
    }

    String friendlyHeroName() {
        return gameManager.getFriendlyPlayerHero().getName().toLowerCase();
    }

    String enemyHeroName() {
        return gameManager.getEnemyPlayerHero().getName().toLowerCase();
    }

    BufferedImage pictureOf(String name) {
        return Constants.cardPics.get(name.toLowerCase());
    }

    Weapon friendlyWeapon() {
        return gameManager.getFriendlyWeapon();
    }

    Weapon enemyWeapon() {
        return gameManager.getEnemyWeapon();
    }

    int friendlyHeroPowerusedTimes() {
        return gameManager.getFriendlyHeroPowerUsageTime();
    }

    int enemyHeroPowerusedTimes() {
        return gameManager.getEnemyHeroPowerUsageTime();
    }

    ArrayList<String> gameLog() {
        return gameManager.getGameLog();
    }

    int friendlyPowerMana() {
        return (friendlyHero().getHeroPower().getManaCost() - gameManager.getFriendlyPowerManaDecrease());
    }

    int enemyPowerMana() {
        return (enemyHero().getHeroPower().getManaCost() - gameManager.getEnemyPowerManaDecrease());
    }

    public void AiTurn(boolean AiTurn) {
        boardPanel.AiTurn(AiTurn);
    }

    public void RequestAct(Object object) {
        boardPanel.request(object);
    }
}


