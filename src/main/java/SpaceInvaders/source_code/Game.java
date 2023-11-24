package SpaceInvaders.source_code;


import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.State.State;

public class Game {

    private State state;

    private GUI gui;

    private int score;

    private Game(GUI gui, State state, int score){
        this.gui = gui;
        this.state = state;
        this.score = score;
    }

    public State getState() {return state;}

    public void setState(State state){this.state = state;}

    private void startGame(){}

    public static void main(String[] args) {

    }
}