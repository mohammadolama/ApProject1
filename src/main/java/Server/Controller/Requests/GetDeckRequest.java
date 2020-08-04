package Server.Controller.Requests;

import Client.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("getdeck")
public class GetDeckRequest implements Request {

    private DeckModel deck;

    public GetDeckRequest(DeckModel deck) {
        this.deck = deck;
    }

    public GetDeckRequest() {
    }

    public DeckModel getDeck() {
        return deck;
    }

    public void setDeck(DeckModel deck) {
        this.deck = deck;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
