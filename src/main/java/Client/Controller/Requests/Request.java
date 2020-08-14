package Client.Controller.Requests;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

public interface Request {
    void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object);
}
