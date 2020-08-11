package Client.Controller.Response;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.PlayMusic;
import Client.Controller.Requests.Request;
import Client.Controller.Responses;
import Client.View.View.Panels.MyFrame;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("canbeplayed")
public class CanBePlayedResponse implements Response {
    private String res;

    public CanBePlayedResponse() {
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {

        if (res.contains("ok")) {
            if (res.equalsIgnoreCase("okminion")) RequestHandler.getInstance().playSound("minion");
            else if (res.equalsIgnoreCase("okspell")) RequestHandler.getInstance().playSound("spell");
            else RequestHandler.getInstance().playSound("weapon");
            Responses.getInstance().setCanBePlayed(true);
        } else {
            if (res.equalsIgnoreCase("full")) {
                RequestHandler.getInstance().sendRequest(new PlayMusic("error"));
                new Thread(() -> {
                    JOptionPane.showConfirmDialog(MyFrame.getInstance(), "Can not play more than 7 minion");
                }).start();
            } else {
                RequestHandler.getInstance().sendRequest(new PlayMusic("mana"));
            }
            Responses.getInstance().setCanBePlayed(false);
        }


        synchronized (object) {
            object.notify();
        }
    }
}
