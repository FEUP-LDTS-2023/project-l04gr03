package SpaceInvaders.Controller.Sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SoundManager {
    private static Clip shootClip;
    private static Clip dyingSoundClip;
    private static Clip switchOptionClip;
    private static Clip backgroundMusicClip;

    static {
        try {
            shootClip = loadingClip("src/main/resources/sounds/shoot.wav");
            dyingSoundClip = loadingClip("src/main/resources/sounds/invaderkilled.wav");
            switchOptionClip = loadingClip("src/main/resources/sounds/light-switch-156813.wav");
            backgroundMusicClip = loadingLoopClip("src/main/resources/sounds/spaceinvaders1.wav");
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
        Path path = Paths.get(file);
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(path.toFile());
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

