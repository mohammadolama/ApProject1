package Client.Controller.Response;

import Client.Controller.Responses;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("candoaction")
public class CanDoActionResponse implements Response {
    private boolean flag;

    public CanDoActionResponse() {
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        Responses.getInstance().setCanDoAction(flag);
        synchronized (object) {
            object.notify();
        }
    }
}
