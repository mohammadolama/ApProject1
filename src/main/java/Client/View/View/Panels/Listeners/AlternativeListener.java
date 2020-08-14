package Client.View.View.Panels.Listeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.AylarActionRequest;
import Client.Controller.Requests.ChangeCardRequest;
import Client.Controller.Requests.CreateGameRequest;
import Client.Controller.Requests.FinishGameRequest;
import Client.View.View.Panels.AlternativePanel;
import Client.View.View.Update.Update;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlternativeListener implements ActionListener {

    private AlternativePanel a;

    public AlternativeListener(AlternativePanel a) {
        this.a = a;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src.equals(a.getOk())) {
            if (a.isWinningMode()) {
                RequestHandler.getInstance().sendRequest(new FinishGameRequest());
            } else {
                if (a.getMode() == 1) {
                    RequestHandler.getInstance().sendRequest(new CreateGameRequest(a.getInfoPassive(), a.getModel1().getName(), a.getModel2().getName(), a.getModel3().getName(), 1));
                } else if (a.getMode() == 2) {
                    RequestHandler.getInstance().sendRequest(new CreateGameRequest(a.getInfoPassive(), a.getModel1().getName(), a.getModel2().getName(), a.getModel3().getName(), 3));
                }
            }
        } else if (src.equals(a.getCard1())) {
            button1Action();
        } else if (src.equals(a.getCard2())) {
            button2Action();
        } else if (src.equals(a.getCard3())) {
            button3Action();
        }
    }

    private void button1Action() {
        if (!a.isDiscoverMode()) {
            RequestHandler.getInstance().sendRequest(new ChangeCardRequest(1, a, a.getModel1().getName(), a.getModel2().getName(), a.getModel3().getName()));
            Update.render();
            a.getCard1().setEnabled(false);
        } else {
            RequestHandler.getInstance().sendRequest(new AylarActionRequest(a.getCard1().getName()));
        }
    }

    private void button2Action() {
        if (!a.isDiscoverMode()) {
            RequestHandler.getInstance().sendRequest(new ChangeCardRequest(2, a, a.getModel1().getName(), a.getModel2().getName(), a.getModel3().getName()));
            Update.render();
            a.getCard2().setEnabled(false);
        } else {
            RequestHandler.getInstance().sendRequest(new AylarActionRequest(a.getCard2().getName()));
        }
    }

    private void button3Action() {
        if (!a.isDiscoverMode()) {
            RequestHandler.getInstance().sendRequest(new ChangeCardRequest(3, a, a.getModel1().getName(), a.getModel2().getName(), a.getModel3().getName()));
            Update.render();
            a.getCard3().setEnabled(false);
        } else {
            RequestHandler.getInstance().sendRequest(new AylarActionRequest(a.getCard3().getName()));
        }
    }
}
