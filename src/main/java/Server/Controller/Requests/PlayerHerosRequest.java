package Server.Controller.Requests;

import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.PlayerHerosResponse;
import Server.Model.Enums.Heroes;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("playerheros")
public class PlayerHerosRequest implements Request {


    public PlayerHerosRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        try {
            String res = objectMapper.writeValueAsString(new PlayerHerosResponse(clientHandler.getPlayer().getPlayerHeroes()));
            outputStream.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
