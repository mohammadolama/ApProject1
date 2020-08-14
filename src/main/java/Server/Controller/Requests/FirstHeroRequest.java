package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.FirstHeroResponse;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("firsthero")
public class FirstHeroRequest implements Request {

    private String hero;


    public FirstHeroRequest() {
    }

    public String getHero() {
        return hero;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        Admin.getInstance().selectFirstHero(hero, clientHandler.getPlayer());
        try {
            outputStream.println(objectMapper.writeValueAsString(new FirstHeroResponse()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
