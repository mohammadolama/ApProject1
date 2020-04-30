package Main;

import AllCards.Cards;
import AllCards.Spell;
import AllCards.Weapon;
import Heros.Hero;

import java.util.*;
import java.util.Collections;

public class GameManager {

    private Player player;
    private InfoPassive infoPassive;
    private int startingMana;
    private int totalMana;
    private int notUsedMana;
    private int drawCardNum;
    private int heroPowerUsageTime;
    private int heroPowerManaDecrease;
    private int manaDecrease;
    private int defenceAdd;
    private ArrayList<Cards> cardsofPlayer;
    private ArrayList<Cards> deckCards;
    private ArrayList<Cards> handCards;
    private ArrayList<Cards> playedCards;
    private ArrayList<String> gameLog;
    private Weapon weapon;
    private Hero playerHero;

    private GameManager(ArrayList<Cards> arrayList) {
        playedCards = new ArrayList<>();
        deckCards = new ArrayList<>();
        handCards = new ArrayList<>();
        gameLog = new ArrayList<>();
        cardsofPlayer = arrayList;
        initCards(cardsofPlayer);

    }

    public GameManager(Player player, InfoPassive infoPassive, ArrayList<Cards> arrayList) {
        this(arrayList);
        this.player = player;
        this.playerHero = player.getSelectedDeck().getHero();
        this.infoPassive = infoPassive;
        init(infoPassive);
    }


    private void init(InfoPassive infoPassive) {
        startingMana = 1;
        totalMana = 1;
        notUsedMana = 1;
        drawCardNum = 1;
        heroPowerUsageTime = 1;
        heroPowerManaDecrease = 0;
        manaDecrease = 0;
        defenceAdd = 0;
        String st = infoPassive.getName();
        if (st.equalsIgnoreCase("twiceDraw")) {
            drawCardNum = 2;
        } else if (st.equalsIgnoreCase("offCards")) {
            manaDecrease = 1;
        } else if (st.equalsIgnoreCase("warriors")) {
            defenceAdd = 2;
        } else if (st.equalsIgnoreCase("manaJump")) {
            startingMana = 2;
            totalMana = 2;
            notUsedMana = 2;
        } else if (st.equalsIgnoreCase("freePower")) {
            heroPowerUsageTime = 2;
            heroPowerManaDecrease = 1;
        }
    }

    public void nextCard() {
        if (deckCards.size() > 0) {
            for (int i = 0; i < drawCardNum; i++) {
                Cards cards = randomCardDraw(deckCards);
                if (handCards.size() < 12) {
                    addCard(handCards, cards);
                }
                removeCard(deckCards, cards);
                if (deckCards.size() == 0) {
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
        handCards = ar;
        deckCards = arrayList;
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
        if (totalMana < 10) {
            totalMana++;
        }
        notUsedMana = totalMana;
    }

    boolean canBePlayed(Cards cards) {
        //////////////////////////////////////////////////////////
        if (cards.getManaCost() >= notUsedMana) {
            return true;
        }
        return false;
    }

    void decreaseMana(Cards cards) {
        notUsedMana -= notUsedMana - cards.getManaCost();
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public InfoPassive getInfoPassive() {
        return infoPassive;
    }

    public void setInfoPassive(InfoPassive infoPassive) {
        this.infoPassive = infoPassive;
    }

    public int getStartingMana() {
        return startingMana;
    }

    public void setStartingMana(int startingMana) {
        this.startingMana = startingMana;
    }


    public int getTotalMana() {
        return totalMana;
    }

    public void setTotalMana(int totalMana) {
        this.totalMana = totalMana;
    }

    public int getNotUsedMana() {
        return notUsedMana;
    }

    public void setNotUsedMana(int notUsedMana) {
        this.notUsedMana = notUsedMana;
    }

    public int getDrawCardNum() {
        return drawCardNum;
    }

    public void setDrawCardNum(int drawCardNum) {
        this.drawCardNum = drawCardNum;
    }

    public int getHeroPowerUsageTime() {
        return heroPowerUsageTime;
    }

    public void setHeroPowerUsageTime(int heroPowerUsageTime) {
        this.heroPowerUsageTime = heroPowerUsageTime;
    }

    public int getHeroPowerManaDecrease() {
        return heroPowerManaDecrease;
    }

    public void setHeroPowerManaDecrease(int heroPowerManaDecrease) {
        this.heroPowerManaDecrease = heroPowerManaDecrease;
    }

    public int getManaDecrease() {
        return manaDecrease;
    }

    public void setManaDecrease(int manaDecrease) {
        this.manaDecrease = manaDecrease;
    }

    public int getDefenceAdd() {
        return defenceAdd;
    }

    public void setDefenceAdd(int defenceAdd) {
        this.defenceAdd = defenceAdd;
    }

    public ArrayList<Cards> getCardsofPlayer() {
        return cardsofPlayer;
    }

    public void setCardsofPlayer(ArrayList<Cards> cardsofPlayer) {
        this.cardsofPlayer = cardsofPlayer;
    }

    public ArrayList<Cards> getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(ArrayList<Cards> deckCards) {
        this.deckCards = deckCards;
    }

    public ArrayList<Cards> getHandCards() {
        return handCards;
    }

    public void setHandCards(ArrayList<Cards> handCards) {
        this.handCards = handCards;
    }

    public ArrayList<Cards> getPlayedCards() {
        return playedCards;
    }

    public void setPlayedCards(ArrayList<Cards> playedCards) {
        this.playedCards = playedCards;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Hero getPlayerHero() {
        return playerHero;
    }

    public void setPlayerHero(Hero playerHero) {
        this.playerHero = playerHero;
    }

    public ArrayList<String> getGameLog() {
        return gameLog;
    }

    public void setGameLog(ArrayList<String> gameLog) {
        this.gameLog = gameLog;
    }
}


