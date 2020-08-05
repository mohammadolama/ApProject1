package Client.Controller.Requests;

import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.View.View.Panels.Constants;
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
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {
        try {
            String s = objectMapper.writeValueAsString(this);
            outputStream.println(s);
            String res = inputStream.nextLine();
            CardModelView pr = objectMapper.readValue(res, CardModelView.class);
            pr.setImage(Constants.cardPics.get(pr.getName().toLowerCase()));
            Responses.getInstance().setCardModelView(pr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
