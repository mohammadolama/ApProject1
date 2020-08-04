package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("sellcard")
public class SellCardRequest implements Request {

    private String name;

    public SellCardRequest(String name) {
        this.name = name;
    }

    public SellCardRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
