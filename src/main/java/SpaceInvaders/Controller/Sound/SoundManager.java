package SpaceInvaders.Controller.Sound;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {
    private static Clip shootClip;
    private static Clip dyingSoundClip;
    private static Clip switchOptionClip;
    private static Clip backgroundMusicClip;
    static {
        try {
            AudioInputStream shootingInputStream = AudioSystem.getAudioInputStream(new File("shoot.wav"));
            shootClip = AudioSystem.getClip();
            shootClip.open(shootingInputStream);
            AudioInputStream explosionInputStream = AudioSystem.getAudioInputStream(new File("invadedkiller.wav"));
            dyingSoundClip = AudioSystem.getClip();
            dyingSoundClip.open(explosionInputStream);
            AudioInputStream switchOptionInputStream = AudioSystem.getAudioInputStream(new File("light-switch-156813.mp3"));
            switchOptionClip = AudioSystem.getClip();
            switchOptionClip.open(switchOptionInputStream);
            AudioInputStream backgroundMusicInputStream = AudioSystem.getAudioInputStream(new File("spaceinvaders1.mpeg"));
            backgroundMusicClip = AudioSystem.getClip();
            backgroundMusicClip.open(backgroundMusicInputStream);
            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playShootingSound() {
        playSound(shootClip);
    }

    public static void playExplosionSound() {
        playSound(dyingSoundClip);
    }

    public static void playSwitchOptionSound() {
        playSound(switchOptionClip);
    }

    private static void playSound(Clip clip) {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);
        clip.start();
    }
}

