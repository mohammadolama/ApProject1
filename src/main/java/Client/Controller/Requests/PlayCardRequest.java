package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("playcard")
public class PlayCardRequest implements Request {

    private String name;
    private int deckIndex;
    private int targetIndex;

    public PlayCardRequest(String name, int deckIndex, int targetIndex) {
        this.name = name;
        this.deckIndex = deckIndex;
        this.targetIndex = targetIndex;
    }

    public PlayCardRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeckIndex() {
        return deckIndex;
    }

    public void setDeckIndex(int deckIndex) {
        this.deckIndex = deckIndex;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
