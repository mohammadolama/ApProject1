package Client.Controller.Response;

import Client.Controller.Responses;
import Client.Model.CardModelView;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("notpurchased")
public class NotPurchasedCardsResponse implements Response {
    private ArrayList<CardModelView> notPurchasedCards;

    public NotPurchasedCardsResponse() {
    }

    public ArrayList<CardModelView> getNotPurchasedCards() {
        return notPurchasedCards;
    }

    public void setNotPurchasedCards(ArrayList<CardModelView> notPurchasedCards) {
        this.notPurchasedCards = notPurchasedCards;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        Responses.getInstance().setNotPurchasedCards(notPurchasedCards);
        synchronized (object) {
            object.notify();
        }
    }
}
