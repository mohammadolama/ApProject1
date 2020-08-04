package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("finishgame")
public class FinishGameRequest implements Request {

    public FinishGameRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
