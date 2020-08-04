package Client.Controller.Requests;

import Client.Model.InfoPassive;
import org.codehaus.jackson.annotate.JsonTypeName;

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
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
