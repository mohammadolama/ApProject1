package Server.Controller.Response;

import Server.Model.InfoPassive;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("passive")
public class PassiveResponse implements Response {
    ArrayList<InfoPassive> list;

    public PassiveResponse(ArrayList<InfoPassive> list) {
        this.list = list;
    }

    public PassiveResponse() {
    }

    public ArrayList<InfoPassive> getList() {
        return list;
    }

    public void setList(ArrayList<InfoPassive> list) {
        this.list = list;
    }
}
