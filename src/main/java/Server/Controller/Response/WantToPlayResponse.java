package Server.Controller.Response;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("wanttoplay")
public class WantToPlayResponse implements Response {
    private String res;

    public WantToPlayResponse(String res) {
        this.res = res;
    }

    public WantToPlayResponse() {
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
