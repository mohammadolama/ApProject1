package Main;

import AllCards.Cards;
import AllCards.Spell;
import AllCards.Weapon;
import Heros.Hero;

import java.util.*;
import java.util.Collections;

public class GameManager {

    private Player friendlyPlayer;
    private Player enemyPlayer;
    private InfoPassive friendlyInfoPassive;
    private InfoPassive enemyInfoPassive;
    private int friendlyStartingMana;
    private int enemyStartingMana;
    private int friendlyTotalMana;
    private int enemyTotalMana;
    private int friendlyNotUsedMana;
    private int enemyNotUsedMana;
    private int friendlyDrawCardNum;
    private int enemyDrawCardNum;
    private int friendlyHeroPowerUsageTime;
    private int enemyHeroPowerUsageTime;
    private int friendlyPowerManaDecrease;
    private int enemyPowerManaDecrease;
    private int friendlyManaDecrease;
    private int enemyManaDecrease;
    private int friendlyDefenceAdd;
    private int enemyDefenceAdd;
    private ArrayList<Cards> friendlyCardsOfPlayer;
    private ArrayList<Cards> enemyCardsOfPlayer;
    private ArrayList<Cards> friendlyDeckCards;
    private ArrayList<Cards> friendLyHandCards;
    private ArrayList<Cards> friendlyPlayedCards;
    private ArrayList<Cards> enemyCardsofPlayer;
    private ArrayList<Cards> enemyDeckCards;
    private ArrayList<Cards> enemyHandCards;
    private ArrayList<Cards> enemyPlayedCards;
    private ArrayList<String> gameLog;
    private Weapon friendlyWeapon;
    private Weapon enemyWeapon;
    private Hero friendlyPlayerHero;
    private Hero enemyPlayerHero;


    private GameManager(ArrayList<Cards> arrayList) {
        friendlyPlayedCards = new ArrayList<>();
        friendlyDeckCards = new ArrayList<>();
        friendLyHandCards = new ArrayList<>();
        gameLog = new ArrayList<>();
        friendlyCardsOfPlayer = arrayList;
        initCards(friendlyCardsOfPlayer);

    }

    public GameManager(Player player, InfoPassive infoPassive, ArrayList<Cards> arrayList) {
        this(arrayList);
        this.friendlyPlayer = player;
        this.friendlyPlayerHero = player.getSelectedDeck().getHero();
        this.friendlyInfoPassive = infoPassive;
        init(infoPassive);
        enemyInit();
    }

    private void enemyInit() {
        Player player = JsonReaders.PlayerJsonReader("enemy");
        this.enemyPlayer = player;
        enemyInfoPassive = InfoPassive.sample();
        this.enemyStartingMana = 1;
        friendlyStartingMana = 1;
        enemyTotalMana = 1;
        enemyNotUsedMana = 1;
        enemyDrawCardNum = 1;
        enemyHeroPowerUsageTime = 1;
        enemyPowerManaDecrease = 0;
        enemyManaDecrease = 1;
        enemyDefenceAdd = 0;

        ArrayList<Cards> ar1=Deck.UpdateDeck(player.getSelectedDeck().getDeck());
        ArrayList<Cards> ar2=new ArrayList<>();
        int i=0;
        while (ar2.size()<3){
            ar2.add(ar1.get(i));
            ar1.remove(i);
        }
        this.enemyHandCards=ar2;
        this.enemyDeckCards=ar1;
        this.enemyPlayedCards=new ArrayList<>();
        this.enemyCardsofPlayer=new ArrayList<>();
        this.enemyPlayerHero=player.getSelectedDeck().getHero();
    }

    private void reversePlayers() {
        Player playerTemp = this.friendlyPlayer;
        friendlyPlayer = enemyPlayer;
        enemyPlayer = playerTemp;


        InfoPassive tempInfo=this.friendlyInfoPassive;
        friendlyInfoPassive=enemyInfoPassive;
        enemyInfoPassive=tempInfo;

        int startingManaTemp=this.friendlyStartingMana;
        friendlyStartingMana=enemyStartingMana;
        enemyStartingMana=startingManaTemp;

        int totalManaTemp=friendlyTotalMana;
        friendlyTotalMana=enemyTotalMana;
        enemyTotalMana=totalManaTemp;

        int notUsedTemp=friendlyNotUsedMana;
        friendlyNotUsedMana=enemyNotUsedMana;
        enemyNotUsedMana=notUsedTemp;

        int drawNumTemp=friendlyDrawCardNum;
        friendlyDrawCardNum=enemyDrawCardNum;
        enemyDrawCardNum=drawNumTemp;

        int heropowerUsageTemp=friendlyHeroPowerUsageTime;
        friendlyHeroPowerUsageTime=enemyHeroPowerUsageTime;
        enemyHeroPowerUsageTime=heropowerUsageTemp;

        int manaDecTemp=friendlyManaDecrease;
        friendlyManaDecrease=enemyManaDecrease;
        enemyManaDecrease=manaDecTemp;

        int powerManaTemp=friendlyPowerManaDecrease;
        friendlyPowerManaDecrease=enemyPowerManaDecrease;
        enemyPowerManaDecrease=powerManaTemp;

        int defenceTemp=friendlyDefenceAdd;
        friendlyDefenceAdd=enemyDefenceAdd;
        enemyDefenceAdd=defenceTemp;

        ArrayList<Cards> handsTemp=this.friendLyHandCards;
        friendLyHandCards=enemyHandCards;
        enemyHandCards=handsTemp;

        ArrayList<Cards> deckTemp=this.friendlyDeckCards;
        friendlyDeckCards=enemyDeckCards;
        enemyDeckCards=deckTemp;

        ArrayList<Cards> playedTemp=this.friendlyPlayedCards;
        friendlyPlayedCards=enemyPlayedCards;
        enemyPlayedCards=playedTemp;

        Weapon temp4=this.friendlyWeapon;
        friendlyWeapon=enemyWeapon;
        enemyWeapon=temp4;

        Hero heroTemp=friendlyPlayerHero;
        friendlyPlayerHero=enemyPlayerHero;
        enemyPlayerHero=heroTemp;
    }


    private void init(InfoPassive infoPassive) {
        friendlyStartingMana = 1;
        friendlyTotalMana = 1;
        friendlyNotUsedMana = 1;
        friendlyDrawCardNum = 1;
        friendlyHeroPowerUsageTime = 1;
        friendlyPowerManaDecrease = 0;
        friendlyManaDecrease = 0;
        friendlyDefenceAdd = 0;
        String st = infoPassive.getName();
        if (st.equalsIgnoreCase("twiceDraw")) {
            friendlyDrawCardNum = 2;
        } else if (st.equalsIgnoreCase("offCards")) {
            friendlyManaDecrease = 1;
        } else if (st.equalsIgnoreCase("warriors")) {
            friendlyDefenceAdd = 2;
        } else if (st.equalsIgnoreCase("manaJump")) {
            friendlyStartingMana = 2;
            friendlyTotalMana = 2;
            friendlyNotUsedMana = 2;
        } else if (st.equalsIgnoreCase("freePower")) {
            friendlyHeroPowerUsageTime = 2;
            friendlyPowerManaDecrease = 1;
        }
    }

    public void nextCard() {
        if (friendlyDeckCards.size() > 0) {
            for (int i = 0; i < friendlyDrawCardNum; i++) {
                Cards cards = randomCardDraw(friendlyDeckCards);
                if (friendLyHandCards.size() < 12) {
                    addCard(friendLyHandCards, cards);
                }
                removeCard(friendlyDeckCards, cards);
                if (friendlyDeckCards.size() == 0) {
                    break;
                }
            }

        }
    }


    public void updateGameLog(String string) {
        gameLog.add(string);
        if (gameLog.size() > 25) {
            gameLog.remove(0);
        }
    }

    private void initCards(ArrayList<Cards> arrayList) {
        threePrimitiveRandom(arrayList);
    }

    private void threePrimitiveRandom(ArrayList<Cards> arrayList) {
        ListIterator<Cards> iterator = arrayList.listIterator();
        ArrayList<Cards> ar = new ArrayList<>();
        while (iterator.hasNext()) {
            Cards cards = iterator.next();
            if (cards instanceof Spell && ((Spell) cards).getTitle() != null && ((Spell) cards).getTitle().equalsIgnoreCase("quest")) {
                ar.add(cards);
                iterator.remove();
                if (ar.size() == 3)
                    break;
            }
        }
        Collections.shuffle(arrayList);
        int i = 0;
        while (ar.size() < 3) {
            ar.add(arrayList.get(i));
            arrayList.remove(i);
        }
        friendLyHandCards = ar;
        friendlyDeckCards = arrayList;
    }

    private Cards randomCardDraw(ArrayList<Cards> cards) {
        if (cards.size() == 0)
            return null;
        Collections.shuffle(cards);
        Collections.shuffle(cards);
        Collections.shuffle(cards);
        return cards.get(0);
    }

    private void addCard(ArrayList<Cards> arrayList, Cards cards) {
        arrayList.add(cards);
    }

    private void removeCard(ArrayList<Cards> arrayList, Cards cards) {
        arrayList.remove(cards);
    }

    public void refillMana() {
        if (friendlyTotalMana < 10) {
            friendlyTotalMana++;
        }
        friendlyNotUsedMana = friendlyTotalMana;
    }

    public void endTurn() {
        reversePlayers();
        refillMana();
        nextCard();
    }

    boolean canBePlayed(Cards cards) {
        //////////////////////////////////////////////////////////
        if (cards.getManaCost() >= friendlyNotUsedMana) {
            return true;
        }
        return false;
    }

    void decreaseMana(Cards cards) {
        friendlyNotUsedMana -= friendlyNotUsedMana - cards.getManaCost();
    }


    public Player getFriendlyPlayer() {
        return friendlyPlayer;
    }

    public void setFriendlyPlayer(Player friendlyPlayer) {
        this.friendlyPlayer = friendlyPlayer;
    }

    public InfoPassive getFriendlyInfoPassive() {
        return friendlyInfoPassive;
    }

    public void setFriendlyInfoPassive(InfoPassive friendlyInfoPassive) {
        this.friendlyInfoPassive = friendlyInfoPassive;
    }

    public int getFriendlyStartingMana() {
        return friendlyStartingMana;
    }

    public void setFriendlyStartingMana(int friendlyStartingMana) {
        this.friendlyStartingMana = friendlyStartingMana;
    }

    public int getFriendlyTotalMana() {
        return friendlyTotalMana;
    }

    public void setFriendlyTotalMana(int friendlyTotalMana) {
        this.friendlyTotalMana = friendlyTotalMana;
    }

    public int getEnemyTotalMana() {
        return enemyTotalMana;
    }

    public void setEnemyTotalMana(int enemyTotalMana) {
        this.enemyTotalMana = enemyTotalMana;
    }

    public int getFriendlyNotUsedMana() {
        return friendlyNotUsedMana;
    }

    public void setFriendlyNotUsedMana(int friendlyNotUsedMana) {
        this.friendlyNotUsedMana = friendlyNotUsedMana;
    }

    public int getFriendlyDrawCardNum() {
        return friendlyDrawCardNum;
    }

    public void setFriendlyDrawCardNum(int friendlyDrawCardNum) {
        this.friendlyDrawCardNum = friendlyDrawCardNum;
    }

    public int getFriendlyHeroPowerUsageTime() {
        return friendlyHeroPowerUsageTime;
    }

    public void setFriendlyHeroPowerUsageTime(int friendlyHeroPowerUsageTime) {
        this.friendlyHeroPowerUsageTime = friendlyHeroPowerUsageTime;
    }

    public int getFriendlyPowerManaDecrease() {
        return friendlyPowerManaDecrease;
    }

    public void setFriendlyPowerManaDecrease(int friendlyPowerManaDecrease) {
        this.friendlyPowerManaDecrease = friendlyPowerManaDecrease;
    }

    public int getFriendlyManaDecrease() {
        return friendlyManaDecrease;
    }

    public void setFriendlyManaDecrease(int friendlyManaDecrease) {
        this.friendlyManaDecrease = friendlyManaDecrease;
    }

    public int getFriendlyDefenceAdd() {
        return friendlyDefenceAdd;
    }

    public void setFriendlyDefenceAdd(int friendlyDefenceAdd) {
        this.friendlyDefenceAdd = friendlyDefenceAdd;
    }

    public ArrayList<Cards> getFriendlyCardsOfPlayer() {
        return friendlyCardsOfPlayer;
    }

    public void setFriendlyCardsOfPlayer(ArrayList<Cards> friendlyCardsOfPlayer) {
        this.friendlyCardsOfPlayer = friendlyCardsOfPlayer;
    }

    public ArrayList<Cards> getFriendlyDeckCards() {
        return friendlyDeckCards;
    }

    public void setFriendlyDeckCards(ArrayList<Cards> friendlyDeckCards) {
        this.friendlyDeckCards = friendlyDeckCards;
    }

    public ArrayList<Cards> getFriendLyHandCards() {
        return friendLyHandCards;
    }

    public void setFriendLyHandCards(ArrayList<Cards> friendLyHandCards) {
        this.friendLyHandCards = friendLyHandCards;
    }

    public ArrayList<Cards> getFriendlyPlayedCards() {
        return friendlyPlayedCards;
    }

    public void setFriendlyPlayedCards(ArrayList<Cards> friendlyPlayedCards) {
        this.friendlyPlayedCards = friendlyPlayedCards;
    }

    public Weapon getFriendlyWeapon() {
        return friendlyWeapon;
    }

    public void setFriendlyWeapon(Weapon friendlyWeapon) {
        this.friendlyWeapon = friendlyWeapon;
    }

    public Hero getFriendlyPlayerHero() {
        return friendlyPlayerHero;
    }

    public void setFriendlyPlayerHero(Hero friendlyPlayerHero) {
        this.friendlyPlayerHero = friendlyPlayerHero;
    }

    public ArrayList<String> getGameLog() {
        return gameLog;
    }

    public void setGameLog(ArrayList<String> gameLog) {
        this.gameLog = gameLog;
    }

    public Player getEnemyPlayer() {
        return enemyPlayer;
    }

    public void setEnemyPlayer(Player enemyPlayer) {
        this.enemyPlayer = enemyPlayer;
    }

    public InfoPassive getEnemyInfoPassive() {
        return enemyInfoPassive;
    }

    public void setEnemyInfoPassive(InfoPassive enemyInfoPassive) {
        this.enemyInfoPassive = enemyInfoPassive;
    }

    public int getEnemyStartingMana() {
        return enemyStartingMana;
    }

    public void setEnemyStartingMana(int enemyStartingMana) {
        this.enemyStartingMana = enemyStartingMana;
    }

    public int getEnemyNotUsedMana() {
        return enemyNotUsedMana;
    }

    public void setEnemyNotUsedMana(int enemyNotUsedMana) {
        this.enemyNotUsedMana = enemyNotUsedMana;
    }

    public int getEnemyDrawCardNum() {
        return enemyDrawCardNum;
    }

    public void setEnemyDrawCardNum(int enemyDrawCardNum) {
        this.enemyDrawCardNum = enemyDrawCardNum;
    }

    public int getEnemyHeroPowerUsageTime() {
        return enemyHeroPowerUsageTime;
    }

    public void setEnemyHeroPowerUsageTime(int enemyHeroPowerUsageTime) {
        this.enemyHeroPowerUsageTime = enemyHeroPowerUsageTime;
    }

    public int getEnemyPowerManaDecrease() {
        return enemyPowerManaDecrease;
    }

    public void setEnemyPowerManaDecrease(int enemyPowerManaDecrease) {
        this.enemyPowerManaDecrease = enemyPowerManaDecrease;
    }

    public int getEnemyManaDecrease() {
        return enemyManaDecrease;
    }

    public void setEnemyManaDecrease(int enemyManaDecrease) {
        this.enemyManaDecrease = enemyManaDecrease;
    }

    public int getEnemyDefenceAdd() {
        return enemyDefenceAdd;
    }

    public void setEnemyDefenceAdd(int enemyDefenceAdd) {
        this.enemyDefenceAdd = enemyDefenceAdd;
    }

    public ArrayList<Cards> getEnemyCardsofPlayer() {
        return enemyCardsofPlayer;
    }

    public void setEnemyCardsofPlayer(ArrayList<Cards> enemyCardsofPlayer) {
        this.enemyCardsofPlayer = enemyCardsofPlayer;
    }

    public ArrayList<Cards> getEnemyDeckCards() {
        return enemyDeckCards;
    }

    public void setEnemyDeckCards(ArrayList<Cards> enemyDeckCards) {
        this.enemyDeckCards = enemyDeckCards;
    }

    public ArrayList<Cards> getEnemyHandCards() {
        return enemyHandCards;
    }

    public void setEnemyHandCards(ArrayList<Cards> enemyHandCards) {
        this.enemyHandCards = enemyHandCards;
    }

    public ArrayList<Cards> getEnemyPlayedCards() {
        return enemyPlayedCards;
    }

    public void setEnemyPlayedCards(ArrayList<Cards> enemyPlayedCards) {
        this.enemyPlayedCards = enemyPlayedCards;
    }

    public Weapon getEnemyWeapon() {
        return enemyWeapon;
    }

    public void setEnemyWeapon(Weapon enemyWeapon) {
        this.enemyWeapon = enemyWeapon;
    }

    public Hero getEnemyPlayerHero() {
        return enemyPlayerHero;
    }

    public void setEnemyPlayerHero(Hero enemyPlayerHero) {
        this.enemyPlayerHero = enemyPlayerHero;
    }

    public ArrayList<Cards> getEnemyCardsOfPlayer() {
        return enemyCardsOfPlayer;
    }

    public void setEnemyCardsOfPlayer(ArrayList<Cards> enemyCardsOfPlayer) {
        this.enemyCardsOfPlayer = enemyCardsOfPlayer;
    }
}


