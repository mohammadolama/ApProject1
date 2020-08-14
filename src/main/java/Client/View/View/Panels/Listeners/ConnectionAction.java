package Client.View.View.Panels.Listeners;

import Client.Controller.RequestHandler;
import Client.View.View.Panels.ConnectionPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectionAction implements ActionListener {
    private final ConnectionPanel c;

    public ConnectionAction(ConnectionPanel c) {
        this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == c.getConnect()) {
            if (c.getIpField().getText().equals("") || c.getPortField().getText().equals("")) {
                return;
            }
            RequestHandler.Connect(c.getIpField().getText(), Integer.parseInt(c.getPortField().getText()), c);
        } else if (e.getSource() == c.getExit()) {
            System.exit(0);
        }
    }
}
