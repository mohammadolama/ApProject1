package Client.Controller.Response;

import Client.Controller.Responses;
import Client.Model.PlayerModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("playermodel")
public class PlayerModelResponse implements Response {

    private PlayerModel playerModel;

    public PlayerModelResponse() {
    }

    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        Responses.getInstance().setPlayer(playerModel);
        synchronized (object) {
            object.notify();
            ;
        }
    }
}
