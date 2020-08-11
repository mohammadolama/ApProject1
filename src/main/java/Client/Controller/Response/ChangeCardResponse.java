package Client.Controller.Response;

import Client.Model.CardModelView;
import Client.View.View.Panels.AlternativePanel;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Update.Update;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("threecard")
public class ChangeCardResponse implements Response {
    private int i;
    private CardModelView view;

    public ChangeCardResponse() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public CardModelView getView() {
        return view;
    }

    public void setView(CardModelView view) {
        this.view = view;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        AlternativePanel alternativePanel = MyFrame.getInstance().getAlternativePanel();
        if (i == 1) {
            alternativePanel.setModel1(view);
        } else if (i == 2) {
            alternativePanel.setModel2(view);
        } else {
            alternativePanel.setModel3(view);
        }
        Update.render();
    }
}
