package Server.Controller.Response;

import Server.Model.CardModelView;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("aylar")
public class NotifyAylar implements Response {

    private CardModelView card1;
    private CardModelView card2;
    private CardModelView card3;

    public NotifyAylar(CardModelView card1, CardModelView card2, CardModelView card3) {
        this.card1 = card1;
        this.card2 = card2;
        this.card3 = card3;
    }

    public NotifyAylar() {
    }

    public CardModelView getCard1() {
        return card1;
    }

    public void setCard1(CardModelView card1) {
        this.card1 = card1;
    }

    public CardModelView getCard2() {
        return card2;
    }

    public void setCard2(CardModelView card2) {
        this.card2 = card2;
    }

    public CardModelView getCard3() {
        return card3;
    }

    public void setCard3(CardModelView card3) {
        this.card3 = card3;
    }
}
