package Client.View.View.ActionListeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.EndTurnRequest;
import Client.Controller.Requests.ExitRequest;
import Client.Controller.Requests.LogRequest;
import Client.Controller.Requests.VisiblePanelRequest;
import Client.View.View.Panels.BoardPanel;
import Client.View.View.Update.Update;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardActionListener implements ActionListener {
    private BoardPanel b;

    public BoardActionListener(BoardPanel b) {
        this.b = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src == b.getExit()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Exit Button"));
            RequestHandler.getInstance().sendRequest(new ExitRequest());
        } else if (src == b.getBack()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Back Button"));
            RequestHandler.getInstance().sendRequest(new VisiblePanelRequest("menu"));
        } else if (src == b.getNextTurnButton()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : NextTurn Button"));
            b.clear();
            b.change();
            boolean flag = b.getRes().board.getUpDeckSize() > 0;
            RequestHandler.getInstance().sendRequest(new EndTurnRequest());
            if (flag) {
                b.getConfig().setToMiddle(true);
                b.getConfig().setBlur(0);
                b.getConfig().setAnimated(true);
                if (!b.isPracticeMode() || !b.isP2Turn()) {
                    b.getToMiddleTimer().start();
                }
            }
//            myTimer.flag1 = false;
            b.setPlayedCardSelected(false);
            b.setPlayedCardSelectedName(null);
            b.setHandCardSelectedName(null);
            b.setCardSelected(false);
            b.removeButton();
//            Update.render();
        }

    }
}
