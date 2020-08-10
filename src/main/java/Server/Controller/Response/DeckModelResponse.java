package Server.Controller.Response;

import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("deckmodel")
public class DeckModelResponse implements Response {

    private DeckModel deckModel;

    public DeckModelResponse() {
    }

    public DeckModelResponse(DeckModel deckModel) {
        this.deckModel = deckModel;
    }

    public DeckModel getDeckModel() {
        return deckModel;
    }

    public void setDeckModel(DeckModel deckModel) {
        this.deckModel = deckModel;
    }
}
