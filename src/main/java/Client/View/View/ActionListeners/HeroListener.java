package Client.View.View.ActionListeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.PlayHeroPowerRequest;
import Client.View.View.Panels.BoardPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HeroListener implements ActionListener {
    private JButton button;
    private BoardPanel b;

    public HeroListener(JButton button, BoardPanel b) {
        this.button = button;
        this.b = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RequestHandler.getInstance().sendRequest(new PlayHeroPowerRequest(Integer.parseInt(button.getName())));
        b.removeButton();
        b.setPlayedCardSelected(false);
        b.setPlayedCardSelectedName(null);
        b.setPlayedIndex(-3);
    }
}
