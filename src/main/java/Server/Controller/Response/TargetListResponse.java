package Server.Controller.Response;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.*;

@JsonTypeName("targetlist")
public class TargetListResponse implements Response {
    private ArrayList<Integer> targets;

    public TargetListResponse(ArrayList<Integer> targets) {
        this.targets = targets;
    }

    public TargetListResponse() {
    }

    public ArrayList<Integer> getTargets() {
        return targets;
    }

    public void setTargets(ArrayList<Integer> targets) {
        this.targets = targets;
    }
}
