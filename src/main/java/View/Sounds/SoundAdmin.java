package View.Sounds;

import javax.sound.sampled.*;
import java.io.*;

public class SoundAdmin {

    public static Clip clip;
    private static Clip clip2;


    private static float soundVolume = 0.2f;
    private static boolean muteSound = false;
    private static String path = "resources\\Sounds\\";

    public static void play1(String string) {
        try {
            if (clip != null) {
                clip.stop();
            }
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(string)));
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(20f * (float) Math.log10(soundVolume));
            if (muteSound) {
                BooleanControl booleanControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
                booleanControl.setValue(true);
            } else {
                BooleanControl booleanControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
                booleanControl.setValue(false);
            }


        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    private static void play2(String string) {
        try {
            if (clip2 != null) {
                clip2.stop();
            }
            clip2 = AudioSystem.getClip();
            clip2.open(AudioSystem.getAudioInputStream(new File(string)));
            clip2.start();
            FloatControl floatControl = (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(20f * (float) Math.log10(soundVolume));
            if (muteSound) {
                BooleanControl booleanControl = (BooleanControl) clip2.getControl(BooleanControl.Type.MUTE);
                booleanControl.setValue(true);
            } else {
                BooleanControl booleanControl = (BooleanControl) clip2.getControl(BooleanControl.Type.MUTE);
                booleanControl.setValue(false);
            }
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }


    public static void playSound(String sound) {
        String pt = path + sound + ".wav";
        play2(pt);
    }


    public static void stopStart(int i) {
        muteSound = !muteSound;
        if (i % 2 == 0) {
            clip.stop();
        } else {
            clip.start();
        }
    }


    public static void decreaseSound() {
        if (soundVolume > 0.07) {
            soundVolume -= 0.06;

            FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(20f * (float) Math.log10(soundVolume));
        }
    }

    public static void increaseSound() {
        if (soundVolume < 1) {
            soundVolume += 0.06;

            FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(20f * (float) Math.log10(soundVolume));
        }
    }

}
