package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.MainLogic.DeckLogic;
import Server.Controller.Manager.Managers;
import Server.Model.CardModelView;
import Server.Model.Cards.Card;
import Server.Model.Deck;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("updatedrawingpanel")
public class UpdateDrawingPanelRequest implements Request {
    private String value;
    private ArrayList<CardModelView> list;

    public UpdateDrawingPanelRequest(String value) {
        this.value = value;
    }

    public UpdateDrawingPanelRequest() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArrayList<CardModelView> getList() {
        return list;
    }

    public void setList(ArrayList<CardModelView> list) {
        this.list = list;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        ArrayList<Card> cards;
        if (value.equalsIgnoreCase("all"))
            cards = Card.allCards();
        else if (value.equalsIgnoreCase("locked"))
            cards = Card.lockedCards(clientHandler.getPlayer());
        else if (value.equalsIgnoreCase("unlocked"))
            cards = Card.purchasedCards(clientHandler.getPlayer());
        else if (value.equalsIgnoreCase("neutral"))
            cards = Card.neutralCardsFilter();
        else if (value.equalsIgnoreCase("special")) {
            cards = Card.specialCardsFilter();
        } else {
            Deck selectedDeck = Deck.cloneDeck(clientHandler.getPlayer().getAllDecks().get(value));
            cards = DeckLogic.UpdateDeck(selectedDeck.getDeck());
        }
        list = new ArrayList<>();
        for (Card card : cards) {
            list.add(Admin.getInstance().getPureViewModelOf(card.getName()));
        }
        try {
            outputStream.println(objectMapper.writeValueAsString(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
