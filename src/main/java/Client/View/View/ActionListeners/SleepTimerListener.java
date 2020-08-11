package Client.View.View.ActionListeners;

import Client.View.View.Panels.BoardPanel;
import Client.View.View.Update.Update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SleepTimerListener implements ActionListener {
    private BoardPanel b;

    public SleepTimerListener(BoardPanel b) {
        this.b = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        b.setSizeX(b.getSizeX() + 10);

        if (b.getSizeX() >= 45) {
            b.setSizeX(20);
        }
        b.setIndex(b.getIndex() + 1);

        if (b.getIndex() == 3) {
            b.setIndex(0);
        }
        Update.render();
    }
}
