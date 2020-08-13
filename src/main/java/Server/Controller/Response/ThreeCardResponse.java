package Server.Controller.Response;

import Server.Model.CardModelView;
import Server.Model.InfoPassive;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("three")
public class ThreeCardResponse implements Response {
    private ArrayList<CardModelView> list;
    private InfoPassive infoPassive;
    private int i;

    public ThreeCardResponse(ArrayList<CardModelView> list, InfoPassive infoPassive, int i) {
        this.list = list;
        this.infoPassive = infoPassive;
        this.i = i;
    }

    public ThreeCardResponse() {
    }

    public ArrayList<CardModelView> getList() {
        return list;
    }

    public void setList(ArrayList<CardModelView> list) {
        this.list = list;
    }

    public InfoPassive getInfoPassive() {
        return infoPassive;
    }

    public void setInfoPassive(InfoPassive infoPassive) {
        this.infoPassive = infoPassive;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
