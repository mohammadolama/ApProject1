package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.MainLogic.ThreadColor;
import Server.Model.DeckModel;
import Server.Model.Enums.Carts;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("changedeck")
public class ChangeDeckRequest implements Request {

    private DeckModel deck;
    private String heroName;
    private String previousName;
    private String curruntName;
    private ArrayList<Carts> list;

    public ChangeDeckRequest(DeckModel deck, String heroName, String previousName, String curruntName, ArrayList<Carts> list) {
        this.deck = deck;
        this.heroName = heroName;
        this.previousName = previousName;
        this.curruntName = curruntName;
        this.list = list;
    }

    public ChangeDeckRequest() {
    }

    public String getCurruntName() {
        return curruntName;
    }

    public void setCurruntName(String curruntName) {
        this.curruntName = curruntName;
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

    public String getPreviousName() {
        return previousName;
    }

    public void setPreviousName(String previousName) {
        this.previousName = previousName;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper) {
        System.out.println(heroName);
        System.out.println("rogue");
        String res = Admin.getInstance().changeDeck(deck, list, heroName, previousName, curruntName, clientHandler.getPlayer());
        System.out.println(ThreadColor.ANSI_BLUE + res + ThreadColor.ANSI_RESET);
        outputStream.println(res);
    }
}
