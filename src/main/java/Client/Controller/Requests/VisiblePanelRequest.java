package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("visiblepanel")
public class VisiblePanelRequest implements Request {

    private String name;

    public VisiblePanelRequest(String name) {
        this.name = name;
    }

    public VisiblePanelRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
