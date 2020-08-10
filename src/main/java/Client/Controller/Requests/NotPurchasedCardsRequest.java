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

@JsonTypeName("notpurchasedcard")
public class NotPurchasedCardsRequest implements Request {
    private ArrayList<CardModelView> notPurchasedCards;

    public NotPurchasedCardsRequest() {
    }

    public ArrayList<CardModelView> getNotPurchasedCards() {
        return notPurchasedCards;
    }

    public void setNotPurchasedCards(ArrayList<CardModelView> notPurchasedCards) {
        this.notPurchasedCards = notPurchasedCards;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            String res = inputStream.nextLine();
            notPurchasedCards = objectMapper.readValue(res, new TypeReference<ArrayList<CardModelView>>() {
            });
            Responses.getInstance().setNotPurchasedCards(notPurchasedCards);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
