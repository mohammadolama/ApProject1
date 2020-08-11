package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.WantToPlayResponse;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("wanttoplay")
public class WantToPlayRequest implements Request {
    public WantToPlayRequest() {

    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        String res = Admin.getInstance().checkNecessary(clientHandler.getPlayer());
        try {
            outputStream.println(objectMapper.writeValueAsString(new WantToPlayResponse(res)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
