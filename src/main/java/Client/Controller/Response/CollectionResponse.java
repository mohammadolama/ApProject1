package Client.Controller.Response;

import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.Model.Enums.Carts;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("collection")
public class CollectionResponse implements Response {

    private String heroName;
    private ArrayList<Carts> list;
    private ArrayList<CardModelView> list2;

    public CollectionResponse() {
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public ArrayList<Carts> getList() {
        return list;
    }

    public void setList(ArrayList<Carts> list) {
        this.list = list;
    }

    public ArrayList<CardModelView> getList2() {
        return list2;
    }

    public void setList2(ArrayList<CardModelView> list2) {
        this.list2 = list2;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        Responses.getInstance().setCollectionModels(list2);
        Responses.getInstance().setCollectionList(list);
        Responses.getInstance().setHeroName(heroName);
        synchronized (object) {
            object.notify();
        }
    }
}
