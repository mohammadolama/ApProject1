package Server.Controller.Requests;

import Client.Model.CardModelView;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.BoardResponse;
import Server.Model.GameState;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("boardpanel")
public class BoardPanelRequest implements Request {
    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        GameState state = clientHandler.gameState();
        try {
            outputStream.println(objectMapper.writeValueAsString(new BoardResponse(state)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
