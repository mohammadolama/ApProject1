package Server.Controller.Requests;

import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.CanBePlayedResponse;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("canbeplayed")
public class CanBePlayedRequest implements Request {

    private String cardName;

    public CanBePlayedRequest(String cardName) {
        this.cardName = cardName;
    }

    public CanBePlayedRequest() {
    }


    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        String res = clientHandler.getGameManager().playCheck(cardName, clientHandler);
        try {
            outputStream.println(objectMapper.writeValueAsString(new CanBePlayedResponse(res)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
