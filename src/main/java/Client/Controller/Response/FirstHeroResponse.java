package Client.Controller.Response;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.PlayMusic;
import Client.View.View.Panels.MenuPanel;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Sounds.SoundAdmin;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("firsthero")
public class FirstHeroResponse implements Response {
    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        SoundAdmin.play1("resources/Sounds/menu.wav");
        MyFrame.getInstance().addPanels();
        RequestHandler.getInstance().sendRequest(new PlayMusic("welcome"));
        MenuPanel menuPanel = MenuPanel.getInstance();
        MyFrame.getPanel().add("menu", menuPanel);
        MyFrame.getInstance().changePanel("menu");
    }
}
