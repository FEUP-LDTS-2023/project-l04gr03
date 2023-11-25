package SpaceInvaders.source_code;


import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.GUI.GUILanterna;
import SpaceInvaders.source_code.Model.Position;
import SpaceInvaders.source_code.State.GameStates;
import SpaceInvaders.source_code.State.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final GUI gui;
    private final State state;

    public Game() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new GUILanterna(20, 20);
        this.state = State.getInstance();
    }
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        GUI gui = new GUILanterna(20,20);
        gui.drawText(new Position(5,5),"\u00c1", "#FF5400");
        gui.refresh();
    }

    public void setState(GameStates gameStates) {
        state.UpdateState(gameStates);
    }

    public void setPrevState() {
        state.UpdateToPrevious();
    }
}