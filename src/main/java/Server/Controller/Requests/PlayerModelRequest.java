package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.PlayerModelResponse;
import Server.Model.PlayerModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("playermodel")
public class PlayerModelRequest implements Request {

    public PlayerModelRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        PlayerModel playerModel = Admin.getInstance().getPlayerModel(clientHandler.getPlayer());
        try {
            outputStream.println(objectMapper.writeValueAsString(new PlayerModelResponse(playerModel)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
