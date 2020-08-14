package Client.View.View.Panels.Listeners.MenuListeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.*;
import Client.View.View.Panels.MenuPanel;
import Client.View.View.Update.Update;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAction implements ActionListener {
    private final MenuPanel m;

    public MenuAction(MenuPanel m) {
        this.m = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button == m.getLogout()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Logout Button"));
            RequestHandler.getInstance().sendRequest(new LogoutRequest());
        } else if (button == m.getCheat()) {
            String st = JOptionPane.showInputDialog(this, "Don't Cheat!!");
            if (st.equalsIgnoreCase("hesoyam")) {
                RequestHandler.getInstance().sendRequest(new UnlockHeroRequest());
            }
        } else if (button == m.getExit()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Exit Button"));
            RequestHandler.getInstance().sendRequest(new ExitRequest());
        } else if (button == m.getPlay()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Play Button"));
            RequestHandler.getInstance().sendRequest(new WantToPlayRequest());
        } else if (button == m.getStatus()) {
            Update.refresh();
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Status Button"));
            RequestHandler.getInstance().sendRequest(new LogRequest("Navigate : Status"));
            RequestHandler.getInstance().sendRequest(new VisiblePanelRequest("status"));
        } else if (button == m.getSetting()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Setting Button"));
            RequestHandler.getInstance().sendRequest(new LogRequest("Navigate : Setting"));
            RequestHandler.getInstance().sendRequest(new VisiblePanelRequest("setting"));
        } else if (button == m.getStore()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Store Button"));
            RequestHandler.getInstance().sendRequest(new LogRequest("Navigate : Store"));
            RequestHandler.getInstance().sendRequest(new VisiblePanelRequest("shop"));
        } else if (button == m.getCollection()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Collection Button"));
            RequestHandler.getInstance().sendRequest(new LogRequest("Navigate : Collection"));
            RequestHandler.getInstance().sendRequest(new VisiblePanelRequest("collection"));
        }
    }
}
