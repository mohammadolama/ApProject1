package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.DeckModelResponse;
import Server.Model.Deck;
import Server.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("deckmodel")
public class DeckModelRequest implements Request {
    private String name;

    public DeckModelRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        Deck deck = clientHandler.getPlayer().getAllDecks().get(name);
        DeckModel deckModel = Admin.getInstance().getDeckModel(deck);
        try {
            outputStream.println(objectMapper.writeValueAsString(new DeckModelResponse(deckModel)));
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
}
