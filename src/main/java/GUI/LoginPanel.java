package GUI;

import G_L_Interface.Update;
import Main.Gamestate;
import Main.LogInSignUp;
import Sounds.SoundAdmin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static GUI.Constants.f1;
import static GUI.Constants.f2;

public class LoginPanel extends JPanel implements ActionListener,MouseListener {
    private static final LoginPanel loginpanel = new LoginPanel();
    static BufferedImage bf;

    private JButton source;


    private JButton createAccount;
    private JButton enter;
    private JLabel userLabel;
    private JLabel passLabel;
    private JLabel error;
    private JTextField userField;
    private JTextField passField;


    {
        try {
            bf = ImageIO.read(new File("D:\\1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LoginPanel() {
        repaint();

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


        setLayout(null);
        userField.setBounds(1050, 350, 150, 27);
        passField.setBounds(1050, 400, 150, 27);
        userLabel.setBounds(900, 350, 150, 27);
        passLabel.setBounds(900, 400, 150, 27);
        error.setBounds(900, 300, 300, 27);
        enter.setBounds(1000, 500, 250, 30);
        createAccount.setBounds(1000, 550, 250, 30);

        add(userField);
        add(passField);
        add(userLabel);
        add(passLabel);
        add(enter);
        add(createAccount);
        add(error);
    }

    static LoginPanel getInstance() {
        return loginpanel;
    }


    @Override
    protected void paintComponent(Graphics gd) {
        Graphics2D g = (Graphics2D) gd;
        g.drawImage(Constants.gamePics.get("login"), 0, 0,  null);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enter) {
            if (userField.getText().equals("") || passField.getText().equals("")) {
                return;
            } else {
                try {
                    String st = LogInSignUp.check(userField.getText(), passField.getText());
                    switch (st) {
                        case "ok":
//                            Update.update();
                            if (Gamestate.getPlayer().getNewToGame()) {
                                MyFrame.getInstance().changePanel("hero");
//                                Update.update();
                            }else {
                                SoundAdmin.clip.stop();
                                SoundAdmin.play("resources\\Sounds\\3.wav");
                                MyFrame.getInstance().changePanel("menu");
                                Constants.addPanels();
//                                Update.update();
                            }
                            userField.setText("");
                            passField.setText("");
                            break;
                        case "wrong password":
                            error.setText("Wrong Password");
                            revalidate();
                            break;
                        case "user not found":
                            error.setText("Username not Found.");
                            revalidate();
                            break;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        }
        else if (e.getSource() == createAccount) {
            if (userField.getText().equals("") || passField.getText().equals("")) {
                return;
            } else {
                try {
                    String st=LogInSignUp.create(userField.getText(),passField.getText());
                    switch (st){
                        case "ok":
                            error.setForeground(Color.GREEN);
                            error.setText("Account Created.");
                            revalidate();
                            error.setForeground(Color.red);
                            break;
                        case "user already exist":
                            error.setText("User already exists.");
                            revalidate();
                            break;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
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
}
