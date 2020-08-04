package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("validdeck")
public class ValidateDeckNameRequest implements Request {
    private String name;
    private boolean valid;

    public ValidateDeckNameRequest(String name) {
        this.name = name;
    }

    public ValidateDeckNameRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
