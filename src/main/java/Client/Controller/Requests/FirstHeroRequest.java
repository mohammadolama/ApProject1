package Client.Controller.Requests;

import Client.Controller.RequestHandler;
import Client.View.View.Panels.MenuPanel;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Sounds.SoundAdmin;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("firsthero")
public class FirstHeroRequest implements Request {

    private String hero;

    public FirstHeroRequest(String hero) {
        this.hero = hero;
    }

    public FirstHeroRequest() {
    }

    public String getHero() {
        return hero;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {
        try {
            String s = objectMapper.writeValueAsString(this);
            outputStream.println(s);
            String res = inputStream.nextLine();
            SoundAdmin.play1("resources/Sounds/menu.wav");
            MyFrame.getInstance().addPanels();
            RequestHandler.getInstance().sendRequest(new PlayMusic("welcome"));
            MenuPanel menuPanel = MenuPanel.getInstance();
            MyFrame.getPanel().add("menu", menuPanel);
            MyFrame.getInstance().changePanel("menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
