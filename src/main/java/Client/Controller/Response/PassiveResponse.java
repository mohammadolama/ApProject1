package Client.Controller.Response;

import Client.Controller.Responses;
import Client.Model.InfoPassive;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("passive")
public class PassiveResponse implements Response {

    private ArrayList<InfoPassive> list;

    public PassiveResponse() {
    }

    public ArrayList<InfoPassive> getList() {
        return list;
    }

    public void setList(ArrayList<InfoPassive> list) {
        this.list = list;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        Responses.getInstance().setPassiveList(list);
        synchronized (object) {
            object.notify();
        }
    }
}
