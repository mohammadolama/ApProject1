package Server.Model;

import Server.Controller.MainLogic.ClientHandler;

public class Requirements {
    private ClientHandler clientHandler;
    private InfoPassive infoPassive;
    private String card1;
    private String card2;
    private String card3;

    public Requirements(ClientHandler clientHandler, InfoPassive infoPassive, String card1, String card2, String card3) {
        this.clientHandler = clientHandler;
        this.infoPassive = infoPassive;
        this.card1 = card1;
        this.card2 = card2;
        this.card3 = card3;
    }

    public ClientHandler getClientHandler() {
        return clientHandler;
    }

    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
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
}
