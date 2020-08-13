package Client.Controller.Response;

import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.View.View.Panels.Constants;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("modelview")
public class ModelViewResponse implements Response {

    private CardModelView view;

    public ModelViewResponse() {
    }

    public CardModelView getView() {
        return view;
    }

    public void setView(CardModelView view) {
        this.view = view;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        Responses.getInstance().setCardModelView(view);
        synchronized (object) {
            object.notify();
        }
    }
}
