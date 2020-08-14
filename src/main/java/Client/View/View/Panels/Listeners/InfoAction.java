package Client.View.View.Panels.Listeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.CreateGameRequest;
import Client.Controller.Requests.LogRequest;
import Client.Controller.Requests.ThreeCardRequest;
import Client.Controller.Requests.VisiblePanelRequest;
import Client.View.View.Panels.InfoPassivePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoAction implements ActionListener {
    private final InfoPassivePanel i;

    public InfoAction(InfoPassivePanel i) {
        this.i = i;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src == i.getNormal()) {
            if (i.getInfoPassive() != null) {
                RequestHandler.getInstance().sendRequest(new ThreeCardRequest(i.getInfoPassive(), 1));
                RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Normal game"));
            }
        } else if (src == i.getDeckReader()) {
            if (i.getInfoPassive() != null) {
                RequestHandler.getInstance().sendRequest(new CreateGameRequest(i.getInfoPassive(), null, null, null, 2));
                RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : DeckReader Game"));
            }
        } else if (src == i.getOnline()) {
            if (i.getInfoPassive() != null) {
                RequestHandler.getInstance().sendRequest(new ThreeCardRequest(i.getInfoPassive(), 2));
                RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : MultiPlayer game"));
            }
        }
        i.getPassive1().setBackground(Color.white);
        i.getPassive2().setBackground(Color.white);
        i.getPassive3().setBackground(Color.white);
        if (src == i.getPassive1() || src == i.getPassive2() || src == i.getPassive3()) {
            src.setBackground(Color.ORANGE);
            i.setInfoPassive(i.getPassives().get(Integer.parseInt(src.getName())));
            return;
        }
        if (src == i.getBackButton()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Exit Button"));
            RequestHandler.getInstance().sendRequest(new LogRequest("Navigate : Main Menu"));
            RequestHandler.getInstance().sendRequest(new VisiblePanelRequest("menu"));
        }
    }
}
