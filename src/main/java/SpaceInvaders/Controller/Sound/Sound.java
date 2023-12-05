package SpaceInvaders.Controller.Sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    private static Clip soundclip;
    public static void getSound(){

        playSound("shoot.wav");
    }

    public static void playSound(String filename) {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
            soundclip = AudioSystem.getClip();
            soundclip.open(audioInputStream);
            soundclip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}




