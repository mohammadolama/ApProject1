package Client.Controller.Requests;

import Server.Model.Enums.Heroes;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("playerheros")
public class PlayerHerosRequest implements Request {

    private ArrayList<Heroes> heros;

    public PlayerHerosRequest(ArrayList<Heroes> heros) {
        this.heros = heros;
    }

    public PlayerHerosRequest() {
    }

    public ArrayList<Heroes> getHeros() {
        return heros;
    }

    public void setHeros(ArrayList<Heroes> heros) {
        this.heros = heros;
    }


    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {

    }
}
