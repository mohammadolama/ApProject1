package Client.View.View.Panels.Listeners.BoardListener.ActionListeners;

import Client.View.View.Panels.BoardPanel;
import Client.View.View.Update.Update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiddleTimerListener implements ActionListener {
    private BoardPanel b;

    public MiddleTimerListener(BoardPanel b) {
        this.b = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        b.setDisabled(true);
        b.getConfig().setToMiddle(true);
        b.getConfig().setDeckAnimationFinished(false);
        b.getConfig().setAnimated(true);
        Update.render();
        b.setX1(b.getX1() + b.getXA());
        b.setY1(b.getY1() + b.getYA());
        if (b.getX1() < 700) {
            b.getConfig().setToMiddle(false);
            try {
                Thread.sleep(300);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            b.getConfig().setBlur(0);
            b.getToHandTimer().start();
            b.getToMiddleTimer().stop();
        }
        Update.render();
    }
}

