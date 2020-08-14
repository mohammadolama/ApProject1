package Client.View.View.Panels.Listeners.CollectionListeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.ProperCardsRequest;
import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.View.View.Panels.CollectionDrawingPanel;
import Client.View.View.Panels.CollectionPanel;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public class CollectionChange implements ChangeListener {
    private final CollectionPanel c;

    public CollectionChange(CollectionPanel c) {
        this.c = c;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int value = c.getManaFilter().getValue();
        if (value == 11) {
            RequestHandler.getInstance().sendRequest(new ProperCardsRequest(3));
            c.setCards(Responses.getInstance().getModelviewList());
        } else {
            RequestHandler.getInstance().sendRequest(new ProperCardsRequest(3));
            ArrayList<CardModelView> ar = Responses.getInstance().getModelviewList();
            c.setCards(new ArrayList<>());
            for (CardModelView cards1 : ar) {
                if (cards1.getManaCost() == value) {
                    c.getCards().add(cards1);
                }
            }
        }
        CollectionDrawingPanel.getInstance().updateContent(c.getCards());
    }

}
