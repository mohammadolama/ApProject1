package Client.Controller.Requests;

import Client.View.View.Panels.MyFrame;
import Client.View.View.Update.Update;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("render")
public class RenderRequest implements Request {
    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {
        Update.render();
    }
}
