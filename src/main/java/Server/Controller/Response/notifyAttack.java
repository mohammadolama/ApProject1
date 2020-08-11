package Server.Controller.Response;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("notifyattack")
public class notifyAttack implements Response {

    private int attacker;
    private int target;
    private int damage1;
    private int damage2;

    public notifyAttack(int attacker, int target, int damage1, int damage2) {
        this.attacker = attacker;
        this.target = target;
        this.damage1 = damage1;
        this.damage2 = damage2;
    }

    public notifyAttack() {
    }

    public int getAttacker() {
        return attacker;
    }

    public void setAttacker(int attacker) {
        this.attacker = attacker;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getDamage1() {
        return damage1;
    }

    public void setDamage1(int damage1) {
        this.damage1 = damage1;
    }

    public int getDamage2() {
        return damage2;
    }

    public void setDamage2(int damage2) {
        this.damage2 = damage2;
    }
}
