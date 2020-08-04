package Client.Controller.Requests;

import Client.Model.Enums.Carts;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("collection")
public class CollectionRequest implements Request {

    private String name;
    private ArrayList<Carts> list;

    public CollectionRequest(String name) {
        this.name = name;
    }

    public CollectionRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Carts> getList() {
        return list;
    }

    public void setList(ArrayList<Carts> list) {
        this.list = list;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
