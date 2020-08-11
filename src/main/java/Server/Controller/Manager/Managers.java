package Server.Controller.Manager;

import Server.Controller.Actions.ActionHandler;
import Server.Controller.Actions.CardVisitors.ActionVisitor;
import Server.Controller.Actions.CardVisitors.BattlecryVisitor;
import Server.Controller.Actions.SPVisitor.HeroPowerVisitor;
import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.MainLogic.ThreadColor;
import Server.Model.*;
import Server.Model.Cards.*;
import Server.Model.Enums.Attribute;
import Server.Model.Enums.ContiniousActionCarts;
import Server.Model.Heros.Hero;
import Server.Model.Heros.Hunter;
import Server.Model.Heros.Warlock;
import Server.Model.Interface.Character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

import static Server.Model.Cards.Card.getCardOf;

public abstract class Managers {

    Player player1, player2;
    ClientHandler cl1, cl2;
    InfoPassive player1InfoPassive, player2InfoPassive;
    int player1StartingMana, player2StartingMana, player1TotalMana, player2TotalMana, player1NotUsedMana,
            player2NotUsedMana, player1DrawCardNum, player2DrawCardNum, player1HeroPowerUsageTime, player2HeroPowerUsageTime,
            p1HPMAXUT, p2HPMAXUT, player1PowerManaDecrease, player2PowerManaDecrease, player1ManaDecrease, player2ManaDecrease;
    int player1DefenceAdd, player2DefenceAdd;
    ArrayList<Card> player1CardsOfPlayer = new ArrayList<>();
    ArrayList<Card> player1DeckCards = new ArrayList<>();
    ArrayList<Card> player1HandCards = new ArrayList<>();
    ArrayList<Card> player1PlayedCards = new ArrayList<>();
    ArrayList<Card> player2CardsOfPlayer = new ArrayList<>();
    ArrayList<Card> player2DeckCards = new ArrayList<>();
    ArrayList<Card> player2HandCards = new ArrayList<>();
    ArrayList<Card> player2PlayedCards = new ArrayList<>();
    ArrayList<String> gameLog = new ArrayList<>();
    ArrayList<Card> player1ContiniousActionCard = new ArrayList<>();
    ArrayList<Card> player2ContiniousActionCard = new ArrayList<>();
    Weapon player1Weapon, player2Weapon;
    Hero player1Hero, player2Hero, currentHero;
    boolean deckReaderMode = false;
    boolean practiceMode = false;
    boolean p2Turn;
    String time;
    boolean flag1;

    void player1InfoInitilize(InfoPassive infoPassive) {
        player1StartingMana = 1;
        player1TotalMana = 1;
        player1NotUsedMana = 1;
        player1DrawCardNum = 1;
        player1HeroPowerUsageTime = 1;
        p1HPMAXUT = 1;
        player1PowerManaDecrease = 0;
        player1ManaDecrease = 0;
        player1DefenceAdd = 0;
        String st = infoPassive.getName();
        if (st.equalsIgnoreCase("twiceDraw")) {
            player1DrawCardNum = 2;
        } else if (st.equalsIgnoreCase("offCards")) {
            player1ManaDecrease = 1;
        } else if (st.equalsIgnoreCase("warriors")) {
            player1DefenceAdd = 2;
        } else if (st.equalsIgnoreCase("manaJump")) {
            player1StartingMana = 2;
            player1TotalMana = 2;
            player1NotUsedMana = 2;
        } else if (st.equalsIgnoreCase("freePower")) {
            player1HeroPowerUsageTime = 2;
            p1HPMAXUT = 2;
            player1PowerManaDecrease = 1;
        }
    }

    void player2InfoInitilize(InfoPassive infoPassive) {
        player2StartingMana = 0;
        player2TotalMana = 0;
        player2NotUsedMana = 0;
        player2DrawCardNum = 1;
        player2HeroPowerUsageTime = 1;
        p2HPMAXUT = 1;
        player2PowerManaDecrease = 0;
        player2ManaDecrease = 0;
        player2DefenceAdd = 0;
        String st = infoPassive.getName();
        if (st.equalsIgnoreCase("twiceDraw")) {
            player2DrawCardNum = 2;
        } else if (st.equalsIgnoreCase("offCards")) {
            player2ManaDecrease = 1;
        } else if (st.equalsIgnoreCase("warriors")) {
            player2DefenceAdd = 2;
        } else if (st.equalsIgnoreCase("manaJump")) {
            player2StartingMana = 2;
            player2TotalMana = 2;
            player2NotUsedMana = 2;
        } else if (st.equalsIgnoreCase("freePower")) {
            player2HeroPowerUsageTime = 2;
            p2HPMAXUT = 2;
            player2PowerManaDecrease = 1;
        }
    }

    void refillMana(boolean p1Turn, ClientHandler cl) {
        if (p1Turn) {
            if (player2TotalMana < 10) {
                player2TotalMana++;
            }
            player2NotUsedMana = player2TotalMana;
            player2HeroPowerUsageTime = p2HPMAXUT;
        } else {
            if (player1TotalMana < 10) {
                player1TotalMana++;
            }
            player1NotUsedMana = player1TotalMana;
            player1HeroPowerUsageTime = p1HPMAXUT;
        }
    }

    public abstract void endTurn(ClientHandler cl);

    void PlayerTurn(ClientHandler cl) {
        checkDestroyMinion();
        canBeAttackedUpdater(cl);
        wakeUp(p2Turn, cl);
        refillMana(p2Turn, cl);
        chargeWeapon(p2Turn, cl);
        if (cl.equals(cl1)) {
            if (!drawCard(player1DrawCardNum, null, player1DeckCards, player1HandCards))
                heroTakeDamage(player1Hero, 1);
        } else {
            if (!drawCard(player2DrawCardNum, null, player2DeckCards, player2HandCards))
                heroTakeDamage(player2Hero, 1);
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

    public void updateGameLog(String string) {
        gameLog.add(string);
        if (gameLog.size() > 25) {
            gameLog.remove(0);
        }
    }

    void addCard(ArrayList<Card> arrayList, Card cards) {
        arrayList.add(cards);
    }

    void removeCard(ArrayList<Card> arrayList, Card cards) {
        arrayList.remove(cards);
    }

    void chargeWeapon(boolean p2Turn, ClientHandler cl) {
        if (player1Hero.getWeapon() != null) {
            player1Hero.setCanAttack(true);
        }
    }

    void wakeUp(boolean p2Turn, ClientHandler cl) {
        if (p2Turn) {
            for (Card card : player2PlayedCards) {
                ((Minion) card).setSleep(false);
            }
        } else {
            for (Card card : player1PlayedCards) {
                ((Minion) card).setSleep(false);
            }
        }
    }

    void canBeAttackedUpdater(ClientHandler cl) {
        ArrayList<Card> list;
        if (cl.equals(cl1)) {
            list = player2PlayedCards;
        } else {
            list = player1PlayedCards;
        }
        boolean flag = false;
        for (Card cards : list) {
            if (cards.getAttributes() != null && cards.getAttributes().contains(Attribute.Taunt)) {
                flag = true;
                break;
            }
        }
        if (flag) {
            for (Card cards : list) {
                if (cards.getAttributes() != null && cards.getAttributes().contains(Attribute.Taunt)) {
                    ((Minion) cards).setCanBeAttacked(true);
                } else {
                    ((Minion) cards).setCanBeAttacked(false);
                }
            }
        } else {
            for (Card card : list) {
                ((Minion) card).setCanBeAttacked(true);
            }
        }
    }

    public void checkDestroyMinion() {
        ListIterator<Card> iterator = this.player2PlayedCards.listIterator();
        while (iterator.hasNext()) {
            Minion minion = (Minion) iterator.next();
            if (minion.getHealth() <= 0) {
                player2Hero.setDefence(player2Hero.getDefence() + player2DefenceAdd);
                updateGameLog(String.format("Minion %s defeated .", minion.getName()));
                iterator.remove();
            }
        }
        ListIterator<Card> iterator1 = this.player1PlayedCards.listIterator();
        while (iterator1.hasNext()) {
            Minion minion = (Minion) iterator1.next();
            if (minion.getHealth() <= 0) {
                player1Hero.setDefence(player1Hero.getDefence() + player1DefenceAdd);
                updateGameLog(String.format("Minion %s defeated .", minion.getName()));
                iterator1.remove();
            }
        }
    }

    void checkDestroyedWeapon(ClientHandler cl) {
        if (player1Weapon != null) {
            if (player1Weapon.getDurability() <= 0) {
                setPlayerWeapon(null, cl);
            }
        }
        if (player2Weapon != null) {
            if (player2Weapon.getDurability() <= 0) {
                setPlayerWeapon(null, cl);
            }
        }
    }

    public void updateWeapon(ClientHandler cl) {
        if (cl.equals(cl1)) {
            Weapon weapon = this.player1Weapon;
            weapon.setDurability(weapon.getDurability() - 1);
            updateGameLog(String.format("%s use Weapon.", player1.getUsername()));
            setPlayerWeapon(weapon, cl);
            player1Hero.setCanAttack(false);
        } else {
            Weapon weapon = this.player2Weapon;
            weapon.setDurability(weapon.getDurability() - 1);
            updateGameLog(String.format("%s use Weapon.", player2.getUsername()));
            setPlayerWeapon(weapon, cl);
            player2Hero.setCanAttack(false);
        }
        checkDestroyedWeapon(cl);
    }

    public void setPlayerWeapon(Weapon weapon, ClientHandler cl) {
        if (cl.equals(cl1)) {
            this.player1Weapon = weapon;
            this.player1Hero.setWeapon(weapon);
            if (weapon == null) {
                this.player1Hero.setCanAttack(false);
            } else {
                this.player1Hero.setCanAttack(true);
            }
        } else {
            this.player2Weapon = weapon;
            this.player2Hero.setWeapon(weapon);
            if (weapon == null) {
                this.player2Hero.setCanAttack(false);
            } else {
                this.player2Hero.setCanAttack(true);
            }
        }
    }

    int WinnerChecker() {
        if (!player2Hero.equals(currentHero) && player2Hero.getHealth() <= 0) {
            return 1;
        } else if (!player1Hero.equals(currentHero) && player1Hero.getHealth() <= 0) {
            return 2;
        } else if (player2Hero.equals(currentHero) && player2Hero.getHealth() <= 0) {
            return 3;
        } else if (player1Hero.equals(currentHero) && player1Hero.getHealth() <= 0) {
            return 4;
        } else {
            return 0;
        }
    }

    public void checkForWinner() {
        int i = WinnerChecker();
        switch (i) {
            case 1:
                Admin.getInstance().winGame(player1Hero.getName().toLowerCase());
                boolean flag = normalMode();
                if (flag) {
//                    Admin.getInstance().updateDeckStates(1);
                }
                break;
            case 2:
                Admin.getInstance().winGame(player2Hero.getName().toLowerCase());
                flag = normalMode();
                if (flag) {
//                    Admin.getInstance().updateDeckStates(1);
                }
                break;
            case 3:
                Admin.getInstance().winGame(player1Hero.getName().toLowerCase());
                flag = normalMode();
                if (flag) {
//                    Admin.getInstance().updateDeckStates(0);
                }
                break;

            case 4:
                Admin.getInstance().winGame(player2Hero.getName().toLowerCase());
                flag = normalMode();
                if (flag) {
//                    Admin.getInstance().updateDeckStates(0);
                }
                break;
            case 0:
                break;
        }
    }


    Card randomCardDraw(ArrayList<Card> cards) {
        if (cards.size() == 0)
            return null;
        if (deckReaderMode) {
            return cards.get(0);
        }
        Collections.shuffle(cards);
        return cards.get(0);
    }


    public ArrayList<ActionModel> friendlyContiniousModel() {
        return getActionModels(player1ContiniousActionCard);
    }

    public ArrayList<ActionModel> enemyContiniousModel() {
        return getActionModels(player2ContiniousActionCard);
    }

    private ArrayList<ActionModel> getActionModels(ArrayList<Card> continiousActionModel) {
        ArrayList<ActionModel> models = new ArrayList<>();
        for (Card card : continiousActionModel) {
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

    boolean normalMode() {
        return !deckReaderMode && !practiceMode;
    }

    void matinAction(boolean p2Turn) {
        if (p2Turn) {
            for (Card card : player2PlayedCards) {
                if (card.getName().equalsIgnoreCase("matin")) {
                    card.accept(new ActionVisitor(), null, player2DeckCards, player2HandCards, player2PlayedCards, player1DeckCards, player1HandCards, player1PlayedCards, player2Hero, player1Hero, this);
                }
            }

        } else {
            for (Card card : player1PlayedCards) {
                if (card.getName().equalsIgnoreCase("matin")) {
                    card.accept(new ActionVisitor(), null, player1DeckCards, player1HandCards, player1PlayedCards, player2DeckCards, player2HandCards, player2PlayedCards, player1Hero, player2Hero, this);
                }
            }
        }
    }

    void benyaminAction(boolean p2Turn) {
        if (p2Turn) {
            for (Card card : player2PlayedCards) {
                if (card.getName().equalsIgnoreCase("benyamin")) {
                    card.accept(new ActionVisitor(), new Benyamin(), player2DeckCards, player2HandCards, player2PlayedCards, player1DeckCards, player1HandCards, player1PlayedCards, player2Hero, player1Hero, this);
                }
            }
        } else {
            for (Card card : player1PlayedCards) {
                if (card.getName().equalsIgnoreCase("benyamin")) {
                    card.accept(new ActionVisitor(), new Benyamin(), player1DeckCards, player1HandCards, player1PlayedCards, player2DeckCards, player2HandCards, player2PlayedCards, player1Hero, player2Hero, this);
                }
            }
        }
    }

    public void shahryarAction(Minion minion, boolean p2Turn) {
        if (p2Turn) {
            for (Card card : player2PlayedCards) {
                if (card.getName().equalsIgnoreCase("shahryar")) {
                    card.accept(new ActionVisitor(), minion, player2DeckCards, player2HandCards, player2PlayedCards, player1DeckCards, player1HandCards, player1PlayedCards, player2Hero, player1Hero, this);
                }
            }
        } else {
            for (Card card : player1PlayedCards) {
                if (card.getName().equalsIgnoreCase("shahryar")) {
                    card.accept(new ActionVisitor(), minion, player1DeckCards, player1HandCards, player1PlayedCards, player2DeckCards, player2HandCards, player2PlayedCards, player1Hero, player2Hero, this);
                }
            }
        }
    }

    public void faezeAction(Minion target, boolean p2Turn, ClientHandler cl) {
        if (p2Turn) {
            for (Card card : player1PlayedCards) {
                if (card.getName().equalsIgnoreCase("faeze")) {
                    Attack2((Minion) card, target, cl);
                }
            }
        } else {
            for (Card card : player2PlayedCards) {
                if (card.getName().equalsIgnoreCase("faeze")) {
                    Attack2((Minion) card, target, cl);
                }
            }
        }
    }

    public void Attack(int attacker, int target, ClientHandler cl) {
        ActionHandler actionHandler = new ActionHandler();
        if (cl.equals(cl1)) {
            actionHandler.Attack(attacker, target, player1PlayedCards, player2PlayedCards, player1Hero, player2Hero,
                    this, cl1, cl2);
        } else {
            actionHandler.Attack(attacker, target, player2PlayedCards, player1PlayedCards, player2Hero, player1Hero,
                    this, cl2, cl1);
        }
        checkDestroyMinion();
        checkForWinner();
    }

    private void Attack2(Minion attacker, Minion target, ClientHandler cl) {
        if (player1PlayedCards.contains(target)) {
            ActionHandler actionHandler = new ActionHandler();
            actionHandler.attackMinion2(attacker, target);
            updateGameLog(String.format("%s Attacked %s", attacker.getName(), target.getName()));
            checkDestroyMinion();
        }
    }

    public void setSleep(Minion minion, ClientHandler cl) {
        for (Card card : player2PlayedCards) {
            if (card.equals(minion)) {
                ((Minion) card).setSleep(true);
            }
        }
    }

    public int heroPowerCanBePlayed(ClientHandler cl) {
        Hero hero;
        int i;
        if (cl.equals(cl1)) {
            hero = player1Hero;
            i = player1HeroPowerUsageTime;
        } else {
            hero = player2Hero;
            i = player2HeroPowerUsageTime;
        }
        if (!(hero instanceof Hunter)) {
            if (i > 0) {
                if (heroPowerTargetNeeded(cl) > 0) {
                    if (heroPowerTargetNeeded(cl) == 1) {
                        return 1;
                    } else if (heroPowerTargetNeeded(cl) == 2) {
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

    public void addContiniousActionCard(Card cards, boolean p2Turn) {
        if (p2Turn) {
            player2ContiniousActionCard.add(cards);
        } else {
            player1ContiniousActionCard.add(cards);
        }
    }

    public void spendManaOnMinion(int i, boolean p2Turn) {
        if (p2Turn) {
            for (Card card : player2ContiniousActionCard) {
                if (card.getName().equalsIgnoreCase("strengthinnumbers") || card.getName().equalsIgnoreCase("strengthinnumbersdr")) {
                    ((Spell) card).setManaSpendOnSth(((Spell) card).getManaSpendOnSth() + i);
                    card.accept(new ActionVisitor(), null, player2DeckCards, player2HandCards, player2PlayedCards, player1DeckCards, player1HandCards, player1PlayedCards, player2Hero, player1Hero, this);
                    break;
                }
            }
        } else {
            for (Card card : player1ContiniousActionCard) {
                if (card.getName().equalsIgnoreCase("strengthinnumbers") || card.getName().equalsIgnoreCase("strengthinnumbersdr")) {
                    ((Spell) card).setManaSpendOnSth(((Spell) card).getManaSpendOnSth() + i);
                    card.accept(new ActionVisitor(), null, player1DeckCards, player1HandCards, player1PlayedCards, player2DeckCards, player2HandCards, player2PlayedCards, player1Hero, player2Hero, this);
                    break;
                }
            }
        }
    }

    public void spendManaOnSpell(int i, boolean p2Turn) {
        if (p2Turn) {
            for (Card card : player2ContiniousActionCard) {
                if (card.getName().equalsIgnoreCase("learnjavadonic")) {
                    ((Spell) card).setManaSpendOnSth(((Spell) card).getManaSpendOnSth() + i);
                    card.accept(new ActionVisitor(), null, player2DeckCards, player2HandCards, player2PlayedCards, player1DeckCards, player1HandCards, player1PlayedCards, player2Hero, player1Hero, this);
                    break;
                }
            }
        } else {
            for (Card card : player1ContiniousActionCard) {
                if (card.getName().equalsIgnoreCase("learnjavadonic")) {
                    ((Spell) card).setManaSpendOnSth(((Spell) card).getManaSpendOnSth() + i);
                    card.accept(new ActionVisitor(), null, player1DeckCards, player1HandCards, player1PlayedCards, player2DeckCards, player2HandCards, player2PlayedCards, player1Hero, player2Hero, this);
                    break;
                }
            }
        }
    }

    public void finishAction(Card card, Hero hero) {
        ArrayList<Card> list;
        if (hero.equals(player1Hero)) {
            list = player1ContiniousActionCard;
        } else {
            list = player2ContiniousActionCard;
        }
        for (Card card1 : list) {
            if (card1.equals(card)) {
                list.remove(card1);
                break;
            }
        }
    }

    private int heroPowerTargetNeeded(ClientHandler cl) {
        Hero hero;
        if (cl.equals(cl1)) {
            hero = player1Hero;
        } else {
            hero = player2Hero;
        }
        if (hero.isPowerNeedFriendlyTarget()) {
            return 1;
        } else if (hero.isPowerNeedEnemyTarget()) {
            return 2;
        }
        return 0;
    }

    public boolean heroPower(int target, ClientHandler cl) {
        Character tar = createTarget(target, cl);
        return playHeroPower(tar, cl);
    }


    private boolean playHeroPower(Character target, ClientHandler cl) {
        if (cl.equals(cl1)) {
            return player1HeroPower(target);
        } else {
            return player2HeroPower(target);
        }
    }

    private boolean player1HeroPower(Character target) {
        if (player1Hero instanceof Warlock) {
            player1Hero.accept(new HeroPowerVisitor(), target, player1DeckCards, player1HandCards, player1PlayedCards, player2DeckCards, player2HandCards, player2PlayedCards, this);
            heroTakeDamage(player1Hero, 2);
            player1HeroPowerUsageTime -= 1;
            updateGameLog(player1.getUsername() + " Use HeroPower");
            checkForWinner();
            return true;
        } else {
            if (player1NotUsedMana >= (player1Hero.getHeroPowerManaCost() - player1PowerManaDecrease)) {
                player1Hero.accept(new HeroPowerVisitor(), target, player1DeckCards, player1HandCards, player1PlayedCards, player2DeckCards, player2HandCards, player2PlayedCards, this);
                player1NotUsedMana -= (player1Hero.getHeroPowerManaCost() - player1PowerManaDecrease);
                player1HeroPowerUsageTime -= 1;
                updateGameLog(player1.getUsername() + " Use HeroPower");
                checkForWinner();
                return true;
            }
            return false;
        }
    }

    private boolean player2HeroPower(Character target) {
        if (player2Hero instanceof Warlock) {
            player2Hero.accept(new HeroPowerVisitor(), target, player2DeckCards, player2HandCards, player2PlayedCards, player1DeckCards, player1HandCards, player1PlayedCards, this);
            heroTakeDamage(player2Hero, 2);
            player2HeroPowerUsageTime -= 1;
            updateGameLog(player2.getUsername() + " Use HeroPower");
            checkForWinner();
            return true;
        } else {
            if (player2NotUsedMana >= (player2Hero.getHeroPowerManaCost() - player2PowerManaDecrease)) {
                player2Hero.accept(new HeroPowerVisitor(), target, player2DeckCards, player2HandCards, player2PlayedCards, player1DeckCards, player1HandCards, player1PlayedCards, this);
                player2NotUsedMana -= (player2Hero.getHeroPowerManaCost() - player2PowerManaDecrease);
                player2HeroPowerUsageTime -= 1;
                updateGameLog(player2.getUsername() + " Use HeroPower");
                checkForWinner();
                return true;
            }
            return false;
        }

    }


    private Character createTarget(int target, ClientHandler cl) {
        if (cl.equals(cl1)) {
            if (target >= 10 && target < 20) {
                return player1PlayedCards.get(target - 10);
            } else if (target >= 20 && target < 30) {
                return player2PlayedCards.get(target - 20);
            } else if (target == 1) {
                return player1Hero;
            } else if (target == 2) {
                return player2Hero;
            } else {
                return null;
            }
        } else {
            if (target >= 10 && target < 20) {
                return player2PlayedCards.get(target - 10);
            } else if (target >= 20 && target < 30) {
                return player1PlayedCards.get(target - 20);
            } else if (target == 1) {
                return player2Hero;
            } else if (target == 2) {
                return player1Hero;
            } else {
                return null;
            }
        }
    }

    public String playCheck(String st, ClientHandler cl) {
        if (cl.equals(cl1)) {
            return canBePlayed(st, player1HandCards, player1PlayedCards,
                    player1NotUsedMana, player1ManaDecrease);
        } else {
            return canBePlayed(st, player2HandCards, player2PlayedCards,
                    player2NotUsedMana, player2ManaDecrease);
        }
    }

    public String canBePlayed(String string, ArrayList<Card> hand, ArrayList<Card> played,
                              int notUsed, int manaDecrease) {
        for (Card cards : hand) {
            if (cards.getName().equalsIgnoreCase(string)) {
                if (notUsed >= cards.getManaCost() - manaDecrease) {
                    if (getCardOf(string) instanceof Minion) {
                        if (played.size() < 7) {
                            return "okminion";
                        } else {
                            return "full";
                        }
                    } else if (getCardOf(string) instanceof Spell) {
                        return "okspell";
                    } else if (getCardOf(string) instanceof Weapon) {
                        return "okweapon";
                    }
                    break;
                } else {
                    return "mana";
                }
            }
        }
        throw new RuntimeException();
    }

    public void playCard(String string, int i, int target, ClientHandler cl) {
        if (cl.equals(cl1)) {
            playCard(string, i, target, this, player1DeckCards, player1HandCards, player1PlayedCards,
                    player2DeckCards, player2HandCards, player2PlayedCards, player1Hero, player2Hero,
                    false, cl1, cl2);
        } else {
            playCard(string, i, target, this, player2DeckCards, player2HandCards, player2PlayedCards,
                    player1DeckCards, player1HandCards, player1PlayedCards, player2Hero, player1Hero,
                    true, cl2, cl1);
        }
    }


    void playCard(String string, int i, int target, Managers gameManager,
                  ArrayList<Card> p1deck, ArrayList<Card> p1hand, ArrayList<Card> p1played,
                  ArrayList<Card> p2deck, ArrayList<Card> p2hand, ArrayList<Card> p2played,
                  Hero p1hero, Hero p2hero, boolean p2Turn, ClientHandler cl1, ClientHandler cl2) {
        Minion targeted = (Minion) createTarget(target, cl1);
        for (Card cards : p1hand) {
            if (cards.getName().equalsIgnoreCase(string)) {
                checkContiniousAction(cards, p2Turn, gameManager);
                cards.accept(new BattlecryVisitor(), targeted, p1deck, p1hand,
                        p1played, p2deck, p2hand,
                        p2played, p1hero, p2hero, gameManager);
                if (cards instanceof Minion) {
                    playMinion((Minion) cards, i, targeted, gameManager, cl1);
                } else if (cards instanceof Spell) {
                    playSpell((Spell) cards, targeted, gameManager, cl1);

                } else if (cards instanceof Weapon) {
                    playWeapon((Weapon) cards, gameManager, cl1);
                }
                Admin.getInstance().updateCardUsageTime(cards.getName().toLowerCase());
                break;
            }
        }
        gameManager.checkDestroyMinion();
        gameManager.checkForWinner();
    }

    private void playMinion(Minion minions, int i, Character target, Managers gameManager, ClientHandler cl) {
        if (cl.equals(cl1)) {
            spendManaOnMinion(minions.getManaCost() - player1ManaDecrease, false);
            setPlayer1NotUsedMana(player1NotUsedMana - (minions.getManaCost() - player1ManaDecrease));
            minions.accept(new ActionVisitor(), target, player1DeckCards, player1HandCards,
                    player1PlayedCards, player2DeckCards, player2HandCards,
                    player2PlayedCards, player1Hero, player2Hero, gameManager);
            summonMinion(minions, i, gameManager);
            checkDestroyMinion();
            hunterPowerAction(minions, false);
            faezeAction(minions, false, cl);
//            Admin.getInstance().updateGameLog(String.format("%s Played %s", player1.getUsername(), minions.getName()));
        } else {
            gameManager.spendManaOnMinion(minions.getManaCost() - player2ManaDecrease, true);
            gameManager.setPlayer1NotUsedMana(player2NotUsedMana - (minions.getManaCost() - player2ManaDecrease));
            minions.accept(new ActionVisitor(), target, player2DeckCards, player2HandCards,
                    player2PlayedCards, player1DeckCards, player1HandCards,
                    player1PlayedCards, player2Hero, player1Hero, gameManager);
            summonMinion(minions, i, gameManager);
            gameManager.checkDestroyMinion();
            gameManager.hunterPowerAction(minions, true);
            gameManager.faezeAction(minions, true, cl);
//            Admin.getInstance().updateGameLog(String.format("%s Played %s", player2.getUsername(), minions.getName()));
        }
    }

    public void summonMinion(Minion minions, int i, Managers gameManager) {
        if (p2Turn) {
//            ((PracticeManager) gameManager).practiceSummonMinion(minions);
            if (player2PlayedCards.size() < 7) {
                gameManager.shahryarAction(minions, false);
                if (minions.getAttributes() != null && (minions.getAttributes().contains(Attribute.Charge) || minions.getAttributes().contains(Attribute.Rush))) {
                    minions.setSleep(false);
                }
                if (i < 0) {
                    player2PlayedCards.add(minions);
                } else {
                    player2PlayedCards.add(i, minions);
                }
                player2HandCards.remove(minions);
            }
        } else {
            if (player1PlayedCards.size() < 7) {
                gameManager.shahryarAction(minions, false);
                if (minions.getAttributes() != null && (minions.getAttributes().contains(Attribute.Charge) || minions.getAttributes().contains(Attribute.Rush))) {
                    minions.setSleep(false);
                }
                if (i < 0) {
                    player1PlayedCards.add(minions);
                } else {
                    player1PlayedCards.add(i, minions);
                }
                player1HandCards.remove(minions);
            }
        }
    }

    private void playSpell(Spell spell, Character target, Managers gameManager, ClientHandler cl) {
        if (cl.equals(cl1)) {
            spendManaOnSpell(spell.getManaCost() - player1ManaDecrease, p2Turn);
            setPlayer1NotUsedMana(player1NotUsedMana - (spell.getManaCost() - player1ManaDecrease));
            player1HandCards.remove(spell);
            spell.accept(new ActionVisitor(), target, player1DeckCards, player1HandCards,
                    player1PlayedCards, player2DeckCards, player2HandCards,
                    player2PlayedCards, player1Hero, player2Hero, gameManager);

//            Admin.getInstance().updateGameLog(String.format("%s Cast %s", player1.getUsername(), spell.getName()));
        } else {
            gameManager.spendManaOnSpell(spell.getManaCost() - player2ManaDecrease, p2Turn);
            setPlayer2NotUsedMana(player2NotUsedMana - (spell.getManaCost() - player2ManaDecrease));
            player2HandCards.remove(spell);
            spell.accept(new ActionVisitor(), target, player2DeckCards, player2HandCards,
                    player2PlayedCards, player1DeckCards, player1HandCards,
                    player1PlayedCards, player2Hero, player1Hero, gameManager);

//            Admin.getInstance().updateGameLog(String.format("%s Cast %s", player2.getUsername(), spell.getName()));
        }
    }

    private void playWeapon(Weapon weapon, Managers gameManager, ClientHandler cl) {
        if (cl.equals(cl1)) {
            setPlayer1NotUsedMana(player1NotUsedMana - (weapon.getManaCost() - player1ManaDecrease));
            player1HandCards.remove(weapon);
//            gameManager.setPlayer1Weapon(weapon);
//            Admin.getInstance().updateGameLog(String.format("%s Equiped %s", player1.getUsername(), weapon.getName()));
        } else {
            setPlayer2NotUsedMana(player2NotUsedMana - (weapon.getManaCost() - player2ManaDecrease));
            player2HandCards.remove(weapon);
//            gameManager.setPlayer2Weapon(weapon);
//            Admin.getInstance().updateGameLog(String.format("%s Equiped %s", player2.getUsername(), weapon.getName()));
        }
        setPlayerWeapon(weapon, cl);
    }


    public void hunterPowerAction(Minion minion, boolean p2Turn) {
        if (p2Turn) {
            if (player1Hero instanceof Hunter) {
                player1Hero.accept(new HeroPowerVisitor(), minion, player2DeckCards, player2HandCards, player2PlayedCards, player1DeckCards, player1HandCards, player1PlayedCards, this);
                Admin.getInstance().summonedMinion(minion, 1, minion.getDamage(), minion.getHealth());
            }
        } else {
            if (player2Hero instanceof Hunter) {
                player2Hero.accept(new HeroPowerVisitor(), minion, player1DeckCards, player1HandCards, player1PlayedCards, player2DeckCards, player2HandCards, player2PlayedCards, this);
                Admin.getInstance().summonedMinion(minion, 1, minion.getDamage(), minion.getHealth());
            }
        }
    }


    private void checkContiniousAction(Card cards, boolean p2Turn, Managers gameManager) {
        for (ContiniousActionCarts value : ContiniousActionCarts.values()) {
            if (cards.getName().equalsIgnoreCase(value.toString())) {
                gameManager.addContiniousActionCard(cards, p2Turn);
                break;
            }
        }
    }


    void heroTakeDamage(Hero hero, int i) {
        hero.setHealth(hero.getHealth() - i);
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public ClientHandler getCl1() {
        return cl1;
    }

    public void setCl1(ClientHandler cl1) {
        this.cl1 = cl1;
    }

    public ClientHandler getCl2() {
        return cl2;
    }

    public void setCl2(ClientHandler cl2) {
        this.cl2 = cl2;
    }

    public InfoPassive getPlayer1InfoPassive() {
        return player1InfoPassive;
    }

    public void setPlayer1InfoPassive(InfoPassive player1InfoPassive) {
        this.player1InfoPassive = player1InfoPassive;
    }

    public InfoPassive getPlayer2InfoPassive() {
        return player2InfoPassive;
    }

    public void setPlayer2InfoPassive(InfoPassive player2InfoPassive) {
        this.player2InfoPassive = player2InfoPassive;
    }

    public int getPlayer1StartingMana() {
        return player1StartingMana;
    }

    public void setPlayer1StartingMana(int player1StartingMana) {
        this.player1StartingMana = player1StartingMana;
    }

    public int getPlayer2StartingMana() {
        return player2StartingMana;
    }

    public void setPlayer2StartingMana(int player2StartingMana) {
        this.player2StartingMana = player2StartingMana;
    }

    public int getPlayer1TotalMana() {
        return player1TotalMana;
    }

    public void setPlayer1TotalMana(int player1TotalMana) {
        this.player1TotalMana = player1TotalMana;
    }

    public int getPlayer2TotalMana() {
        return player2TotalMana;
    }

    public void setPlayer2TotalMana(int player2TotalMana) {
        this.player2TotalMana = player2TotalMana;
    }

    public int getPlayer1NotUsedMana() {
        return player1NotUsedMana;
    }

    public void setPlayer1NotUsedMana(int player1NotUsedMana) {
        this.player1NotUsedMana = player1NotUsedMana;
    }

    public int getPlayer2NotUsedMana() {
        return player2NotUsedMana;
    }

    public void setPlayer2NotUsedMana(int player2NotUsedMana) {
        this.player2NotUsedMana = player2NotUsedMana;
    }

    public int getPlayer1DrawCardNum() {
        return player1DrawCardNum;
    }

    public void setPlayer1DrawCardNum(int player1DrawCardNum) {
        this.player1DrawCardNum = player1DrawCardNum;
    }

    public int getPlayer2DrawCardNum() {
        return player2DrawCardNum;
    }

    public void setPlayer2DrawCardNum(int player2DrawCardNum) {
        this.player2DrawCardNum = player2DrawCardNum;
    }

    public int getPlayer1HeroPowerUsageTime() {
        return player1HeroPowerUsageTime;
    }

    public void setPlayer1HeroPowerUsageTime(int player1HeroPowerUsageTime) {
        this.player1HeroPowerUsageTime = player1HeroPowerUsageTime;
    }

    public int getPlayer2HeroPowerUsageTime() {
        return player2HeroPowerUsageTime;
    }

    public void setPlayer2HeroPowerUsageTime(int player2HeroPowerUsageTime) {
        this.player2HeroPowerUsageTime = player2HeroPowerUsageTime;
    }

    public int getP1HPMAXUT() {
        return p1HPMAXUT;
    }

    public void setP1HPMAXUT(int p1HPMAXUT) {
        this.p1HPMAXUT = p1HPMAXUT;
    }

    public int getP2HPMAXUT() {
        return p2HPMAXUT;
    }

    public void setP2HPMAXUT(int p2HPMAXUT) {
        this.p2HPMAXUT = p2HPMAXUT;
    }

    public int getPlayer1PowerManaDecrease() {
        return player1PowerManaDecrease;
    }

    public void setPlayer1PowerManaDecrease(int player1PowerManaDecrease) {
        this.player1PowerManaDecrease = player1PowerManaDecrease;
    }

    public int getPlayer2PowerManaDecrease() {
        return player2PowerManaDecrease;
    }

    public void setPlayer2PowerManaDecrease(int player2PowerManaDecrease) {
        this.player2PowerManaDecrease = player2PowerManaDecrease;
    }

    public int getPlayer1ManaDecrease() {
        return player1ManaDecrease;
    }

    public void setPlayer1ManaDecrease(int player1ManaDecrease) {
        this.player1ManaDecrease = player1ManaDecrease;
    }

    public int getPlayer2ManaDecrease() {
        return player2ManaDecrease;
    }

    public void setPlayer2ManaDecrease(int player2ManaDecrease) {
        this.player2ManaDecrease = player2ManaDecrease;
    }

    public int getPlayer1DefenceAdd() {
        return player1DefenceAdd;
    }

    public void setPlayer1DefenceAdd(int player1DefenceAdd) {
        this.player1DefenceAdd = player1DefenceAdd;
    }

    public int getPlayer2DefenceAdd() {
        return player2DefenceAdd;
    }

    public void setPlayer2DefenceAdd(int player2DefenceAdd) {
        this.player2DefenceAdd = player2DefenceAdd;
    }

    public ArrayList<Card> getPlayer1CardsOfPlayer() {
        return player1CardsOfPlayer;
    }

    public void setPlayer1CardsOfPlayer(ArrayList<Card> player1CardsOfPlayer) {
        this.player1CardsOfPlayer = player1CardsOfPlayer;
    }

    public ArrayList<Card> getPlayer1DeckCards() {
        return player1DeckCards;
    }

    public void setPlayer1DeckCards(ArrayList<Card> player1DeckCards) {
        this.player1DeckCards = player1DeckCards;
    }

    public ArrayList<Card> getPlayer1HandCards() {
        return player1HandCards;
    }

    public void setPlayer1HandCards(ArrayList<Card> player1HandCards) {
        this.player1HandCards = player1HandCards;
    }

    public ArrayList<Card> getPlayer1PlayedCards() {
        return player1PlayedCards;
    }

    public void setPlayer1PlayedCards(ArrayList<Card> player1PlayedCards) {
        this.player1PlayedCards = player1PlayedCards;
    }

    public ArrayList<Card> getPlayer2CardsOfPlayer() {
        return player2CardsOfPlayer;
    }

    public void setPlayer2CardsOfPlayer(ArrayList<Card> player2CardsOfPlayer) {
        this.player2CardsOfPlayer = player2CardsOfPlayer;
    }

    public ArrayList<Card> getPlayer2DeckCards() {
        return player2DeckCards;
    }

    public void setPlayer2DeckCards(ArrayList<Card> player2DeckCards) {
        this.player2DeckCards = player2DeckCards;
    }

    public ArrayList<Card> getPlayer2HandCards() {
        return player2HandCards;
    }

    public void setPlayer2HandCards(ArrayList<Card> player2HandCards) {
        this.player2HandCards = player2HandCards;
    }

    public ArrayList<Card> getPlayer2PlayedCards() {
        return player2PlayedCards;
    }

    public void setPlayer2PlayedCards(ArrayList<Card> player2PlayedCards) {
        this.player2PlayedCards = player2PlayedCards;
    }

    public ArrayList<String> getGameLog() {
        return gameLog;
    }

    public void setGameLog(ArrayList<String> gameLog) {
        this.gameLog = gameLog;
    }

    public ArrayList<Card> getPlayer1ContiniousActionCard() {
        return player1ContiniousActionCard;
    }

    public void setPlayer1ContiniousActionCard(ArrayList<Card> player1ContiniousActionCard) {
        this.player1ContiniousActionCard = player1ContiniousActionCard;
    }

    public ArrayList<Card> getPlayer2ContiniousActionCard() {
        return player2ContiniousActionCard;
    }

    public void setPlayer2ContiniousActionCard(ArrayList<Card> player2ContiniousActionCard) {
        this.player2ContiniousActionCard = player2ContiniousActionCard;
    }

    public Weapon getPlayer1Weapon() {
        return player1Weapon;
    }

    public void setPlayer1Weapon(Weapon player1Weapon) {
        this.player1Weapon = player1Weapon;
    }

    public void setPlayer2Weapon(Weapon player1Weapon) {
        this.player1Weapon = player1Weapon;
    }


    public Weapon getPlayer2Weapon() {
        return player2Weapon;
    }

    public Hero getPlayer1Hero() {
        return player1Hero;
    }

    public void setPlayer1Hero(Hero player1Hero) {
        this.player1Hero = player1Hero;
    }

    public Hero getCurrentHero() {
        return currentHero;
    }

    public void setCurrentHero(Hero currentHero) {
        this.currentHero = currentHero;
    }

    public Hero getPlayer2Hero() {
        return player2Hero;
    }

    public void setPlayer2Hero(Hero player2Hero) {
        this.player2Hero = player2Hero;
    }

    public boolean isDeckReaderMode() {
        return deckReaderMode;
    }

    public void setDeckReaderMode(boolean deckReaderMode) {
        this.deckReaderMode = deckReaderMode;
    }

    public boolean isPracticeMode() {
        return practiceMode;
    }

    public void setPracticeMode(boolean practiceMode) {
        this.practiceMode = practiceMode;
    }

    public boolean isP2Turn() {
        return p2Turn;
    }

    public void setP2Turn(boolean p2Turn) {
        this.p2Turn = p2Turn;
    }

    public GameState getState(ClientHandler cl) {
        if (cl.equals(cl1)) {
            ArrayList<CardModelView> hand = Admin.getInstance().modelList(player1HandCards);
            ArrayList<CardModelView> p1Played = Admin.getInstance().modelList(player1PlayedCards);
            ArrayList<CardModelView> p2Played = Admin.getInstance().modelList(player2PlayedCards);
            CardModelView p1w = Admin.getInstance().getWeaponViewModel(player1Weapon);
            CardModelView p2w = Admin.getInstance().getWeaponViewModel(player2Weapon);
            int dpm = player1Hero.getHeroPower().getManaCost() - player1PowerManaDecrease;
            int upm = player2Hero.getHeroPower().getManaCost() - player2PowerManaDecrease;
            System.out.println(ThreadColor.ANSI_GREEN + player1Hero.isCanAttack() + ThreadColor.ANSI_RESET);
            return new GameState(player1.getUsername(), player2.getUsername(), player1Hero.getName(),
                    player2Hero.getName(), time, player1HeroPowerUsageTime, player2HeroPowerUsageTime,
                    dpm, upm, player1NotUsedMana, player1TotalMana, player1Hero.getHealth(),
                    player2Hero.getHealth(), player1Hero.getDefence(), player2Hero.getDefence(),
                    player1HandCards.size(), player2HandCards.size(), player1PlayedCards.size(),
                    player2PlayedCards.size(), player1DeckCards.size(), player2DeckCards.size(),
                    player1Weapon != null, player2Weapon != null, player1Hero.isCanAttack(),
                    p1w, p2w, null, null, hand, p1Played, p2Played, gameLog);
        } else {
            ArrayList<CardModelView> hand = Admin.getInstance().modelList(player2HandCards);
            ArrayList<CardModelView> p1Played = Admin.getInstance().modelList(player2PlayedCards);
            ArrayList<CardModelView> p2Played = Admin.getInstance().modelList(player1PlayedCards);
            CardModelView p1w = Admin.getInstance().getWeaponViewModel(player2Weapon);
            CardModelView p2w = Admin.getInstance().getWeaponViewModel(player1Weapon);
            int upm = player1Hero.getHeroPower().getManaCost() - player1PowerManaDecrease;
            int dpm = player2Hero.getHeroPower().getManaCost() - player2PowerManaDecrease;
            return new GameState(player2.getUsername(), player1.getUsername(), player2Hero.getName(),
                    player1Hero.getName(), time, player2HeroPowerUsageTime, player1HeroPowerUsageTime,
                    dpm, upm, player2NotUsedMana, player2TotalMana, player2Hero.getHealth(),
                    player1Hero.getHealth(), player2Hero.getDefence(), player1Hero.getDefence(),
                    player2HandCards.size(), player1HandCards.size(), player2PlayedCards.size(),
                    player1PlayedCards.size(), player2DeckCards.size(), player1DeckCards.size(),
                    player2Weapon != null, player1Weapon != null, player2Hero.isCanAttack(),
                    p1w, p2w, null, null, hand, p1Played, p2Played, gameLog);
        }
    }

    public boolean getHeroCanAttack(ClientHandler cl) {
        if (cl.equals(cl1)) {
            return player1Hero.isCanAttack();
        } else {
            return player2Hero.isCanAttack();
        }
    }

    public ArrayList<Integer> listOfTargets(ClientHandler cl) {
        ArrayList<Card> list;
        if (cl.equals(cl1)) list = player2PlayedCards;
        else list = player1PlayedCards;

        ArrayList<Integer> targets = new ArrayList<>();
        int i = 0;
        for (Card card : list) {
            if (card.getAttributes().contains(Attribute.Taunt)) {
                targets.add(i);
            }
            i++;
        }
        if (targets.size() == 0) {
            for (int j = 0; j < list.size(); j++) {
                targets.add(j);
            }
            targets.add(-1);
        }
        return targets;
    }

    public boolean canDoAction(int i, ClientHandler cl) {
        if (cl.equals(cl1)) {
            return !((Minion) player1PlayedCards.get(i)).isSleep();
        }
        return !((Minion) player2PlayedCards.get(i)).isSleep();
    }


    class MyTimer extends Thread {
        private final java.text.SimpleDateFormat timerFormat = new java.text.SimpleDateFormat(" ss");
        private long startTime;
        private boolean isClicked;
        private boolean aiTurn;

        public MyTimer() {
        }

        public void run() {
            while (true) {
                isClicked = true;
                flag1 = true;
                aiTurn = true;
                startTime = 60 * 1000 + System.currentTimeMillis();
                while (flag1 && aiTurn && startTime - System.currentTimeMillis() > 0) {
                    long time1 = startTime - System.currentTimeMillis();
                    time = timerFormat.format(time1);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}