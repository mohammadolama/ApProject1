package Server.Controller.Response;

import Server.Model.CardModelView;
import Server.Model.Enums.Carts;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("collection")
public class CollectionResponse implements Response {
    private String heroName;
    private ArrayList<Carts> list;
    private ArrayList<CardModelView> list2;

    public CollectionResponse(String heroName, ArrayList<Carts> list, ArrayList<CardModelView> list2) {
        this.heroName = heroName;
        this.list = list;
        this.list2 = list2;
    }

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
}
