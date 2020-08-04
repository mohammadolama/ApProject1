package Server.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("selectfirsthero")
public class SelectFirstHeroRequest implements Request {

    private String name;

    public SelectFirstHeroRequest(String name) {
        this.name = name;
    }

    public SelectFirstHeroRequest() {
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
