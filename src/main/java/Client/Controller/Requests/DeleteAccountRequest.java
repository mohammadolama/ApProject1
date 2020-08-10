package Client.Controller.Requests;

import Client.View.View.Panels.MyFrame;
import Client.View.View.Sounds.SoundAdmin;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("deleteaccount")
public class DeleteAccountRequest implements Request {
    public DeleteAccountRequest() {

    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {

        try {
            if (JOptionPane.showConfirmDialog(MyFrame.getInstance(), "Are you sure", "Delete Account", JOptionPane.YES_NO_OPTION) == 0) {
                outputStream.println(objectMapper.writeValueAsString(this));
                SoundAdmin.play1("resources/Sounds/login.wav");
                MyFrame.getInstance().changePanel("login");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
