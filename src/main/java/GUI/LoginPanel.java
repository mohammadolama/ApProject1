package GUI;


import Util.Admin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static GUI.Constants.*;

public class LoginPanel extends JPanel implements ActionListener,MouseListener {
    private static final LoginPanel loginpanel = new LoginPanel();
    private static BufferedImage bf;

    private JButton source;


    private JButton createAccount;
    private JButton enter;
    private JButton exit;
    private JLabel userLabel;
    private JLabel passLabel;
    private JLabel error;
    private JTextField userField;
    private JTextField passField;

    private int userlabelX=900;
    private int userLabelY=350;
    private int userLabelWidth=150;
    private int userLabelHeight=27;

    {
        try {
            bf = ImageIO.read(new File("D:\\1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LoginPanel() {
        setLayout(null);
        createAccount = new JButton("Create new Account");
        createAccount.setFont(f1);

        enter = new JButton("Enter");
        enter.setFont(f1);

        userLabel = new JLabel("Username : ");
        userLabel.setFont(f2);
        userLabel.setForeground(Color.YELLOW);

        passLabel = new JLabel("Password  : ");
        passLabel.setFont(f2);
        passLabel.setForeground(Color.YELLOW);

        error = new JLabel("");
        error.setFont(f2);
        error.setForeground(Color.RED);

        userField = new JTextField(10);
        passField = new JTextField(10);

        createAccount.setFont(f1);
        createAccount.setFocusable(false);
        createAccount.addActionListener(this);
        createAccount.addMouseListener(this);

        enter.setFont(f1);
        enter.setFocusable(false);
        enter.addActionListener(this);
        enter.addMouseListener(this);

        exit=new JButton();
        exit.setIcon(exitIcon);
        exit.setFocusable(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverEnabled(false);
        exit.setBorderPainted(false);
        exit.addActionListener(this);


        userField.setBounds(userlabelX+150, userLabelY, userLabelWidth, userLabelHeight);
        passField.setBounds(userlabelX+150, userLabelY+50, userLabelWidth, userLabelHeight);
        userLabel.setBounds(userlabelX, userLabelY, userLabelWidth, userLabelHeight);
        passLabel.setBounds(userlabelX, userLabelY+50, userLabelWidth, userLabelHeight);
        error.setBounds(900, 300, 300, 27);
        enter.setBounds(1000, 500, 250, 30);
        createAccount.setBounds(1000, 550, 250, 30);
        exit.setBounds(1500, 890, 60, 60);


        add(exit);
        add(userField);
        add(passField);
        add(userLabel);
        add(passLabel);
        add(enter);
        add(createAccount);
        add(error);
    }

    public static LoginPanel getInstance() {
        return loginpanel;
    }


    @Override
    protected void paintComponent(Graphics gd) {
        Graphics2D g = (Graphics2D) gd;
        g.drawImage(Constants.gamePics.get("login"), 0, 0,  null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enter) {
            if (userField.getText().equals("") || passField.getText().equals("")) {
                return;
            }
                Admin.getInstance().logIn(userField.getText(), passField.getText());
        }
        else if (e.getSource() == createAccount) {
            if (userField.getText().equals("") || passField.getText().equals("")) {
                return;
            }
            Admin.getInstance().signUp(userField.getText(), passField.getText());
        }
        else if (e.getSource() == exit){
            System.exit(0);
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
        source=(JButton) e.getSource();
        source.setBackground(new Color(240, 255, 97));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        source.setBackground(Color.WHITE);
    }

    public JLabel getError() {
        return error;
    }


    public JTextField getUserField() {
        return userField;
    }


    public JTextField getPassField() {
        return passField;
    }

}
