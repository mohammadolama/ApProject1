package Client.Controller.Requests;

import Client.Controller.Responses;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("price")
public class PriceRequest implements Request {

    private String name;

    private long price;
    private String className;

    public PriceRequest() {
    }

    public PriceRequest(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public long getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            String res1 = inputStream.nextLine();
            String res2 = inputStream.nextLine();
            className = objectMapper.readValue(res1, String.class);
            price = objectMapper.readValue(res2, long.class);
            Responses.getInstance().setPrice(price);
            Responses.getInstance().setClassName(className);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
