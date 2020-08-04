package Client.Controller;

import Client.Controller.Requests.BoardPanelRequest;
import Client.Model.CardModelView;
import Client.Model.DeckModel;
import Client.Model.Enums.Carts;
import Client.Model.Enums.Heroes;
import Client.Model.InfoPassive;

import java.util.ArrayList;
import java.util.HashMap;

public class Responses {

    private static final Responses responses = new Responses();
    private CardModelView cardModelView;
    private DeckModel deckModel;
    private ArrayList<CardModelView> modelviewList, purchasedCards, notPurchasedCards,
            collectionModels;
    private long price, wallet;
    private String className;
    private ArrayList<InfoPassive> passiveList;
    private HashMap<String, DeckModel> decks;
    private ArrayList<Heroes> heroesList;
    private ArrayList<Carts> collectionList;
    private boolean canBePlayed, heroCanAttack, canDoAction;
    private int heroPowerCanBePlayed;


    public BoardPanelRequest board;

    public static Responses getInstance() {
        return responses;
    }

    public HashMap<String, DeckModel> getDecks() {
        return decks;
    }

    public void setDecks(HashMap<String, DeckModel> decks) {
        this.decks = decks;
    }

    public ArrayList<InfoPassive> getPassiveList() {
        return passiveList;
    }

    public void setPassiveList(ArrayList<InfoPassive> passiveList) {
        this.passiveList = passiveList;
    }

    public DeckModel getDeckModel() {
        return deckModel;
    }

    public ArrayList<Carts> getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(ArrayList<Carts> collectionList) {
        this.collectionList = collectionList;
    }

    public void setDeckModel(DeckModel deckModel) {
        this.deckModel = deckModel;
    }

    public CardModelView getCardModelView() {
        return cardModelView;
    }

    public ArrayList<CardModelView> getPurchasedCards() {
        return purchasedCards;
    }

    public void setPurchasedCards(ArrayList<CardModelView> purchasedCards) {
        this.purchasedCards = purchasedCards;
    }

    public ArrayList<CardModelView> getNotPurchasedCards() {
        return notPurchasedCards;
    }

    public void setNotPurchasedCards(ArrayList<CardModelView> notPurchasedCards) {
        this.notPurchasedCards = notPurchasedCards;
    }

    public void setCardModelView(CardModelView cardModelView) {
        this.cardModelView = cardModelView;
    }

    public ArrayList<CardModelView> getModelviewList() {
        return modelviewList;
    }

    public void setModelviewList(ArrayList<CardModelView> modelviewList) {
        this.modelviewList = modelviewList;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public long getWallet() {
        return wallet;
    }

    public void setWallet(long wallet) {
        this.wallet = wallet;
    }

    public ArrayList<Heroes> getHeroesList() {
        return heroesList;
    }

    public ArrayList<CardModelView> getCollectionModels() {
        return collectionModels;
    }

    public void setCollectionModels(ArrayList<CardModelView> collectionModels) {
        this.collectionModels = collectionModels;
    }

    public boolean isCanBePlayed() {
        return canBePlayed;
    }

    public void setCanBePlayed(boolean canBePlayed) {
        this.canBePlayed = canBePlayed;
    }

    public int getHeroPowerCanBePlayed() {
        return heroPowerCanBePlayed;
    }

    public void setHeroPowerCanBePlayed(int heroPowerCanBePlayed) {
        this.heroPowerCanBePlayed = heroPowerCanBePlayed;
    }

    public boolean isHeroCanAttack() {
        return heroCanAttack;
    }

    public void setHeroCanAttack(boolean heroCanAttack) {
        this.heroCanAttack = heroCanAttack;
    }

    public boolean isCanDoAction() {
        return canDoAction;
    }

    public void setCanDoAction(boolean canDoAction) {
        this.canDoAction = canDoAction;
    }

    public void setHeroesList(ArrayList<Heroes> heroesList) {
        this.heroesList = heroesList;
    }
}
