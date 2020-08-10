package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.ExitLogoutResponse;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("logout")
public class LogoutRequest implements Request {
    public LogoutRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        Admin.getInstance().logOut(clientHandler.getPlayer());
        try {
            outputStream.println(objectMapper.writeValueAsString(new ExitLogoutResponse(1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
