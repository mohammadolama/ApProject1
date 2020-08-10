package Client.Controller.Response;

import Client.View.View.Panels.MyFrame;
import Client.View.View.Sounds.SoundAdmin;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("exit")
public class ExitLogoutResponse implements Response {

    private int i;

    public ExitLogoutResponse() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        if (i == 1) {
            SoundAdmin.play1("resources/Sounds/login.wav");
            MyFrame.getInstance().changePanel("login");
        } else {
            System.exit(0);
        }
    }
}
