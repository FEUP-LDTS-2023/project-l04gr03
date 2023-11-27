package SpaceInvaders.State

import SpaceInvaders.GUI.GUI
import SpaceInvaders.Game
import spock.lang.Specification

class StateTests extends Specification{
    def "Update State"(){
        given:
            State state = State.getInstance();
        when:
            state.UpdateState(GameStates.GAME)
        then:
            state.currentState == GameStates.GAME
    }

    def "Update State When New State is Start Menu"(){
        given:
            State state = State.getInstance();
        when:
            state.UpdateState(GameStates.GAME)
            state.UpdateState(GameStates.GAME_OVER)
            state.UpdateState(GameStates.START_MENU)
        then:
            state.currentState == GameStates.START_MENU
            state.previousState == GameStates.START_MENU
    }

    def "Update to Previous"(){
        given:
            State state = State.getInstance();
        when:
            state.UpdateState(GameStates.GAME)
            state.UpdateToPrevious()
        then:
            state.currentState == GameStates.START_MENU
            state.previousState == GameStates.GAME

    }


    def "State Step"(){
        given:
        GUI gui = Mock(GUI.class)
        Game game = Mock(Game)
        State stateMock = Mock(State)
        when: 'State mocked'
        stateMock.step(gui,game,0)
        then:
        1 * stateMock.step(_,_,_)
    }

}

