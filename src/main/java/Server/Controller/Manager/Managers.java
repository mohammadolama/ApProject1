package Server.Controller.Manager;

import Server.Controller.Actions.*;
import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Model.*;
import Server.Model.Cards.Card;
import Server.Model.Cards.Minion;
import Server.Model.Cards.Spell;
import Server.Model.Cards.Weapon;
import Server.Model.Enums.Attribute;
import Server.Model.Heros.Hero;

import java.util.ArrayList;
import java.util.ListIterator;

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
    boolean multyplayer = false;
    boolean p2Turn;
    String time;
    MyTimer timer;

    public abstract void endTurn(ClientHandler cl);

    void player1InfoInitilize(InfoPassive infoPassive) {
        InfoInitilizer ii = new InfoInitilizer(this);
        ii.player1InfoInitilize(infoPassive);
    }

    void player2InfoInitilize(InfoPassive infoPassive) {
        InfoInitilizer ii = new InfoInitilizer(this);
        ii.player2InfoInitilize(infoPassive);
    }

    void refillMana(boolean p2Turn, ClientHandler cl) {
        if (p2Turn) {
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

    void PlayerTurn(ClientHandler cl) {
        if (cl.equals(cl1)) {
            if (!drawCard(player1DrawCardNum, null, player1DeckCards, player1HandCards))
                heroTakeDamage(player1Hero, 1);
        } else {
            if (!drawCard(player2DrawCardNum, null, player2DeckCards, player2HandCards))
                heroTakeDamage(player2Hero, 1);
        }
        checkDestroyMinion();
        canBeAttackedUpdater(cl);
        wakeUp(cl);
        refillMana(p2Turn, cl);
        chargeWeapon(cl);
    }


    public boolean drawCard(int j, String mode, ArrayList<Card> deck, ArrayList<Card> hand) {
        HandleCards hc = new HandleCards(this);
        return hc.drawCard(j, mode, deck, hand);
    }

    public void updateGameLog(String string) {
        gameLog.add(string);
        if (gameLog.size() > 25) {
            gameLog.remove(0);
        }
    }


    void chargeWeapon(ClientHandler cl) {
        if (cl.equals(cl1)) {
            if (player1Hero.getWeapon() != null) {
                player1Hero.setCanAttack(true);
            }
        } else {
            if (player2Hero.getWeapon() != null) {
                player2Hero.setCanAttack(true);
            }
        }
    }

    void wakeUp(ClientHandler cl) {
        if (cl.equals(cl1)) {
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
                ((Minion) cards).setCanBeAttacked(cards.getAttributes() != null && cards.getAttributes().contains(Attribute.Taunt));
            }
        } else {
            for (Card card : list) {
                ((Minion) card).setCanBeAttacked(true);
            }
        }
    }

    public void checkDestroyMinion() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GameChecker gc = new GameChecker(this);
        gc.checkDestroyMinion();
    }

    public void updateWeapon(ClientHandler cl) {
        GameChecker gc = new GameChecker(this);
        gc.updateWeapon(cl);
    }

    public void setPlayerWeapon(Weapon weapon, ClientHandler cl) {
        if (cl.equals(cl1)) {
            this.player1Weapon = weapon;
            this.player1Hero.setWeapon(weapon);
            this.player1Hero.setCanAttack(weapon != null);
        } else {
            this.player2Weapon = weapon;
            this.player2Hero.setWeapon(weapon);
            this.player2Hero.setCanAttack(weapon != null);
        }
    }

    public void checkForWinner() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GameChecker gc = new GameChecker(this);
        gc.checkForWinner();
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

    public boolean onlineMode() {
        return deckReaderMode || multyplayer;
    }

    public void matinAction(boolean p2Turn) {
        AdditionalActions aa = new AdditionalActions(this);
        aa.matinAction(p2Turn);
    }

    void benyaminAction(boolean p2Turn) {
        AdditionalActions aa = new AdditionalActions(this);
        aa.benyaminAction(p2Turn);
    }

    public void shahryarAction(Minion minion, boolean p2Turn) {
        AdditionalActions aa = new AdditionalActions(this);
        aa.shahryarAction(minion, p2Turn);
    }

    public void faezeAction(Minion target, boolean p2Turn, ClientHandler cl) {
        AdditionalActions aa = new AdditionalActions(this);
        aa.faezeAction(target, p2Turn, cl);
    }

    public void Attack(int attacker, int target, ClientHandler cl) {
        ActionHandler actionHandler = new ActionHandler(this);
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

    public void Attack2(Minion attacker, Minion target, ArrayList<Card> list) {
        if (list.contains(target)) {
            ActionHandler actionHandler = new ActionHandler(this);
            actionHandler.attackMinion2(attacker, target);
            updateGameLog(String.format("%s Attacked %s", attacker.getName(), target.getName()));
            checkDestroyMinion();
        }
    }

    public void setSleep(Minion minion) {
        for (Card card : player2PlayedCards) {
            if (card.equals(minion)) {
                ((Minion) card).setSleep(true);
            }
        }
    }

    public int heroPowerCanBePlayed(ClientHandler cl) {
        PlayHeroPower ph = new PlayHeroPower(this);
        return ph.heroPowerCanBePlayed(cl);
    }

    public void addContiniousActionCard(Card cards, boolean p2Turn) {
        if (p2Turn) {
            player2ContiniousActionCard.add(cards);
        } else {
            player1ContiniousActionCard.add(cards);
        }
    }

    public void spendManaOnMinion(int i, boolean p2Turn) {
        SpendMana sm = new SpendMana(this);
        sm.spendManaOnMinion(i, p2Turn);
    }

    public void spendManaOnSpell(int i, ClientHandler cl) {
        SpendMana sm = new SpendMana(this);
        sm.spendManaOnSpell(i, cl);
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

    public boolean heroPower(int target, ClientHandler cl) {
        PlayHeroPower ph = new PlayHeroPower(this);
        return ph.heroPower(target, cl);
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
        PlayCards pc = new PlayCards(this);
        return pc.canBePlayed(string, hand, played, notUsed, manaDecrease);
    }

    public void playCard(String string, int i, int target, ClientHandler cl) {
        PlayCards pc = new PlayCards(this);
        pc.playCard(string, i, target, cl);
    }


    public void summonMinion(Minion minions, int i, Hero hero) {
        PlayCards pc = new PlayCards(this);
        if (hero.equals(player1Hero)) {
            pc.summonMinion(minions, i, this, cl1);
        } else if (hero.equals(player2Hero)) {
            pc.summonMinion(minions, i, this, cl2);
        }
    }

    public synchronized void summonedMinion(Card card, int mode, int damage, int hp) {
        CardModelView view = new CardModelView(card.getName(), 0);
        cl1.notifySummon(view, mode, damage, hp);
        if (cl2 != null) {
            cl2.notifySummon(view, mode, damage, hp);
        }
    }


    public void hunterPowerAction(Minion minion, boolean p2Turn) {
        AdditionalActions aa = new AdditionalActions(this);
        aa.hunterPowerAction(minion, p2Turn);
    }


    public void heroTakeDamage(Hero hero, int i) {
        hero.setHealth(hero.getHealth() - i);
    }

    public GameState getState(ClientHandler cl) {
        HandleGameState hgs = new HandleGameState(this);
        return hgs.getState(cl);
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


    public ArrayList<ActionModel> getPlayer1Actions(ClientHandler cl) {
        if (cl.equals(cl1)) {
            return friendlyContiniousModel();
        } else {
            return enemyContiniousModel();
        }
    }

    public ArrayList<ActionModel> getPlayer2Actions(ClientHandler cl) {
        if (cl.equals(cl1)) {
            return enemyContiniousModel();
        } else {
            return friendlyContiniousModel();
        }
    }

    public void discoverMode(String card1, String card2, String card3, Hero hero) {
        CardModelView view1 = Admin.getInstance().getPureViewModelOf(card1.toLowerCase());
        CardModelView view2 = Admin.getInstance().getPureViewModelOf(card2.toLowerCase());
        CardModelView view3 = Admin.getInstance().getPureViewModelOf(card3.toLowerCase());
        if (hero.equals(player1Hero)) {
            cl1.notifyAylar(view1, view2, view3);
        } else if (hero.equals(player2Hero)) {
            cl2.notifyAylar(view1, view2, view3);
        }
    }

    public void aylarAction(String weapon, ClientHandler cl) {
        Weapon weapon1 = (Weapon) Admin.getInstance().getCardOf(weapon);
        weapon1.setDurability(weapon1.getDurability() + 2);
        weapon1.setDamage(weapon1.getDamage() + 2);
        if (cl.equals(cl1)) {
            player1DeckCards.add(weapon1);
            updateGameLog(String.format("%s Choosed %s", player1.getUsername(), weapon1.getName()));
        } else {
            player2DeckCards.add(weapon1);
            updateGameLog(String.format("%s Choosed %s", player2.getUsername(), weapon1.getName()));
        }
    }

    public void cancleGame(ClientHandler cl) {
        timer.stopTimer();
        if (cl.equals(cl1)) {
            player1Hero.setHealth(0);
        } else {
            player2Hero.setHealth(0);
        }
        checkForWinner();
    }

    public void finishGame(ClientHandler cl) {
        if (!timer.isStopped()) {
            timer.stopTimer();
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
            player1HandCards = ar;
            player1DeckCards = arrayList;
        } else {
            player2HandCards = ar;
            player2DeckCards = arrayList;
        }
    }

    public boolean isP2Turn() {
        return p2Turn;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public ClientHandler getCl1() {
        return cl1;
    }

    public ClientHandler getCl2() {
        return cl2;
    }

    public void setPlayer1StartingMana(int player1StartingMana) {
        this.player1StartingMana = player1StartingMana;
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

    public void setPlayer1DrawCardNum(int player1DrawCardNum) {
        this.player1DrawCardNum = player1DrawCardNum;
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

    public void setP1HPMAXUT(int p1HPMAXUT) {
        this.p1HPMAXUT = p1HPMAXUT;
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

    public ArrayList<Card> getPlayer1DeckCards() {
        return player1DeckCards;
    }

    public ArrayList<Card> getPlayer1HandCards() {
        return player1HandCards;
    }

    public ArrayList<Card> getPlayer1PlayedCards() {
        return player1PlayedCards;
    }

    public ArrayList<Card> getPlayer2DeckCards() {
        return player2DeckCards;
    }

    public ArrayList<Card> getPlayer2HandCards() {
        return player2HandCards;
    }

    public ArrayList<Card> getPlayer2PlayedCards() {
        return player2PlayedCards;
    }

    public ArrayList<String> getGameLog() {
        return gameLog;
    }

    public ArrayList<Card> getPlayer1ContiniousActionCard() {
        return player1ContiniousActionCard;
    }

    public ArrayList<Card> getPlayer2ContiniousActionCard() {
        return player2ContiniousActionCard;
    }

    public Weapon getPlayer1Weapon() {
        return player1Weapon;
    }

    public Weapon getPlayer2Weapon() {
        return player2Weapon;
    }

    public Hero getPlayer1Hero() {
        return player1Hero;
    }

    public Hero getCurrentHero() {
        return currentHero;
    }

    public Hero getPlayer2Hero() {
        return player2Hero;
    }

    public boolean isDeckReaderMode() {
        return deckReaderMode;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }


}