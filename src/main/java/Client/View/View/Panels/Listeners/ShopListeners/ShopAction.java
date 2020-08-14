package Client.View.View.Panels.Listeners.ShopListeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.*;
import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.Model.Enums.Type;
import Client.View.View.Panels.ShopPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShopAction implements ActionListener {
    private final ShopPanel s;

    public ShopAction(ShopPanel s) {
        this.s = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton src = (JButton) e.getSource();
        if (src == s.getBackButton()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Exit Button"));
            RequestHandler.getInstance().sendRequest(new LogRequest("Navigate : Main Menu"));
            RequestHandler.getInstance().sendRequest(new SaveRequest());
            RequestHandler.getInstance().sendRequest(new VisiblePanelRequest("menu"));
        } else if (src == s.getExit()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Exit Button"));
            RequestHandler.getInstance().sendRequest(new ExitRequest());
        } else if (src == s.getBuyActivatedButton()) {
            s.getBuyActivatedButton().setBackground(Color.yellow);
            s.getSellActivatedButton().setBackground(Color.WHITE);
            s.setBuyActivated(true);
            s.BuySellChanger();
        } else if (src == s.getSellActivatedButton()) {
            s.getBuyActivatedButton().setBackground(Color.white);
            s.getSellActivatedButton().setBackground(Color.yellow);
            s.setBuyActivated(false);
            s.BuySellChanger();
        } else if (src == s.getBuyButton()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Buy Button"));
            RequestHandler.getInstance().sendRequest(new BuyCardRequest(s.getName1()));
        } else if (src == s.getSellButton()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Sell Button"));
            RequestHandler.getInstance().sendRequest(new SellCardRequest(s.getName1()));
        } else {
            s.getImages().clear();
            RequestHandler.getInstance().sendRequest(new ProperCardsRequest(s.isBuyActivated() ? 1 : 2));
            ArrayList<CardModelView> ar = Responses.getInstance().getModelviewList();
            s.setCards(new ArrayList<>());
            for (CardModelView cards1 : ar) {
                if (src == s.getAllbutton()) {
                    s.getCards().add(cards1);
                } else if ((src == s.getMinionButton() && cards1.getType().equals(Type.Minion)) ||
                        (src == s.getSpellButton() && cards1.getType().equals(Type.Spell)) ||
                        (src == s.getWeaponButton() && cards1.getType().equals(Type.Weapon))) {
                    s.getCards().add(cards1);
                }
            }
            s.pictures(s.getCards());
        }
    }

}
