package Server.Controller.Response;

import Server.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.HashMap;

@JsonTypeName("decks")
public class PlayerDecksResponse implements Response {
    private HashMap<String, DeckModel> alldecks;

    public PlayerDecksResponse(HashMap<String, DeckModel> alldecks) {
        this.alldecks = alldecks;
    }

    public PlayerDecksResponse() {
    }

    public HashMap<String, DeckModel> getAlldecks() {
        return alldecks;
    }

    public void setAlldecks(HashMap<String, DeckModel> alldecks) {
        this.alldecks = alldecks;
    }


}
