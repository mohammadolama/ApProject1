package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Model.InfoPassive;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("passive")
public class PassiveRequest implements Request {
    ArrayList<InfoPassive> list;

    public PassiveRequest() {
    }

    public ArrayList<InfoPassive> getList() {
        return list;
    }

    public void setList(ArrayList<InfoPassive> list) {
        this.list = list;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        list = Admin.getInstance().generatePassive();
        try {
            outputStream.println(objectMapper.writeValueAsString(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
