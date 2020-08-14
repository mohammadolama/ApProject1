package Client.View.View.Panels.Listeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.FirstHeroRequest;
import Client.View.View.Panels.FirstHeroSelector;
import Client.View.View.Panels.MyFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstHeroAction implements ActionListener {
    private final FirstHeroSelector f;

    public FirstHeroAction(FirstHeroSelector f) {
        this.f = f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src == f.getMage()) {
            JOptionPane.showMessageDialog(MyFrame.getInstance(), "Mage selected");
            f.setHero("mage");
        } else if (src == f.getRogue()) {
            JOptionPane.showMessageDialog(MyFrame.getInstance(), "Rogue selected");
            f.setHero("rogue");
        } else if (src == f.getWarlock()) {
            JOptionPane.showMessageDialog(MyFrame.getInstance(), "Warlock selected");
            f.setHero("warlock");
        } else if (src == f.getChoose()) {
            if (f.getHero() == null || f.getHero().equals(""))
                return;
            RequestHandler.getInstance().sendRequest(new FirstHeroRequest(f.getHero()));
        }
    }
}
