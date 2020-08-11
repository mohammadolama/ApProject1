package Server.Controller.Response;

import Server.Model.GameState;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("board")
public class BoardResponse implements Response {
    private GameState state;

    public BoardResponse(GameState state) {
        this.state = state;
    }

    public BoardResponse() {
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
