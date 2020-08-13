package Client.Controller.Response;

import Client.Controller.Responses;
import Client.Model.ActionModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("actionchart")
public class ActionChartResponse implements Response {
    private ArrayList<ActionModel> friednlyModel;
    private ArrayList<ActionModel> enemyModel;

    public ActionChartResponse() {
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
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        Responses.getInstance().setFriendlyModel(friednlyModel);
        Responses.getInstance().setEnemyModel(enemyModel);
    }
}
