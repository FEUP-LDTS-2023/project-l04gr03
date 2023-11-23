package SpaceInvaders.source_code.State;

import SpaceInvaders.source_code.Controller.Controller;
import SpaceInvaders.source_code.Controller.Game.ArenaController;
import SpaceInvaders.source_code.Controller.Menu.GameOverController;
import SpaceInvaders.source_code.Controller.Menu.OnlyTextMenuController;
import SpaceInvaders.source_code.Controller.Menu.PauseMenuController;
import SpaceInvaders.source_code.Controller.Menu.StartMenuController;
import SpaceInvaders.source_code.Model.Game.Arena;
import SpaceInvaders.source_code.Model.Menu.*;
import SpaceInvaders.source_code.Viewer.Game.GameViewer;
import SpaceInvaders.source_code.Viewer.Menu.*;
import SpaceInvaders.source_code.Viewer.Viewer;

public class State {

    GameStates currentState;
    GameStates previousState;

    Controller controller;


    Viewer viewer;

    Arena arena;

    public State(){
        currentState = GameStates.START_MENU;
        previousState = GameStates.START_MENU;
        StartMenu menu = new StartMenu();
        viewer = new StartMenuViewer(menu);
        controller = new StartMenuController(menu);
    }

    public void UpdateState(GameStates newState){
        if(newState == GameStates.START_MENU){
            previousState = GameStates.START_MENU;
        }
        else {
            previousState = currentState;
        }
        currentState = newState;
    }

    public void UpdateToPrevious(){
        GameStates aux = currentState;
        currentState = previousState;
        previousState = aux;
    }

    public void StateActions (){

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
                this.arena = new Arena(20,20);
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
