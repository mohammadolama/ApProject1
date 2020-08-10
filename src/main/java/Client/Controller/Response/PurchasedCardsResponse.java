package Client.Controller.Response;

import Client.Controller.Responses;
import Client.Model.CardModelView;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("purchased")
public class PurchasedCardsResponse implements Response {
    ArrayList<CardModelView> purchasedCard;

    public PurchasedCardsResponse() {
    }

    public ArrayList<CardModelView> getPurchasedCard() {
        return purchasedCard;
    }

    public void setPurchasedCard(ArrayList<CardModelView> purchasedCard) {
        this.purchasedCard = purchasedCard;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        Responses.getInstance().setPurchasedCards(purchasedCard);
        synchronized (object) {
            object.notify();
        }
    }
}
