package Client.View.View.Panels.Listeners.ShopListeners;

import Client.Model.Images;
import Client.View.View.Panels.ShopPanel;
import Client.View.View.Update.Update;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShopMouse implements MouseListener {
    private final ShopPanel s;

    public ShopMouse(ShopPanel s) {
        this.s = s;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        String st = null;
        for (Images images : s.getImages()) {
            if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                st = images.getName();
                break;
            }
        }
        if (st == null || st.equals("")) {
            s.setClicked(false);
            Update.render();
            return;
        }
        s.drawBigger(st);
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
