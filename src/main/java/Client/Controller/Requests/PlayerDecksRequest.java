package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

@JsonTypeName("playerdecks")
public class PlayerDecksRequest implements Request {
    private HashMap<String, DeckModelRequest> alldecks;

    public PlayerDecksRequest(HashMap<String, DeckModelRequest> alldecks) {
        this.alldecks = alldecks;
    }

    public PlayerDecksRequest() {
    }

    public HashMap<String, DeckModelRequest> getAlldecks() {
        return alldecks;
    }

    public void setAlldecks(HashMap<String, DeckModelRequest> alldecks) {
        this.alldecks = alldecks;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
