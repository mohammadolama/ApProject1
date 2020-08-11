package Server.Controller.Requests;

import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.MainLogic.DeckLogic;
import Server.Controller.Manager.Managers;
import Server.Controller.Manager.NormalModeManager;
import Server.Controller.Response.CreateNormalResponse;
import Server.Model.InfoPassive;
import Server.Model.Player;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
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
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        Player player = clientHandler.getPlayer();
        Managers gameManager = new NormalModeManager(player, infoPassive, DeckLogic.UpdateDeck(player.getSelectedDeck().getDeck()), card1, card2, card3, clientHandler);
        clientHandler.setGameManager(gameManager);
        try {
            outputStream.println(objectMapper.writeValueAsString(new CreateNormalResponse()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
