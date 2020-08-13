package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.MainLogic.DeckLogic;
import Server.Controller.MainLogic.Server;
import Server.Controller.Manager.Managers;
import Server.Controller.Manager.NormalModeManager;
import Server.Controller.Response.CreateNormalResponse;
import Server.Model.InfoPassive;
import Server.Model.Player;
import Server.Model.Requirements;
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
    private int i;

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

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        if (i == 1) {
            Player player = clientHandler.getPlayer();
            Managers gameManager = new NormalModeManager(player, infoPassive, DeckLogic.UpdateDeck(player.getSelectedDeck().getDeck()), card1, card2, card3, clientHandler);
            clientHandler.setGameManager(gameManager);
            try {
                outputStream.println(objectMapper.writeValueAsString(new CreateNormalResponse()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (i == 2) {
            Admin.getInstance().addDeckReader(new Requirements(clientHandler, infoPassive, card1, card2, card3));
        } else {
            Admin.getInstance().addMultiplayer(new Requirements(clientHandler, infoPassive, card1, card2, card3));
        }
    }
}
