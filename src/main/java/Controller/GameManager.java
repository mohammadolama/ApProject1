package Controller;

import Configs.DeckReader;
import Controller.Actions.ActionVisitor;
import Controller.Actions.BattlecryVisitor;
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
    private boolean AiTurn;

    private GameManager(ArrayList<Card> arrayList, String card1, String card2, String card3) {
        initilizeLists();
        friendlyCardsOfPlayer = arrayList;
        initilizeCards(friendlyCardsOfPlayer, card1, card2, card3);
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

    GameManager(Player player, InfoPassive infoPassive, ArrayList<Card> arrayList, boolean practiceMode) {
        initilizeLists();
        this.deckReaderMode = !practiceMode;
        this.practiceMode = practiceMode;
        if (practiceMode) {
            practice(player, infoPassive, arrayList);
        } else {
            deckReader(infoPassive);
        }
    }

    private void initilizeLists() {
        friendlyPlayedCards = new ArrayList<>();
        friendlyDeckCards = new ArrayList<>();
        friendLyHandCards = new ArrayList<>();
        friendlyContiniousActionCard = new ArrayList<>();
        enemyContiniousActionCard = new ArrayList<>();
        gameLog = new ArrayList<>();
    }

    private void deckReader(InfoPassive infoPassive) {
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

    private void practice(Player player, InfoPassive infoPassive, ArrayList<Card> list) {
        try {
            this.friendlyPlayer = player;
            this.friendlyPlayerHero = (Hero) player.getSelectedDeck().getHero().clone();
            this.playerHero = friendlyPlayerHero;
            this.friendlyCardsOfPlayer = list;
            ThreePrimitiveRandom(this.friendlyCardsOfPlayer, "friendly");
            this.friendlyInfoPassive = infoPassive;
            friendlyInfoInitilize(infoPassive);
            practiceEnemyInit();
            friendlyPlayerHero.accept(new SpecialPowerVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);
            enemyPlayerHero.accept(new SpecialPowerVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private void practiceEnemyInit() {
        Player player = JsonReaders.PlayerJsonReader("practice");
        this.enemyPlayer = player;
        enemyInfoPassive = InfoPassive.sample();
        enemyInfoInitilize(enemyInfoPassive);
        ArrayList<Card> ar1 = Deck.UpdateDeck(player.getSelectedDeck().getDeck());
        ThreePrimitiveRandom(ar1, "enemy");
        this.enemyPlayedCards = new ArrayList<>();
        this.enemyPlayerHero = player.getSelectedDeck().getHero();
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


    void drawCard(int j, String mode, ArrayList<Card> deck, ArrayList<Card> hand) {
        if (deck.size() < j) {
            j = deck.size();
        }
        for (int i = 0; i < j; i++) {
            Card cards = randomCardDraw(deck);
            if (hand.size() < 12) {
                if (mode == null || (mode.equalsIgnoreCase("extra") && !(cards instanceof Spell))) {
                    addCard(hand, cards);
                    matinAction(false);
                }
            }
            removeCard(deck, cards);
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

    private void refillMana(boolean AI) {
        if (AI) {
            if (enemyTotalMana < 10) {
                enemyTotalMana++;
            }
            enemyNotUsedMana = enemyTotalMana;
            enemyHeroPowerUsageTime = eHPMAXUT;
        } else {
            if (friendlyTotalMana < 10) {
                friendlyTotalMana++;
            }
            friendlyNotUsedMana = friendlyTotalMana;
            friendlyHeroPowerUsageTime = fHPMAXUT;

        }
    }

    private void wakeUp(boolean AI) {
        if (AI) {
            for (Card card : enemyPlayedCards) {
                ((Minion) card).setSleep(false);
            }
        } else {
            for (Card card : friendlyPlayedCards) {
                ((Minion) card).setSleep(false);
            }
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
        if (practiceMode) {
            benyaminAction(false);
            AiActions();
        } else {
            benyaminAction(false);
            reversePlayers();
            checkDestroyMinion();
            canBeAttackedUpdater();
            wakeUp(false);
            refillMana(false);
            drawCard(friendlyDrawCardNum, null, friendlyDeckCards, friendLyHandCards);
        }
    }

    void PlayerTurn() {
        checkDestroyMinion();
        canBeAttackedUpdater();
        wakeUp(false);
        refillMana(false);
        drawCard(friendlyDrawCardNum, null, friendlyDeckCards, friendLyHandCards);
    }

    void AiActions() {
        try {
            Admin.getInstance().AiTurn(true);
            Thread.sleep(300);
            checkDestroyMinion();
            drawCard(enemyDrawCardNum, null, enemyDeckCards, enemyHandCards);
            Thread.sleep(1000);
            wakeUp(true);
            refillMana(true);
            practicePlayCard();
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PlayerTurn();
        Admin.getInstance().AiTurn(false);
    }

    void practiceModeEndTurn() {
        benyaminAction(true);
        checkDestroyMinion();
        canBeAttackedUpdater();
        wakeUp(true);
        refillMana(true);

    }

    void checkDestroyMinion() {
        System.out.println(ThreadColor.ANSI_BLUE + enemyDefenceAdd + "\t" + friendlyDefenceAdd + ThreadColor.ANSI_RESET);
        ListIterator<Card> iterator = this.enemyPlayedCards.listIterator();
        while (iterator.hasNext()) {
            if (((Minion) iterator.next()).getHealth() <= 0) {
                enemyPlayerHero.setDefence(enemyPlayerHero.getDefence() + enemyDefenceAdd);
                iterator.remove();
            }
        }
        ListIterator<Card> iterator1 = this.friendlyPlayedCards.listIterator();
        while (iterator1.hasNext()) {
            if (((Minion) iterator1.next()).getHealth() <= 0) {
                friendlyPlayerHero.setDefence(friendlyPlayerHero.getDefence() + friendlyDefenceAdd);
                iterator1.remove();
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

    void setEnemyWeapon(Weapon enemyWeapon) {
        this.enemyWeapon = enemyWeapon;
        this.enemyPlayerHero.setWeapon(enemyWeapon);
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

    void addContiniousActionCard(Card cards, boolean AI) {
        if (AI) {
            enemyContiniousActionCard.add(cards);
        } else {
            friendlyContiniousActionCard.add(cards);
        }
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

    void spendManaOnMinion(int i, boolean AI) {
        if (AI) {
            for (Card card : enemyContiniousActionCard) {
                if (card.getName().equalsIgnoreCase("strengthinnumbers")) {
                    ((Spell) card).setManaSpendOnSth(((Spell) card).getManaSpendOnSth() + i);
                    card.accept(new ActionVisitor(), null, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyPlayerHero, friendlyPlayerHero);
                    break;
                }
            }
        } else {
            for (Card card : friendlyContiniousActionCard) {
                if (card.getName().equalsIgnoreCase("strengthinnumbers")) {
                    ((Spell) card).setManaSpendOnSth(((Spell) card).getManaSpendOnSth() + i);
                    card.accept(new ActionVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyPlayerHero, enemyPlayerHero);
                    break;
                }
            }
        }
    }

    void spendManaOnSpell(int i, boolean AI) {
        if (AI) {
            for (Card card : enemyContiniousActionCard) {
                if (card.getName().equalsIgnoreCase("learnjavadonic")) {
                    ((Spell) card).setManaSpendOnSth(((Spell) card).getManaSpendOnSth() + i);
                    card.accept(new ActionVisitor(), null, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyPlayerHero, friendlyPlayerHero);
                    break;
                }
            }
        } else {
            for (Card card : friendlyContiniousActionCard) {
                if (card.getName().equalsIgnoreCase("learnjavadonic")) {
                    ((Spell) card).setManaSpendOnSth(((Spell) card).getManaSpendOnSth() + i);
                    card.accept(new ActionVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyPlayerHero, enemyPlayerHero);
                    break;
                }
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

    boolean playHeroPower(Character target) {
        if (friendlyNotUsedMana >= (friendlyPlayerHero.getHeroPowerManaCost() - friendlyPowerManaDecrease)) {
            friendlyPlayerHero.accept(new HeroPowerVisitor(), target, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);
            friendlyNotUsedMana -= (friendlyPlayerHero.getHeroPowerManaCost() - friendlyPowerManaDecrease);
            friendlyHeroPowerUsageTime -= 1;
            return true;
        }
        return false;
    }

    private void benyaminAction(boolean AI) {
        if (AI) {
            for (Card card : enemyPlayedCards) {
                if (card.getName().equalsIgnoreCase("benyamin")) {
                    card.accept(new ActionVisitor(), new Benyamin(), enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyPlayerHero, friendlyPlayerHero);
                }
            }
        } else {
            for (Card card : friendlyPlayedCards) {
                if (card.getName().equalsIgnoreCase("benyamin")) {
                    card.accept(new ActionVisitor(), new Benyamin(), friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyPlayerHero, enemyPlayerHero);
                }
            }
        }
    }

    private void matinAction(boolean AI) {
        if (AI) {
            for (Card card : enemyPlayedCards) {
                if (card.getName().equalsIgnoreCase("matin")) {
                    card.accept(new ActionVisitor(), null, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyPlayerHero, friendlyPlayerHero);
                }
            }

        } else {
            for (Card card : friendlyPlayedCards) {
                if (card.getName().equalsIgnoreCase("matin")) {
                    card.accept(new ActionVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyPlayerHero, enemyPlayerHero);
                }
            }
        }
    }

    void shahryarAction(Minion minion, boolean AI) {
        if (AI) {
            for (Card card : enemyPlayedCards) {
                if (card.getName().equalsIgnoreCase("shahryar")) {
                    card.accept(new ActionVisitor(), minion, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyPlayerHero, friendlyPlayerHero);
                }
            }
        } else {
            for (Card card : friendlyPlayedCards) {
                if (card.getName().equalsIgnoreCase("shahryar")) {
                    card.accept(new ActionVisitor(), minion, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyPlayerHero, enemyPlayerHero);
                }
            }
        }
    }

    void faezeAction(Minion target, boolean AI) {
        if (AI) {
            for (Card card : friendlyPlayedCards) {
                if (card.getName().equalsIgnoreCase("faeze")) {
                    Admin.getInstance().Attack2((Minion) card, target);
                }
            }
        } else {
            for (Card card : enemyPlayedCards) {
                if (card.getName().equalsIgnoreCase("faeze")) {
                    Admin.getInstance().Attack2((Minion) card, target);
                }
            }
        }
    }

    void hunterPowerAction(Minion minion, boolean AI) {
        if (AI) {
            if (friendlyPlayerHero instanceof Hunter) {
                friendlyPlayerHero.accept(new HeroPowerVisitor(), minion, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards);
                Admin.getInstance().summonedMinion(minion, 1, minion.getDamage(), minion.getHealth());
            }
        } else {
            if (enemyPlayerHero instanceof Hunter) {
                enemyPlayerHero.accept(new HeroPowerVisitor(), minion, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);
                Admin.getInstance().summonedMinion(minion, 1, minion.getDamage(), minion.getHealth());
            }
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

    public boolean isPracticeMode() {
        return practiceMode;
    }

    public void setPracticeMode(boolean practiceMode) {
        this.practiceMode = practiceMode;
    }


    private void practiceAttack() {
        if (enemyPlayedCards.size() >= 0) {
            Random random = new Random();
            int index = random.nextInt(enemyPlayedCards.size());
            Minion minion = (Minion) enemyPlayedCards.get(index);
            int j = 0;
            for (Card card : friendlyPlayedCards) {
                if (card.getAttributes() != null && card.getAttributes().contains(Attribute.Taunt)) {
                    Admin.getInstance().practiceAttack(minion, (Minion) card, index, j);
                    return;
                }
                j++;
            }
            j = 0;
            for (Card card : enemyPlayedCards) {
                if (((Minion) card).getDamage() >= friendlyPlayerHero.getHealth()) {
                    Admin.getInstance().practiceAttack((Minion) card, friendlyPlayerHero, j, -1);
                    return;
                }
                j++;
            }
            int chance = random.nextInt(10);
            if (friendlyPlayedCards.size() == 0 || chance % 2 == 0) {
                Admin.getInstance().practiceAttack(minion, friendlyPlayerHero, index, -1);
                return;
            } else {
                j = 0;
                for (Card card : friendlyPlayedCards) {
                    Admin.getInstance().practiceAttack(minion, (Minion) card, index, j);
                }
            }
        }
    }

    private void practicePlayCard() {
        if (enemyHandCards.size() >= 0) {
            System.out.println("first");
            Collections.shuffle(enemyHandCards);
            for (Card card : enemyHandCards) {
                System.out.println("second");
                if (enemyNotUsedMana >= card.getManaCost() - enemyManaDecrease) {
                    System.out.println("third");
                    if (!card.isNeedEnemyTarget() && !card.isNeedFriendlyTarget()) {
                        System.out.println("forth");
                        playCard(card);
                        break;
                    }
                }
            }
        }
    }


    void playCard(Card card) {
        System.out.println(card.getName());
        for (Card cards : enemyHandCards) {
            if (card.equals(cards)) {
                System.out.println("Start playing");
                if (enemyNotUsedMana >= cards.getManaCost() - enemyManaDecrease) {
                    System.out.println("close enogh");
                    Admin.getInstance().checkContiniousAction(cards, true);
                    cards.accept(new BattlecryVisitor(), null, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyPlayerHero, friendlyPlayerHero);
                    if (cards instanceof Minion) {
                        System.out.println("play minion");
                        practicePlayMinion((Minion) cards, null);
                        spendManaOnMinion(cards.getManaCost() - enemyManaDecrease, true);
                    } else if (cards instanceof Spell) {
                        System.out.println("play spell");
                        practicePlaySpell((Spell) cards, null);
                        spendManaOnSpell(cards.getManaCost() - enemyManaDecrease, true);
                    } else if (cards instanceof Weapon) {
                        System.out.println("play weapon");
                        practicePlayWeapon((Weapon) cards);
                    }
                    Admin.getInstance().summonedMinion(card, 0, 0, 0);
                    String log = String.format("Play : played card \"%s\"", cards.getName());
                    break;
                } else {
                    Admin.getInstance().playSound("mana");
                }
            }
        }
        checkDestroyMinion();
        checkForWinner();
    }

    private void practicePlayMinion(Minion minions, Character target) {
        if (enemyPlayedCards.size() < 7) {
            Admin.getInstance().playSound("minion");
            enemyNotUsedMana -= minions.getManaCost() - enemyManaDecrease;
            minions.accept(new ActionVisitor(), target, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyPlayerHero, friendlyPlayerHero);
            practiceSummonMinion(minions);
            checkDestroyMinion();
            hunterPowerAction(minions, true);
            faezeAction(minions, true);
            shahryarAction(minions, true);
            updateGameLog(String.format("%s played", minions.getName().toLowerCase()));
        } else {
            Admin.getInstance().playSound("error");
            Admin.getInstance().frameRender();
        }
    }

    public void practiceSummonMinion(Minion minions) {
        if (enemyPlayedCards.size() < 7) {
            if (minions.getAttributes() != null && (minions.getAttributes().contains(Attribute.Charge) || minions.getAttributes().contains(Attribute.Rush))) {
                minions.setSleep(false);
            }
            enemyPlayedCards.add(minions);
            enemyHandCards.remove(minions);
        }
    }

    private void practicePlaySpell(Spell spell, Character target) {
        Admin.getInstance().playSound("spell");
        enemyNotUsedMana -= spell.getManaCost() - enemyManaDecrease;
        enemyHandCards.remove(spell);
        spell.accept(new ActionVisitor(), target, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyPlayerHero, friendlyPlayerHero);
        updateGameLog(String.format("%s played", spell.getName().toLowerCase()));
    }

    private void practicePlayWeapon(Weapon weapon) {
        Admin.getInstance().playSound("weapon");
        enemyNotUsedMana -= weapon.getManaCost() - enemyManaDecrease;
        enemyHandCards.remove(weapon);
        setEnemyWeapon(weapon);
        updateGameLog(String.format("%s played", weapon.getName().toLowerCase()));
    }


    private void practiceHeroPower() {
        if (enemyNotUsedMana >= enemyPlayerHero.getHeroPowerManaCost() - enemyPowerManaDecrease) {
            Random random = new Random();
            int chance = random.nextInt(1000);
            if (chance % 5 == 0) {

            }
        }
    }

    public void setSleep(Minion minion) {
        for (Card card : enemyPlayedCards) {
            if (card.equals(minion)) {
                ((Minion) card).setSleep(true);
            }
        }
    }
}

