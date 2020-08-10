package Server.Controller.Response;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("price")
public class PriceResponse implements Response {
    private long price;
    private String className;

    public PriceResponse(long price, String className) {
        this.price = price;
        this.className = className;
    }

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
}
