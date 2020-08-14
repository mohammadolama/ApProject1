package Client.View.View.Panels.Listeners.CollectionListeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.ProperCardsRequest;
import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.View.View.Panels.CollectionDrawingPanel;
import Client.View.View.Panels.CollectionPanel;
import Client.View.View.Update.Update;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;

public class CollectionDocument implements DocumentListener {
    private final CollectionPanel c;

    public CollectionDocument(CollectionPanel c) {
        this.c = c;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        warn();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        warn();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        warn();
    }

    void warn() {
        if (c.getSearchField().getText() == null || c.getSearchField().getText().equals("")) {
            return;
        }
        RequestHandler.getInstance().sendRequest(new ProperCardsRequest(3));
        ArrayList<CardModelView> ar = Responses.getInstance().getModelviewList();
        c.setCards(new ArrayList<>());
        for (CardModelView cards1 : ar) {
            if (cards1.getName().toLowerCase().contains(c.getSearchField().getText())) {
                c.getCards().add(cards1);
            }
        }
        CollectionDrawingPanel.getInstance().updateContent(c.getCards());
        Update.render();
    }
}
