package Client.Controller.Response;

import Client.Model.CardModelView;
import Client.View.View.Panels.CollectionDrawingPanel;
import Client.View.View.Panels.CollectionPanel;
import Client.View.View.Update.Update;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("drawing")
public class UpdateDrawingPanelResponse implements Response {
    private ArrayList<CardModelView> list;
    private String value;

    public UpdateDrawingPanelResponse() {
    }

    public ArrayList<CardModelView> getList() {
        return list;
    }

    public void setList(ArrayList<CardModelView> list) {
        this.list = list;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        CollectionDrawingPanel.getInstance().setSpecialSelected(false);
        if (value.equalsIgnoreCase("special")) {
            CollectionDrawingPanel.getInstance().setSpecialSelected(true);
        }
        CollectionDrawingPanel.getInstance().updateContent(list);
        Update.render();
    }
}
