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
            shootClip = loadingClip("src/resources/sounds/shoot.wav");
            dyingSoundClip = loadingClip("src/resources/sounds/invaderkilled.wav");
            switchOptionClip = loadingClip("src/resources/sounds/light-switch-156813.mp3");
            backgroundMusicClip = loadingLoopClip("src/resources/sounds/spaceinvaders1.mpeg");
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

    private static Clip loadingClip(String file) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(file));
        Clip clip = AudioSystem.getClip();
        clip.open(inputStream);
        return clip;
    }

    private static Clip loadingLoopClip(String file) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Clip clip = loadingClip(file);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        return clip;
    }

    private static void playSound(Clip clip) {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);
        clip.start();
    }
}

