package Client.Controller.Requests;

import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.Model.GameState;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("boardpanel")
public class BoardPanelRequest implements Request {

    private String friendlyUser, enemyUser, friendlyHero, enemyHero, time;
    private int downPowerUsage, upPowerUsage, downPowerMana, upPowerMana, notUsedMana,
            totalMana, downHP, upHP, downDefence, upDefence, downHandSize, upHandSize,
            downPalyedSize, upPlayedSize, downDeckSize, upDeckSize;
    private boolean downHasWeapon, upHasWeapon, heroCanAttack;
    private CardModelView downWeapon, upWeapon, downHeroPower, upHeroPower;

    private ArrayList<CardModelView> handCards, downPlayedCards, upPlayedCards;
    private ArrayList<String> logs;

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            String res = inputStream.nextLine();
            GameState state = objectMapper.readValue(res, GameState.class);
            friendlyUser = state.getFriendlyUser();
            enemyUser = state.getEnemyUser();
            friendlyHero = state.getFriendlyHero();
            enemyHero = state.getEnemyHero();
            time = state.getTime();
            downPowerUsage = state.getDownPowerUsage();
            upPowerUsage = state.getUpPowerUsage();
            downPowerMana = state.getDownPowerMana();
            upPowerMana = state.getUpPowerMana();
            notUsedMana = state.getNotUsedMana();
            totalMana = state.getTotalMana();
            downHP = state.getDownHP();
            upHP = state.getUpHP();
            downDefence = state.getDownDefence();
            upDefence = state.getUpDefence();
            downHandSize = state.getDownHandSize();
            upHandSize = state.getUpHandSize();
            downPalyedSize = state.getDownPalyedSize();
            upPlayedSize = state.getUpPlayedSize();
            downDeckSize = state.getDownDeckSize();
            upDeckSize = state.getUpDeckSize();
            downHasWeapon = state.isDownHasWeapon();
            upHasWeapon = state.isUpHasWeapon();
            heroCanAttack = state.isHeroCanAttack();
            downWeapon = state.getDownWeapon();
            upWeapon = state.getUpWeapon();
            downHeroPower = state.getDownHeroPower();
            upHeroPower = state.getUpHeroPower();
            handCards = state.getHandCards();
            downPlayedCards = state.getDownPlayedCards();
            upPlayedCards = state.getUpPlayedCards();
            logs = state.getLogs();

            Responses.getInstance().board = state;

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public boolean isHeroCanAttack() {
        return heroCanAttack;
    }

    public void setHeroCanAttack(boolean heroCanAttack) {
        this.heroCanAttack = heroCanAttack;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
