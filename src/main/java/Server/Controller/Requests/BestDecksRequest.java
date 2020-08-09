package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("bestdecks")
public class BestDecksRequest implements Request {

    private ArrayList<String> bestDecks;

    public BestDecksRequest(ArrayList<String> bestDecks) {
        this.bestDecks = bestDecks;
    }

    public BestDecksRequest() {
    }

    public ArrayList<String> getBestDecks() {
        return bestDecks;
    }

    public void setBestDecks(ArrayList<String> bestDecks) {
        this.bestDecks = bestDecks;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        ArrayList<String> list = Admin.getInstance().bestDecks(clientHandler.getPlayer());
        try {
            outputStream.println(objectMapper.writeValueAsString(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
