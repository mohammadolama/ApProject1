package Server.Controller.Actions;

import Server.Controller.Manager.Managers;
import Server.Model.InfoPassive;

public class InfoInitilizer {

    private Managers m;

    public InfoInitilizer(Managers m) {
        this.m = m;
    }

    public void player1InfoInitilize(InfoPassive infoPassive) {
        m.setPlayer1StartingMana(8);
        m.setPlayer1TotalMana(8);
        m.setPlayer1NotUsedMana(8);
        m.setPlayer1DrawCardNum(1);
        m.setPlayer1HeroPowerUsageTime(1);
        m.setP1HPMAXUT(1);
        m.setPlayer1PowerManaDecrease(0);
        m.setPlayer1ManaDecrease(0);
        m.setPlayer1DefenceAdd(0);
        String st = infoPassive.getName();
        if (st.equalsIgnoreCase("twiceDraw")) {
            m.setPlayer1DrawCardNum(2);
        } else if (st.equalsIgnoreCase("offCards")) {
            m.setPlayer1ManaDecrease(1);
        } else if (st.equalsIgnoreCase("warriors")) {
            m.setPlayer1DefenceAdd(2);
        } else if (st.equalsIgnoreCase("manaJump")) {
            m.setPlayer1StartingMana(2);
            m.setPlayer1TotalMana(2);
            m.setPlayer1NotUsedMana(2);
        } else if (st.equalsIgnoreCase("freePower")) {
            m.setPlayer1HeroPowerUsageTime(2);
            m.setP1HPMAXUT(2);
            m.setPlayer1PowerManaDecrease(1);
        }
    }

    public void player2InfoInitilize(InfoPassive infoPassive) {
        m.setPlayer2StartingMana(7);
        m.setPlayer2TotalMana(7);
        m.setPlayer2NotUsedMana(7);
        m.setPlayer2DrawCardNum(1);
        m.setPlayer2HeroPowerUsageTime(1);
        m.setP2HPMAXUT(1);
        m.setPlayer2PowerManaDecrease(0);
        m.setPlayer2PowerManaDecrease(0);
        m.setPlayer2DefenceAdd(0);
        String st = infoPassive.getName();
        if (st.equalsIgnoreCase("twiceDraw")) {
            m.setPlayer2DrawCardNum(2);
        } else if (st.equalsIgnoreCase("offCards")) {
            m.setPlayer2PowerManaDecrease(1);
        } else if (st.equalsIgnoreCase("warriors")) {
            m.setPlayer2DefenceAdd(2);
        } else if (st.equalsIgnoreCase("manaJump")) {
            m.setPlayer2StartingMana(2);
            m.setPlayer2TotalMana(2);
            m.setPlayer2NotUsedMana(2);
        } else if (st.equalsIgnoreCase("freePower")) {
            m.setPlayer2HeroPowerUsageTime(2);
            m.setP2HPMAXUT(2);
            m.setPlayer2PowerManaDecrease(1);
        }
    }

}
