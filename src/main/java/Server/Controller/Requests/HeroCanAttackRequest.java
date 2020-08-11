package Server.Controller.Requests;

import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.HeroCanAttackResponse;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("herocanattack")
public class HeroCanAttackRequest implements Request {
    private boolean flag;

    public HeroCanAttackRequest() {
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        boolean flag = clientHandler.getGameManager().getHeroCanAttack(clientHandler);
        try {
            outputStream.println(objectMapper.writeValueAsString(new HeroCanAttackResponse(flag)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
