package Client.Controller.Response;

import Client.Controller.RequestHandler;
import Client.View.View.Panels.MyFrame;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("notifyattack")
public class notifyAttack implements Response {
    private int attacker;
    private int target;
    private int damage1;
    private int damage2;

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

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        MyFrame.getInstance().getBoardPanel().drawDamages(attacker, target, damage1, damage2);
    }
}
