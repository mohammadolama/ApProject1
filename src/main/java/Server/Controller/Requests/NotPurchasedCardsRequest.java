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
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper) {
        notPurchasedCards = new ArrayList<>();
        for (Card card : Card.lockedCards(clientHandler.getPlayer())) {
            notPurchasedCards.add(Admin.getInstance().getPureViewModelOf(card.getName()));
        }
        try {
            outputStream.println(objectMapper.writeValueAsString(notPurchasedCards));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
