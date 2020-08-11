package Client.Controller.Response;

import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.Model.GameState;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("board")
public class BoardResponse implements Response {

    private GameState state;

    public BoardResponse() {
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        Responses.getInstance().board = state;
        synchronized (object) {
            object.notify();
        }
    }
}
