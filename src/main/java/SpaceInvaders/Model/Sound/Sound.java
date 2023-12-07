package SpaceInvaders.Model.Sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Sound {
    private Clip sound;

    public Sound(String filename){
        Path path = Paths.get(filename);
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(path.toFile());
            sound = AudioSystem.getClip();
            sound.open(inputStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void play(){
        sound.setFramePosition(0);
        sound.start();
    }

    public void playContinuously(){
        sound.loop(Clip.LOOP_CONTINUOUSLY);
        play();
    }

    public void stop(){
        sound.stop();
    }

    public boolean isPlaying(){
        return sound.isRunning();
    }

    public Clip getSound(){
        return sound;
    }

    public void setSound(Clip sound){
        this.sound = sound;
    }

}
