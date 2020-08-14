package Server.Controller.Requests;

import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.MainLogic.DeckLogic;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.PriceResponse;
import Server.Model.Cards.Card;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("price")
public class PriceRequest implements Request {

    private String name;
    public PriceRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        Card card = DeckLogic.getCardOf(name);
        try {
            outputStream.println(objectMapper.writeValueAsString(new PriceResponse(card.getPrice(), card.getHeroClass())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
