package GUI;

import Main.Gamestate;
import Main.JsonBuilders;
import Main.LOGGER;
import Main.Menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static GUI.Constants.*;

public class MenuPanel extends JPanel implements ActionListener, MouseMotionListener, MouseListener {

    private int startY=100;
    private int startX=20;
    private int spcaing=100;

    private static JButton play=new JButton("Play");
    private static JButton collection = new JButton("Collection");
    private static JButton store = new JButton("Store");
    private static JButton setting=new JButton("Setting");
    private static JButton status=new JButton("Status");
    private static JButton logout=new JButton("Log Out");
    private static JButton exit=new JButton("Exit");
    private static JButton src;


    private static final MenuPanel menu=new MenuPanel();

    private MenuPanel(){
        setLayout(null);
        play.addActionListener(this);
        play.addMouseListener(this);
        play.setBounds(startX,startY,200,50);
        play.setFont(f2);
        play.setFocusable(false);
        add(play);



        collection.addActionListener(this);
        collection.addMouseListener(this);
        collection.setBounds(startX,startY+spcaing,200,50);
        collection.setFocusable(false);
        collection.setFont(f2);
        add(collection);

        store.addActionListener(this);
        store.addMouseListener(this);
        store.setBounds(startX,startY+ 2 * spcaing,200,50);
        store.setFocusable(false);

        store.setFont(f2);
        add(store);

        setting.addActionListener(this);
        setting.addMouseListener(this);
        setting.setBounds(startX,startY+ 3 *spcaing,200,50);
        setting.setFocusable(false);
        setting.setFont(f2);
        add(setting);

        status.addActionListener(this);
        status.addMouseListener(this);
        status.setBounds(startX,startY+ 4 *spcaing,200,50);
        status.setFocusable(false);
        status.setFont(f2);
        add(status);

        logout.addActionListener(this);
        logout.addMouseListener(this);
        logout.setBounds(startX,startY+ 5 *spcaing,200,50);
        logout.setFocusable(false);


        logout.setText("Log Out");
        logout.setFont(f2);

        add(logout);
        exit.addActionListener(this);
        exit.addMouseListener(this);
        exit.setBounds(startX,startY+ 6 *spcaing,200,50);
        exit.setFocusable(false);
        exit.setFont(f2);
        add(exit);
    }

    static MenuPanel getInstance(){
        return menu;
    }


    @Override
    protected void paintComponent(Graphics g)
    {
        g.drawImage(Constants.gamePics.get("main"),0,0,null);
        g.setColor(new Color(254, 255, 253,170));
        g.fillRect(0,0,250,1000);

        Graphics2D g2d=(Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(Constants.Title);
        g2d.setColor(Color.ORANGE);
        g2d.drawString("Math & CStone" , 550,100);
        g2d.setColor(Color.RED);
        g2d.setFont(designer);
        g2d.drawString("Designed by Ghaffari" , 5,940);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button=(JButton)e.getSource();
        System.out.println("helloo");
        if (button == logout){
            System.out.println("hadadadadadd");

            LOGGER.playerlog(Gamestate.getPlayer(),"Sign out ");
            try {
                JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername() , Gamestate.getPlayer());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            MyFrame.getInstance().changePanel("login");
        }
        else if (button == exit){
            try {
                JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername() , Gamestate.getPlayer());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
        else if (button == status){
            MyFrame.getInstance().changePanel("status");
            StatusPanel.getInstance().requestFocus();
            revalidate();
        }
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
        src=(JButton) e.getSource();
        src.setBackground(new Color(240, 255, 97));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        src.setBackground(Color.WHITE);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
