package Client.Controller.Requests;

import Server.Model.Cards.Card;
import Server.Model.Enums.Carts;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("selecteddeckcards")
public class SelectedDeckCardsRequest implements Request {

    private ArrayList<Carts> cartList;
    private ArrayList<Card> cardList;
    private String name;

    public SelectedDeckCardsRequest(ArrayList<Carts> cartList, ArrayList<Card> cardList, String name) {
        this.cartList = cartList;
        this.cardList = cardList;
        this.name = name;
    }

    public SelectedDeckCardsRequest() {
    }

    public ArrayList<Carts> getCartList() {
        return cartList;
    }

    public void setCartList(ArrayList<Carts> cartList) {
        this.cartList = cartList;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public void setCardList(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
