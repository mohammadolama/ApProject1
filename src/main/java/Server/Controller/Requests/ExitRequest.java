package Server.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("exit")
public class ExitRequest implements Request {

    public ExitRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
