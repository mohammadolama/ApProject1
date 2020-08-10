package Client.Controller.Response;

import Client.Controller.Responses;
import Client.Model.CardModelView;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("propercards")
public class ProperCardsResponse implements Response {
    private ArrayList<CardModelView> list;

    public ProperCardsResponse() {
    }

    public ArrayList<CardModelView> getList() {
        return list;
    }

    public void setList(ArrayList<CardModelView> list) {
        this.list = list;
    }


    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        Responses.getInstance().setModelviewList(list);
        synchronized (object) {
            object.notify();
        }
    }
}
