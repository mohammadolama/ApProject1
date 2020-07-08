package Controller;

import Controller.Actions.*;
import Model.ActionModel;
import Model.Cards.*;
import Model.Enums.*;
import Model.CardModelView;
import Model.Heros.*;
import Model.Interface.Character;
import View.Panels.*;
import View.Update.Update;
import Main.*;
import View.Sounds.SoundAdmin;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.*;

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

    void levelUp() {
        player().setLevel(player().getLevel() + 1);
        frameRender();
    }

    void unlockHero() {
        Hero.HeroAdder(player());
        frameRender();
        Col_Change.getInstance().update();
    }

    void frameRender() {
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

    void logIn(String username, String password) {
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

    void deleteAccount() {
        int i = JOptionPane.showConfirmDialog(MyFrame.getInstance(), "You are about to delete your account.\n Are you sure?", "Delete Account", JOptionPane.YES_NO_OPTION);
        if (i == 0) {
            logInSignUp.DeleteAccount(player());
            playMusic("login");
            setVisiblePanel("login");
        }
    }

    void logOut() {
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

    void signUp(String username, String password) {
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

    void setVisiblePanel(String panel) {
        if (panel.equalsIgnoreCase("menu")) {
            gameManager = null;
        }
        MyFrame.getInstance().changePanel(panel);
    }

    void createNewDeck() {
        if (Gamestate.getPlayer().getAllDecks().size() < 12) {
            Col_Change.getInstance().setCreateMode(true);
            admin.setVisiblePanel("col");
        } else {
            JOptionPane.showMessageDialog(CollectionPanel.getInstance(), "Can not create more than 12 decks");
        }
    }

    void updateDrawingPanel(String name) {
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

    void saveAndUpdate() {
        Update.saveAndUpdate();
    }

    private void updateGameLog(String log) {
        gameManager.updateGameLog(log);
    }

    void buyCard(String name) {
        Shop.Buy(name.toLowerCase());
        playSound("buy");
        Log(String.format("Buy : %s  is added to purchased cards .", name));
        saveAndUpdate();
    }

    void sellCard(String name) {
        Shop.Sell(name.toLowerCase());
        playSound("sell");
        Log(String.format("Sell : %s  is removed from  purchased cards .", name));
        saveAndUpdate();
    }

    long wallet() {
        return player().getMoney();
    }

    ArrayList<Card> properCards(int i) {
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

    long price(String name) {
        return Shop.Price((name.toLowerCase()));
    }

    public boolean canBeSold(String name) {
        return Shop.CanBeSold(name.toLowerCase());
    }

    public void createDeck(String name, ArrayList<Carts> selectedCards, String heroName) {
        Deck deck = new Deck(0, 0, name);
        deck.setDeck(selectedCards);
        deck.setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), heroName));
        deck.setUsedTimes(Deck.resetUsedTimes(selectedCards, deck));
        deck.setMostUsedCard(deck.mostUsedCard());
        Gamestate.getPlayer().getAllDecks().put(deck.getName(), deck);
        Admin.getInstance().Log(String.format("Create : deck %s is created.", deck.getName()));
        JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername(), Gamestate.getPlayer());
    }

    public void changeDeck(Deck selectedDeck, ArrayList<Carts> selectedCards, String heroName) {
        selectedDeck.setDeck(selectedCards);
        selectedDeck.setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), heroName));
        selectedDeck.setUsedTimes(Deck.resetUsedTimes(selectedCards, selectedDeck));
        selectedDeck.setMostUsedCard(selectedDeck.mostUsedCard());
        Gamestate.getPlayer().getAllDecks().replace(selectedDeck.getName(), selectedDeck, selectedDeck);
        Admin.getInstance().Log(String.format("Change : deck %s has been changed.", selectedDeck.getName()));
        JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername(), Gamestate.getPlayer());
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

    ArrayList<String> bestDecks() {
        return Deck.bestDeck(player());
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

    public void create(InfoPassive infoPassive, String card1, String card2, String card3) {
        createPlayBoard(infoPassive, card1, card2, card3);
    }

    private void createGameManager(InfoPassive infoPassive, String card1, String card2, String card3) {
        gameManager = new GameManager(Gamestate.getPlayer(), infoPassive, Deck.UpdateDeck(player().getSelectedDeck().getDeck()), card1, card2, card3);
    }

    /* Deck ReaderMode Generator  */
    private void createGameManger(InfoPassive infoPassive, boolean practiceMode) {
        gameManager = new GameManager(player(), infoPassive, Deck.UpdateDeck(player().getSelectedDeck().getDeck()), practiceMode);
    }

    private void createPlayBoard(InfoPassive infoPassive, String card1, String card2, String card3) {
        createGameManager(infoPassive, card1, card2, card3);
        boardPanel = new BoardPanel();
        boardPanel.setBounds(0, 0, 1600, 1000);
        MyFrame.getPanel().add(boardPanel, "play");
        setVisiblePanel("play");
    }

    public void createDeckReaderMode(InfoPassive infoPassive) {
        createGameManger(infoPassive, false);
        boardPanel = new BoardPanel();
        boardPanel.setBounds(0, 0, 1600, 1000);
        MyFrame.getPanel().add(boardPanel, "play");
        setVisiblePanel("play");
    }

    public void createPracticeMode(InfoPassive infoPassive) {
        createGameManger(infoPassive, true);
        boardPanel = new BoardPanel();
        boardPanel.setBounds(0, 0, 1600, 1000);
        MyFrame.getPanel().add(boardPanel, "play");
        setVisiblePanel("play");
    }

    void endTurn() {
        gameManager.endTurn();
        playSound("nextturn");
        updateGameLog(String.format("%s  EndTurn .", enemyPlayerName()));
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
        if (!(friendlyHero() instanceof Hunter)) {
            if (gameManager.getFriendlyHeroPowerUsageTime() > 0) {
                if (gameManager.heroPowerTargetNeeded() > 0) {
                    if (gameManager.heroPowerTargetNeeded() == 1) {
                        return 1;
                    } else if (gameManager.heroPowerTargetNeeded() == 2) {
                        return 2;
                    }
                    return 0;
                } else {
                    return 3;
                }
            } else {
                playSound("mana");
                return 0;
            }
        }
        return 0;
    }

    public void playHeroPower(int target) {
        Character target1 = createTarget(target);
        gameManager.playHeroPower(target1);
        playSound("heropower");
    }

    CardModelView getWeaponViewModel(String string) {
        if (string.equalsIgnoreCase("friendly")) {
            Weapon weapon = friendlyWeapon();
            return new CardModelView(pictureOf(weapon.getName().toLowerCase()), weapon.getName(), weapon.getManaCost(), weapon.getDamage(), weapon.getDurability(), Type.Weapon, null, false, false, false, false);
        } else {
            Weapon weapon = enemyWeapon();
            return new CardModelView(pictureOf(weapon.getName().toLowerCase()), weapon.getName(), weapon.getManaCost(), weapon.getDamage(), weapon.getDurability(), Type.Weapon, null, false, false, false, false);
        }
    }

    CardModelView getViewModelOf(String list, int index) {
        if (list.equalsIgnoreCase("friendly")) {
            Card cards = gameManager.getFriendlyPlayedCards().get(index);
            String string = cards.getName().toLowerCase();
            BufferedImage image = pictureOf(string);
            if (cards instanceof Minion) {
                Minion minions = (Minion) cards;
                return new CardModelView(image, string, minions.getManaCost(), minions.getDamage(), minions.getHealth(), minions.getType(), minions.getAttributes(), minions.isSleep(), minions.isCanBeAttacked(), minions.isNeedFriendlyTarget(), minions.isNeedEnemyTarget());
            }
            return null;
        } else {
            Card cards = gameManager.getEnemyPlayedCards().get(index);
            String string = cards.getName().toLowerCase();
            BufferedImage image = pictureOf(string);
            if (cards instanceof Minion) {
                Minion minions = (Minion) cards;
                return new CardModelView(image, string, minions.getManaCost(), minions.getDamage(), minions.getHealth(), minions.getType(), minions.getAttributes(), minions.isSleep(), minions.isCanBeAttacked(), minions.isNeedFriendlyTarget(), minions.isNeedEnemyTarget());
            }
            return null;
        }
    }

    public void drawCard(int i, String mode, ArrayList<Card> deck, ArrayList<Card> hand) {
        gameManager.drawCard(i, mode, deck, hand);
    }

    boolean canDoAction(int i) {
        Minion minion = (Minion) friendlyPlayedCards().get(i);
        return !minion.isSleep();
    }

    CardModelView getPureViewModelOf(String string) {
        Card cards = getCardOf(string.toLowerCase());
        BufferedImage image = pictureOf(string.toLowerCase());
        int mana = cards.getManaCost();
        if (gameManager != null) {
            mana -= gameManager.getFriendlyManaDecrease();
        }
        if (cards instanceof Minion) {
            Minion minions = (Minion) cards;
            return new CardModelView(image, string, mana, minions.getDamage(), minions.getHealth(), minions.getType(), minions.getAttributes(), minions.isSleep(), minions.isCanBeAttacked(), minions.isNeedFriendlyTarget(), minions.isNeedEnemyTarget());
        } else if (cards instanceof Weapon) {
            Weapon weapon = (Weapon) cards;
            return new CardModelView(image, string, mana, weapon.getDamage(), weapon.getDurability(), weapon.getType(), null, false, false, false, false);
        } else {
            Spell spell = (Spell) cards;
            return new CardModelView(image, string, mana, spell.getType(), spell.isNeedFriendlyTarget(), spell.isNeedEnemyTarget());
        }
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
        Minion targeted = (Minion) createTarget(target);
        for (Card cards : friendlyHandCards()) {
            if (cards.getName().equalsIgnoreCase(string)) {
                if (gameManager.getFriendlyNotUsedMana() >= cards.getManaCost() - gameManager.getFriendlyManaDecrease()) {
                    checkContiniousAction(cards, false);
                    cards.accept(new BattlecryVisitor(), targeted, friendlyDeckCards(), friendlyHandCards(), friendlyPlayedCards(), enemyDeckCards(), enemyHandCards(), enemyPlayedCards(), friendlyHero(), enemyHero());
                    if (cards instanceof Minion) {
                        playMinion((Minion) cards, i, targeted);
                        gameManager.spendManaOnMinion(cards.getManaCost() - gameManager.getFriendlyManaDecrease(), false);
                    } else if (cards instanceof Spell) {
                        playSpell((Spell) cards, targeted);
                        gameManager.spendManaOnSpell(cards.getManaCost() - gameManager.getFriendlyManaDecrease(), false);
                    } else if (cards instanceof Weapon) {
                        playWeapon((Weapon) cards);
                    }
                    String log = String.format("Play : played card \"%s\"", cards.getName());
                    Log(log);
                    updateCardUsageTime(cards.getName().toLowerCase());
                    break;
                } else {
                    playSound("mana");
                }
            }
        }
        gameManager.checkDestroyMinion();
        checkForWinner();
    }

    private void playMinion(Minion minions, int i, Character target) {
        if (friendlyPlayedCards().size() < 7) {
            playSound("minion");
            gameManager.setFriendlyNotUsedMana(gameManager.getFriendlyNotUsedMana() - (minions.getManaCost() - gameManager.getFriendlyManaDecrease()));
            minions.accept(new ActionVisitor(), target, friendlyDeckCards(), friendlyHandCards(), friendlyPlayedCards(), enemyDeckCards(), enemyHandCards(), enemyPlayedCards(), friendlyHero(), enemyHero());
            summonMinion(minions, i);
            gameManager.checkDestroyMinion();
            gameManager.hunterPowerAction(minions, false);
            gameManager.faezeAction(minions, false);
            gameManager.shahryarAction(minions, false);
            updateGameLog(String.format("%s played", minions.getName().toLowerCase()));
        } else {
            playSound("error");
            frameRender();
        }
    }

    public void summonMinion(Minion minions, int i) {
        if (friendlyPlayedCards().size() < 7) {
            if (minions.getAttributes() != null && (minions.getAttributes().contains(Attribute.Charge) || minions.getAttributes().contains(Attribute.Rush))) {
                minions.setSleep(false);
            }
            if (i < 0) {
                friendlyPlayedCards().add(minions);
            } else {
                friendlyPlayedCards().add(i, minions);
            }
            friendlyHandCards().remove(minions);
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


    void checkContiniousAction(Card cards, boolean AI) {
        for (ContiniousActionCarts value : ContiniousActionCarts.values()) {
            if (cards.getName().equalsIgnoreCase(value.toString())) {
                gameManager.addContiniousActionCard(cards, AI);
                break;
            }
        }
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
        return friendlyHero().getWeapon() != null;
    }

    public void Attack(int attacker, int target, BoardPanel panel) {
        ActionHandler actionHandler = new ActionHandler();
        new Thread(() -> playSound("attack")).start();
        if (attacker >= 0 && target >= 0) {
            Minion attacker1 = (Minion) friendlyPlayedCards().get(attacker);
            Minion target1 = (Minion) enemyPlayedCards().get(target);
            actionHandler.Attack(attacker1, target1, enemyPlayedCards());
            setSleep(attacker);
            panel.drawDamages(attacker, target, attacker1.getAttack(), target1.getAttack());
        } else if (attacker >= 0) {
            Minion attacker1 = (Minion) friendlyPlayedCards().get(attacker);
            Hero target1 = enemyHero();
            actionHandler.Attack(attacker1, target1, enemyPlayedCards());
            setSleep(attacker);
            panel.drawDamages(attacker, target, attacker1.getAttack(), target1.getAttack());
        } else if (target >= 0) {
            Hero attacker1 = friendlyHero();
            Minion target1 = (Minion) enemyPlayedCards().get(target);
            panel.drawDamages(attacker, target, attacker1.getAttack(), target1.getAttack());
            if (actionHandler.Attack(attacker1, target1, enemyPlayedCards())) {
                gameManager.updateWeapon();
            }
        } else {
            Hero attacker1 = friendlyHero();
            Hero target1 = enemyHero();
            panel.drawDamages(attacker, target, attacker1.getAttack(), target1.getAttack());
            if (actionHandler.Attack(attacker1, target1, enemyPlayedCards())) {
                gameManager.updateWeapon();
            }
        }
        gameManager.checkDestroyMinion();
        checkForWinner();
    }

    public void practiceAttack(Minion attacker, Character target, int i, int j) {
        ActionHandler actionHandler = new ActionHandler();
        new Thread(() -> playSound("attack")).start();
        if (j >= 0) {
            Minion target1 = (Minion) target;
            actionHandler.Attack(attacker, target1, friendlyPlayedCards());
            setSleep(attacker);
            boardPanel.drawPracticeDamage(i, j, attacker.getDamage(), target.getAttack());
        } else {
            Hero target1 = friendlyHero();
            actionHandler.Attack(attacker, target1, friendlyPlayedCards());
            setSleep(attacker);
            boardPanel.drawPracticeDamage(i, j, attacker.getDamage(), target.getAttack());
        }
    }


    void Attack2(Minion attacker, Minion target) {
        if (friendlyPlayedCards().contains(target)) {
            ActionHandler actionHandler = new ActionHandler();
            actionHandler.attackMinion2(attacker, target);
            for (Card card : enemyPlayedCards()) {
                if (card.equals(target)) {
                    break;
                }
            }
            gameManager.checkDestroyMinion();
        }
    }

    public void threeCardChoose(InfoPassive infoPassive) {
        ArrayList<Card> list = Deck.UpdateDeck(player().getSelectedDeck().getDeck());
        Collections.shuffle(list);
        AlternativePanel th = new AlternativePanel(false);
        th.setModel1(getPureViewModelOf(list.get(0).getName().toLowerCase()));
        th.setModel2(getPureViewModelOf(list.get(1).getName().toLowerCase()));
        th.setModel3(getPureViewModelOf(list.get(2).getName().toLowerCase()));
        th.setInfoPassive(infoPassive);
        th.setEnabled(true);
        MyFrame.getPanel().add("three", th);
        MyFrame.getInstance().changePanel("three");
    }

    public void changeCard(int i, AlternativePanel th) {
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
        AlternativePanel th = new AlternativePanel(true);
        th.setModel1(getPureViewModelOf(card1.toLowerCase()));
        th.setModel2(getPureViewModelOf(card2.toLowerCase()));
        th.setModel3(getPureViewModelOf(card3.toLowerCase()));
        th.setEnabled(true);
        MyFrame.getPanel().add("three", th);
        MyFrame.getInstance().changePanel("three");
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
        if (i == 1) {
            return new CardModelView(Constants.powerPics.get(friendlyHero().getName().toLowerCase()));
        } else
            return new CardModelView(Constants.powerPics.get(enemyHero().getName().toLowerCase()));
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
        System.out.println(card + "\t" + card.getName());
        CardModelView view = new CardModelView(Constants.cardPics.get(card.getName().toLowerCase()));
        boardPanel.summonedMinion(view, mode, damage, hp);
    }

    public void restoreHealth(Character target, int i) {
        target.setLife(target.getLife() + i);
        if (target.getLife() > target.getMaxLife()) {
            target.setLife(target.getMaxLife());
        }
        if (target instanceof Minion) {
            summonedMinion((Minion) target, 1, target.getAttack(), target.getLife());
        }
    }

    private void checkForWinner() {
        int i = gameManager.checkForWinner();
        switch (i) {
            case 1:
                winGame(friendlyHeroName().toLowerCase());
                boolean flag = gameManager.normalMode();
                if (flag) {
                    updateDeckStates(1);
                }
                break;
            case 2:
                winGame(enemyHeroName().toLowerCase());
                flag = gameManager.normalMode();
                if (flag) {
                    updateDeckStates(1);
                }
                break;
            case 3:
                winGame(friendlyHeroName().toLowerCase());
                flag = gameManager.normalMode();
                if (flag) {
                    updateDeckStates(0);
                }
                break;

            case 4:
                winGame(enemyHeroName().toLowerCase());
                flag = gameManager.normalMode();
                if (flag) {
                    updateDeckStates(0);
                }
                break;
            case 0:
                break;
        }
    }

    private void winGame(String name) {
        AlternativePanel alternativePanel = new AlternativePanel(false);
        alternativePanel.setEnabled(true);
        alternativePanel.setWinner(Constants.heroPortraits.get(name));
        alternativePanel.setWinningMode(true);
        MyFrame.getPanel().add("three", alternativePanel);
        MyFrame.getInstance().changePanel("three");
    }

    private void updateDeckStates(int i) {
        player().getSelectedDeck().setTotalPlays(player().getSelectedDeck().getTotalPlays() + 1);
        player().getAllDecks().get(player().getSelectedDeck().getName()).setTotalPlays(player().getSelectedDeck().getTotalPlays() + 1);
        if (i == 1) {
            player().getSelectedDeck().setTotalWins(player().getSelectedDeck().getTotalWins() + 1);
            player().getAllDecks().get(player().getSelectedDeck().getName()).setTotalWins(player().getSelectedDeck().getTotalWins() + 1);
        }
    }

    void finishGame() {
        saveAndUpdate();
        setVisiblePanel("menu");
    }

    private void updateCardUsageTime(String name) {
        HashMap<String, Integer> usedTimes = player().getSelectedDeck().getUsedTimes();
        if (usedTimes.containsKey(name.toLowerCase())) {
            usedTimes.replace(name, usedTimes.get(name) + 1);
        }
        HashMap<String, Deck> allDecks = player().getAllDecks();
        Deck deck = allDecks.get(player().getSelectedDeck().getName());
        HashMap<String, Integer> usedTimes1 = deck.getUsedTimes();
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
    }

    BufferedImage deckAnimationCard() {
        return pictureOf(friendlyHandCards().get(friendlyHandCards().size() - 1).getName().toLowerCase());
    }

    Card getCardOf(String name) {
        return Card.getCardOf(name.toLowerCase());
    }

    public Player player() {
        return Gamestate.getPlayer();
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
}


