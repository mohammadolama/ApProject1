package Client.View.View.ActionListeners;

import Client.View.View.Panels.BoardPanel;
import Client.View.View.Update.Update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HandTimerListener implements ActionListener {

    private BoardPanel b;

    public HandTimerListener(BoardPanel b) {
        this.b = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int desX = 0;
        if (b.getHandImages().size() > 0) {
            desX = (700 - (b.getHandImages().get(b.getHandImages().size() - 1).getX() + b.getHandImages().get(b.getHandImages().size() - 1).getWidth()) + 15) / 30;
        }
        b.setY1(b.getY1() + 20);
        b.setX1(b.getX1() - desX);
        if (b.getY1() > 950) {
            b.getConfig().setDeckAnimationFinished(true);
            b.getConfig().setAnimated(false);
            b.setDisabled(false);
            b.getToHandTimer().stop();
        }
        Update.render();
    }
}

