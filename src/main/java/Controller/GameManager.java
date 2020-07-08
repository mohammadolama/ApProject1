package Controller;

import Configs.DeckReader;
import Controller.Actions.ActionVisitor;
import Controller.Actions.SPVisitor.*;
import Main.*;
import Model.ActionModel;
import Model.Cards.*;
import Model.Enums.Attribute;
import Model.Heros.*;
import Model.Interface.Character;

import java.util.*;


class GameManager {

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
    private int fHPMAXUT;
    private int eHPMAXUT;
    private int friendlyPowerManaDecrease;
    private int enemyPowerManaDecrease;
    private int friendlyManaDecrease;
    private int enemyManaDecrease;
    private int friendlyDefenceAdd;
    private int enemyDefenceAdd;
    private ArrayList<Card> friendlyCardsOfPlayer;
    private ArrayList<Card> friendlyDeckCards;
    private ArrayList<Card> friendLyHandCards;
    private ArrayList<Card> friendlyPlayedCards;
    private ArrayList<Card> enemyDeckCards;
    private ArrayList<Card> enemyHandCards;
    private ArrayList<Card> enemyPlayedCards;
    private ArrayList<String> gameLog;
    private ArrayList<Card> friendlyContiniousActionCard;
    private ArrayList<Card> enemyContiniousActionCard;
    private Weapon friendlyWeapon;
    private Weapon enemyWeapon;
    private Hero friendlyPlayerHero;
    private Hero playerHero;
    private Hero enemyPlayerHero;
    private boolean deckReaderMode = false;
    private boolean practiceMode = false;

    private GameManager() {
        friendlyPlayedCards = new ArrayList<>();
        friendlyDeckCards = new ArrayList<>();
        friendLyHandCards = new ArrayList<>();
        friendlyContiniousActionCard = new ArrayList<>();
        enemyContiniousActionCard = new ArrayList<>();
        gameLog = new ArrayList<>();
    }


    GameManager(Player player, InfoPassive infoPassive, ArrayList<Card> arrayList,
                String card1, String card2, String card3) {
        this(arrayList, card1, card2, card3);
        this.friendlyPlayer = player;
        try {
            this.friendlyPlayerHero = (Hero) player.getSelectedDeck().getHero().clone();
            this.playerHero = friendlyPlayerHero;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.friendlyInfoPassive = infoPassive;
        friendlyInfoInitilize(infoPassive);
        enemyInit();
        friendlyPlayerHero.accept(new SpecialPowerVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);
        enemyPlayerHero.accept(new SpecialPowerVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);
    }

    private GameManager(ArrayList<Card> arrayList, String card1, String card2, String card3) {
        this();
        friendlyCardsOfPlayer = arrayList;
        initilizeCards(friendlyCardsOfPlayer, card1, card2, card3);

    }

    public GameManager(InfoPassive infoPassive) {
        this();
        deckReaderMode = true;
        Player player = JsonReaders.deckReaderPlayer("hunter");
        this.friendlyPlayer = player;
        this.friendlyPlayerHero = player.getSelectedDeck().getHero();
        friendlyInfoInitilize(infoPassive);
        deckReaderEnemy();
        DeckReader deckReader = JsonReaders.deckReader();
        ArrayList<Card> friend = Deck.UpdateDeck(deckReader.getFriend());
        ArrayList<Card> enemy = Deck.UpdateDeck(deckReader.getEnemy());
        ThreePrimitiveRandom(friend, "friendly");
        ThreePrimitiveRandom(enemy, "enemy");
        friendlyPlayerHero.accept(new SpecialPowerVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);
        enemyPlayerHero.accept(new SpecialPowerVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);
    }


    private void initilizeCards(ArrayList<Card> arrayList, String card1, String card2, String card3) {
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
        this.enemyPlayedCards = new ArrayList<>();
        this.enemyPlayerHero = player.getSelectedDeck().getHero();
    }

    private void deckReaderEnemy() {
        Player player = JsonReaders.deckReaderPlayer("mage");
        this.enemyPlayer = player;
        enemyInfoPassive = InfoPassive.sample();
        enemyInfoInitilize(enemyInfoPassive);
        this.enemyPlayedCards = new ArrayList<>();
        this.enemyPlayerHero = player.getSelectedDeck().getHero();
    }


    private void reversePlayers() {
        Player playerTemp = this.friendlyPlayer;
        friendlyPlayer = enemyPlayer;
        enemyPlayer = playerTemp;

        InfoPassive tempInfo = this.friendlyInfoPassive;
        friendlyInfoPassive = enemyInfoPassive;
        enemyInfoPassive = tempInfo;

        int startingManaTemp = this.friendlyStartingMana;
        friendlyStartingMana = enemyStartingMana;
        enemyStartingMana = startingManaTemp;

        int totalManaTemp = friendlyTotalMana;
        friendlyTotalMana = enemyTotalMana;
        enemyTotalMana = totalManaTemp;

        int notUsedTemp = friendlyNotUsedMana;
        friendlyNotUsedMana = enemyNotUsedMana;
        enemyNotUsedMana = notUsedTemp;

        int drawNumTemp = friendlyDrawCardNum;
        friendlyDrawCardNum = enemyDrawCardNum;
        enemyDrawCardNum = drawNumTemp;

        int heropowerUsageTemp = friendlyHeroPowerUsageTime;
        friendlyHeroPowerUsageTime = enemyHeroPowerUsageTime;
        enemyHeroPowerUsageTime = heropowerUsageTemp;

        int HPMaxTemp = fHPMAXUT;
        fHPMAXUT = eHPMAXUT;
        eHPMAXUT = HPMaxTemp;

        int manaDecTemp = friendlyManaDecrease;
        friendlyManaDecrease = enemyManaDecrease;
        enemyManaDecrease = manaDecTemp;

        int powerManaTemp = friendlyPowerManaDecrease;
        friendlyPowerManaDecrease = enemyPowerManaDecrease;
        enemyPowerManaDecrease = powerManaTemp;

        int defenceTemp = friendlyDefenceAdd;
        friendlyDefenceAdd = enemyDefenceAdd;
        enemyDefenceAdd = defenceTemp;

        ArrayList<Card> handsTemp = this.friendLyHandCards;
        friendLyHandCards = enemyHandCards;
        enemyHandCards = handsTemp;

        ArrayList<Card> deckTemp = this.friendlyDeckCards;
        friendlyDeckCards = enemyDeckCards;
        enemyDeckCards = deckTemp;

        ArrayList<Card> playedTemp = this.friendlyPlayedCards;
        friendlyPlayedCards = enemyPlayedCards;
        enemyPlayedCards = playedTemp;

        ArrayList<Card> continiousTemp = this.friendlyContiniousActionCard;
        friendlyContiniousActionCard = enemyContiniousActionCard;
        enemyContiniousActionCard = continiousTemp;

        Weapon temp4 = this.friendlyWeapon;
        friendlyWeapon = enemyWeapon;
        enemyWeapon = temp4;

        Hero heroTemp = friendlyPlayerHero;
        friendlyPlayerHero = enemyPlayerHero;
        enemyPlayerHero = heroTemp;
    }

    private void friendlyInfoInitilize(InfoPassive infoPassive) {
        friendlyStartingMana = 8;
        friendlyTotalMana = 8;
        friendlyNotUsedMana = 8;
        friendlyDrawCardNum = 1;
        friendlyHeroPowerUsageTime = 1;
        fHPMAXUT = 1;
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
            fHPMAXUT = 2;
            friendlyPowerManaDecrease = 1;
        }
    }

    private void enemyInfoInitilize(InfoPassive infoPassive) {
        enemyStartingMana = 8;
        enemyTotalMana = 8;
        enemyNotUsedMana = 8;
        enemyDrawCardNum = 1;
        enemyHeroPowerUsageTime = 1;
        eHPMAXUT = 1;
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
            eHPMAXUT = 2;
            enemyPowerManaDecrease = 1;
        }
    }


    void drawCard(int j, String mode) {
        if (friendlyDeckCards.size() < j) {
            j = friendlyDeckCards.size();
        }
        for (int i = 0; i < j; i++) {
            Card cards = randomCardDraw(friendlyDeckCards);
            if (friendLyHandCards.size() < 12) {
                if (mode == null || (mode.equalsIgnoreCase("extra") && !(cards instanceof Spell))) {
                    addCard(friendLyHandCards, cards);
                    matinAction();
                }
            }
            removeCard(friendlyDeckCards, cards);
        }
    }


    void updateGameLog(String string) {
        gameLog.add(string);
        if (gameLog.size() > 25) {
            gameLog.remove(0);
        }
    }

    private void ThreePrimitiveRandom(ArrayList<Card> arrayList, String value) {
        ListIterator<Card> iterator = arrayList.listIterator();
        ArrayList<Card> ar = new ArrayList<>();
        while (iterator.hasNext()) {
            Card cards = iterator.next();
            ar.add(cards);
            iterator.remove();
            if (ar.size() == 3)
                break;
        }
        while (ar.size() < 3) {
            ar.add(arrayList.get(0));
            arrayList.remove(0);
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
        if (deckReaderMode) {
            return cards.get(0);
        }
        Collections.shuffle(cards);
        return cards.get(0);
    }

    private void addCard(ArrayList<Card> arrayList, Card cards) {
        arrayList.add(cards);
    }

    private void removeCard(ArrayList<Card> arrayList, Card cards) {
        arrayList.remove(cards);
    }

    private void refillMana() {
        if (friendlyTotalMana < 10) {
            friendlyTotalMana++;
        }
        friendlyNotUsedMana = friendlyTotalMana;
        friendlyHeroPowerUsageTime = fHPMAXUT;
    }

    private void wakeUp() {
        for (Card card : friendlyPlayedCards) {
            ((Minion) card).setSleep(false);
        }
    }

    private void canBeAttackedUpdater() {
        boolean flag = false;
        for (Card cards : enemyPlayedCards) {
            if (cards.getAttributes() != null && cards.getAttributes().contains(Attribute.Taunt)) {
                flag = true;
                break;
            }
        }
        if (flag) {
            for (Card cards : enemyPlayedCards) {
                if (cards.getAttributes() != null && cards.getAttributes().contains(Attribute.Taunt)) {
                    ((Minion) cards).setCanBeAttacked(true);
                } else {
                    ((Minion) cards).setCanBeAttacked(false);
                }
            }
        } else {
            for (Card card : enemyPlayedCards) {
                ((Minion) card).setCanBeAttacked(true);
            }
        }
    }

    void endTurn() {
        benyaminAction();
        reversePlayers();
        checkDestroyMinion();
        canBeAttackedUpdater();
        wakeUp();
        refillMana();
        drawCard(friendlyDrawCardNum, null);
    }


    void practiceModeEndTurn() {
        benyaminAction();
        checkDestroyMinion();
        canBeAttackedUpdater();
        wakeUp();
        refillMana();

    }

    void checkDestroyMinion() {
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

    private void checkDestroyedWeapon() {
        if (friendlyWeapon != null) {
            if (friendlyWeapon.getDurability() <= 0) {
                setFriendlyWeapon(null);
            }
        }
    }

    void updateWeapon() {
        Weapon weapon = this.friendlyWeapon;
        weapon.setDurability(weapon.getDurability() - 1);
        setFriendlyWeapon(weapon);
        checkDestroyedWeapon();
    }

    Player getFriendlyPlayer() {
        return friendlyPlayer;
    }

    int getFriendlyTotalMana() {
        return friendlyTotalMana;
    }

    int getFriendlyNotUsedMana() {
        return friendlyNotUsedMana;
    }

    void setFriendlyNotUsedMana(int friendlyNotUsedMana) {
        this.friendlyNotUsedMana = friendlyNotUsedMana;
    }

    int getFriendlyHeroPowerUsageTime() {
        return friendlyHeroPowerUsageTime;
    }

    int getFriendlyPowerManaDecrease() {
        return friendlyPowerManaDecrease;
    }

    int getFriendlyManaDecrease() {
        return friendlyManaDecrease;
    }

    ArrayList<Card> getFriendlyDeckCards() {
        return friendlyDeckCards;
    }

    ArrayList<Card> getFriendLyHandCards() {
        return friendLyHandCards;
    }

    ArrayList<Card> getFriendlyPlayedCards() {
        return friendlyPlayedCards;
    }

    Weapon getFriendlyWeapon() {
        return friendlyWeapon;
    }

    void setFriendlyWeapon(Weapon friendlyWeapon) {
        this.friendlyWeapon = friendlyWeapon;
        this.friendlyPlayerHero.setWeapon(friendlyWeapon);
    }

    Hero getFriendlyPlayerHero() {
        return friendlyPlayerHero;
    }

    ArrayList<String> getGameLog() {
        return gameLog;
    }

    Player getEnemyPlayer() {
        return enemyPlayer;
    }

    int getEnemyHeroPowerUsageTime() {
        return enemyHeroPowerUsageTime;
    }

    int getEnemyPowerManaDecrease() {
        return enemyPowerManaDecrease;
    }

    ArrayList<Card> getEnemyDeckCards() {
        return enemyDeckCards;
    }

    ArrayList<Card> getEnemyHandCards() {
        return enemyHandCards;
    }

    ArrayList<Card> getEnemyPlayedCards() {
        return enemyPlayedCards;
    }

    Weapon getEnemyWeapon() {
        return enemyWeapon;
    }

    Hero getEnemyPlayerHero() {
        return enemyPlayerHero;
    }

    void addContiniousActionCard(Card cards) {
        friendlyContiniousActionCard.add(cards);
    }

    ArrayList<ActionModel> friendlyContiniousModel() {
        return getActionModels(friendlyContiniousActionCard);
    }

    ArrayList<ActionModel> enemyContiniousModel() {
        return getActionModels(enemyContiniousActionCard);
    }

    private ArrayList<ActionModel> getActionModels(ArrayList<Card> enemyContiniousActionCard) {
        ArrayList<ActionModel> models = new ArrayList<>();
        for (Card card : enemyContiniousActionCard) {
            if (card.getName().equalsIgnoreCase("learnjavadonic")) {
                ActionModel model = new ActionModel(card.getName().toLowerCase(), ((Spell) card).getManaSpendOnSth(), 8);
                models.add(model);
            } else if (card.getName().equalsIgnoreCase("strengthinnumbers")) {
                ActionModel model = new ActionModel(card.getName().toLowerCase(), ((Spell) card).getManaSpendOnSth(), 10);
                models.add(model);
            }

        }
        return models;
    }

    void spendManaOnMinion(int i) {
        for (Card card : friendlyContiniousActionCard) {
            if (card.getName().equalsIgnoreCase("strengthinnumbers")) {
                ((Spell) card).setManaSpendOnSth(((Spell) card).getManaSpendOnSth() + i);
                card.accept(new ActionVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyPlayerHero, enemyPlayerHero);
                break;
            }
        }
    }

    void spendManaOnSpell(int i) {
        for (Card card : friendlyContiniousActionCard) {
            if (card.getName().equalsIgnoreCase("learnjavadonic")) {
                ((Spell) card).setManaSpendOnSth(((Spell) card).getManaSpendOnSth() + i);
                card.accept(new ActionVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyPlayerHero, enemyPlayerHero);
                break;
            }
        }
    }

    void finishAction(Card card) {
        for (Card card1 : friendlyContiniousActionCard) {
            if (card1.equals(card)) {
                friendlyContiniousActionCard.remove(card1);
                break;
            }
        }
    }

    int heroPowerTargetNeeded() {
        if (friendlyPlayerHero.isPowerNeedFriendlyTarget()) {
            return 1;
        } else if (friendlyPlayerHero.isPowerNeedEnemyTarget()) {
            return 2;
        }
        return 0;
    }

    void playHeroPower(Character target) {
        friendlyPlayerHero.accept(new HeroPowerVisitor(), target, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);
        friendlyNotUsedMana -= (friendlyPlayerHero.getHeroPowerManaCost() - friendlyPowerManaDecrease);
        friendlyHeroPowerUsageTime -= 1;
    }

    private void benyaminAction() {
        for (Card card : friendlyPlayedCards) {
            if (card.getName().equalsIgnoreCase("benyamin")) {
                card.accept(new ActionVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyPlayerHero, enemyPlayerHero);
            }
        }
    }

    private void matinAction() {
        for (Card card : friendlyPlayedCards) {
            if (card.getName().equalsIgnoreCase("matin")) {
                card.accept(new ActionVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyPlayerHero, enemyPlayerHero);
            }
        }
    }

    void shahryarAction(Minion minion) {
        for (Card card : friendlyPlayedCards) {
            if (card.getName().equalsIgnoreCase("shahryar")) {
                card.accept(new ActionVisitor(), minion, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyPlayerHero, enemyPlayerHero);
            }
        }
    }

    void faezeAction(Minion target) {
        for (Card card : enemyPlayedCards) {
            if (card.getName().equalsIgnoreCase("faeze")) {
                Admin.getInstance().Attack2((Minion) card, target);
            }
        }
    }

    void hunterPowerAction(Minion minion) {
        if (enemyPlayerHero instanceof Hunter) {
            enemyPlayerHero.accept(new HeroPowerVisitor(), minion, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);
            Admin.getInstance().summonedMinion(minion, 1, 0, -1);
        }
    }


    int checkForWinner() {
        if (!enemyPlayerHero.equals(playerHero) && enemyPlayerHero.getHealth() <= 0) {
            return 1;
        } else if (!friendlyPlayerHero.equals(playerHero) && friendlyPlayerHero.getHealth() <= 0) {
            return 2;
        } else if (enemyPlayerHero.equals(playerHero) && enemyPlayerHero.getHealth() <= 0) {
            return 3;
        } else if (friendlyPlayerHero.equals(playerHero) && friendlyPlayerHero.getHealth() <= 0) {
            return 4;
        } else {
            return 0;
        }
    }


    public boolean isDeckReaderMode() {
        return deckReaderMode;
    }

    public void setDeckReaderMode(boolean deckReaderMode) {
        this.deckReaderMode = deckReaderMode;
    }

    boolean normalMode() {
        return !deckReaderMode && !practiceMode;
    }

}


