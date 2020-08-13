package Client.Controller.Response;

import Client.View.View.Panels.MyFrame;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("endturn")
public class NotifyEndTurn implements Response {


    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        MyFrame.getInstance().getBoardPanel().forceEndTurn();
    }
}
