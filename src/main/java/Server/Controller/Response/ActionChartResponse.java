package Server.Controller.Response;

import Server.Model.ActionModel;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("actionchart")
public class ActionChartResponse implements Response {
    private ArrayList<ActionModel> friednlyModel;
    private ArrayList<ActionModel> enemyModel;

    public ActionChartResponse(ArrayList<ActionModel> friednlyModel, ArrayList<ActionModel> enemyModel) {
        this.friednlyModel = friednlyModel;
        this.enemyModel = enemyModel;
    }

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


}
