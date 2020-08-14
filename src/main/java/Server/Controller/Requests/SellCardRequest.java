package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.BuySellResponse;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("sellcard")
public class SellCardRequest implements Request {

    private String name;

    public SellCardRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        String res = Admin.getInstance().sellCard(name, clientHandler.getPlayer());
        try {
            outputStream.println(objectMapper.writeValueAsString(new BuySellResponse(2, res)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
