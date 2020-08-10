package Client.Controller.Requests;

import Client.Controller.Responses;
import Client.Model.DeckModel;
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
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            String res = inputStream.nextLine();
            deck = objectMapper.readValue(res, DeckModel.class);
            Responses.getInstance().setSelectedDeck(deck);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
