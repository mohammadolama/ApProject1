package View;

import View.Configs.ConfigsLoader;
import View.Panels.Constants;
import View.Panels.MyFrame;
import Main.Fundamentals;
import View.Sounds.SoundAdmin;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class test {
    static Clip clip;

    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        ConfigsLoader configsLoader=ConfigsLoader.getInstance();
        Fundamentals.MkDirs();
        Constants.pictureLoader();
        SoundAdmin.play1("resources\\Sounds\\login.wav");
        MyFrame.getInstance();
    }
}
