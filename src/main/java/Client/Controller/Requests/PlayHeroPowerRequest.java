package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("heropower")
public class PlayHeroPowerRequest implements Request {

    private int target;

    public PlayHeroPowerRequest(int target) {
        this.target = target;
    }

    public PlayHeroPowerRequest() {
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
