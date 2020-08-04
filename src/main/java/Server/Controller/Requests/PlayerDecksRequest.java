package Server.Controller.Requests;

import Client.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

@JsonTypeName("playerdecks")
public class PlayerDecksRequest implements Request {
    private HashMap<String, DeckModel> alldecks;

    public PlayerDecksRequest(HashMap<String, DeckModel> alldecks) {
        this.alldecks = alldecks;
    }

    public PlayerDecksRequest() {
    }

    public HashMap<String, DeckModel> getAlldecks() {
        return alldecks;
    }

    public void setAlldecks(HashMap<String, DeckModel> alldecks) {
        this.alldecks = alldecks;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
