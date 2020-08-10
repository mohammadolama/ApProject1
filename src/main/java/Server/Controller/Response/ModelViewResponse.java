package Server.Controller.Response;

import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Model.CardModelView;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("modelview")
public class ModelViewResponse implements Response {
    private CardModelView view;

    public ModelViewResponse(CardModelView view) {
        this.view = view;
    }

    public ModelViewResponse() {
    }

    public CardModelView getView() {
        return view;
    }

    public void setView(CardModelView view) {
        this.view = view;
    }

}
