package GUI;

import javax.swing.*;
import java.awt.*;

import static GUI.Constants.status2;

public class Panel extends JPanel {

    public Panel(){
    setSize(1600,1000);
    setPreferredSize(new Dimension(1600,1000));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(status2, 0, 0,null);
    }
}
