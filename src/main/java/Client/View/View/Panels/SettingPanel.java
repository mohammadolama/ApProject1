package Client.View.View.Panels;

import Client.View.View.Panels.Listeners.SettingAction;

import javax.swing.*;
import java.awt.*;

import static Client.View.View.Panels.Constants.*;

public class SettingPanel extends JPanel {

    private final JButton decreaseSound;
    private final JButton increaseSound;
    private final JButton muteSound;
    private final JButton back;
    private final JButton exit;

    private int i = 0;

    private static final SettingPanel settingPanel = new SettingPanel();
    private final SettingAction sa = new SettingAction(this);

    private SettingPanel() {
        setLayout(null);
        setFocusable(true);
        back = new JButton();
        back.setIcon(gameIcon.get("back"));
        back.setFocusable(false);
        back.addActionListener(sa);
        back.setBounds(1430, 890, 60, 60);
        back.setContentAreaFilled(false);
        back.setRolloverEnabled(false);
        back.setBorderPainted(false);
        add(back);

        exit = new JButton();
        exit.addActionListener(sa);
        exit.setIcon(gameIcon.get("exit"));
        exit.setBounds(1500, 890, 60, 60);
        exit.setFocusable(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverEnabled(false);
        exit.setBorderPainted(false);
        add(exit);


        decreaseSound = new JButton("Decrease volume");
        decreaseSound.addActionListener(sa);
        decreaseSound.setFocusable(false);
        decreaseSound.setFont(f2.deriveFont(25.0f));
        decreaseSound.setBounds(200, 300, 250, 100);
        add(decreaseSound);

        increaseSound = new JButton("Increase volume");
        increaseSound.addActionListener(sa);
        increaseSound.setFocusable(false);
        increaseSound.setFont(f2.deriveFont(25.0f));
        increaseSound.setBounds(200, 500, 250, 100);
        add(increaseSound);

        muteSound = new JButton("Mute volume");
        muteSound.addActionListener(sa);
        muteSound.setFocusable(false);
        muteSound.setFont(f2.deriveFont(25.0f));
        muteSound.setBounds(200, 700, 250, 100);
        add(muteSound);


    }

    public static SettingPanel getInstance() {
        return settingPanel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gamePics.get("setting"), 0, 0, 1600, 1000, null);
        g.setColor(new Color(222, 222, 222, 130));
        g.fillRect(0, 0, 1600, 1000);
    }

    public JButton getDecreaseSound() {
        return decreaseSound;
    }

    public JButton getIncreaseSound() {
        return increaseSound;
    }

    public JButton getMuteSound() {
        return muteSound;
    }

    public JButton getBack() {
        return back;
    }

    public JButton getExit() {
        return exit;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
