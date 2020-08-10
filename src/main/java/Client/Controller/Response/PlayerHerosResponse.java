package Client.Controller.Response;

import Client.Controller.Responses;
import Client.Model.Enums.Heroes;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("heros")
public class PlayerHerosResponse implements Response {
    private ArrayList<Heroes> heros;

    public PlayerHerosResponse() {
    }

    public ArrayList<Heroes> getHeros() {
        return heros;
    }

    public void setHeros(ArrayList<Heroes> heros) {
        this.heros = heros;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        Responses.getInstance().setHeroesList(heros);
        synchronized (object) {
            object.notify();
        }
    }
}
