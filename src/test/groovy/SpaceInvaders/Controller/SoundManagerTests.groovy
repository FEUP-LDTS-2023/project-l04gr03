package SpaceInvaders.Controller

import org.junit.jupiter.api.Test;

import javax.sound.sampled.Clip;

import static org.junit.jupiter.api.Assertions.*;

class SoundManagerTests {

    @Test
    void shouldLoadClipSuccessfully() {
        assertDoesNotThrow(() -> SoundManager.loadClip("src/resources/sounds/shoot.wav"));
    }

    @Test
    void shouldLoadLoopClipSuccessfully() {
        assertDoesNotThrow(() -> SoundManager.loadLoopClip("src/resources/sounds/spaceinvaders1.mpeg"));
    }

    @Test
    void shouldPlayShootClip() {
        resetClipPosition(SoundManager.getShootClip());
        SoundManager.playShootingSound();
        assertTrue(SoundManager.getShootClip().isRunning());
    }

    @Test
    void shouldPlayDyingSoundClip() {
        resetClipPosition(SoundManager.getDyingSoundClip());
        SoundManager.playExplosionSound();
        assertTrue(SoundManager.getDyingSoundClip().isRunning());
    }

    @Test
    void shouldPlaySwitchOptionClip() {
        resetClipPosition(SoundManager.getSwitchOptionClip());
        SoundManager.playSwitchOptionSound();
        assertTrue(SoundManager.getSwitchOptionClip().isRunning());
    }

    @Test
    void shouldPlayBackgroundMusic() {
        resetClipPosition(SoundManager.backgroundMusicClip);
        SoundManager.playBackgroundMusic();
        assertTrue(SoundManager.backgroundMusicClip.isRunning());
    }

    private void resetClipPosition(Clip clip) {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);
    }
}
