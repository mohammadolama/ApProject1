package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("log")
public class LogRequest implements Request {

    private String log;

    public LogRequest(String log) {
        this.log = log;
    }

    public LogRequest() {
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {

    }
}
