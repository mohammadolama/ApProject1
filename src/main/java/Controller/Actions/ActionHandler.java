package Controller.Actions;

import Model.Interface.Character;

public class ActionHandler {

    public static void Attack(Character attacker , Character target) {
        int damage=attacker.getat();
        target.setHE(target.getHE() - damage);
    }

}
