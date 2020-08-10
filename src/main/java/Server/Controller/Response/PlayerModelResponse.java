package Server.Controller.Response;

import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Model.PlayerModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("playermodel")
public class PlayerModelResponse implements Response {

    private PlayerModel playerModel;

    public PlayerModelResponse(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    public PlayerModelResponse() {
    }

    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

}
