package Server.Controller.Requests;

import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("heropower")
public class PlayHeroPowerRequest implements Request {

    private int target;

    public PlayHeroPowerRequest(int target) {
        this.target = target;
    }

    public PlayHeroPowerRequest() {
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        clientHandler.getGameManager().heroPower(target, clientHandler);
    }
}
