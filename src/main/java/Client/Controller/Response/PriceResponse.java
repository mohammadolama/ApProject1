package Client.Controller.Response;

import Client.Controller.Responses;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("price")
public class PriceResponse implements Response {

    private long price;
    private String className;

    public PriceResponse() {
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        Responses.getInstance().setPrice(price);
        Responses.getInstance().setClassName(className);
        synchronized (object) {
            object.notify();
        }
    }
}
