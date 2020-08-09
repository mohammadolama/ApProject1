package Server.Model;


import java.util.ArrayList;

public class State {
    private String friendlyUser, enemyUser, friendlyHero, enemyHero, time;
    private int downPowerUsage, upPowerUsage, downPowerMana, upPowerMana, notUsedMana,
            totalMana, downHP, upHP, downDefence, upDefence, downHandSize, upHandSize,
            downPalyedSize, upPlayedSize, downDeckSize, upDeckSize;
    private boolean downHasWeapon, upHasWeapon, heroCanAttack;
    private CardModelView downWeapon, upWeapon, downHeroPower, upHeroPower;

    private ArrayList<CardModelView> handCards, downPlayedCards, upPlayedCards;
    private ArrayList<String> logs;

    public State(String friendlyUser, String enemyUser, String friendlyHero, String enemyHero,
                 String time, int downPowerUsage, int upPowerUsage, int downPowerMana,
                 int upPowerMana, int notUsedMana, int totalMana, int downHP, int upHP,
                 int downDefence, int upDefence, int downHandSize, int upHandSize,
                 int downPalyedSize, int upPlayedSize, int downDeckSize, int upDeckSize,
                 boolean downHasWeapon, boolean upHasWeapon, boolean heroCanAttack,
                 CardModelView downWeapon, CardModelView upWeapon, CardModelView downHeroPower,
                 CardModelView upHeroPower, ArrayList<CardModelView> handCards,
                 ArrayList<CardModelView> downPlayedCards, ArrayList<CardModelView> upPlayedCards,
                 ArrayList<String> logs) {
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
    }
}
