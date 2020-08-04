package Client.Controller.Requests;

import Client.Model.DeckModel;
import Client.Model.Enums.Carts;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("changedeck")
public class ChangeDeckRequest implements Request {

    private DeckModel deck;
    private String heroName;
    private ArrayList<Carts> list;

    public ChangeDeckRequest(DeckModel deck, String heroName, ArrayList<Carts> list) {
        this.deck = deck;
        this.heroName = heroName;
        this.list = list;
    }

    public ChangeDeckRequest() {
    }

    public DeckModel getDeck() {
        return deck;
    }

    public void setDeck(DeckModel deck) {
        this.deck = deck;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public ArrayList<Carts> getList() {
        return list;
    }

    public void setList(ArrayList<Carts> list) {
        this.list = list;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
