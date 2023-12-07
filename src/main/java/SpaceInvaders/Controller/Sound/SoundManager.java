package SpaceInvaders.Controller.Sound;

import SpaceInvaders.Model.Sound.Sound;
import SpaceInvaders.Model.Sound.Sound_Options;

public class SoundManager {
    private final Sound laser;
    private final Sound dyingSound;
    private final Sound switchOption;
    private final Sound backgroundMusic;

    private static SoundManager soundManager;

    private SoundManager(){
            this.laser = new Sound("src/main/resources/sounds/shoot.wav");
            this.dyingSound = new Sound("src/main/resources/sounds/invaderkilled.wav");
            this.switchOption = new Sound("src/main/resources/sounds/light-switch-156813.wav");
            this.backgroundMusic = new Sound("src/main/resources/sounds/spaceinvaders1.wav");
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
        }
    }

    public void stopSound(Sound_Options option){
        switch (option){
            case MUSIC -> backgroundMusic.stop();
            case LASER -> laser.stop();
            case MENU_SWITCH -> switchOption.stop();
            case DESTRUCTION -> dyingSound.stop();
        }
    }
    public void stopAllSounds(){
        backgroundMusic.stop();
        laser.stop();
        switchOption.stop();
        dyingSound.stop();
    }

    public boolean isSoundPlaying(Sound_Options option){
        return switch (option){
            case MUSIC -> backgroundMusic.isPlaying();
            case LASER -> laser.isPlaying();
            case MENU_SWITCH -> switchOption.isPlaying();
            case DESTRUCTION -> dyingSound.isPlaying();
        };
    }
}

