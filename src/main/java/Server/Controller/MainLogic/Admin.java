package Server.Controller.MainLogic;

import Client.View.View.Panels.AlternativePanel;
import Server.Controller.Actions.ActionHandler;
import Server.Controller.Manager.DeckReaderManager;
import Server.Controller.Manager.NormalManagers;
import Server.Controller.Manager.NormalModeManager;
import Server.Controller.Manager.PracticeManager;
import Server.Model.Heros.*;
import Server.Model.*;
import Server.Model.Cards.*;
import Server.Model.Enums.*;

import java.awt.image.BufferedImage;
import java.util.*;

public class Admin {
    private static Admin admin;
    private static NormalManagers gameManager;
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

    public void unlockHero(Player player) {
        Hero.HeroAdder(player);
        saveAndUpdate(player);
    }

    public void Log(String log, Player player) {
//        LOGGER.playerlog(player, log);
    }

    public String signUp(String username, String password) {
        return logInSignUp.create(username, password);
    }


    public String logIn(String username, String password, ClientHandler clientHandler) {
        return logInSignUp.check(username, password, clientHandler);
    }


    public void deleteAccount(Player player) {
        logInSignUp.DeleteAccount(player);
    }

    public void logOut(Player player) {
        Log("Sign out ", player);
        saveAndUpdate(player);
    }

    public void selectFirstHero(String string, Player player) {
        updateFirstHero(string, player);
        playerFirstUpdate(string, player);
    }

    private void playerFirstUpdate(String string, Player player) {
        Log(String.format("Select : %s as first selected hero", string.toUpperCase()), player);
        player.setNewToGame(false);
        List<Heroes> ar1 = player.getPlayerHeroes();
        if (player.getPlayerHeroes() == null) ar1 = new ArrayList<>();
        ar1.add(Heroes.valueOf(string));
        player.setPlayerHeroes(ar1);
        DataBaseManagment.PlayerJsonBuilder(player.getUsername(), player);
    }

    public boolean createNewDeck(Player player) {
        return player.getAllDecks().size() < 12;
    }

    public void saveAndUpdate(Player player) {
        DataBaseManagment.PlayerJsonBuilder(player.getUsername(), player);
    }

    public static void updateFirstHero(String hero, Player player) {
        switch (hero) {
            case "mage":
                List<Carts> ar = player.getPlayerCarts();
                ar.add(Carts.polymorph);
                player.setPlayerCarts(ar);
                ar = player.getSelectedDeck().getDeck();
                ar.add(Carts.polymorph);
                player.getAllDecks().get("Default Deck").getUsedTimes().put("polymorph", 0);
                player.getSelectedDeck().getUsedTimes().put("polymorph", 0);
                player.getSelectedDeck().setDeck(ar);
                player.getSelectedDeck().setHero(DataBaseManagment.HeroJsonReader(player, "mage"));
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
                player.getAllDecks().replace(player.getSelectedDeck().getName(), player.getSelectedDeck());
                player.getSelectedDeck().setHero(DataBaseManagment.HeroJsonReader(player, "rogue"));
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
                player.getAllDecks().replace(player.getSelectedDeck().getName(), player.getSelectedDeck());
                player.getSelectedDeck().setHero(DataBaseManagment.HeroJsonReader(player, "warlock"));
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
                player.getAllDecks().replace(player.getSelectedDeck().getName(), player.getSelectedDeck());
                player.getSelectedDeck().setHero(DataBaseManagment.HeroJsonReader(player, "priest"));
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
                player.getAllDecks().replace(player.getSelectedDeck().getName(), player.getSelectedDeck());
                player.getSelectedDeck().setHero(DataBaseManagment.HeroJsonReader(player, "hunter"));
                break;
        }
    }


    public String buyCard(String name, Player player) {
        if (Shop.Buy(name.toLowerCase(), player)) {
            Log(String.format("Buy : %s  is added to purchased cards .", name), player);
            saveAndUpdate(player);
            return "ok";
        }
        return "gold";
    }

    public String sellCard(String name, Player player) {
        if (canBeSold(name, player)) {
            Shop.Sell(name.toLowerCase(), player);
            Log(String.format("Sell : %s  is removed from  purchased cards .", name), player);
            saveAndUpdate(player);
            return "ok";
        }
        return "reject";
    }

    public boolean canBeSold(String name, Player player) {
        return Shop.CanBeSold(name.toLowerCase(), player);
    }

    public ArrayList<CardModelView> properCards(int i, Player player) {
        ArrayList<Card> ar;
        if (i == 1) {
            ar = Shop.Buyable(player);
        } else if (i == 2) {
            ar = Shop.Sellable(player);
        } else {
            ar = Card.allCards();
        }
        return modelList(ar);
    }

    public ArrayList<CardModelView> modelList(ArrayList<Card> list) {
        CardModelViewGetter cd = new CardModelViewGetter();
        ArrayList<CardModelView> list1 = new ArrayList<>();
        for (Card card : list) {
            list1.add(cd.getPureViewModelOf(card.getName(), card));
        }
        return list1;
    }

    public String createDeck(String name, ArrayList<Carts> selectedCards, String heroName, Player player) {
        for (Map.Entry<String, Deck> entry : player.getAllDecks().entrySet()) {
            String st = entry.getKey();
            if (name.equalsIgnoreCase(st)) {
                return "Name had been taken before !";
            }
        }
        if (selectedCards.size() > 30 || selectedCards.size() < 15) {
            return "Number of cards in your deck must be in range [15,30].";
        }
        Deck deck = new Deck(0, 0, name);
        deck.setDeck(selectedCards);
        deck.setHero(DataBaseManagment.HeroJsonReader(player, heroName.toLowerCase()));
        deck.setUsedTimes(Deck.resetUsedTimes(selectedCards, deck));
        player.getAllDecks().put(deck.getName(), deck);
        Log(String.format("Create : deck %s is created.", deck.getName()), player);
        DataBaseManagment.PlayerJsonBuilder(player.getUsername(), player);
        return "ok";
    }

    public String changeDeck(DeckModel deck, ArrayList<Carts> selectedCards, String heroName, String previous, String current, Player player) {
        if (!current.equals(previous)) {
            for (Map.Entry<String, Deck> entry : player.getAllDecks().entrySet()) {
                String st = entry.getKey();
                if (current.equalsIgnoreCase(st)) {
                    return "Name had been taken before !";
                }
            }
        }
        if (selectedCards.size() > 30 || selectedCards.size() < 15) {
            return "Number of cards in your deck must be in range [15,30].";
        }
        Deck selectedDeck = player.getAllDecks().get(previous);
        selectedDeck.setDeck(selectedCards);
        selectedDeck.setName(current);
        Hero hero = DataBaseManagment.HeroJsonReader(player, heroName.toLowerCase());
        selectedDeck.setHero(hero);
        selectedDeck.setUsedTimes(Deck.resetUsedTimes(selectedCards, selectedDeck));
        player.getAllDecks().remove(previous);
        player.getAllDecks().put(current, selectedDeck);
        player.setSelectedDeck(null);
        Log(String.format("Change : deck %s has been changed.", selectedDeck.getName()), player);
        DataBaseManagment.PlayerJsonBuilder(player.getUsername(), player);
        return "ok";
    }

    public void setSelectedDeck(DeckModel deck, Player player) {
        Deck deck1 = player.getAllDecks().get(deck.getName());
        player.setSelectedDeck(deck1);
        saveAndUpdate(player);
    }

    public String removeDeck(DeckModel selectedDeck, Player player) {
        player.getAllDecks().remove(selectedDeck.getName());
        Log(String.format("Delete : deck %s is deleted.", selectedDeck.getName()), player);
        saveAndUpdate(player);
        if (player.getSelectedDeck() != null && selectedDeck.getName().equalsIgnoreCase(player.getSelectedDeck().getName())) {
            player.setSelectedDeck(null);
            saveAndUpdate(player);
            return "ok1";
        }
        return "ok2";
    }

    public ArrayList<String> bestDecks(Player player) {
        return DeckLogic.bestDeck(player);
    }

    public ArrayList<InfoPassive> generatePassive() {
        return InfoPassive.randomGenerate(InfoPassive.infoPassiveCreator());
    }

    public String checkNecessary(Player player) {
        if (player.getSelectedDeck() == null) {
            return "You must choose a deck, first";
        }
        if (player.getSelectedDeck().getDeck().size() < 15 || player.getSelectedDeck().getDeck().size() > 30) {
            return "notEnoughCard.Please go to Collection.";
        }
        return "ok";
    }

//    public void create(InfoPassive infoPassive, String card1, String card2, String card3, Player player) {
//        createPlayBoard(infoPassive, card1, card2, card3, player);
//    }
//
//    private void createGameManager(InfoPassive infoPassive, String card1, String card2, String card3, Player player) {
//        gameManager = new NormalModeManager(player, infoPassive, DeckLogic.UpdateDeck(player().getSelectedDeck().getDeck()), card1, card2, card3);
//    }

    //    private void createPlayBoard(InfoPassive infoPassive, String card1, String card2, String card3, Player player) {
//        createGameManager(infoPassive, card1, card2, card3, player);
//
//    }
//
//    public void createDeckReaderMode(InfoPassive infoPassive) {
//        gameManager = new DeckReaderManager(infoPassive);
//        boardPanel = new BoardPanel(false);
//        boardPanel.setBounds(0, 0, 1600, 1000);
//        MyFrame.getPanel().add(boardPanel, "play");
//        setVisiblePanel("play");
//    }
//
//    public void createPracticeMode(InfoPassive infoPassive) {
//        createGameManger(infoPassive, true);
//        gameManager = new PracticeManager(player(), infoPassive, DeckLogic.UpdateDeck(player().getSelectedDeck().getDeck()));
//        boardPanel = new BoardPanel(true);
//        boardPanel.setBounds(0, 0, 1600, 1000);
//        MyFrame.getPanel().add(boardPanel, "play");
//        setVisiblePanel("play");
//    }
//
    public CardModelView getWeaponViewModel(Weapon weapon) {
        CardModelViewGetter cd = new CardModelViewGetter();
        return cd.getWeaponViewModel(weapon);
    }

    //
    public CardModelView getPureViewModelOf(String string) {
        CardModelViewGetter cd = new CardModelViewGetter();
        Card cards = getCardOf(string.toLowerCase());
        return cd.getPureViewModelOf(string, cards, gameManager);
    }


    void playCard(String string, int i, int target) {
        ActionHandler actionHandler = new ActionHandler();
//        actionHandler.playCard(string, i, target, gameManager);
    }


    public ArrayList<CardModelView> threeCardChoose(Player player) {
        ArrayList<CardModelView> list = new ArrayList<>();
        ArrayList<Card> list1 = DeckLogic.UpdateDeck(player.getSelectedDeck().getDeck());
        Collections.shuffle(list1);

        list.add(getPureViewModelOf(list1.get(0).getName().toLowerCase()));
        list.add(getPureViewModelOf(list1.get(1).getName().toLowerCase()));
        list.add(getPureViewModelOf(list1.get(2).getName().toLowerCase()));
        return list;


//        AlternativePanel th = new AlternativePanel(false);
//        th.setInfoPassive(infoPassive);
//        th.setEnabled(true);
//        MyFrame.getPanel().add("three", th);
//        MyFrame.getInstance().changePanel("three");
    }

    public CardModelView changeCard(String card1, String card2, String card3, Player player) {
        ArrayList<Card> list = DeckLogic.UpdateDeck(player.getSelectedDeck().getDeck());
        Collections.shuffle(list);
        for (Card card : list) {
            if (!card.getName().equalsIgnoreCase(card1) && !card.getName().equalsIgnoreCase(card2) && !card.getName().equalsIgnoreCase(card3)) {
                return (getPureViewModelOf(card.getName().toLowerCase()));
            }
        }
        return null;
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


//
public synchronized void summonedMinion(Card card, int mode, int damage, int hp) {
//        CardModelView view = new CardModelView(Constants.cardPics.get(card.getName().toLowerCase()));
//        boardPanel.summonedMinion(view, mode, damage, hp);
}

    public void winGame(String name) {
//        AlternativePanel alternativePanel = new AlternativePanel(false);
//        alternativePanel.setEnabled(true);
//        alternativePanel.setWinner(Constants.heroPortraits.get(name));
//        alternativePanel.setWinningMode(true);
//        MyFrame.getPanel().add("three", alternativePanel);
//        MyFrame.getInstance().changePanel("three");
    }

    public void updateDeckStates(int i, Player player) {
        player.getAllDecks().get(player.getSelectedDeck().getName()).setTotalPlays(player.getSelectedDeck().getTotalPlays() + 1);
        if (i == 1) {
            player.getAllDecks().get(player.getSelectedDeck().getName()).setTotalWins(player.getSelectedDeck().getTotalWins() + 1);
        }
    }


    public void updateCardUsageTime(String name) {
//        Map<String, Integer> usedTimes = player().getSelectedDeck().getUsedTimes();
//        if (usedTimes.containsKey(name.toLowerCase())) {
//            usedTimes.replace(name, usedTimes.get(name) + 1);
//        }
//        Map<String, Deck> allDecks = player().getAllDecks();
//        Deck deck = allDecks.get(player().getSelectedDeck().getName());
//        Map<String, Integer> usedTimes1 = deck.getUsedTimes();
//        if (usedTimes1.containsKey(name.toLowerCase())) {
//            usedTimes1.replace(name, usedTimes1.get(name) + 1);
//        }
    }

    void aylarAction(String weapon) {
        Weapon weapon1 = (Weapon) getCardOf(weapon);
        weapon1.setDurability(weapon1.getDurability() + 2);
        weapon1.setDamage(weapon1.getDamage() + 2);
//        friendlyDeckCards().add(weapon1);
//        setVisiblePanel("play");
//        updateGameLog(String.format("%s Choosed %s", friendlyPlayerName(), weapon1.getName()));
    }


    Card getCardOf(String name) {
        Card card = Card.getCardOf(name.toLowerCase());
        return card;
    }

    public PlayerModel getPlayerModel(Player player) {
        String st = null;
        if (player.getSelectedDeck() != null) {
            st = player.getSelectedDeck().getName();
        }
        return new PlayerModel(player.getUsername(), player.getExp(), player.getLevel(),
                player.getMoney(), st);
    }

    public DeckModel getDeckModel(Deck deck) {
        Carts mostused = DeckLogic.mostUsedCard(deck);
        double avg = DeckLogic.avarageMana(deck);
        double winrate = DeckLogic.winRate(deck);
        return new DeckModel(deck.getName(), deck.getDeck(), deck.getHero().getName(), mostused,
                deck.getTotalPlays(), deck.getTotalWins(), avg, winrate);

    }

    public HashMap<String, DeckModel> playerDecks(Player player) {
        HashMap<String, DeckModel> map = new HashMap<>();
        for (Map.Entry<String, Deck> entry : player.getAllDecks().entrySet()) {
            DeckModel deckModel = getDeckModel(entry.getValue());
            map.put(entry.getKey(), deckModel);
        }
        return map;
    }

}


