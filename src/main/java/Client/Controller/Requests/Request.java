package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "model")
public interface Request {
    void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object);
}
