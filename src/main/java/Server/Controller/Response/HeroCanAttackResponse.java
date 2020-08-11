package Server.Controller.Response;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("herocanattack")
public class HeroCanAttackResponse implements Response {
    private boolean flag;

    public HeroCanAttackResponse(boolean flag) {
        this.flag = flag;
    }

    public HeroCanAttackResponse() {
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
