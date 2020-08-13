package Server.Controller.Requests;

import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Model.ActionModel;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ActionChartRequest implements Request {

    private ArrayList<ActionModel> friednlyModel;
    private ArrayList<ActionModel> enemyModel;

    public ActionChartRequest(ArrayList<ActionModel> friednlyModel, ArrayList<ActionModel> enemyModel) {
        this.friednlyModel = friednlyModel;
        this.enemyModel = enemyModel;
    }

    public ActionChartRequest() {
    }

    public ArrayList<ActionModel> getFriednlyModel() {
        return friednlyModel;
    }

    public void setFriednlyModel(ArrayList<ActionModel> friednlyModel) {
        this.friednlyModel = friednlyModel;
    }

    public ArrayList<ActionModel> getEnemyModel() {
        return enemyModel;
    }

    public void setEnemyModel(ArrayList<ActionModel> enemyModel) {
        this.enemyModel = enemyModel;
    }


    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        friednlyModel = clientHandler.getGameManager().getPlayer1Actions(clientHandler);
        enemyModel = clientHandler.getGameManager().getPlayer2Actions(clientHandler);
        try {
            outputStream.println(objectMapper.writeValueAsString(new ActionChartRequest(friednlyModel, enemyModel)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
