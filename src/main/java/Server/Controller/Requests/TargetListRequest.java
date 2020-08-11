package Server.Controller.Requests;

import Client.View.View.Panels.BoardPanel;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.TargetListResponse;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("targetlist")
public class TargetListRequest implements Request {
    private ArrayList<Integer> list;

    public TargetListRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        list = clientHandler.getGameManager().listOfTargets(clientHandler);
        try {
            outputStream.println(objectMapper.writeValueAsString(new TargetListResponse(list)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
