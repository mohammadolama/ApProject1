package Client.View.View.Panels.Listeners.ShopListeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.ProperCardsRequest;
import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.View.View.Panels.ShopPanel;
import Client.View.View.Update.Update;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public class ShopChange implements ChangeListener {
    private final ShopPanel s;

    public ShopChange(ShopPanel s) {
        this.s = s;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int value = s.getManaFilter().getValue();
        if (value == 11) {
            RequestHandler.getInstance().sendRequest(new ProperCardsRequest(s.isBuyActivated() ? 1 : 2));
            s.setCards(Responses.getInstance().getModelviewList());
        } else {
            s.getImages().clear();
            RequestHandler.getInstance().sendRequest(new ProperCardsRequest(s.isBuyActivated() ? 1 : 2));
            ArrayList<CardModelView> ar = Responses.getInstance().getModelviewList();
            s.setCards(new ArrayList<>());
            for (CardModelView cards1 : ar) {
                if (cards1.getManaCost() == value) {
                    s.getCards().add(cards1);
                }
            }
        }
        s.pictures(s.getCards());
        Update.render();
    }
}
