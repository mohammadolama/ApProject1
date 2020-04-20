package GUI;

import Util.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static GUI.Constants.*;

public class MenuPanel extends JPanel implements ActionListener, MouseListener {

    private int startY=200;
    private int startX=20;
    private int spcaing=100;

    private static JButton play=new JButton("Play");
    private static JButton collection = new JButton("Collection");
    private static JButton store = new JButton("Store");
    private static JButton setting=new JButton("Setting");
    private static JButton status=new JButton("Status");
    private static JButton logout=new JButton("Log Out");
    private static JButton exit=new JButton();
    private static JButton src;

    private static final MenuPanel menu=new MenuPanel();

    private MenuPanel(){
        setLayout(null);
        setFocusable(true);


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
        exit.setIcon(exitIcon);
        exit.setBounds(10,890,60,60);
        exit.setFocusable(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverEnabled(false);
        exit.setBorderPainted(false);
        add(exit);
    }

   public static MenuPanel getInstance(){
        return menu;
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(Constants.gamePics.get("main"),0,0,null);
        g.setColor(new Color(254, 255, 253,170));
        g.fillRect(0,0,250,1000);
        Graphics2D g2d=(Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(Constants.Title);
        g2d.setColor(Color.red);
        g2d.drawString("Math & CStone" , 550,100);
        g2d.setColor(Color.RED);
        g2d.setFont(designer);
        g2d.drawString("Designed by Ghaffari" , 1450,940);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button=(JButton)e.getSource();
        if (button == logout){
            Admin.getInstance().logOut();
            }
        else if (button == exit){
            Admin.getInstance().exit();
        }
        else if (button == status){
            Admin.getInstance().setVisiblePanel("status");
        }
        else if (button==store){
            Admin.getInstance().setVisiblePanel("store");
        }else if(button==setting){

        }else if (button == collection){
            Admin.getInstance().setVisiblePanel("collection");
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
}
