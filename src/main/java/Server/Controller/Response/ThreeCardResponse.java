package Server.Controller.Response;

import Server.Model.CardModelView;
import Server.Model.InfoPassive;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("three")
public class ThreeCardResponse implements Response {
    private ArrayList<CardModelView> list;
    private InfoPassive infoPassive;

    public ThreeCardResponse(ArrayList<CardModelView> list, InfoPassive infoPassive) {
        this.list = list;
        this.infoPassive = infoPassive;
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
}
