package Client.View.View.Panels.Listeners.LoginListeners;

import Client.View.View.Panels.LoginPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginMouse implements MouseListener {
    private final LoginPanel l;

    public LoginMouse(LoginPanel l) {
        this.l = l;
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
        l.setSource((JButton) e.getSource());
        l.getSource().setBackground(new Color(240, 255, 97));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        l.getSource().setBackground(Color.WHITE);
    }

}
