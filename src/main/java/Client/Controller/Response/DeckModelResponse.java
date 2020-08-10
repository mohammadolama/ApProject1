package Client.Controller.Response;

import Client.Controller.Responses;
import Client.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("deckmodel")
public class DeckModelResponse implements Response {

    private DeckModel deckModel;

    public DeckModelResponse() {
    }

    public DeckModel getDeckModel() {
        return deckModel;
    }

    public void setDeckModel(DeckModel deckModel) {
        this.deckModel = deckModel;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        Responses.getInstance().setDeckModel(deckModel);
        synchronized (object) {
            object.notify();
        }
    }
}
