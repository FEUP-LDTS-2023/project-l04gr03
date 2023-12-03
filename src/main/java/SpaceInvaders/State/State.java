package SpaceInvaders.State;

import SpaceInvaders.Controller.Menu.GameOverController;
import SpaceInvaders.Controller.Menu.PauseMenuController;
import SpaceInvaders.Controller.Menu.StartMenuController;
import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Menu.*;
import SpaceInvaders.Viewer.Menu.*;
import SpaceInvaders.Controller.Controller;
import SpaceInvaders.Controller.Game.ArenaController;
import SpaceInvaders.Controller.Menu.OnlyTextMenuController;
import SpaceInvaders.Game;
import SpaceInvaders.Model.Game.Arena;
import SpaceInvaders.Model.Game.ArenaBuilderByRound;
import SpaceInvaders.Controller.Menu.*;
import SpaceInvaders.Viewer.Game.GameViewer;
import SpaceInvaders.Viewer.Menu.*;
import SpaceInvaders.Viewer.Viewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.groupcdg.pitest.annotations.DoNotMutate;

import java.io.IOException;

public class State {

    private GameStates currentState;
    private GameStates previousState;

    private Controller controller;
    private Viewer viewer;

   private Arena arena;

   private static  State instance;


    private State(){
        currentState = GameStates.START_MENU;
        previousState = GameStates.START_MENU;
        StartMenu menu = new StartMenu();
        viewer = new StartMenuViewer(menu);
        controller = new StartMenuController(menu);
    }

    public static State getInstance(){
        if(instance == null){
            instance = new State();
        }
        return instance;
    }

    public Arena getArena() {
        return arena;
    }

    public Controller getController() {
        return controller;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public GameStates getCurrentState() {
        return currentState;
    }

    public GameStates getPreviousState() {
        return previousState;
    }

    public void UpdateState(GameStates newState) throws IOException {
        if(newState == GameStates.START_MENU){
            previousState = GameStates.START_MENU;
        }
        else {
            previousState = currentState;
        }
        currentState = newState;
        StateActions();
    }

    public void UpdateToPrevious() throws IOException {
        GameStates aux = currentState;
        currentState = previousState;
        previousState = aux;
        StateActions();
    }

    public void step(GUI gui, Game game, long time) throws IOException {
        KeyStroke key = gui.getNextAction();
        controller.step(game,key,time);
        viewer.draw(gui, time);
    }

    @DoNotMutate
    public void StateActions () throws IOException {

        switch (currentState){
            case START_MENU:
                StartMenu menuS = new StartMenu();
                controller = new StartMenuController(menuS);
                viewer = new StartMenuViewer(menuS);
                break;

            case PAUSE:
                PauseMenu menuP = new PauseMenu();
                controller = new PauseMenuController(menuP);
                viewer = new PauseMenuViewer(menuP);
                break;

            case GAME:
                ArenaBuilderByRound arenaBuilder = new ArenaBuilderByRound(1);
                this.arena = arenaBuilder.buildArena();
                controller = new ArenaController(arena);
                viewer = new GameViewer(arena);
                break;

            case LEADERBOARD:
                Leaderboard leaderboard = new Leaderboard();
                controller = new OnlyTextMenuController(leaderboard);
                viewer = new LeaderboardViewer(leaderboard);
                break;

            case GAME_OVER:
                GameOverMenu menuG = new GameOverMenu(arena.getScore());
                controller = new GameOverController(menuG);
                viewer = new GameOverMenuViewer(menuG);
                break;

            case RESUME_GAME:
                controller = new ArenaController(arena);
                viewer = new GameViewer(arena);
                break;

            case INSTRUCTIONS:
                Instructions instructions = new Instructions();
                controller = new OnlyTextMenuController(instructions);
                viewer = new InstructionsViewer(instructions);
                break;
        }

    }

}
