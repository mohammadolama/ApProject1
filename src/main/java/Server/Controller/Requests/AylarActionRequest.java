package Server.Controller.Requests;

import Server.Controller.MainLogic.ClientHandler;
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
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper) {

    }
}
