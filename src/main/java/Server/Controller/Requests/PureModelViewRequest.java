package Server.Controller.Requests;

import Server.Model.CardModelView;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("puremodelview")
public class PureModelViewRequest implements Request {

    private CardModelView view;
    private String name;

    public PureModelViewRequest(CardModelView view, String name) {
        this.view = view;
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
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
