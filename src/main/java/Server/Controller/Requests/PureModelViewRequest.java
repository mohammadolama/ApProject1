package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Model.CardModelView;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("puremodelview")
public class PureModelViewRequest implements Request {

    private CardModelView view;
    private String name;

    public PureModelViewRequest(String name) {
        this.name = name;
    }

    public PureModelViewRequest() {
    }

    public CardModelView getView() {
        return view;
    }

    public void setView(CardModelView view) {
        this.view = view;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper) {
        CardModelView cardModelView = Admin.getInstance().getPureViewModelOf(name);
        try {
            String s = objectMapper.writeValueAsString(cardModelView);
            outputStream.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
