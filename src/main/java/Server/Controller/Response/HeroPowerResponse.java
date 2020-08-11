package Server.Controller.Response;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("heropower")
public class HeroPowerResponse implements Response {
    private int i;

    public HeroPowerResponse(int i) {
        this.i = i;
    }

    public HeroPowerResponse() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
