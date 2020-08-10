package Client.Controller.Requests;

import Client.Model.CardModelView;
import Client.Model.InfoPassive;
import Client.View.View.Panels.AlternativePanel;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Update.Update;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@JsonTypeName("threecard")
public class ThreeCardRequest implements Request {
    private ArrayList<CardModelView> list;
    @JsonIgnore
    private InfoPassive infoPassive;

    public ThreeCardRequest(InfoPassive infoPassive) {
        this.infoPassive = infoPassive;
    }

    public ThreeCardRequest() {
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

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            String res = inputStream.nextLine();
            list = objectMapper.readValue(res, new TypeReference<ArrayList<CardModelView>>() {
            });

            AlternativePanel th = new AlternativePanel(false);
            th.setModel1(list.get(0));
            th.setModel2(list.get(1));
            th.setModel3(list.get(2));
            th.setInfoPassive(infoPassive);
            th.setEnabled(true);
            MyFrame.getPanel().add("three", th);
            MyFrame.getInstance().changePanel("three");
            Update.render();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
