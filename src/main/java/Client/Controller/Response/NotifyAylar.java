package Client.Controller.Response;

import Client.Model.CardModelView;
import Client.View.View.Panels.AlternativePanel;
import Client.View.View.Panels.MyFrame;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("aylar")
public class NotifyAylar implements Response {
    private CardModelView card1;
    private CardModelView card2;
    private CardModelView card3;

    public NotifyAylar() {
    }

    public CardModelView getCard1() {
        return card1;
    }

    public void setCard1(CardModelView card1) {
        this.card1 = card1;
    }

    public CardModelView getCard2() {
        return card2;
    }

    public void setCard2(CardModelView card2) {
        this.card2 = card2;
    }

    public CardModelView getCard3() {
        return card3;
    }

    public void setCard3(CardModelView card3) {
        this.card3 = card3;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        AlternativePanel th = new AlternativePanel(true, 0);
        th.setModel1(card1);
        th.setModel2(card2);
        th.setModel3(card3);
        th.setEnabled(true);
        MyFrame.getPanel().add("three", th);
        MyFrame.getInstance().changePanel("three");
    }
}
