package Client.View.View.Panels.Listeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.*;
import Client.View.View.Panels.SettingPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingAction implements ActionListener {
    private final SettingPanel s;

    public SettingAction(SettingPanel s) {
        this.s = s;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sr = (JButton) e.getSource();
        if (sr == s.getDecreaseSound()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Volume_Down Button"));
            RequestHandler.getInstance().sendRequest(new SoundManagmentRequest(-2));
        } else if (sr == s.getIncreaseSound()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Volume_Up Button"));
            RequestHandler.getInstance().sendRequest(new SoundManagmentRequest(-1));
        } else if (sr == s.getMuteSound()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Mute Button"));
            RequestHandler.getInstance().sendRequest(new SoundManagmentRequest(s.getI()));
            s.setI(s.getI() + 1);
        } else if (sr == s.getBack()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Back Button"));
            RequestHandler.getInstance().sendRequest(new LogRequest("Navigate : Main Menu"));
            RequestHandler.getInstance().sendRequest(new SaveRequest());
            RequestHandler.getInstance().sendRequest(new VisiblePanelRequest("menu"));
        } else if (sr == s.getExit()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Exit Button"));
            RequestHandler.getInstance().sendRequest(new ExitRequest());
        }
    }
}
