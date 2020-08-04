package Client.View.View.Panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame {
    private static final MyFrame frame = new MyFrame();
    private static JPanel panel;
    private CardLayout cardLayout;

    private MyFrame() {
        setSize(1600, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image customimage = null;
        try {
            customimage = ImageIO.read(new File("resources/pics/game/mouse.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(customimage, new Point(0, 0), "customCursor");
        setCursor(customCursor);


        cardLayout = new CardLayout();
        panel = new JPanel();


        panel.setLayout(cardLayout);

        LoginPanel loginPanel = LoginPanel.getInstance();
        MenuPanel menuPanel = MenuPanel.getInstance();

        panel.add(loginPanel, "login");
        panel.add(menuPanel, "menu");

        add(panel);
        setVisible(true);

        setPreferredSize(new Dimension(1600, 1000));

    }

    public static MyFrame getInstance() {
        return frame;
    }

    public static JPanel getPanel() {
        return panel;
    }

    public void changePanel(String string) {
        cardLayout.show(panel, string);
    }

}
