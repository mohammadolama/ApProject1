package Client.Controller.Requests;

import Server.Model.Deck;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("selecteddeck")
public class SelectedDeckRequest implements Request {

    private Deck deck;

    public SelectedDeckRequest(Deck deck) {
        this.deck = deck;
    }

    public SelectedDeckRequest() {
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {


    }
}
