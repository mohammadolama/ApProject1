package Client.Controller.Requests;

import Client.Model.CardModelView;
import Client.View.View.Panels.CollectionDrawingPanel;
import Client.View.View.Panels.CollectionPanel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("updatedrawingpanel")
public class UpdateDrawingPanelRequest implements Request {
    private String value;
    private ArrayList<CardModelView> list;

    public UpdateDrawingPanelRequest(String value) {
        this.value = value;
    }

    public UpdateDrawingPanelRequest() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArrayList<CardModelView> getList() {
        return list;
    }

    public void setList(ArrayList<CardModelView> list) {
        this.list = list;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {
        try {
            CollectionDrawingPanel.getInstance().setSpecialSelected(false);
            CollectionPanel.getInstance().getChangeButton().setEnabled(false);
            outputStream.println(objectMapper.writeValueAsString(this));
            String res = inputStream.nextLine();
            list = objectMapper.readValue(res, new TypeReference<ArrayList<CardModelView>>() {
            });
            if (value.equalsIgnoreCase("special")) {
                CollectionDrawingPanel.getInstance().setSpecialSelected(true);
            }
            CollectionDrawingPanel.getInstance().updateContent(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
