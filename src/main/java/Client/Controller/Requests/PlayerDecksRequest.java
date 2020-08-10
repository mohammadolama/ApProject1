package Client.Controller.Requests;

import Client.Controller.Responses;
import Client.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
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
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            String res = inputStream.nextLine();
            alldecks = objectMapper.readValue(res, new TypeReference<HashMap<String, DeckModel>>() {
            });
            Responses.getInstance().setDecks(alldecks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
