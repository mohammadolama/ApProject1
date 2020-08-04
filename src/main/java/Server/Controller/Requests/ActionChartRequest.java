package Server.Controller.Requests;

import Server.Model.ActionModel;

import java.io.InputStream;
import java.io.OutputStream;
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
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}