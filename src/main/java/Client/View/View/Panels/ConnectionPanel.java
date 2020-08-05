package Client.View.View.Panels;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.ConnectRequest;
import Client.Controller.Requests.LoginRequest;
import Client.Controller.Requests.SignupRequest;
import Client.View.Configs.ConfigsLoader;
import Client.View.Configs.LoginConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectionPanel extends JPanel implements ActionListener {

    public JLabel label;
    private JTextField portField;
    private JTextField ipField;
    private JButton connect;

    private JButton exit;
    private JLabel portLabel;
    private JLabel ipLabel;


    private LoginConfig config;

    private void initConfig() {
        config = ConfigsLoader.getInstance().getLoginConfig();
    }


    public ConnectionPanel() {
        initConfig();
        setLayout(null);
        connect = new JButton("Connect");
        connect.setFont(Constants.f2.deriveFont(20.0f));
        connect.setFont(Constants.f2.deriveFont(20.f));
        connect.setFocusable(false);
        connect.addActionListener(this);


        ipLabel = new JLabel("IP : ");
        ipLabel.setFont(Constants.f2);
        ipLabel.setForeground(Color.YELLOW);

        portLabel = new JLabel("Port  : ");
        portLabel.setFont(Constants.f2);
        portLabel.setForeground(Color.YELLOW);

        label = new JLabel("");
        label.setFont(Constants.f2);
        label.setForeground(Color.RED);

        ipField = new JTextField("localhost", 10);
        portField = new JTextField("8000", 10);

        exit = new JButton();
        exit.setIcon(Constants.gameIcon.get("exit"));
        exit.setFocusable(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverEnabled(false);
        exit.setBorderPainted(false);
        exit.addActionListener(this);


        ipField.setBounds(config.getUserlabelX() + 150, config.getUserLabelY(), config.getUserLabelWidth(), config.getUserLabelHeight());
        portField.setBounds(config.getUserlabelX() + 150, config.getUserLabelY() + 50, config.getUserLabelWidth(), config.getUserLabelHeight());
        ipLabel.setBounds(config.getUserlabelX(), config.getUserLabelY(), config.getUserLabelWidth(), config.getUserLabelHeight());
        portLabel.setBounds(config.getUserlabelX(), config.getUserLabelY() + 50, config.getUserLabelWidth(), config.getUserLabelHeight());
        label.setBounds(970, 300, 300, 27);
        connect.setBounds(1000, 500, 250, 30);
        exit.setBounds(1500, 890, 60, 60);

        add(exit);
        add(ipField);
        add(portField);
        add(ipLabel);
        add(portLabel);
        add(connect);
        add(label);
    }

    @Override
    protected void paintComponent(Graphics gd) {
        Graphics2D g = (Graphics2D) gd;
        g.drawImage(Constants.gamePics.get("login"), 0, 0, null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connect) {
            if (ipField.getText().equals("") || portField.getText().equals("")) {
                return;
            }
            RequestHandler.Connect(ipField.getText(), Integer.parseInt(portField.getText()), this);
        } else if (e.getSource(

        ) == exit) {
            System.exit(0);
        }
    }


    public static void main(String[] args) {
    }
}
