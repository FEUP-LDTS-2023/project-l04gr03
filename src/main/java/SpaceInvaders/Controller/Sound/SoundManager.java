package SpaceInvaders.Controller.Sound;

import SpaceInvaders.Model.Sound.Sound;
import SpaceInvaders.Model.Sound.Sound_Options;

public class SoundManager {
    private final Sound laser;
    private final Sound dyingSound;
    private final Sound switchOption;
    private final Sound backgroundMusic;
    private final Sound collectableSound;
    private final Sound alienShipLowPitch;
    private final Sound alienShipHighPitch;
    private static SoundManager soundManager;

    private SoundManager(){
            this.laser = new Sound("src/main/resources/sounds/shoot.wav");
            this.dyingSound = new Sound("src/main/resources/sounds/invaderkilled.wav");
            this.switchOption = new Sound("src/main/resources/sounds/Menu_option.wav");
            this.backgroundMusic = new Sound("src/main/resources/sounds/spaceinvaders1.wav");
            this.collectableSound = new Sound("src/main/resources/sounds/Collectable.wav");
            this.alienShipHighPitch = new Sound("src/main/resources/sounds/ufo_highpitch.wav");
            this.alienShipLowPitch = new Sound("src/main/resources/sounds/ufo_lowpitch.wav");
    }

    public static SoundManager getInstance(){
        if(soundManager == null){
            soundManager = new SoundManager();
        }
        return soundManager;
    }

    public void playSound(Sound_Options option){
        switch (option){
            case MUSIC -> backgroundMusic.playContinuously();
            case LASER -> laser.play();
            case MENU_SWITCH -> switchOption.play();
            case DESTRUCTION -> dyingSound.play();
            case COLLECTABLE -> collectableSound.play();
            case ALIEN_SHIP_LOW -> alienShipLowPitch.playContinuously();
            case ALIEN_SHIP_HIGH -> alienShipHighPitch.playContinuously();
        }
    }

    public void stopSound(Sound_Options option){
        switch (option){
            case MUSIC -> backgroundMusic.stop();
            case LASER -> laser.stop();
            case MENU_SWITCH -> switchOption.stop();
            case DESTRUCTION -> dyingSound.stop();
            case COLLECTABLE -> collectableSound.stop();
            case ALIEN_SHIP_HIGH -> alienShipHighPitch.stop();
            case ALIEN_SHIP_LOW -> alienShipLowPitch.stop();
        }
    }

    public void resumePlaying(){
        backgroundMusic.resumePlaying();
        alienShipLowPitch.resumePlaying();
        alienShipHighPitch.resumePlaying();
    }
    public void stopAllSounds(){
        backgroundMusic.stop();
        laser.stop();
        switchOption.stop();
        dyingSound.stop();
        collectableSound.stop();
        alienShipHighPitch.stop();
        alienShipLowPitch.stop();
    }

    public boolean isSoundPlaying(Sound_Options option){
        return switch (option){
            case MUSIC -> backgroundMusic.isPlaying();
            case LASER -> laser.isPlaying();
            case MENU_SWITCH -> switchOption.isPlaying();
            case DESTRUCTION -> dyingSound.isPlaying();
            case COLLECTABLE -> collectableSound.isPlaying();
            case ALIEN_SHIP_LOW -> alienShipLowPitch.isPlaying();
            case ALIEN_SHIP_HIGH -> alienShipHighPitch.isPlaying();
        };
    }
}

