package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.PassiveResponse;
import Server.Model.InfoPassive;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("passive")
public class PassiveRequest implements Request {
    public PassiveRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {

        try {
            outputStream.println(objectMapper.writeValueAsString(new PassiveResponse(Admin.getInstance().generatePassive())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
