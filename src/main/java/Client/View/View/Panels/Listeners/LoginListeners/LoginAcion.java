package Client.View.View.Panels.Listeners.LoginListeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.LoginRequest;
import Client.Controller.Requests.SignupRequest;
import Client.View.View.Panels.LoginPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAcion implements ActionListener {
    private final LoginPanel l;

    public LoginAcion(LoginPanel l) {
        this.l = l;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == l.getEnter()) {
            if (l.getUserField().getText().equals("") || l.getPassField().getText().equals("")) {
                return;
            }
            RequestHandler.getInstance().sendRequest(new LoginRequest(l.getUserField().getText(), l.getPassField().getText()));
        } else if (e.getSource() == l.getCreateAccount()) {
            if (l.getUserField().getText().equals("") || l.getPassField().getText().equals("")) {
                return;
            }
            RequestHandler.getInstance().sendRequest(new SignupRequest(l.getUserField().getText(), l.getPassField().getText()));
        } else if (e.getSource() == l.getExit()) {
            System.exit(0);
        }
    }

}
