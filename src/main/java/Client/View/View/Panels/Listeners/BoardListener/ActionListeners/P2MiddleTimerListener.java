package Client.View.View.Panels.Listeners.BoardListener.ActionListeners;

import Client.View.View.Panels.BoardPanel;
import Client.View.View.Update.Update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class P2MiddleTimerListener implements ActionListener {
    private BoardPanel b;

    public P2MiddleTimerListener(BoardPanel b) {
        this.b = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        b.setDisabled(true);
        b.getConfig().setToMiddle(true);
        b.getConfig().setDeckAnimationFinished(false);
        b.getConfig().setAnimated(true);
//        Update.render();
        b.setP2X(b.getP2X() + b.getXA());
        b.setP2Y(b.getP2Y() - b.getYA());
        System.out.println(b.getP2X() + "  :  " + b.getP2Y() + "  :  " + b.getXA() + "  :  " + b.getYA());
        if (b.getP2X() < 700) {
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
