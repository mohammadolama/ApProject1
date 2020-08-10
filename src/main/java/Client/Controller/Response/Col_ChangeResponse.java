package Client.Controller.Response;

import Client.View.View.Panels.Col_Change;
import Client.View.View.Panels.CollectionPanel;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Update.Update;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("col")
public class Col_ChangeResponse implements Response {
    private String res;

    public Col_ChangeResponse() {
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        if (!res.equalsIgnoreCase("ok")) {
            JOptionPane.showMessageDialog(MyFrame.getInstance(), res);
        } else {
            CollectionPanel.getInstance().refresh();
            Col_Change.getInstance().getBackButton().doClick();
            Update.render();
        }
    }
}
