package SpaceInvaders;


import SpaceInvaders.GUI.GUI;
import SpaceInvaders.GUI.GUILanterna;
import SpaceInvaders.Model.Position;
import SpaceInvaders.State.GameStates;
import SpaceInvaders.State.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import com.googlecode.lanterna.screen.Screen;

public class Game {

    private State state;

    private GUI gui;

    private int score;

    private final long launchTime;

    private Game() throws IOException, URISyntaxException, FontFormatException {
        Screen screen = null;
        this.gui = new GUILanterna(74,32);
        this.state = State.getInstance();
        this.score = 0;
        this.launchTime = System.currentTimeMillis();
    }

    public State getState() {return state;}

    public long getLaunchTime() {return launchTime;}

    public void setState(GameStates gameStates) throws IOException {state.UpdateState(gameStates);}


    private void startGame() throws IOException {
        int FPS = 30;
        int frameTime = 1000 / FPS;
        while(this.state.getCurrentState() != GameStates.QUIT_GAME){
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


    public void setPrevState() throws IOException {
        state.UpdateToPrevious();
    }
}