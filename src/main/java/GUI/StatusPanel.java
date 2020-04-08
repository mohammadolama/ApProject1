package GUI;

import Main.Gamestate;
import Main.Player;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static GUI.Constants.f2;
import static GUI.Constants.hp;

public class StatusPanel extends JPanel implements KeyListener {

    private static Player player=Gamestate.getPlayer();

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        StatusPanel.player = player;
    }

    private static StatusPanel statusPanel=new StatusPanel();

    private StatusPanel(){
        requestFocus();
        setFocusable(true);
        revalidate();
        repaint();
        addKeyListener(this);
    }

    public static StatusPanel getInstance(){
        return statusPanel;
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(hp , 0 ,-55,350,1050 , null);
        Graphics2D g2d=(Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.YELLOW);
        g2d.setFont(f2);
        g2d.drawString("Username: "+player.getUsername() , 50 , 200);
        g2d.drawString("Level   : "+player.getLevel() , 50 , 300);
        g2d.drawString("EXP     : "+player.getExp() , 50 , 400);
        g2d.drawString("Wallet: "+player.getMoney() , 50 , 500);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key=e.getKeyCode();
        switch (key){
            case KeyEvent.VK_C :
                System.out.println(player.getId());
                revalidate();
                repaint();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
