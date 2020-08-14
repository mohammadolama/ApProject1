package Client.View.View.Panels;


import Client.View.Configs.ConfigsLoader;
import Client.View.Configs.LoginConfig;
import Client.View.View.Panels.Listeners.LoginListeners.LoginAcion;
import Client.View.View.Panels.Listeners.LoginListeners.LoginMouse;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private static final LoginPanel loginpanel = new LoginPanel();


    private JButton source;

    private final JButton createAccount;
    private final JButton enter;
    private JButton exit;
    private final JLabel userLabel;
    private final JLabel passLabel;
    private final JLabel error;
    private final JTextField userField;
    private final JTextField passField;
    private final LoginAcion la = new LoginAcion(this);
    private final LoginMouse lm = new LoginMouse(this);
    private LoginConfig config;

    private void initConfig() {
        config = ConfigsLoader.getInstance().getLoginConfig();
    }


    private LoginPanel() {
        initConfig();
        setLayout(null);
        createAccount = new JButton("Create new Account");
        createAccount.setFont(Constants.f2.deriveFont(20.0f));

        enter = new JButton("Enter");
        enter.setFont(Constants.f2.deriveFont(20.0f));

        userLabel = new JLabel("Username : ");
        userLabel.setFont(Constants.f2);
        userLabel.setForeground(Color.YELLOW);

        passLabel = new JLabel("Password  : ");
        passLabel.setFont(Constants.f2);
        passLabel.setForeground(Color.YELLOW);

        error = new JLabel("");
        error.setFont(Constants.f2);
        error.setForeground(Color.RED);

        userField = new JTextField(15);
        passField = new JTextField(15);


        createAccount.setFont(Constants.f2.deriveFont(20.f));
        createAccount.setFocusable(false);
        createAccount.addActionListener(la);
        createAccount.addMouseListener(lm);

        enter.setFont(Constants.f2.deriveFont(20.f));
        enter.setFocusable(false);
        enter.addActionListener(la);
        enter.addMouseListener(lm);

        exit = new JButton();
        exit.setIcon(Constants.gameIcon.get("exit"));
        exit.setFocusable(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverEnabled(false);
        exit.setBorderPainted(false);
        exit.addActionListener(la);


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

    public JLabel getError() {
        return error;
    }


    public JTextField getUserField() {
        return userField;
    }


    public JTextField getPassField() {
        return passField;
    }

    public void reset() {
        userField.setText("");
        passField.setText("");
        error.setText("");
    }

    public JButton getSource() {
        return source;
    }

    public void setSource(JButton source) {
        this.source = source;
    }

    public JButton getCreateAccount() {
        return createAccount;
    }

    public JButton getEnter() {
        return enter;
    }

    public JButton getExit() {
        return exit;
    }

    public void setExit(JButton exit) {
        this.exit = exit;
    }

    public LoginConfig getConfig() {
        return config;
    }

    public void setConfig(LoginConfig config) {
        this.config = config;
    }
}
