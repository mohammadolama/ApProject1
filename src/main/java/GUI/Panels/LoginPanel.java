package GUI.Panels;

import GUI.Configs.ConfigsLoader;
import GUI.Configs.LoginConfig;
import Util.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static GUI.Panels.Constants.*;

public class LoginPanel extends JPanel implements ActionListener, MouseListener {
    private static final LoginPanel loginpanel = new LoginPanel();


    private JButton source;


    private JButton createAccount;
    private JButton enter;
    private JButton exit;
    private JLabel userLabel;
    private JLabel passLabel;
    private JLabel error;
    private JTextField userField;
    private JTextField passField;

    private LoginConfig config;

    private void initConfig() {
        config = ConfigsLoader.getInstance().getLoginConfig();
    }


    private LoginPanel() {
        initConfig();
        setLayout(null);
        createAccount = new JButton("Create new Account");
        createAccount.setFont(f2.deriveFont(20.0f));

        enter = new JButton("Enter");
        enter.setFont(f2.deriveFont(20.0f));

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


        createAccount.setFont(f2.deriveFont(20.f));
        createAccount.setFocusable(false);
        createAccount.addActionListener(this);
        createAccount.addMouseListener(this);

        enter.setFont(f2.deriveFont(20.f));
        enter.setFocusable(false);
        enter.addActionListener(this);
        enter.addMouseListener(this);

        exit = new JButton();
        exit.setIcon(gameIcon.get("exit"));
        exit.setFocusable(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverEnabled(false);
        exit.setBorderPainted(false);
        exit.addActionListener(this);


        userField.setBounds(config.getUserlabelX() + 150, config.getUserLabelY(), config.getUserLabelWidth(), config.getUserLabelHeight());
        passField.setBounds(config.getUserlabelX() + 150, config.getUserLabelY() + 50, config.getUserLabelWidth(), config.getUserLabelHeight());
        userLabel.setBounds(config.getUserlabelX(), config.getUserLabelY(), config.getUserLabelWidth(), config.getUserLabelHeight());
        passLabel.setBounds(config.getUserlabelX(), config.getUserLabelY() + 50, config.getUserLabelWidth(), config.getUserLabelHeight());
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
        g.drawImage(Constants.gamePics.get("login"), 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enter) {
            if (userField.getText().equals("") || passField.getText().equals("")) {
                return;
            }
            Admin.getInstance().logIn(userField.getText(), passField.getText());
        } else if (e.getSource() == createAccount) {
            if (userField.getText().equals("") || passField.getText().equals("")) {
                return;
            }
            Admin.getInstance().signUp(userField.getText(), passField.getText());
        } else if (e.getSource() == exit) {
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
        source = (JButton) e.getSource();
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
