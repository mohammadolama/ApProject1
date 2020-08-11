package Client.Controller.Response;

import Client.View.View.Panels.InfoPassivePanel;
import Client.View.View.Panels.MyFrame;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("wanttoplay")
public class WantToPlayResponse implements Response {
    private String res;

    public WantToPlayResponse() {
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
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
    }
}
