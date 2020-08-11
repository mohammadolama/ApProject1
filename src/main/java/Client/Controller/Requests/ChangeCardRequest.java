package Client.Controller.Requests;

import Client.Model.CardModelView;
import Client.View.View.Panels.AlternativePanel;
import Client.View.View.Update.Update;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("changecard")
public class ChangeCardRequest implements Request {

    private int i;
    @JsonIgnore
    private AlternativePanel alternativePanel;
    private CardModelView view;
    private String card1, card2, card3;

    public ChangeCardRequest(int i, AlternativePanel alternativePanel, String card1, String card2, String card3) {
        this.i = i;
        this.alternativePanel = alternativePanel;
        this.card1 = card1;
        this.card2 = card2;
        this.card3 = card3;
    }

    public ChangeCardRequest() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public AlternativePanel getAlternativePanel() {
        return alternativePanel;
    }

    public void setAlternativePanel(AlternativePanel alternativePanel) {
        this.alternativePanel = alternativePanel;
    }

    public CardModelView getView() {
        return view;
    }

    public void setView(CardModelView view) {
        this.view = view;
    }

    public String getCard1() {
        return card1;
    }

    public void setCard1(String card1) {
        this.card1 = card1;
    }

    public String getCard2() {
        return card2;
    }

    public void setCard2(String card2) {
        this.card2 = card2;
    }

    public String getCard3() {
        return card3;
    }

    public void setCard3(String card3) {
        this.card3 = card3;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
