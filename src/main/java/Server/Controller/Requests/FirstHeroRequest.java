package Server.Controller.Requests;

import Client.View.View.Panels.MyFrame;
import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

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
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper) {
        Admin.getInstance().selectFirstHero(hero, clientHandler.getPlayer());
        outputStream.println("ok");
    }
}
