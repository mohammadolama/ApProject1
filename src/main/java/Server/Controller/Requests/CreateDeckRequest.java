package Server.Controller.Requests;

import Client.Model.Enums.Carts;
import Server.Controller.MainLogic.ClientHandler;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("createdeck")
public class CreateDeckRequest implements Request {

    private String name;
    private ArrayList<Carts> list;
    private String heroName;

    public CreateDeckRequest(String name, ArrayList<Carts> list, String heroName) {
        this.name = name;
        this.list = list;
        this.heroName = heroName;
    }

    public CreateDeckRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Carts> getList() {
        return list;
    }

    public void setList(ArrayList<Carts> list) {
        this.list = list;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper) {

    }
}
