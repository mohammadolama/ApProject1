package Client.Controller.Requests;

import Client.Model.InfoPassive;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("creategame")
public class CreateGameRequest implements Request {

    private InfoPassive infoPassive;
    private String card1;
    private String card2;
    private String card3;

    public CreateGameRequest(InfoPassive infoPassive, String card1, String card2, String card3) {
        this.infoPassive = infoPassive;
        this.card1 = card1;
        this.card2 = card2;
        this.card3 = card3;
    }

    public CreateGameRequest() {
    }

    public InfoPassive getInfoPassive() {
        return infoPassive;
    }

    public void setInfoPassive(InfoPassive infoPassive) {
        this.infoPassive = infoPassive;
    }

    public String getCard1() {
        return card1;
    }

    public void setCard1(String card1) {
        this.card1 = card1;
    }

    public String getCard2() {
        return card2;
    }

    public void setCard2(String card2) {
        this.card2 = card2;
    }

    public String getCard3() {
        return card3;
    }

    public void setCard3(String card3) {
        this.card3 = card3;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
