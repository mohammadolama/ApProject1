package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Model.CardModelView;
import Server.Model.Enums.*;
import Server.Model.Heros.*;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
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

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public void setList2(ArrayList<CardModelView> list2) {
        this.list2 = list2;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper) {
        String heroName = null;
        List<Carts> ar1 = clientHandler.getPlayer().getPlayerCarts();
        ArrayList<Carts> ar2 = new ArrayList<>();
        ArrayList<Carts> ar3 = new ArrayList<>();
        ArrayList<CardModelView> ar4 = new ArrayList<>();
        for (Carts carts : ar1) {
            for (NeutralCarts value : NeutralCarts.values()) {
                if (carts.toString().equalsIgnoreCase(value.toString())) {
                    ar2.add(carts);
                }
            }
        }
        switch (name.toLowerCase()) {
            case "mage":
                heroName = "mage";
                ar3 = Mage.Spcards();
                break;
            case "rogue":
                heroName = "rogue";
                ar3 = Rogue.Spcards();
                break;
            case "warlock":
                heroName = "warlock";
                ar3 = Warlock.Spcards();
                break;
            case "priest":
                heroName = "priest";
                ar3 = Priest.Spcards();
                break;
            case "hunter":
                heroName = "hunter";
                ar3 = Hunter.Spcards();
                break;
        }
        for (Carts carts : ar3) {
            if (clientHandler.getPlayer().getPlayerCarts().contains(carts)) {
                ar2.add(carts);
            }
        }

        for (Carts carts : ar2) {
            ar4.add(Admin.getInstance().getPureViewModelOf(carts.toString()));
            System.out.println(Admin.getInstance().getPureViewModelOf(carts.toString()).toString());
        }

        try {
            outputStream.println(objectMapper.writeValueAsString(ar2));
            outputStream.println(objectMapper.writeValueAsString(ar4));
            outputStream.println(heroName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}