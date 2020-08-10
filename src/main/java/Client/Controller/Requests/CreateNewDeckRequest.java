package Client.Controller.Requests;

import Client.View.View.Panels.Col_Change;
import Client.View.View.Panels.MyFrame;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("createnewdeck")
public class CreateNewDeckRequest implements Request {
    private boolean response;

    public CreateNewDeckRequest(boolean response) {
        this.response = response;
    }

    public CreateNewDeckRequest() {
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            String res = inputStream.nextLine();
            response = objectMapper.readValue(res, boolean.class);
            if (response) {
                Col_Change.getInstance().setCreateMode(true);
                MyFrame.getInstance().changePanel("col");
            } else {
                JOptionPane.showMessageDialog(MyFrame.getInstance(), "Can not create more than 12 decks");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
