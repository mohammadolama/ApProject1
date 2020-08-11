package Client.Controller.Response;

import Client.Controller.Responses;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.*;

@JsonTypeName("targetlist")
public class TargetListResponse implements Response {
    private ArrayList<Integer> targets;

    public TargetListResponse() {
    }

    public ArrayList<Integer> getTargets() {
        return targets;
    }

    public void setTargets(ArrayList<Integer> targets) {
        this.targets = targets;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        Responses.getInstance().setTargets(targets);
        synchronized (object) {
            object.notify();
        }
    }
}
