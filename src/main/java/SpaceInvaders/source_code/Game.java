package SpaceInvaders.source_code;


import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.GUI.GUILanterna;
import SpaceInvaders.source_code.State.GameStates;
import SpaceInvaders.source_code.State.State;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {

    private State state;

    private GUILanterna gui;

    private int score;

    private Game() throws IOException, URISyntaxException, FontFormatException {
        Screen screen = null;
        this.gui = new GUILanterna(75,30);
        this.state = new State();
        this.score = 0;
    }

    public State getState() {return state;}

    public void setState(GameStates gameStates){state.UpdateState(gameStates);}

    private void startGame() throws IOException {
        int FPS = 20;
        int frameTime = 1000 / FPS;
        while(this.state != null){
            long startTime = System.currentTimeMillis();
            state.step(gui,this,startTime);
            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;
            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }
        gui.close();
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        game.startGame();
    }
}