package Client.Controller.Response;

import Client.View.View.Panels.FirstHeroSelector;
import Client.View.View.Panels.LoginPanel;
import Client.View.View.Panels.MenuPanel;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Sounds.SoundAdmin;
import Client.View.View.Update.Update;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("login")
public class LoginSignupResponse implements Response {

    private int i;
    private String res;

    public LoginSignupResponse() {
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
                LoginPanel.getInstance().getError().setText("Account Created.");
            } else if (res.equalsIgnoreCase("user already exist")) {
                LoginPanel.getInstance().getError().setText("User already exists.");
            }
            Update.render();
        } else {
            if (res.equalsIgnoreCase("new player")) {
                FirstHeroSelector firstHeroSelector = new FirstHeroSelector();
                MyFrame.getPanel().add("hero", firstHeroSelector);
                MyFrame.getInstance().changePanel("hero");
                LoginPanel.getInstance().reset();
            } else if (res.equalsIgnoreCase("welcome")) {
                MyFrame.getInstance().addPanels();
                SoundAdmin.clip.stop();
                SoundAdmin.play1("resources/Sounds/menu.wav");
                new Thread(() -> SoundAdmin.playSound("welcome")).start();
                MenuPanel menuPanel = MenuPanel.getInstance();
                MyFrame.getPanel().add("menu", menuPanel);
                MyFrame.getInstance().changePanel("menu");
                MenuPanel.getInstance().setFocusable(true);
                MenuPanel.getInstance().grabFocus();
            } else {
                LoginPanel.getInstance().getError().setText(res);
            }
        }
    }
}
