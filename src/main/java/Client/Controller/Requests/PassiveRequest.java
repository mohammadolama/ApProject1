package Client.Controller.Requests;

import Client.Controller.Responses;
import Client.Model.InfoPassive;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("passive")
public class PassiveRequest implements Request {
    ArrayList<InfoPassive> list;

    public PassiveRequest() {
    }

    public ArrayList<InfoPassive> getList() {
        return list;
    }

    public void setList(ArrayList<InfoPassive> list) {
        this.list = list;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            String res = inputStream.nextLine();
            list = objectMapper.readValue(res, new TypeReference<ArrayList<InfoPassive>>() {
            });
            Responses.getInstance().setPassiveList(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
