package Client.View.View.Update;

import Client.View.View.Panels.CollectionPanel;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Panels.StatusPanel;

public class Update {

    public static void refresh() {
        CollectionPanel.getInstance().refresh();
        StatusPanel.getInstance().refresh();
        render();
    }

    public static  void render(){
        MyFrame.getInstance().revalidate();
        MyFrame.getInstance().repaint();
    }

}
