package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("aylaraction")
public class AylarActionRequest implements Request {

    private String name;

    public AylarActionRequest(String name) {
        this.name = name;
    }

    public AylarActionRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {

    }
}
