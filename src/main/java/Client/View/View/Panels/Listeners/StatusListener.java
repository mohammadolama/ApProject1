package Client.View.View.Panels.Listeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.*;
import Client.View.View.Panels.StatusPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusListener implements ActionListener {
    private StatusPanel s;

    public StatusListener(StatusPanel s) {
        this.s = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src == s.getBack()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Exit Button"));
            RequestHandler.getInstance().sendRequest(new LogRequest("Navigate : Main Menu"));
            RequestHandler.getInstance().sendRequest(new VisiblePanelRequest("menu"));
            s.setAr1(null);
            s.setSelectedDeck(null);
        } else if (src == s.getDeleteAccount()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : DeleteAccount Button"));
            RequestHandler.getInstance().sendRequest(new DeleteAccountRequest());
        } else if (src == s.getExit()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Exit Button"));
            RequestHandler.getInstance().sendRequest(new ExitRequest());
        } else {
            for (JButton button : s.getButtons()) {
                if (src.getName().equalsIgnoreCase(button.getName())) {
                    s.updateSelectedDeck(button.getName());
                    break;
                }
            }
        }
        RequestHandler.getInstance().sendRequest(new RenderRequest());
    }
}
