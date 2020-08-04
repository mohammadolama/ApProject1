package Client.Controller.Requests;

import Server.Model.Cards.Card;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("propercard")
public class ProperCardsRequest implements Request {
    private int i;
    private ArrayList<Card> list;

    public ProperCardsRequest(int i) {
        this.i = i;
    }

    public ProperCardsRequest() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public ArrayList<Card> getList() {
        return list;
    }

    public void setList(ArrayList<Card> list) {
        this.list = list;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
