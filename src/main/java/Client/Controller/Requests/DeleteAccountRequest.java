package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("deleteaccount")
public class DeleteAccountRequest implements Request {
    public DeleteAccountRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
