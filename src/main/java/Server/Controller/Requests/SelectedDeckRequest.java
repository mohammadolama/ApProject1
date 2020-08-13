package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.SelectedDeckResponse;
import Server.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("selecteddeck")
public class SelectedDeckRequest implements Request {

    public SelectedDeckRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        DeckModel deckModel = Admin.getInstance().getDeckModel(clientHandler.getPlayer().getSelectedDeck());
        try {
            outputStream.println(objectMapper.writeValueAsString(new SelectedDeckResponse(deckModel)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
