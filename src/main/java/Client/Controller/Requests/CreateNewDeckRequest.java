package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("createnewdeck")
public class CreateNewDeckRequest implements Request {
    private boolean response;

    public CreateNewDeckRequest(boolean response) {
        this.response = response;
    }

    public CreateNewDeckRequest() {
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
