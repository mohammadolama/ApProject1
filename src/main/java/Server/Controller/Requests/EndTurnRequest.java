package Server.Controller.Requests;

import Server.Controller.MainLogic.ClientHandler;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("endturn")
public class EndTurnRequest implements Request {
    private boolean friendlyDeck;

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper) {

    }
}