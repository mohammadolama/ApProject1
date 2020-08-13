package Client.Controller.Response;

import Client.Model.CardModelView;
import Client.Model.InfoPassive;
import Client.View.View.Panels.AlternativePanel;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Update.Update;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("three")
public class ThreeCardResponse implements Response {
    private ArrayList<CardModelView> list;
    private InfoPassive infoPassive;
    private int i;


    public ThreeCardResponse() {
    }

    public ArrayList<CardModelView> getList() {
        return list;
    }

    public void setList(ArrayList<CardModelView> list) {
        this.list = list;
    }

    public InfoPassive getInfoPassive() {
        return infoPassive;
    }

    public void setInfoPassive(InfoPassive infoPassive) {
        this.infoPassive = infoPassive;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        AlternativePanel th = new AlternativePanel(false, i);
        th.setModel1(list.get(0));
        th.setModel2(list.get(1));
        th.setModel3(list.get(2));
        th.setInfoPassive(infoPassive);
        th.setEnabled(true);
        MyFrame.getInstance().setAlternativePanel(th);
        MyFrame.getPanel().add("three", th);
        MyFrame.getInstance().changePanel("three");
        Update.render();
    }
}
