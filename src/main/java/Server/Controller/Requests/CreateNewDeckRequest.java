package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.CreateNewDeckResponse;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("createnewdeck")
public class CreateNewDeckRequest implements Request {
    private boolean response;

    public CreateNewDeckRequest() {
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        response = Admin.getInstance().createNewDeck(clientHandler.getPlayer());
        try {
            outputStream.println(objectMapper.writeValueAsString(new CreateNewDeckResponse(response)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
