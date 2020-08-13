package Client.Controller.Requests;

import Client.View.View.Panels.MyFrame;
import Client.View.View.Update.Update;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("unlockhero")
public class UnlockHeroRequest implements Request {
    public UnlockHeroRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            JOptionPane.showMessageDialog(MyFrame.getInstance(), "All Heros Unlocked!");
            Update.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
