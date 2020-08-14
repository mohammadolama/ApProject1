package Client.View.View.Panels.Listeners.Col_ChangeListeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.LogRequest;
import Client.Controller.Requests.RemoveDeckRequest;
import Client.Controller.Requests.RenderRequest;
import Client.View.View.Panels.Col_Change;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Update.Update;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Col_ChangeAction implements ActionListener {
    private final Col_Change c;

    public Col_ChangeAction(Col_Change c) {
        this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src == c.getBackButton()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : back Button"));
            RequestHandler.getInstance().sendRequest(new LogRequest("Cancle the process of creating new/changing  deck."));
            RequestHandler.getInstance().sendRequest(new RenderRequest());
            c.clear();
            Update.refresh();
            MyFrame.getInstance().changePanel("collection");
        } else if (src == c.getAddButton()) {
            c.addSelectedCard(c.getName1().toLowerCase());
            RequestHandler.getInstance().sendRequest(new LogRequest(String.format("Add : %s is added to deck.", c.getName1())));
            c.setClicked(false);
            Update.render();
        } else if (src == c.getRemoveButton()) {
            c.removeSelectedCard(c.getName1().toLowerCase());
            RequestHandler.getInstance().sendRequest(new LogRequest(String.format("Remove : %s is removed from deck.", c.getName1())));
            c.setClicked(false);
            Update.render();
        } else if (src == c.getDeckRemoveButton()) {
            if (c.getSelectedDeck() != null) {
                RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Deck_Remove Button"));
                RequestHandler.getInstance().sendRequest(new RemoveDeckRequest(c.getSelectedDeck()));
                c.clear();
            }
        } else if (src == c.getCreateButton()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Create_Deck Button"));
            c.createDeck();
            Update.refresh();
        } else if (src == c.getChangeButton()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Change_Deck Button"));
            c.changeDeck();
            Update.refresh();
        } else {
            c.getCreateButton().setEnabled(true);
            c.setHeroSelected(true);
            for (JButton button : c.getButtons()) {
                if (src == button) {
                    c.setSelectedCards(new ArrayList<>());
                    c.setSelectedBuferredImages(new ArrayList<>());
                    c.updateSelectedDeck(button.getName());
                    Update.render();
                }
            }
        }
        Update.render();
    }
}
