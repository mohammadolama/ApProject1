package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.Col_ChangeResponse;
import Server.Model.DeckModel;
import Server.Model.Enums.Carts;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
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
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        String res = Admin.getInstance().changeDeck(deck, list, heroName, previousName, curruntName, clientHandler.getPlayer());
        try {
            outputStream.println(objectMapper.writeValueAsString(new Col_ChangeResponse(res)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
