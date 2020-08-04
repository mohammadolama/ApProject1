package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("heropowercanbeplayed")
public class HeroPowerCanBePlayedRequest implements Request {
    private int i;

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
