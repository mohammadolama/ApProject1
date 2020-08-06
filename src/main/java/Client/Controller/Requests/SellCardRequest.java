package Client.Controller.Requests;

import Client.Controller.RequestHandler;
import Client.View.View.Panels.MyFrame;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("sellcard")
public class SellCardRequest implements Request {

    private String name;

    public SellCardRequest(String name) {
        this.name = name;
    }

    public SellCardRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            String res = inputStream.nextLine();
            if (res.equalsIgnoreCase("ok")) {
                RequestHandler.getInstance().sendRequest(new PlayMusic("sell"));
            } else {
                JOptionPane.showMessageDialog(MyFrame.getInstance(), "Can't be sold,It's in one of your decks.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
