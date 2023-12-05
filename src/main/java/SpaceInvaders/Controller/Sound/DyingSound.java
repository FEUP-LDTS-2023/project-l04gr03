package SpaceInvaders.Controller.Sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class DyingSound {
    private static Clip dsoundclip;
    public static void getDyingSound(){

        playDyingSound("invadedkiller.wav");
    }

    public static void playDyingSound(String filename) {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
            dsoundclip = AudioSystem.getClip();
            dsoundclip.open(audioInputStream);
            dsoundclip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}