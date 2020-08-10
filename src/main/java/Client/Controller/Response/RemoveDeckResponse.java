package Client.Controller.Response;

import Client.View.View.Panels.Col_Change;
import Client.View.View.Panels.CollectionPanel;
import Client.View.View.Update.Update;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("remove")
public class RemoveDeckResponse implements Response {
    private String res;

    public RemoveDeckResponse() {
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        if (res.equalsIgnoreCase("ok1")) {
            CollectionPanel.getInstance().setSelectedDeck(null);
        }
        Update.render();
        Col_Change.getInstance().getBackButton().doClick();
    }
}
