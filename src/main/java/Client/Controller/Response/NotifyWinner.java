package Client.Controller.Response;

import Client.View.View.Panels.AlternativePanel;
import Client.View.View.Panels.Constants;
import Client.View.View.Panels.MyFrame;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("notifywinner")
public class NotifyWinner implements Response {
    private String name;

    public NotifyWinner() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        AlternativePanel alternativePanel = new AlternativePanel(false, 0);
        alternativePanel.setEnabled(true);
        alternativePanel.setWinner(Constants.heroPortraits.get(name));
        alternativePanel.setWinningMode(true);
        MyFrame.getPanel().add("three", alternativePanel);
        MyFrame.getInstance().changePanel("three");
    }
}
