package Server.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("unlockhero")
public class UnlockHeroRequest implements Request {
    public UnlockHeroRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
