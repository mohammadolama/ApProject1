package Client.View.View.ActionListeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.*;
import Client.View.View.Panels.BoardPanel;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Sounds.SoundAdmin;

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
            if (JOptionPane.showConfirmDialog(MyFrame.getInstance(), "Are you sure", "Delete Account", JOptionPane.YES_NO_OPTION) == 0) {
                b.getRequests().stop();
                RequestHandler.getInstance().sendRequest(new CancleGameRequest());
                RequestHandler.getInstance().sendRequest(new SaveRequest());
                RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Back Button"));
                RequestHandler.getInstance().sendRequest(new VisiblePanelRequest("menu"));
            }
        } else if (src == b.getNextTurnButton()) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : NextTurn Button"));
            b.clear();
            b.change();
            boolean flag = b.getRes().board.getUpDeckSize() > 0;
            RequestHandler.getInstance().sendRequest(new EndTurnRequest());
            try {
                Thread.sleep(500);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            if (flag) {
                b.getConfig().setToMiddle(true);
                b.getConfig().setBlur(0);
                b.getConfig().setAnimated(true);
//                if (!b.isMultiplayer() || !b.isP2Turn()) {
                b.getToMiddleTimer().start();
//                }
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
