package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Model.Deck;
import Server.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("selecteddeck")
public class SelectedDeckRequest implements Request {

    private DeckModel deck;

    public SelectedDeckRequest(DeckModel deck) {
        this.deck = deck;
    }

    public SelectedDeckRequest() {
    }

    public DeckModel getDeck() {
        return deck;
    }

    public void setDeck(DeckModel deck) {
        this.deck = deck;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper) {
        DeckModel deckModel = Admin.getInstance().getDeckModel(clientHandler.getPlayer().getSelectedDeck());
        try {
            outputStream.println(objectMapper.writeValueAsString(deckModel));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
