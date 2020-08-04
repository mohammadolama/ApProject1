package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("render")
public class RenderRequest implements Request {
    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
