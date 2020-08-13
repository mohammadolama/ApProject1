package Client;

import Client.View.View.Panels.Constants;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Sounds.SoundAdmin;
import Server.Controller.MainLogic.Fundamentals;

public class ClientMain {
    public static void main(String[] args) {
        Fundamentals.MkDirs();
        Constants.pictureLoader();
        SoundAdmin.play1("resources/Sounds/login.wav");
        MyFrame.getInstance();
    }
}
