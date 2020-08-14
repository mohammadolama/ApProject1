package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.RemoveDeckResponse;
import Server.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("removedeck")
public class RemoveDeckRequest implements Request {

    private DeckModel deck;

    public RemoveDeckRequest() {
    }

    public DeckModel getDeck() {
        return deck;
    }

    public void setDeck(DeckModel deck) {
        this.deck = deck;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        String res = Admin.getInstance().removeDeck(deck, clientHandler.getPlayer());
        try {
            outputStream.println(objectMapper.writeValueAsString(new RemoveDeckResponse(res)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
