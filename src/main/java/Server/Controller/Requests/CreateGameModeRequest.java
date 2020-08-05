package Server.Controller.Requests;

import Client.Model.InfoPassive;
import Server.Controller.MainLogic.ClientHandler;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("creategamemode")
public class CreateGameModeRequest implements Request {
    private int i;
    private InfoPassive infoPassive;

    public CreateGameModeRequest(int i, InfoPassive infoPassive) {
        this.i = i;
        this.infoPassive = infoPassive;
    }

    public CreateGameModeRequest() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public InfoPassive getInfoPassive() {
        return infoPassive;
    }

    public void setInfoPassive(InfoPassive infoPassive) {
        this.infoPassive = infoPassive;
    }


    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper) {

    }
}
