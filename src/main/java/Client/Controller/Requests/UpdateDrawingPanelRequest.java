package Client.Controller.Requests;

import Server.Model.Cards.Card;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("updatedrawingpanel")
public class UpdateDrawingPanelRequest implements Request {
    private String value;
    private ArrayList<Card> list;

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

    public ArrayList<Card> getList() {
        return list;
    }

    public void setList(ArrayList<Card> list) {
        this.list = list;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {

    }
}
