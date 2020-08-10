package Client.Controller.Response;

import Client.Controller.Responses;
import Client.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

@JsonTypeName("decks")
public class PlayerDecksResponse implements Response {
    private HashMap<String, DeckModel> alldecks;

    public PlayerDecksResponse() {
    }

    public HashMap<String, DeckModel> getAlldecks() {
        return alldecks;
    }

    public void setAlldecks(HashMap<String, DeckModel> alldecks) {
        this.alldecks = alldecks;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        Responses.getInstance().setDecks(alldecks);
        synchronized (object) {
            object.notify();
        }
    }
}
