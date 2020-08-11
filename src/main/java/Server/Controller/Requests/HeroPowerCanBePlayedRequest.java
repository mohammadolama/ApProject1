package Server.Controller.Requests;

import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.HeroPowerResponse;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("heropowercanbeplayed")
public class HeroPowerCanBePlayedRequest implements Request {
    private int i;

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        i = clientHandler.getGameManager().heroPowerCanBePlayed(clientHandler);
        try {
            outputStream.println(objectMapper.writeValueAsString(new HeroPowerResponse(i)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
