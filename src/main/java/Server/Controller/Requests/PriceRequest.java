package Server.Controller.Requests;

import Server.Controller.MainLogic.ClientHandler;
import Server.Model.Cards.Card;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("price")
public class PriceRequest implements Request {

    private String name;

    private long price;
    private String className;

    public PriceRequest() {
    }

    public PriceRequest(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public long getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper) {
        Card card = Card.getCardOf(name);
        try {
            outputStream.println(objectMapper.writeValueAsString(card.getHeroClass()));
            outputStream.println(objectMapper.writeValueAsString(card.getPrice()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
