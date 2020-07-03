package Controller;

import Main.Deck;
import Main.InfoPassive;
import Main.JsonReaders;
import Main.Player;
import Model.Cards.Card;
import Model.Cards.Minion;
import Model.Cards.Spell;
import Model.Cards.Weapon;
import Model.Enums.Attribute;
import Model.Heros.Hero;

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
    private ArrayList<Card> friendlyCardsOfPlayer;
    private ArrayList<Card> enemyCardsOfPlayer;
    private ArrayList<Card> friendlyDeckCards;
    private ArrayList<Card> friendLyHandCards;
    private ArrayList<Card> friendlyPlayedCards;
    private ArrayList<Card> enemyCardsofPlayer;
    private ArrayList<Card> enemyDeckCards;
    private ArrayList<Card> enemyHandCards;
    private ArrayList<Card> enemyPlayedCards;
    private ArrayList<String> gameLog;
    private Weapon friendlyWeapon;
    private Weapon enemyWeapon;
    private Hero friendlyPlayerHero;
    private Hero enemyPlayerHero;


    private GameManager(ArrayList<Card> arrayList) {
        friendlyPlayedCards = new ArrayList<>();
        friendlyDeckCards = new ArrayList<>();
        friendLyHandCards = new ArrayList<>();
        gameLog = new ArrayList<>();
        friendlyCardsOfPlayer = arrayList;
        initCards(friendlyCardsOfPlayer);

    }

    public GameManager(Player player, InfoPassive infoPassive, ArrayList<Card> arrayList) {
        this(arrayList);
        this.friendlyPlayer = player;
        this.friendlyPlayerHero = player.getSelectedDeck().getHero();
        this.friendlyInfoPassive = infoPassive;
        friendlyInfoInitilize(infoPassive);
        enemyInit();
    }

    public GameManager(Player player, InfoPassive infoPassive, ArrayList<Card> arrayList,
                       String card1, String card2, String card3) {
        this(arrayList, card1, card2, card3);
        this.friendlyPlayer = player;
        this.friendlyPlayerHero = player.getSelectedDeck().getHero();
        this.friendlyInfoPassive = infoPassive;
        friendlyInfoInitilize(infoPassive);
        enemyInit();
    }

    private GameManager(ArrayList<Card> arrayList, String card1, String card2, String card3) {
        friendlyPlayedCards = new ArrayList<>();
        friendlyDeckCards = new ArrayList<>();
        friendLyHandCards = new ArrayList<>();
        gameLog = new ArrayList<>();
        friendlyCardsOfPlayer = arrayList;
        initilizeCards(friendlyCardsOfPlayer, card1, card2, card3);

    }

    void initilizeCards(ArrayList<Card> arrayList, String card1, String card2, String card3) {
        ArrayList<Card> ar = new ArrayList<>();
        for (Card card : arrayList) {
            if (card.getName().equalsIgnoreCase(card1)) {
                ar.add(card);
                arrayList.remove(card);
                break;
            }
        }
        for (Card card : arrayList) {
            if (card.getName().equalsIgnoreCase(card2)) {
                ar.add(card);
                arrayList.remove(card);
                break;
            }
        }
        for (Card card : arrayList) {
            if (card.getName().equalsIgnoreCase(card3)) {
                ar.add(card);
                arrayList.remove(card);
                break;
            }
        }

        friendLyHandCards = ar;
        friendlyDeckCards = arrayList;
    }


    private void enemyInit() {
        Player player = JsonReaders.PlayerJsonReader("enemy");
        this.enemyPlayer = player;
        enemyInfoPassive = InfoPassive.sample();
        enemyInfoInitilize(enemyInfoPassive);

        ArrayList<Card> ar1 = Deck.UpdateDeck(player.getSelectedDeck().getDeck());
        ThreePrimitiveRandom(ar1, "enemy");
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

        ArrayList<Card> handsTemp=this.friendLyHandCards;
        friendLyHandCards=enemyHandCards;
        enemyHandCards=handsTemp;

        ArrayList<Card> deckTemp=this.friendlyDeckCards;
        friendlyDeckCards=enemyDeckCards;
        enemyDeckCards=deckTemp;

        ArrayList<Card> playedTemp=this.friendlyPlayedCards;
        friendlyPlayedCards=enemyPlayedCards;
        enemyPlayedCards=playedTemp;

        Weapon temp4=this.friendlyWeapon;
        friendlyWeapon=enemyWeapon;
        enemyWeapon=temp4;

        Hero heroTemp=friendlyPlayerHero;
        friendlyPlayerHero=enemyPlayerHero;
        enemyPlayerHero=heroTemp;
    }

    private void friendlyInfoInitilize(InfoPassive infoPassive) {
        friendlyStartingMana = 8;
        friendlyTotalMana = 8;
        friendlyNotUsedMana = 8;
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

    private void enemyInfoInitilize(InfoPassive infoPassive) {
        enemyStartingMana = 8;
        enemyTotalMana = 8;
        enemyNotUsedMana = 8;
        enemyDrawCardNum = 1;
        enemyHeroPowerUsageTime = 1;
        enemyPowerManaDecrease = 0;
        enemyManaDecrease = 0;
        enemyDefenceAdd = 0;
        String st = infoPassive.getName();
        if (st.equalsIgnoreCase("twiceDraw")) {
            enemyDrawCardNum = 2;
        } else if (st.equalsIgnoreCase("offCards")) {
            enemyManaDecrease = 1;
        } else if (st.equalsIgnoreCase("warriors")) {
            enemyDefenceAdd = 2;
        } else if (st.equalsIgnoreCase("manaJump")) {
            enemyStartingMana = 2;
            enemyTotalMana = 2;
            enemyNotUsedMana = 2;
        } else if (st.equalsIgnoreCase("freePower")) {
            enemyHeroPowerUsageTime = 2;
            enemyPowerManaDecrease = 1;
        }
    }




    public void nextCard() {
        if (friendlyDeckCards.size() > 0) {
            for (int i = 0; i < friendlyDrawCardNum; i++) {
                Card cards = randomCardDraw(friendlyDeckCards);
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

    private void initCards(ArrayList<Card> arrayList) {
        ThreePrimitiveRandom(arrayList, "friendly");
    }

    private void ThreePrimitiveRandom(ArrayList<Card> arrayList, String value) {
        ListIterator<Card> iterator = arrayList.listIterator();
        ArrayList<Card> ar = new ArrayList<>();
        while (iterator.hasNext()) {
            Card cards = iterator.next();
            if (cards instanceof Spell && ((Spell) cards).getAttributes() != null && ((Spell) cards).getAttributes().contains(Attribute.Reward)) {
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
        if (value.equalsIgnoreCase("friendly")) {
            friendLyHandCards = ar;
            friendlyDeckCards = arrayList;
        } else {
            enemyHandCards = ar;
            enemyDeckCards = arrayList;
        }
    }

    private Card randomCardDraw(ArrayList<Card> cards) {
        if (cards.size() == 0)
            return null;
        Collections.shuffle(cards);
        Collections.shuffle(cards);
        Collections.shuffle(cards);
        return cards.get(0);
    }

    private void addCard(ArrayList<Card> arrayList, Card cards) {
        arrayList.add(cards);
    }

    private void removeCard(ArrayList<Card> arrayList, Card cards) {
        arrayList.remove(cards);
    }

    public void refillMana() {
        if (friendlyTotalMana < 10) {
            friendlyTotalMana++;
        }
        friendlyNotUsedMana = friendlyTotalMana;
    }

    private void wakeUp(){
        for (Card card : friendlyPlayedCards) {
            ((Minion)card).setSleep(false);
        }
    }

    private void canBeAttackedUpdater(){
        boolean flag=false;
        for (Card cards : enemyPlayedCards) {
            if (((Minion) cards).getAttributes() != null && ((Minion) cards).getAttributes().contains(Attribute.Taunt)) {
                flag=true;
                break;
            }
        }
        if (flag) {
            for (Card cards : enemyPlayedCards) {
                if (((Minion) cards).getAttributes() != null && ((Minion) cards).getAttributes().contains(Attribute.Taunt)) {
                    ((Minion)cards).setCanBeAttacked(true);
                }else {
                    ((Minion)cards).setCanBeAttacked(false);
                }
            }
        }else {
            for (Card card : enemyPlayedCards) {
                ((Minion)card).setCanBeAttacked(true);
            }
        }
    }

    public void endTurn() {
        reversePlayers();
        checkDestroyMinion();
        canBeAttackedUpdater();
        wakeUp();
        refillMana();
        nextCard();
    }

    protected void checkDestroyMinion() {
        this.enemyPlayedCards.removeIf(card -> ((Minion) card).getHealth() <= 0);
        ListIterator<Card> iterator = this.enemyPlayedCards.listIterator();
        while (iterator.hasNext()) {
            if (((Minion) iterator.next()).getHealth() <= 0) {
                iterator.remove();
                enemyPlayerHero.setDefence(enemyPlayerHero.getDefence() + enemyDefenceAdd);
            }
        }
        ListIterator<Card> iterator1 = this.friendlyPlayedCards.listIterator();
        while (iterator1.hasNext()) {
            if (((Minion) iterator1.next()).getHealth() <= 0) {
                iterator1.remove();
                friendlyPlayerHero.setDefence(friendlyPlayerHero.getDefence() + friendlyDefenceAdd);
            }
        }
    }

    boolean canBePlayed(Card cards) {
        //////////////////////////////////////////////////////////
        if (cards.getManaCost() >= friendlyNotUsedMana) {
            return true;
        }
        return false;
    }

    void decreaseMana(Card cards) {
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

    public ArrayList<Card> getFriendlyCardsOfPlayer() {
        return friendlyCardsOfPlayer;
    }

    public void setFriendlyCardsOfPlayer(ArrayList<Card> friendlyCardsOfPlayer) {
        this.friendlyCardsOfPlayer = friendlyCardsOfPlayer;
    }

    public ArrayList<Card> getFriendlyDeckCards() {
        return friendlyDeckCards;
    }

    public void setFriendlyDeckCards(ArrayList<Card> friendlyDeckCards) {
        this.friendlyDeckCards = friendlyDeckCards;
    }

    public ArrayList<Card> getFriendLyHandCards() {
        return friendLyHandCards;
    }

    public void setFriendLyHandCards(ArrayList<Card> friendLyHandCards) {
        this.friendLyHandCards = friendLyHandCards;
    }

    public ArrayList<Card> getFriendlyPlayedCards() {
        return friendlyPlayedCards;
    }

    public void setFriendlyPlayedCards(ArrayList<Card> friendlyPlayedCards) {
        this.friendlyPlayedCards = friendlyPlayedCards;
    }

    public Weapon getFriendlyWeapon() {
        return friendlyWeapon;
    }

    public void setFriendlyWeapon(Weapon friendlyWeapon) {
        this.friendlyWeapon = friendlyWeapon;
        this.friendlyPlayerHero.setAttack(friendlyWeapon.getAttack());
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

    public ArrayList<Card> getEnemyCardsofPlayer() {
        return enemyCardsofPlayer;
    }

    public void setEnemyCardsofPlayer(ArrayList<Card> enemyCardsofPlayer) {
        this.enemyCardsofPlayer = enemyCardsofPlayer;
    }

    public ArrayList<Card> getEnemyDeckCards() {
        return enemyDeckCards;
    }

    public void setEnemyDeckCards(ArrayList<Card> enemyDeckCards) {
        this.enemyDeckCards = enemyDeckCards;
    }

    public ArrayList<Card> getEnemyHandCards() {
        return enemyHandCards;
    }

    public void setEnemyHandCards(ArrayList<Card> enemyHandCards) {
        this.enemyHandCards = enemyHandCards;
    }

    public ArrayList<Card> getEnemyPlayedCards() {
        return enemyPlayedCards;
    }

    public void setEnemyPlayedCards(ArrayList<Card> enemyPlayedCards) {
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

    public ArrayList<Card> getEnemyCardsOfPlayer() {
        return enemyCardsOfPlayer;
    }

    public void setEnemyCardsOfPlayer(ArrayList<Card> enemyCardsOfPlayer) {
        this.enemyCardsOfPlayer = enemyCardsOfPlayer;
    }
}


