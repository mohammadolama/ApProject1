package Controller.Actions;

import Model.Interface.Character;

public class ActionHandler {

    public static void Attack(Character attacker , Character target) {
        int attackerdamage = attacker.getAttack();
        int targetdamage = target.getAttack();
        target.setLife(target.getLife() - attackerdamage);
        attacker.setLife(attacker.getLife() - targetdamage);
    }

}
