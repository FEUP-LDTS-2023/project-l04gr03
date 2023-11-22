package SpaceInvaders.source_code.State;

import SpaceInvaders.source_code.Model.Game.Arena;

public class State {

    GameStates currentState;
    GameStates previousState;

    Arena arena;

    public State(Arena arena){
        currentState = GameStates.START_MENU;
        previousState = GameStates.START_MENU;
        this.arena = arena;
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
                break;

            case PAUSE:
                break;

            case GAME:
                break;

            case LEADERBOARD:
                break;

            case GAME_OVER:
                break;

            case RESUME_GAME:
                break;  
            case INSTRUCTIONS:
                break;
        }

    }

}
