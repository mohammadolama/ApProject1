package Server.Controller.Response;

import Server.Model.CardModelView;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("purchased")
public class PurchasedCardsResponse implements Response {
    ArrayList<CardModelView> purchasedCard;

    public PurchasedCardsResponse(ArrayList<CardModelView> purchasedCard) {
        this.purchasedCard = purchasedCard;
    }

    public PurchasedCardsResponse() {
    }

    public ArrayList<CardModelView> getPurchasedCard() {
        return purchasedCard;
    }

    public void setPurchasedCard(ArrayList<CardModelView> purchasedCard) {
        this.purchasedCard = purchasedCard;
    }
}
