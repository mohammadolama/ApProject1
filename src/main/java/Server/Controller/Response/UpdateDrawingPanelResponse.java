package Server.Controller.Response;

import Server.Model.CardModelView;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("drawing")
public class UpdateDrawingPanelResponse implements Response {
    private String value;
    private ArrayList<CardModelView> list;

    public UpdateDrawingPanelResponse(String value, ArrayList<CardModelView> list) {
        this.value = value;
        this.list = list;
    }

    public UpdateDrawingPanelResponse() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArrayList<CardModelView> getList() {
        return list;
    }

    public void setList(ArrayList<CardModelView> list) {
        this.list = list;
    }
}
