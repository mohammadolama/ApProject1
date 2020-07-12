package Controller.Manager;

import Controller.Actions.ActionHandler;
import Controller.Actions.CardVisitors.ActionVisitor;
import Controller.Actions.SPVisitor.HeroPowerVisitor;
import Controller.Admin;
import Model.ActionModel;
import Model.Cards.*;
import Model.Enums.Attribute;
import Model.Enums.ContiniousActionCarts;
import Model.Heros.Hero;
import Model.Heros.Hunter;
import Model.Heros.Warlock;
import Model.InfoPassive;
import Model.Interface.Character;
import Model.Player;
import View.Panels.BoardPanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

import static View.Sounds.SoundAdmin.playSound;

public abstract class Managers {

    Player friendlyPlayer;
    Player enemyPlayer;
    InfoPassive friendlyInfoPassive;
    InfoPassive enemyInfoPassive;
    private int friendlyStartingMana;
    private int enemyStartingMana;
    private int friendlyTotalMana;
    private int enemyTotalMana;
    private int friendlyNotUsedMana;
    int enemyNotUsedMana;
    private int friendlyDrawCardNum;
    int enemyDrawCardNum;
    private int friendlyHeroPowerUsageTime;
    private int enemyHeroPowerUsageTime;
    private int fHPMAXUT;       // friendly heroPower max Usage Time
    private int eHPMAXUT;
    private int friendlyPowerManaDecrease;
    private int enemyPowerManaDecrease;
    private int friendlyManaDecrease;
    int enemyManaDecrease;
    private int friendlyDefenceAdd;
    private int enemyDefenceAdd;
    ArrayList<Card> friendlyCardsOfPlayer = new ArrayList<>();
    ArrayList<Card> friendlyDeckCards = new ArrayList<>();
    ArrayList<Card> friendLyHandCards = new ArrayList<>();
    ArrayList<Card> friendlyPlayedCards = new ArrayList<>();
    ArrayList<Card> enemyDeckCards = new ArrayList<>();
    ArrayList<Card> enemyHandCards = new ArrayList<>();
    ArrayList<Card> enemyPlayedCards = new ArrayList<>();
    private ArrayList<String> gameLog = new ArrayList<>();
    private ArrayList<Card> friendlyContiniousActionCard = new ArrayList<>();
    private ArrayList<Card> enemyContiniousActionCard = new ArrayList<>();
    private Weapon friendlyWeapon;
    private Weapon enemyWeapon;
    Hero friendlyPlayerHero;
    Hero playerHero;
    Hero enemyPlayerHero;
    boolean deckReaderMode = false;
    boolean practiceMode = false;
    boolean AiTurn;


    void friendlyInfoInitilize(InfoPassive infoPassive) {
        friendlyStartingMana = 1;
        friendlyTotalMana = 1;
        friendlyNotUsedMana = 1;
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

    void enemyInfoInitilize(InfoPassive infoPassive) {
        enemyStartingMana = 0;
        enemyTotalMana = 0;
        enemyNotUsedMana = 0;
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

    void ThreePrimitiveRandom(ArrayList<Card> arrayList, String value) {
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

    public void updateGameLog(String string) {
        Admin.getInstance().Log(string);
        gameLog.add(string);
        if (gameLog.size() > 25) {
            gameLog.remove(0);
        }
    }

    private void addCard(ArrayList<Card> arrayList, Card cards) {
        arrayList.add(cards);
    }

    private void removeCard(ArrayList<Card> arrayList, Card cards) {
        arrayList.remove(cards);
    }

    void refillMana(boolean AI) {
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

    private void chargeWeapon(boolean AI) {
        if (friendlyPlayerHero.getWeapon() != null) {
            friendlyPlayerHero.setCanAttack(true);
        }
    }

    void wakeUp(boolean AI) {
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

    public abstract void endTurn();

    void reversePlayers() {
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


    void PlayerTurn() {
        checkDestroyMinion();
        canBeAttackedUpdater();
        wakeUp(false);
        refillMana(false);
        chargeWeapon(false);
        if (!drawCard(friendlyDrawCardNum, null, friendlyDeckCards, friendLyHandCards))
            heroTakeDamage(friendlyPlayerHero, 1);
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

    void benyaminAction(boolean AI) {
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

    public void shahryarAction(Minion minion, boolean AI) {
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

    public void faezeAction(Minion target, boolean AI) {
        if (AI) {
            for (Card card : friendlyPlayedCards) {
                if (card.getName().equalsIgnoreCase("faeze")) {
                    Attack2((Minion) card, target);
                }
            }
        } else {
            for (Card card : enemyPlayedCards) {
                if (card.getName().equalsIgnoreCase("faeze")) {
                    Attack2((Minion) card, target);
                }
            }
        }
    }


    public void Attack(int attacker, int target, BoardPanel panel) {
        ActionHandler actionHandler = new ActionHandler();
        new Thread(() -> playSound("attack")).start();
        if (attacker >= 0 && target >= 0) {
            Minion attacker1 = (Minion) friendlyPlayedCards.get(attacker);
            Minion target1 = (Minion) enemyPlayedCards.get(target);
            actionHandler.Attack(attacker1, target1, enemyPlayedCards);
            setSleep(attacker);
            panel.drawDamages(attacker, target, attacker1.getAttack(), target1.getAttack());
            updateGameLog(String.format("%s Attacked %s", attacker1.getName(), target1.getName()));
        } else if (attacker >= 0) {
            Minion attacker1 = (Minion) friendlyPlayedCards.get(attacker);
            Hero target1 = enemyPlayerHero;
            actionHandler.Attack(attacker1, target1, enemyPlayedCards);
            setSleep(attacker);
            panel.drawDamages(attacker, target, attacker1.getAttack(), target1.getAttack());
            updateGameLog(String.format("%s Attacked %s", attacker1.getName(), target1.getName()));
        } else if (target >= 0) {
            Hero attacker1 = friendlyPlayerHero;
            Minion target1 = (Minion) enemyPlayedCards.get(target);
            panel.drawDamages(attacker, target, attacker1.getAttack(), target1.getAttack());
            if (actionHandler.Attack(attacker1, target1, enemyPlayedCards)) {
                updateGameLog(String.format("%s Attacked %s", attacker1.getName(), target1.getName()));
                updateWeapon();
            }
        } else {
            Hero attacker1 = friendlyPlayerHero;
            Hero target1 = enemyPlayerHero;
            panel.drawDamages(attacker, target, attacker1.getAttack(), target1.getAttack());
            if (actionHandler.Attack(attacker1, target1, enemyPlayedCards)) {
                updateGameLog(String.format("%s Attacked %s", attacker1.getName(), target1.getName()));
                updateWeapon();
            }
        }
        checkDestroyMinion();
        checkForWinner();
    }


    private void Attack2(Minion attacker, Minion target) {
        if (friendlyPlayedCards.contains(target)) {
            ActionHandler actionHandler = new ActionHandler();
            actionHandler.attackMinion2(attacker, target);
            updateGameLog(String.format("%s Attacked %s", attacker.getName(), target.getName()));
            checkDestroyMinion();
        }
    }


    public void hunterPowerAction(Minion minion, boolean AI) {
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

    public void checkDestroyMinion() {
        ListIterator<Card> iterator = this.enemyPlayedCards.listIterator();
        while (iterator.hasNext()) {
            Minion minion = (Minion) iterator.next();
            if (minion.getHealth() <= 0) {
                enemyPlayerHero.setDefence(enemyPlayerHero.getDefence() + enemyDefenceAdd);
                updateGameLog(String.format("Minion %s defeated .", minion.getName()));
                iterator.remove();
            }
        }
        ListIterator<Card> iterator1 = this.friendlyPlayedCards.listIterator();
        while (iterator1.hasNext()) {
            Minion minion = (Minion) iterator1.next();
            if (minion.getHealth() <= 0) {
                friendlyPlayerHero.setDefence(friendlyPlayerHero.getDefence() + friendlyDefenceAdd);
                updateGameLog(String.format("Minion %s defeated .", minion.getName()));
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

    public boolean drawCard(int j, String mode, ArrayList<Card> deck, ArrayList<Card> hand) {
        if (deck.size() == 0) {
            return false;
        } else {
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
            return true;
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

    void heroTakeDamage(Hero hero, int i) {
        hero.setHealth(hero.getHealth() - i);
    }

    private void updateWeapon() {
        Weapon weapon = this.friendlyWeapon;
        weapon.setDurability(weapon.getDurability() - 1);
        updateGameLog(String.format("%s use Weapon.", friendlyPlayer.getUsername()));
        setFriendlyWeapon(weapon);
        friendlyPlayerHero.setCanAttack(false);
        checkDestroyedWeapon();
    }

    public void setFriendlyWeapon(Weapon friendlyWeapon) {
        this.friendlyWeapon = friendlyWeapon;
        this.friendlyPlayerHero.setWeapon(friendlyWeapon);
        this.friendlyPlayerHero.setCanAttack(true);
    }

    public void addContiniousActionCard(Card cards, boolean AI) {
        if (AI) {
            enemyContiniousActionCard.add(cards);
        } else {
            friendlyContiniousActionCard.add(cards);
        }
    }

    public ArrayList<ActionModel> friendlyContiniousModel() {
        return getActionModels(friendlyContiniousActionCard);
    }

    public ArrayList<ActionModel> enemyContiniousModel() {
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
            } else if (card.getName().equalsIgnoreCase("strengthinnumbersdr")) {
                ActionModel model = new ActionModel(card.getName().toLowerCase(), ((Spell) card).getManaSpendOnSth(), 10);
                models.add(model);
            }

        }
        return models;
    }

    public void spendManaOnMinion(int i, boolean AI) {
        if (AI) {
            for (Card card : enemyContiniousActionCard) {
                if (card.getName().equalsIgnoreCase("strengthinnumbers") || card.getName().equalsIgnoreCase("strengthinnumbersdr")) {
                    ((Spell) card).setManaSpendOnSth(((Spell) card).getManaSpendOnSth() + i);
                    card.accept(new ActionVisitor(), null, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyPlayerHero, friendlyPlayerHero);
                    break;
                }
            }
        } else {
            for (Card card : friendlyContiniousActionCard) {
                if (card.getName().equalsIgnoreCase("strengthinnumbers") || card.getName().equalsIgnoreCase("strengthinnumbersdr")) {
                    ((Spell) card).setManaSpendOnSth(((Spell) card).getManaSpendOnSth() + i);
                    card.accept(new ActionVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyPlayerHero, enemyPlayerHero);
                    break;
                }
            }
        }
    }

    public void spendManaOnSpell(int i, boolean AI) {
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

    public void finishAction(Card card) {
        for (Card card1 : friendlyContiniousActionCard) {
            if (card1.equals(card)) {
                friendlyContiniousActionCard.remove(card1);
                break;
            }
        }
    }

    private int heroPowerTargetNeeded() {
        if (friendlyPlayerHero.isPowerNeedFriendlyTarget()) {
            return 1;
        } else if (friendlyPlayerHero.isPowerNeedEnemyTarget()) {
            return 2;
        }
        return 0;
    }

    public boolean playHeroPower(Character target) {
        if (friendlyPlayerHero instanceof Warlock) {
            friendlyPlayerHero.accept(new HeroPowerVisitor(), target, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);
            heroTakeDamage(friendlyPlayerHero, 2);
            friendlyHeroPowerUsageTime -= 1;
            updateGameLog(friendlyPlayer.getUsername() + " Use HeroPower");
            checkForWinner();
            return true;
        } else {
            if (friendlyNotUsedMana >= (friendlyPlayerHero.getHeroPowerManaCost() - friendlyPowerManaDecrease)) {
                friendlyPlayerHero.accept(new HeroPowerVisitor(), target, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);
                friendlyNotUsedMana -= (friendlyPlayerHero.getHeroPowerManaCost() - friendlyPowerManaDecrease);
                friendlyHeroPowerUsageTime -= 1;
                updateGameLog(friendlyPlayer.getUsername() + " Use HeroPower");
                checkForWinner();
                return true;
            }
            return false;
        }
    }

    private int WinnerChecker() {
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

    public void checkForWinner() {
        int i = WinnerChecker();
        switch (i) {
            case 1:
                Admin.getInstance().winGame(friendlyPlayerHero.getName().toLowerCase());
                boolean flag = normalMode();
                if (flag) {
                    Admin.getInstance().updateDeckStates(1);
                }
                break;
            case 2:
                Admin.getInstance().winGame(enemyPlayerHero.getName().toLowerCase());
                flag = normalMode();
                if (flag) {
                    Admin.getInstance().updateDeckStates(1);
                }
                break;
            case 3:
                Admin.getInstance().winGame(friendlyPlayerHero.getName().toLowerCase());
                flag = normalMode();
                if (flag) {
                    Admin.getInstance().updateDeckStates(0);
                }
                break;

            case 4:
                Admin.getInstance().winGame(enemyPlayerHero.getName().toLowerCase());
                flag = normalMode();
                if (flag) {
                    Admin.getInstance().updateDeckStates(0);
                }
                break;
            case 0:
                break;
        }
    }

    public int heroPowerCanBePlayed() {
        if (!(friendlyPlayerHero instanceof Hunter)) {
            if (getFriendlyHeroPowerUsageTime() > 0) {
                if (heroPowerTargetNeeded() > 0) {
                    if (heroPowerTargetNeeded() == 1) {
                        return 1;
                    } else if (heroPowerTargetNeeded() == 2) {
                        return 2;
                    }
                    return 0;
                } else {
                    return 3;
                }
            } else {
                return 0;
            }
        }
        return 0;
    }


    public void setSleep(Minion minion) {
        for (Card card : enemyPlayedCards) {
            if (card.equals(minion)) {
                ((Minion) card).setSleep(true);
            }
        }
    }

    public void setSleep(int i) {
        ((Minion) friendlyPlayedCards.get(i)).setSleep(true);
    }


    void checkContiniousAction(Card cards, boolean AI) {
        for (ContiniousActionCarts value : ContiniousActionCarts.values()) {
            if (cards.getName().equalsIgnoreCase(value.toString())) {
                addContiniousActionCard(cards, AI);
                break;
            }
        }
    }

    private boolean normalMode() {
        return !deckReaderMode && !practiceMode;
    }

    void setEnemyWeapon(Weapon enemyWeapon) {
        this.enemyWeapon = enemyWeapon;
        this.enemyPlayerHero.setWeapon(enemyWeapon);
    }

    public Player getFriendlyPlayer() {
        return friendlyPlayer;
    }

    public Player getEnemyPlayer() {
        return enemyPlayer;
    }

    public int getFriendlyTotalMana() {
        return friendlyTotalMana;
    }

    public int getFriendlyNotUsedMana() {
        return friendlyNotUsedMana;
    }

    public void setFriendlyNotUsedMana(int friendlyNotUsedMana) {
        this.friendlyNotUsedMana = friendlyNotUsedMana;
    }

    public int getFriendlyHeroPowerUsageTime() {
        return friendlyHeroPowerUsageTime;
    }

    public int getEnemyHeroPowerUsageTime() {
        return enemyHeroPowerUsageTime;
    }

    public int getFriendlyPowerManaDecrease() {
        return friendlyPowerManaDecrease;
    }

    public int getEnemyPowerManaDecrease() {
        return enemyPowerManaDecrease;
    }

    public int getFriendlyManaDecrease() {
        return friendlyManaDecrease;
    }

    public ArrayList<Card> getFriendlyDeckCards() {
        return friendlyDeckCards;
    }

    public ArrayList<Card> getFriendLyHandCards() {
        return friendLyHandCards;
    }

    public ArrayList<Card> getFriendlyPlayedCards() {
        return friendlyPlayedCards;
    }

    public ArrayList<Card> getEnemyDeckCards() {
        return enemyDeckCards;
    }

    public ArrayList<Card> getEnemyHandCards() {
        return enemyHandCards;
    }

    public ArrayList<Card> getEnemyPlayedCards() {
        return enemyPlayedCards;
    }

    public ArrayList<String> getGameLog() {
        return gameLog;
    }

    public Weapon getFriendlyWeapon() {
        return friendlyWeapon;
    }

    public Weapon getEnemyWeapon() {
        return enemyWeapon;
    }

    public Hero getFriendlyPlayerHero() {
        return friendlyPlayerHero;
    }

    public Hero getEnemyPlayerHero() {
        return enemyPlayerHero;
    }

    public boolean isAiTurn() {
        return AiTurn;
    }

}
