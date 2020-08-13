package Server.Model;


import java.util.ArrayList;

public class GameState {
    private String friendlyUser, enemyUser, friendlyHero, enemyHero, time;
    private int downPowerUsage, upPowerUsage, downPowerMana, upPowerMana, notUsedMana,
            totalMana, downHP, upHP, downDefence, upDefence, downHandSize, upHandSize,
            downPalyedSize, upPlayedSize, downDeckSize, upDeckSize;
    private boolean downHasWeapon, upHasWeapon, heroCanAttack, p2Turn;
    private CardModelView downWeapon, upWeapon, downHeroPower, upHeroPower;

    private ArrayList<CardModelView> handCards, downPlayedCards, upPlayedCards;
    private ArrayList<String> logs;

    public GameState(String friendlyUser, String enemyUser, String friendlyHero, String enemyHero,
                     String time, int downPowerUsage, int upPowerUsage, int downPowerMana,
                     int upPowerMana, int notUsedMana, int totalMana, int downHP, int upHP,
                     int downDefence, int upDefence, int downHandSize, int upHandSize,
                     int downPalyedSize, int upPlayedSize, int downDeckSize, int upDeckSize,
                     boolean downHasWeapon, boolean upHasWeapon, boolean heroCanAttack,
                     CardModelView downWeapon, CardModelView upWeapon, CardModelView downHeroPower,
                     CardModelView upHeroPower, ArrayList<CardModelView> handCards,
                     ArrayList<CardModelView> downPlayedCards, ArrayList<CardModelView> upPlayedCards,
                     ArrayList<String> logs, boolean p2Turn) {
        this.friendlyUser = friendlyUser;
        this.enemyUser = enemyUser;
        this.friendlyHero = friendlyHero;
        this.enemyHero = enemyHero;
        this.time = time;
        this.downPowerUsage = downPowerUsage;
        this.upPowerUsage = upPowerUsage;
        this.downPowerMana = downPowerMana;
        this.upPowerMana = upPowerMana;
        this.notUsedMana = notUsedMana;
        this.totalMana = totalMana;
        this.downHP = downHP;
        this.upHP = upHP;
        this.downDefence = downDefence;
        this.upDefence = upDefence;
        this.downHandSize = downHandSize;
        this.upHandSize = upHandSize;
        this.downPalyedSize = downPalyedSize;
        this.upPlayedSize = upPlayedSize;
        this.downDeckSize = downDeckSize;
        this.upDeckSize = upDeckSize;
        this.downHasWeapon = downHasWeapon;
        this.upHasWeapon = upHasWeapon;
        this.heroCanAttack = heroCanAttack;
        this.downWeapon = downWeapon;
        this.upWeapon = upWeapon;
        this.downHeroPower = downHeroPower;
        this.upHeroPower = upHeroPower;
        this.handCards = handCards;
        this.downPlayedCards = downPlayedCards;
        this.upPlayedCards = upPlayedCards;
        this.logs = logs;
        this.p2Turn = p2Turn;
    }

    public GameState() {
    }

    public String getFriendlyUser() {
        return friendlyUser;
    }

    public void setFriendlyUser(String friendlyUser) {
        this.friendlyUser = friendlyUser;
    }

    public String getEnemyUser() {
        return enemyUser;
    }

    public void setEnemyUser(String enemyUser) {
        this.enemyUser = enemyUser;
    }

    public String getFriendlyHero() {
        return friendlyHero;
    }

    public void setFriendlyHero(String friendlyHero) {
        this.friendlyHero = friendlyHero;
    }

    public String getEnemyHero() {
        return enemyHero;
    }

    public void setEnemyHero(String enemyHero) {
        this.enemyHero = enemyHero;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDownPowerUsage() {
        return downPowerUsage;
    }

    public void setDownPowerUsage(int downPowerUsage) {
        this.downPowerUsage = downPowerUsage;
    }

    public int getUpPowerUsage() {
        return upPowerUsage;
    }

    public void setUpPowerUsage(int upPowerUsage) {
        this.upPowerUsage = upPowerUsage;
    }

    public int getDownPowerMana() {
        return downPowerMana;
    }

    public void setDownPowerMana(int downPowerMana) {
        this.downPowerMana = downPowerMana;
    }

    public int getUpPowerMana() {
        return upPowerMana;
    }

    public void setUpPowerMana(int upPowerMana) {
        this.upPowerMana = upPowerMana;
    }

    public int getNotUsedMana() {
        return notUsedMana;
    }

    public void setNotUsedMana(int notUsedMana) {
        this.notUsedMana = notUsedMana;
    }

    public int getTotalMana() {
        return totalMana;
    }

    public void setTotalMana(int totalMana) {
        this.totalMana = totalMana;
    }

    public int getDownHP() {
        return downHP;
    }

    public void setDownHP(int downHP) {
        this.downHP = downHP;
    }

    public int getUpHP() {
        return upHP;
    }

    public void setUpHP(int upHP) {
        this.upHP = upHP;
    }

    public int getDownDefence() {
        return downDefence;
    }

    public void setDownDefence(int downDefence) {
        this.downDefence = downDefence;
    }

    public int getUpDefence() {
        return upDefence;
    }

    public void setUpDefence(int upDefence) {
        this.upDefence = upDefence;
    }

    public int getDownHandSize() {
        return downHandSize;
    }

    public void setDownHandSize(int downHandSize) {
        this.downHandSize = downHandSize;
    }

    public int getUpHandSize() {
        return upHandSize;
    }

    public void setUpHandSize(int upHandSize) {
        this.upHandSize = upHandSize;
    }

    public int getDownPalyedSize() {
        return downPalyedSize;
    }

    public void setDownPalyedSize(int downPalyedSize) {
        this.downPalyedSize = downPalyedSize;
    }

    public int getUpPlayedSize() {
        return upPlayedSize;
    }

    public void setUpPlayedSize(int upPlayedSize) {
        this.upPlayedSize = upPlayedSize;
    }

    public int getDownDeckSize() {
        return downDeckSize;
    }

    public void setDownDeckSize(int downDeckSize) {
        this.downDeckSize = downDeckSize;
    }

    public int getUpDeckSize() {
        return upDeckSize;
    }

    public void setUpDeckSize(int upDeckSize) {
        this.upDeckSize = upDeckSize;
    }

    public boolean isDownHasWeapon() {
        return downHasWeapon;
    }

    public void setDownHasWeapon(boolean downHasWeapon) {
        this.downHasWeapon = downHasWeapon;
    }

    public boolean isUpHasWeapon() {
        return upHasWeapon;
    }

    public void setUpHasWeapon(boolean upHasWeapon) {
        this.upHasWeapon = upHasWeapon;
    }

    public boolean isHeroCanAttack() {
        return heroCanAttack;
    }

    public void setHeroCanAttack(boolean heroCanAttack) {
        this.heroCanAttack = heroCanAttack;
    }

    public CardModelView getDownWeapon() {
        return downWeapon;
    }

    public void setDownWeapon(CardModelView downWeapon) {
        this.downWeapon = downWeapon;
    }

    public CardModelView getUpWeapon() {
        return upWeapon;
    }

    public void setUpWeapon(CardModelView upWeapon) {
        this.upWeapon = upWeapon;
    }

    public CardModelView getDownHeroPower() {
        return downHeroPower;
    }

    public void setDownHeroPower(CardModelView downHeroPower) {
        this.downHeroPower = downHeroPower;
    }

    public CardModelView getUpHeroPower() {
        return upHeroPower;
    }

    public void setUpHeroPower(CardModelView upHeroPower) {
        this.upHeroPower = upHeroPower;
    }

    public ArrayList<CardModelView> getHandCards() {
        return handCards;
    }

    public void setHandCards(ArrayList<CardModelView> handCards) {
        this.handCards = handCards;
    }

    public ArrayList<CardModelView> getDownPlayedCards() {
        return downPlayedCards;
    }

    public void setDownPlayedCards(ArrayList<CardModelView> downPlayedCards) {
        this.downPlayedCards = downPlayedCards;
    }

    public ArrayList<CardModelView> getUpPlayedCards() {
        return upPlayedCards;
    }

    public void setUpPlayedCards(ArrayList<CardModelView> upPlayedCards) {
        this.upPlayedCards = upPlayedCards;
    }

    public ArrayList<String> getLogs() {
        return logs;
    }

    public void setLogs(ArrayList<String> logs) {
        this.logs = logs;
    }

    public boolean isP2Turn() {
        return p2Turn;
    }

    public void setP2Turn(boolean p2Turn) {
        this.p2Turn = p2Turn;
    }
}
