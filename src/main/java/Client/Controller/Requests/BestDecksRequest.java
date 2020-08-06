package Client.Controller.Requests;

import Client.Controller.Responses;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
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
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            String res = inputStream.nextLine();
            bestDecks = objectMapper.readValue(res, new TypeReference<ArrayList<String>>() {
            });
            Responses.getInstance().setBestDecks(bestDecks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
