package Client.Controller.Response;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.PlayMusic;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Panels.ShopPanel;
import Client.View.View.Update.Update;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("buy")
public class BuySellResponse implements Response {

    private int i;
    private String res;

    public BuySellResponse() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        if (i == 1) {
            if (res.equalsIgnoreCase("ok")) {
                RequestHandler.getInstance().sendRequest(new PlayMusic("buy"));
            } else {
                RequestHandler.getInstance().sendRequest(new PlayMusic("gold"));
            }
            ShopPanel.getInstance().revalidateCards(true);
        } else {
            if (res.equalsIgnoreCase("ok")) {
                RequestHandler.getInstance().sendRequest(new PlayMusic("sell"));
            } else {
                new Thread(() -> {
                    JOptionPane.showMessageDialog(MyFrame.getInstance(), "Can't be sold");
                }).start();
//                ,It's in one of your decks.
            }
            ShopPanel.getInstance().revalidateCards(false);
        }
        synchronized (object) {
            object.notify();
        }
        Update.render();
    }
}
