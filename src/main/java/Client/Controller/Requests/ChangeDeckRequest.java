package Client.Controller.Requests;

import Client.Model.DeckModel;
import Client.Model.Enums.Carts;
import Client.View.View.Panels.Col_Change;
import Client.View.View.Panels.CollectionPanel;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Update.Update;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
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
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {
        try {
            System.out.println(heroName);
            System.out.println("rogue");
            outputStream.println(objectMapper.writeValueAsString(this));
            String res = inputStream.nextLine();
            System.out.println("**" + res + "**");
            if (!res.equalsIgnoreCase("ok")) {
                JOptionPane.showMessageDialog(MyFrame.getInstance(), res);
            } else {
                CollectionPanel.getInstance().refresh();
                Col_Change.getInstance().getBackButton().doClick();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
