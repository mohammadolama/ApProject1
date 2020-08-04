package Client.Controller.Requests;

import Client.Model.CardModelView;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("notpurchased")
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
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
