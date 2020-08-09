package Client.Controller.Requests;

import Client.View.View.Panels.InfoPassivePanel;
import Client.View.View.Panels.MyFrame;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("wanttoplay")
public class WantToPlayRequest implements Request {
    public WantToPlayRequest() {

    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            String res = inputStream.nextLine();
            if (res.equalsIgnoreCase("ok")) {
                InfoPassivePanel infoPassivePanel = new InfoPassivePanel();
                MyFrame.getPanel().add(infoPassivePanel, "info");
                MyFrame.getInstance().changePanel("info");
            } else {
                int i = JOptionPane.showConfirmDialog(MyFrame.getInstance(), res, "Error", JOptionPane.DEFAULT_OPTION);
                if (i == 0) {
                    MyFrame.getInstance().changePanel("collection");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
