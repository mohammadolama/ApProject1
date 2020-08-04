package Server.Controller.Requests;

import Server.Model.Enums.Heroes;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

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
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
