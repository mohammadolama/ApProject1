package Server.Controller.Requests;

import Server.Model.Deck;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("selectdeck")
public class SelectDeckRequest implements Request {

    private Deck deck;

    public SelectDeckRequest(Deck deck) {
        this.deck = deck;
    }

    public SelectDeckRequest() {
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
