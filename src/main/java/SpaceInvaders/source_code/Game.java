package SpaceInvaders.source_code;


import SpaceInvaders.source_code.GUI.GUILanterna;
import SpaceInvaders.source_code.State.GameStates;
import SpaceInvaders.source_code.State.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final GUILanterna gui;
    private final State state;

    public Game() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new GUILanterna(20, 20);
        this.state = new State();
    }
    public static void main(String[] args) {

    }

    public void setState(GameStates gameStates) {
        state.UpdateState(gameStates);
    }

    public void setPrevState() {
        state.UpdateToPrevious();
    }
}