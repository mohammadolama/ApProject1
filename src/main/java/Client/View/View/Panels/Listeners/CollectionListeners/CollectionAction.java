package Client.View.View.Panels.Listeners.CollectionListeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.*;
import Client.Controller.Responses;
import Client.View.View.Panels.Col_Change;
import Client.View.View.Panels.CollectionDrawingPanel;
import Client.View.View.Panels.CollectionPanel;
import Client.View.View.Panels.MyFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CollectionAction implements ActionListener {
    private final CollectionPanel c;

    public CollectionAction(CollectionPanel c) {
        this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        c.getChangeButton().setEnabled(false);
        JButton src = (JButton) e.getSource();
        if (src == c.getBackButton()) {
            c.getAllCards().doClick();
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Back Button"));
            RequestHandler.getInstance().sendRequest(new LogRequest("Navigate : Main Menu"));
            RequestHandler.getInstance().sendRequest(new SaveRequest());
            RequestHandler.getInstance().sendRequest(new VisiblePanelRequest("menu"));
        } else if (src == c.getExitButton()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Exit Button"));
            RequestHandler.getInstance().sendRequest(new ExitRequest());
        } else if (src == c.getNewDeck()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : New_Deck Button"));
            RequestHandler.getInstance().sendRequest(new CreateNewDeckRequest());
        } else if (src == c.getAllCards()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : AllCards Button"));
            RequestHandler.getInstance().sendRequest(new UpdateDrawingPanelRequest("all"));
        } else if (src == c.getLockedCards()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : LockedCards Button"));
            RequestHandler.getInstance().sendRequest(new UpdateDrawingPanelRequest("locked"));
        } else if (src == c.getUnlockedCards()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : UnlockedCards Button"));
            RequestHandler.getInstance().sendRequest(new UpdateDrawingPanelRequest("unlocked"));
        } else if (src == c.getNeutralCards()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : NeutralCards Button"));
            RequestHandler.getInstance().sendRequest(new UpdateDrawingPanelRequest("neutral"));
        } else if (src == c.getSpecialCards()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : SpecialCards Button"));
            RequestHandler.getInstance().sendRequest(new UpdateDrawingPanelRequest("special"));
        } else if (src == c.getChangeButton()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Change_Deck Button"));
            c.getAllCards().doClick();
            Col_Change.getInstance().setCreateMode(false);
            Col_Change.getInstance().getDeckName().setText(c.getSelectedDeck().getName());
            Col_Change.getInstance().setPreviousName(c.getSelectedDeck().getName());
            Col_Change.getInstance().updateSelectedDeck(c.getSelectedDeck().getHero());
            Col_Change.getInstance().setHeroSelected(true);
            Col_Change.getInstance().setSelectedCards(new ArrayList<>(c.getSelectedDeck().getList()));
            Col_Change.getInstance().setSelectedDeck(c.getSelectedDeck());
            Col_Change.getInstance().setCreateMode(false);
            MyFrame.getInstance().changePanel("col");
        } else if (src == c.getSelectButton()) {
            if (c.getSelectedDeck() != null) {
                RequestHandler.getInstance().sendRequest(new SelectDeckRequest(c.getSelectedDeck()));
                RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Select Button"));
                RequestHandler.getInstance().sendRequest(new LogRequest(String.format("Deck : choose \"%s\" as selected deck.", c.getSelectedDeck().getName())));
                JOptionPane.showMessageDialog(MyFrame.getInstance(), String.format("%s is your selected deck now.", c.getSelectedDeck().getName()));
            }
        } else {
            c.getChangeButton().setEnabled(true);
            for (JButton button : c.getButtons()) {
                CollectionDrawingPanel.getInstance().setSpecialSelected(false);
                if (src.getName().equalsIgnoreCase(button.getName())) {
                    RequestHandler.getInstance().sendRequest(new DeckModelRequest(button.getName()));
                    c.setSelectedDeck(Responses.getInstance().getDeckModel());
                    RequestHandler.getInstance().sendRequest(new UpdateDrawingPanelRequest(button.getName()));
                    c.getChangeButton().setEnabled(true);
                }
            }
        }


    }
}
