package SpaceInvaders.Controller.Sound;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {
    private static Clip shootClip;
    private static Clip dyingsoundClip;

    static {
        try {
            AudioInputStream shootingInputStream = AudioSystem.getAudioInputStream(new File("shoot.wav"));
            shootClip = AudioSystem.getClip();
            shootClip.open(shootingInputStream);
            AudioInputStream explosionInputStream = AudioSystem.getAudioInputStream(new File("invadedkiller.wav"));
            dyingsoundClip = AudioSystem.getClip();
            dyingsoundClip.open(explosionInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playShootingSound() {
        playSound(shootClip);
    }

    public static void playExplosionSound() {
        playSound(dyingsoundClip);
    }

    private static void playSound(Clip clip) {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);
        clip.start();
    }
}

