package Server.Controller.Response;

import Server.Model.CardModelView;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("notpurchased")
public class NotPurchasedCardsResponse implements Response {
    private ArrayList<CardModelView> notPurchasedCards;

    public NotPurchasedCardsResponse(ArrayList<CardModelView> notPurchasedCards) {
        this.notPurchasedCards = notPurchasedCards;
    }

    public NotPurchasedCardsResponse() {
    }

    public ArrayList<CardModelView> getNotPurchasedCards() {
        return notPurchasedCards;
    }

    public void setNotPurchasedCards(ArrayList<CardModelView> notPurchasedCards) {
        this.notPurchasedCards = notPurchasedCards;
    }
}
