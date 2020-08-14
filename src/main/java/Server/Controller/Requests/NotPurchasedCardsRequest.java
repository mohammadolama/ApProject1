package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.MainLogic.DeckLogic;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.NotPurchasedCardsResponse;
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

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        notPurchasedCards = new ArrayList<>();
        for (Card card : DeckLogic.lockedCards(clientHandler.getPlayer())) {
            notPurchasedCards.add(Admin.getInstance().getPureViewModelOf(card.getName()));
        }
        try {
            outputStream.println(objectMapper.writeValueAsString(new NotPurchasedCardsResponse(notPurchasedCards)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
