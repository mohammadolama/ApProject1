package Server.Controller.Requests;

import Server.Controller.MainLogic.ClientHandler;
import Server.Model.Enums.Heroes;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("playerheros")
public class PlayerHerosRequest implements Request {

    private ArrayList<Heroes> heros;

    public PlayerHerosRequest(ArrayList<Heroes> heros) {
        this.heros = heros;
    }

    public PlayerHerosRequest() {
    }

    public ArrayList<Heroes> getHeros() {
        return heros;
    }

    public void setHeros(ArrayList<Heroes> heros) {
        this.heros = heros;
    }


    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper) {
        try {
            String res = objectMapper.writeValueAsString(clientHandler.getPlayer().getPlayerHeroes());
            outputStream.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
