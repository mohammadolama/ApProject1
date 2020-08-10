package Server.Controller.Response;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("exit")
public class ExitLogoutResponse implements Response {
    private int i;

    public ExitLogoutResponse(int i) {
        this.i = i;
    }

    public ExitLogoutResponse() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
