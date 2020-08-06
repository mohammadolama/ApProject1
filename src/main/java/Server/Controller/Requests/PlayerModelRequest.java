package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Model.PlayerModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
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
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper) {
        PlayerModel playerModel = Admin.getInstance().getPlayerModel(clientHandler.getPlayer());
        try {
            String s = objectMapper.writeValueAsString(playerModel);
            outputStream.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
