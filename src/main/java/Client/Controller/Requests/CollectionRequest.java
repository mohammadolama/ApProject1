package Client.Controller.Requests;

import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.Model.Enums.Carts;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("collection")
public class CollectionRequest implements Request {

    private String name;
    private String heroName;
    private ArrayList<Carts> list;
    private ArrayList<CardModelView> list2;

    public CollectionRequest(String name) {
        this.name = name;
    }

    public CollectionRequest() {
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
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

    public ArrayList<CardModelView> getList2() {
        return list2;
    }

    public void setList2(ArrayList<CardModelView> list2) {
        this.list2 = list2;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            String res1 = inputStream.nextLine();
            String res2 = inputStream.nextLine();
            String res3 = inputStream.nextLine();
            list = objectMapper.readValue(res1, new TypeReference<ArrayList<Carts>>() {
            });
            list2 = objectMapper.readValue(res2, new TypeReference<ArrayList<CardModelView>>() {
            });
            Responses.getInstance().setCollectionModels(list2);
            Responses.getInstance().setCollectionList(list);
            Responses.getInstance().setHeroName(res3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
