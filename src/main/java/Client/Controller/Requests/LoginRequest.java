package Client.Controller.Requests;

import Client.View.View.Panels.FirstHeroSelector;
import Client.View.View.Panels.LoginPanel;
import Client.View.View.Panels.MenuPanel;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Sounds.SoundAdmin;
import Server.Model.Deck;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Scanner;

@JsonTypeName("login")
public class LoginRequest implements Request {

    private String username;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {
//        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(this);
            outputStream.println(s);
            String res = inputStream.nextLine();
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
                MyFrame.getInstance().changePanel("menu");
                MenuPanel.getInstance().setFocusable(true);
                MenuPanel.getInstance().grabFocus();
            } else {
                LoginPanel.getInstance().getError().setText(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
