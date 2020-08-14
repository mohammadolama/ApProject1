package Client.View.View.Panels.Listeners.CollectionDrawingListeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.BuyCardRequest;
import Client.Controller.Requests.ProperCardsRequest;
import Client.Controller.Responses;
import Client.View.View.Panels.CollectionDrawingPanel;
import Client.View.View.Update.Update;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CDAction implements ActionListener {
    private final CollectionDrawingPanel c;

    public CDAction(CollectionDrawingPanel c) {
        this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src == c.getBuyButton()) {
            RequestHandler.getInstance().sendRequest(new BuyCardRequest(c.getName1()));
            RequestHandler.getInstance().sendRequest(new ProperCardsRequest(3));
            c.setCards(Responses.getInstance().getModelviewList());
            c.pictures(c.getCards());
            c.getImages().clear();
            c.update();
            c.setClicked(false);
            Update.render();
        }
    }

}
