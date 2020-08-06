package Client.Controller.Requests;

import Client.Controller.Responses;
import Client.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("deckmodel")
public class DeckModelRequest implements Request {
    private DeckModel deckModel;
    private String name;

    public DeckModelRequest(String name) {
        this.name = name;
    }

    public DeckModelRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            String res = inputStream.nextLine();
            deckModel = objectMapper.readValue(res, DeckModel.class);
            System.out.println(deckModel + "******************");
            Responses.getInstance().setDeckModel(deckModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public DeckModel getDeckModel() {
        return deckModel;
    }

    public void setDeckModel(DeckModel deckModel) {
        this.deckModel = deckModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
