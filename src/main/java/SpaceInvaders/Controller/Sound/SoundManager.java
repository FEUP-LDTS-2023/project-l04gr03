package SpaceInvaders.Controller.Sound;

import SpaceInvaders.Model.Sound.Sound;
import SpaceInvaders.Model.Sound.Sound_Options;

import javax.sound.sampled.FloatControl;

public class SoundManager {
    private Sound laser;
    private Sound dyingSound;
    private Sound switchOption;
    private Sound backgroundMusic;
    private Sound collectableSound;
    private Sound alienShipLowPitch;
    private Sound alienShipHighPitch;
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

    public void resumePlayingMusic(){
        backgroundMusic.resumePlaying();
    }

    public void resumePlayingAlienShipSound(){
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

    public void setLaser(Sound laser){
        this.laser = laser;
    }

    public void setDyingSound(Sound dyingSound) {
        this.dyingSound = dyingSound;
    }

    public void setSwitchOption(Sound switchOption) {
        this.switchOption = switchOption;
    }

    public void setBackgroundMusic(Sound backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }

    public void setCollectableSound(Sound collectableSound) {
        this.collectableSound = collectableSound;
    }

    public void setAlienShipLowPitch(Sound alienShipLowPitch) {
        this.alienShipLowPitch = alienShipLowPitch;
    }

    public void setAlienShipHighPitch(Sound alienShipHighPitch) {
        this.alienShipHighPitch = alienShipHighPitch;
    }

}

