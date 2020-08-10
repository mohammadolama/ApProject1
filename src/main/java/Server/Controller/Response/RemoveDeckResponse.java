package Server.Controller.Response;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("remove")
public class RemoveDeckResponse implements Response {
    private String res;

    public RemoveDeckResponse(String res) {
        this.res = res;
    }

    public RemoveDeckResponse() {
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
