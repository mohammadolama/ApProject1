package Server.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("bestdecks")
public class BestDecksRequest implements Request {

    private ArrayList<String> bestDecks;

    public BestDecksRequest(ArrayList<String> bestDecks) {
        this.bestDecks = bestDecks;
    }

    public BestDecksRequest() {
    }

    public ArrayList<String> getBestDecks() {
        return bestDecks;
    }

    public void setBestDecks(ArrayList<String> bestDecks) {
        this.bestDecks = bestDecks;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
