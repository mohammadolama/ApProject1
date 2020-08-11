package Server.Controller.Response;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("candoaction")
public class CanDoActionResponse implements Response {
    private boolean flag;

    public CanDoActionResponse(boolean flag) {
        this.flag = flag;
    }

    public CanDoActionResponse() {
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
