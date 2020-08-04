package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("wanttoplay")
public class WantToPlayRequest implements Request {
    public WantToPlayRequest() {

    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
