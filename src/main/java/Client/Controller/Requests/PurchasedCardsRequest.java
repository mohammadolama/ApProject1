package Client.Controller.Requests;


import Client.Controller.Responses;
import Client.Model.CardModelView;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("purchasedcard")
public class PurchasedCardsRequest implements Request {
    ArrayList<CardModelView> purchasedCard;

    public PurchasedCardsRequest() {
    }

    public ArrayList<CardModelView> getPurchasedCard() {
        return purchasedCard;
    }

    public void setPurchasedCard(ArrayList<CardModelView> purchasedCard) {
        this.purchasedCard = purchasedCard;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            String res = inputStream.nextLine();
            purchasedCard = objectMapper.readValue(res, new TypeReference<ArrayList<CardModelView>>() {
            });
            Responses.getInstance().setPurchasedCards(purchasedCard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
