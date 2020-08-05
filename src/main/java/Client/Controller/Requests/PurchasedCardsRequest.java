package Client.Controller.Requests;


import Server.Model.Cards.Card;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("purchasedcard")
public class PurchasedCardsRequest implements Request {
    ArrayList<Card> purchasedCard;

    public PurchasedCardsRequest() {
    }

    public ArrayList<Card> getPurchasedCard() {
        return purchasedCard;
    }

    public void setPurchasedCard(ArrayList<Card> purchasedCard) {
        this.purchasedCard = purchasedCard;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {

    }
}
