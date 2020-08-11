package Server.Controller.Response;

import Server.Model.CardModelView;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("changecard")
public class ChangeCardResponse implements Response {
    private int i;
    private CardModelView view;

    public ChangeCardResponse(int i, CardModelView view) {
        this.i = i;
        this.view = view;
    }

    public ChangeCardResponse() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public CardModelView getView() {
        return view;
    }

    public void setView(CardModelView view) {
        this.view = view;
    }
}
