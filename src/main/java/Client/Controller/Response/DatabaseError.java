package Client.Controller.Response;

import Client.View.View.Panels.ConnectionPanel;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Sounds.SoundAdmin;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("databaseerror")
public class DatabaseError implements Response {


    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        SoundAdmin.play1("resources/Sounds/login.wav");
        ConnectionPanel connectionPanel = new ConnectionPanel();
        MyFrame.getPanel().add("connection", connectionPanel);
        MyFrame.getInstance().changePanel("connection");
    }
}
