package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("removedeck")
public class RemoveDeckRequest implements Request {

    private DeckModel deck;

    public RemoveDeckRequest(DeckModel deck) {
        this.deck = deck;
    }

    public RemoveDeckRequest() {
    }

    public DeckModel getDeck() {
        return deck;
    }

    public void setDeck(DeckModel deck) {
        this.deck = deck;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper) {
        String res = Admin.getInstance().removeDeck(deck, clientHandler.getPlayer());
        outputStream.println(res);
    }
}
