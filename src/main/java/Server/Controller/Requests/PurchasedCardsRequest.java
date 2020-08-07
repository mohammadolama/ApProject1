package Server.Controller.Requests;


import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Model.CardModelView;
import Server.Model.Cards.Card;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

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
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper) {
        purchasedCard = new ArrayList<>();
        for (Card card : Card.purchasedCards(clientHandler.getPlayer())) {
            purchasedCard.add(Admin.getInstance().getPureViewModelOf(card.getName()));
        }
        try {
            outputStream.println(objectMapper.writeValueAsString(purchasedCard));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
