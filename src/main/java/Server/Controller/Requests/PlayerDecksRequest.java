package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.PlayerDecksResponse;
import Server.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

@JsonTypeName("playerdecks")
public class PlayerDecksRequest implements Request {
    private HashMap<String, DeckModel> alldecks;

    public PlayerDecksRequest() {
    }


    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        alldecks = Admin.getInstance().playerDecks(clientHandler.getPlayer());
        try {
            outputStream.println(objectMapper.writeValueAsString(new PlayerDecksResponse(alldecks)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
