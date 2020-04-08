package GUI;

import Main.Gamestate;

public class Update {

    public static void upcdate(){
        System.out.println(Gamestate.getPlayer());
        StatusPanel.setPlayer(Gamestate.getPlayer());
        StatusPanel.getInstance().revalidate();
        StatusPanel.getInstance().repaint();
    }
}
