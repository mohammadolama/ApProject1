package Sounds;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundAdmin {

    public static Clip clip;

    private static float soundVolume = 1f;
    private static AudioInputStream audioInputStream;
    private static boolean muteSound = false;

    public static void play(String string) {
        try {
            if (clip!=null){
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

    public static void decreaseSound() {
        soundVolume -= 0.06;

        FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        floatControl.setValue(20f * (float) Math.log10(soundVolume));
    }

    public static void increaseSound() {
        if (soundVolume < 1) {
            soundVolume += 0.06;

            FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(20f * (float) Math.log10(soundVolume));
        }
    }

}
