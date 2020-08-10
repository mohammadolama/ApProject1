package Client.Controller.Response;

import Client.View.View.Panels.Col_Change;
import Client.View.View.Panels.MyFrame;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("createnewdeck")
public class CreateNewDeckResponse implements Response {
    private boolean response;

    public CreateNewDeckResponse() {
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        if (response) {
            Col_Change.getInstance().setCreateMode(true);
            MyFrame.getInstance().changePanel("col");
        } else {
            JOptionPane.showMessageDialog(MyFrame.getInstance(), "Can not create more than 12 decks");

        }
    }
}
