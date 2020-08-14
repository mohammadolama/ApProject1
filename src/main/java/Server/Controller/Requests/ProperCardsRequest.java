package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.ProperCardsResponse;
import Server.Model.CardModelView;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("propercard")
public class ProperCardsRequest implements Request {
    private int i;
    private ArrayList<CardModelView> list;

    public ProperCardsRequest() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        list = Admin.getInstance().properCards(i, clientHandler.getPlayer());
        try {
            outputStream.println(objectMapper.writeValueAsString(new ProperCardsResponse(list)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
