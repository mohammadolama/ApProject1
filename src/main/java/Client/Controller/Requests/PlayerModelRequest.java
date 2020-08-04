package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("playermodel")
public class PlayerModelRequest implements Request {
    private String name;
    private int exp;
    private int level;
    private int money;
    private String selectedDeckName;

    public PlayerModelRequest(String name, int exp, int level, int money, String selectedDeckName) {
        this.name = name;
        this.exp = exp;
        this.level = level;
        this.money = money;
        this.selectedDeckName = selectedDeckName;
    }

    public PlayerModelRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getSelectedDeckName() {
        return selectedDeckName;
    }

    public void setSelectedDeckName(String selectedDeckName) {
        this.selectedDeckName = selectedDeckName;
    }

}
