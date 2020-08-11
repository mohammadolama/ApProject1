package Client.View.View.ActionListeners;

import Client.View.View.Panels.BoardPanel;
import Client.View.View.Update.Update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class P2HandTimerListener implements ActionListener {
    private BoardPanel b;

    public P2HandTimerListener(BoardPanel b) {
        this.b = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        b.setP2Y(b.getP2Y() - 20);
        if (b.getP2Y() < -100) {
            b.getConfig().setDeckAnimationFinished(true);
            b.getConfig().setAnimated(false);
            b.setDisabled(false);
            b.getToHandTimer().stop();
        }
        Update.render();
    }
}
