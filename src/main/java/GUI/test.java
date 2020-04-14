package GUI;

import Main.Fundamentals;
import Sounds.SoundAdmin;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class test {
    static Clip clip;

    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        Fundamentals.MkDirs();
        Constants.pictureLoader();
        SoundAdmin.play("resources\\Sounds\\2.wav");
        MyFrame.getInstance();











    }
}
