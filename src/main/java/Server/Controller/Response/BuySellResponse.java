package Server.Controller.Response;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("buy")
public class BuySellResponse implements Response {
    private int i;
    private String res;

    public BuySellResponse() {
    }

    public BuySellResponse(int i, String res) {
        this.i = i;
        this.res = res;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }


}
