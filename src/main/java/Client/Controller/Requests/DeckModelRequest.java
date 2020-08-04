package Client.Controller.Requests;

import Client.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;

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
    public void excute(Scanner inputStream, PrintWriter outputStream) {

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
