package Client.Controller.Requests;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

public class ConnectRequest implements Request {

    private String port;
    private int ip;

    public ConnectRequest(String port, int ip) {
        this.port = port;
        this.ip = ip;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {

    }
}
