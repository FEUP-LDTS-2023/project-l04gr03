package SpaceInvaders.Controller.Sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Song {
    private static Clip songclip;
    public static void getSong(){

        playSong("spaceinvaders1.mpeg");
    }

    public static void playSong(String filename) {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
            songclip = AudioSystem.getClip();
            songclip.open(audioInputStream);
            songclip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}

