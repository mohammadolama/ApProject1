package Server.Controller.Response;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("canbeplayed")
public class CanBePlayedResponse implements Response {
    private String res;

    public CanBePlayedResponse(String res) {
        this.res = res;
    }

    public CanBePlayedResponse() {
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
