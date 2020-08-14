package Client.View.View.Panels.Listeners.ShopListeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.ProperCardsRequest;
import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.View.View.Panels.ShopPanel;
import Client.View.View.Update.Update;

import javax.swing.event.DocumentEvent;
import java.util.ArrayList;

public class ShopDocument implements javax.swing.event.DocumentListener {
    private final ShopPanel s;

    public ShopDocument(ShopPanel s) {
        this.s = s;
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
        s.getSearchField().requestFocus();
        if (s.getSearchField().getText() == null || s.getSearchField().getText().equals("")) {
            return;
        }
        RequestHandler.getInstance().sendRequest(new ProperCardsRequest(s.isBuyActivated() ? 1 : 2));
        ArrayList<CardModelView> ar = Responses.getInstance().getModelviewList();
        s.setCards(new ArrayList<>());
        for (CardModelView cards1 : ar) {
            if (cards1.getName().toLowerCase().contains(s.getSearchField().getText())) {
                s.getCards().add(cards1);
            }
        }
        s.pictures(s.getCards());
        Update.render();
    }
}
