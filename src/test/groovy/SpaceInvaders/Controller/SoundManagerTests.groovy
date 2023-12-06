package SpaceInvaders.Controller

import org.junit.jupiter.api.Test

import javax.sound.sampled.Clip;

import static org.junit.jupiter.api.Assertions.*;

class SoundManagerTest {

    @Test
    void playShootingSound_shouldPlayShootClip() {
        resetClipPosition(SoundManager.getShootClip());
        SoundManager.playShootingSound();
        assertTrue(SoundManager.getShootClip().isRunning());
    }

    @Test
    void playExplosionSound_shouldPlayDyingSoundClip() {
        resetClipPosition(SoundManager.getDyingSoundClip());
        SoundManager.playExplosionSound();
        assertTrue(SoundManager.getDyingSoundClip().isRunning());
    }

    @Test
    void playSwitchOptionSound_shouldPlaySwitchOptionClip() {
        resetClipPosition(SoundManager.getSwitchOptionClip());
        SoundManager.playSwitchOptionSound();
        assertTrue(SoundManager.getSwitchOptionClip().isRunning());
    }

    private void resetClipPosition(Clip clip) {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);
    }
}
