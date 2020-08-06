package Client.Controller.Requests;

import Client.Controller.Responses;
import Client.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("selectdeck")
public class SelectDeckRequest implements Request {

    private DeckModel deck;

    public SelectDeckRequest(DeckModel deck) {
        this.deck = deck;
    }

    public SelectDeckRequest() {
    }

    public DeckModel getDeck() {
        return deck;
    }

    public void setDeck(DeckModel deck) {
        this.deck = deck;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {

    }
}
