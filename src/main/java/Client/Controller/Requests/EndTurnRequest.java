package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("endturn")
public class EndTurnRequest implements Request {
    private boolean friendlyDeck;

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
