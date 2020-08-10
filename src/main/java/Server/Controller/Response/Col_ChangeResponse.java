package Server.Controller.Response;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("col")
public class Col_ChangeResponse implements Response {

    private String res;

    public Col_ChangeResponse(String res) {
        this.res = res;
    }

    public Col_ChangeResponse() {
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
