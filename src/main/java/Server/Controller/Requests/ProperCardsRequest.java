package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
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

    public ProperCardsRequest(int i) {
        this.i = i;
    }

    public ProperCardsRequest() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public ArrayList<CardModelView> getList() {
        return list;
    }

    public void setList(ArrayList<CardModelView> list) {
        this.list = list;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        list = Admin.getInstance().properCards(i, clientHandler.getPlayer());
        try {
            outputStream.println(objectMapper.writeValueAsString(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
