package SpaceInvaders.State

import SpaceInvaders.GUI.GUI
import SpaceInvaders.Game
import SpaceInvaders.Model.Game.Arena
import spock.lang.Specification

class StateTests extends Specification{
    def "Update State"(){
        given:
            State state = State.getInstance();
        when:
            state.UpdateState(GameStates.NEW_GAME)
        then:
            state.currentState == GameStates.NEW_GAME
    }

    def "Update State When New State is Start Menu"(){
        given:
            State state = State.getInstance();
        when:
            state.UpdateState(GameStates.NEW_GAME)
            state.UpdateState(GameStates.GAME_OVER)
            state.UpdateState(GameStates.START_MENU)
        then:
            state.currentState == GameStates.START_MENU
            state.previousState == GameStates.START_MENU
    }

    def "Update to Previous"(){
        given:
            def state = State.getInstance()
        when:
            state.UpdateState(GameStates.NEW_GAME)
            state.UpdateToPrevious()
        then:
            state.currentState == GameStates.START_MENU
            state.previousState == GameStates.NEW_GAME

    }




    def "State Step"(){
        given:
        GUI gui = Mock(GUI.class)
        Game game = Mock(Game.class)
        State stateMock = Mock(State.class)
        when: 'State mocked'
        stateMock.step(gui,game,0)
        then:
        1 * stateMock.step(_,_,_)
    }

    def "Get arena"(){
        given:
            def state = State.getInstance()
        expect:
            state.getArena().getClass() == Arena.class
    }

}

