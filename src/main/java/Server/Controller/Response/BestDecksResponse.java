package Server.Controller.Response;

import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("bestdecks")
public class BestDecksResponse implements Response {
    private ArrayList<String> bestDecks;

    public BestDecksResponse(ArrayList<String> bestDecks) {
        this.bestDecks = bestDecks;
    }

    public BestDecksResponse() {
    }

    public ArrayList<String> getBestDecks() {
        return bestDecks;
    }

    public void setBestDecks(ArrayList<String> bestDecks) {
        this.bestDecks = bestDecks;
    }

}
