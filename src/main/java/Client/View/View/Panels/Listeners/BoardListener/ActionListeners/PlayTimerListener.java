package Client.View.View.Panels.Listeners.BoardListener.ActionListeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.PlayCardRequest;
import Client.View.View.Panels.BoardPanel;
import Client.View.View.Update.Update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayTimerListener implements ActionListener {
    private BoardPanel b;

    public PlayTimerListener(BoardPanel b) {
        this.b = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        b.setDisabled(true);
        int desX = (b.getMouseDesX() - b.getMouseStartX()) / 30;
        int desY = (b.getMouseDesY() - b.getMouseStartY()) / 30;
        b.setX1(b.getX1() + desX);
        b.setY1(b.getY1() + desY);
        Update.render();
        if (b.getY1() < b.getMouseDesY()) {
            b.getConfig().setPlayAnimation(false);
            RequestHandler.getInstance().sendRequest(new PlayCardRequest(b.getHandCardSelectedName(),
                    b.getDeckIndex(), b.getSelectedTargetIndex()));
            b.setDisabled(false);
            b.setSelectedTargetIndex(-1);
            b.getPlayTimer().stop();
        }
    }
}
