package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.ThreeCardResponse;
import Server.Model.CardModelView;
import Server.Model.InfoPassive;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("threecard")
public class ThreeCardRequest implements Request {
    private ArrayList<CardModelView> list;
    private InfoPassive infoPassive;
    private int i;

    public ThreeCardRequest() {
    }

    public ArrayList<CardModelView> getList() {
        return list;
    }

    public void setList(ArrayList<CardModelView> list) {
        this.list = list;
    }

    public InfoPassive getInfoPassive() {
        return infoPassive;
    }

    public void setInfoPassive(InfoPassive infoPassive) {
        this.infoPassive = infoPassive;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        list = Admin.getInstance().threeCardChoose(clientHandler.getPlayer());
        try {
            outputStream.println(objectMapper.writeValueAsString(new ThreeCardResponse(list, infoPassive, i)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
