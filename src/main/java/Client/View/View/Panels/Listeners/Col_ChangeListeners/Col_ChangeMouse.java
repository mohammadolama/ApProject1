package Client.View.View.Panels.Listeners.Col_ChangeListeners;

import Client.Model.Images;
import Client.View.View.Panels.Col_Change;
import Client.View.View.Update.Update;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Col_ChangeMouse implements MouseListener {
    private final Col_Change c;

    public Col_ChangeMouse(Col_Change c) {
        this.c = c;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        String st = null;
        for (Images images : c.getImages()) {
            if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                st = images.getName();
                break;
            }
        }

        if (st == null || st.equals("")) {
            c.setClicked(false);
            Update.render();
            return;
        }
        c.drawBigger(st);
        Update.render();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
