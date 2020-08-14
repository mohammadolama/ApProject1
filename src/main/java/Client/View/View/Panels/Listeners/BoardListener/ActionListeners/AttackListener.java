package Client.View.View.Panels.Listeners.BoardListener.ActionListeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.AttackRequest;
import Client.View.View.Panels.BoardPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttackListener implements ActionListener {
    private JButton button;
    private BoardPanel b;

    public AttackListener(JButton button, BoardPanel b) {
        this.button = button;
        this.b = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RequestHandler.getInstance().sendRequest(new AttackRequest(b.getPlayedIndex(), Integer.parseInt(button.getName()), b));
        b.removeButton();
        b.setPlayedCardSelected(false);
        b.setPlayedCardSelectedName(null);
        b.setPlayedIndex(-3);
    }
}
