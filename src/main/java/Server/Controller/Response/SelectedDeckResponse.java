package Server.Controller.Response;

import Server.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("selecteddeck")
public class SelectedDeckResponse implements Response {

    private DeckModel deck;

    public SelectedDeckResponse(DeckModel deck) {
        this.deck = deck;
    }

    public SelectedDeckResponse() {
    }

    public DeckModel getDeck() {
        return deck;
    }

    public void setDeck(DeckModel deck) {
        this.deck = deck;
    }


}
