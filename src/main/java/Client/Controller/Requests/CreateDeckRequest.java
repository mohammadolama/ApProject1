package Client.Controller.Requests;

import Client.Model.Enums.Carts;
import Client.View.View.Panels.Col_Change;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Update.Update;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("createdeck")
public class CreateDeckRequest implements Request {

    private String name;
    private ArrayList<Carts> list;
    private String heroName;

    public CreateDeckRequest(String name, ArrayList<Carts> list, String heroName) {
        this.name = name;
        this.list = list;
        this.heroName = heroName;
    }

    public CreateDeckRequest() {
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

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        if (name == null || name.equals("")) {
            JOptionPane.showMessageDialog(MyFrame.getInstance(), "Choose a name for your deck");
            Update.render();
        } else {
            try {
                outputStream.println(objectMapper.writeValueAsString(this));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
