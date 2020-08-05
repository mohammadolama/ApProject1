package Client.Controller.Requests;

import Client.View.View.Panels.MyFrame;
import Client.View.View.Sounds.SoundAdmin;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("logout")
public class LogoutRequest implements Request {
    public LogoutRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            String res = inputStream.nextLine();
            SoundAdmin.play1("resources/Sounds/login.wav");
            MyFrame.getInstance().changePanel("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
