package Client.View.View.Panels.Listeners.MenuListeners;

import Client.View.View.Panels.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuMouse implements MouseListener {
    private final MenuPanel m;

    public MenuMouse(MenuPanel m) {
        this.m = m;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        m.setSrc((JButton) e.getSource());
        m.getSrc().setBackground(new Color(240, 255, 97));

    }

    @Override
    public void mouseExited(MouseEvent e) {
        m.getSrc().setBackground(Color.WHITE);
    }
}
