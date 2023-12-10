package SpaceInvaders.State

import SpaceInvaders.Controller.Controller
import SpaceInvaders.Controller.Sound.SoundManager
import SpaceInvaders.GUI.GUI
import SpaceInvaders.Game
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Viewer.Viewer
import org.mockito.MockedStatic
import org.mockito.Mockito
import spock.lang.Specification

class StateTests extends Specification{

    def soundManager = Mockito.mock(SoundManager.class)

    def "Update State"(){
        given:
            State state = State.getInstance()
            when:
                state.UpdateState(GameStates.NEW_GAME)
            then:
                state.getCurrentState() == GameStates.NEW_GAME
    }

    def "Update State When New State is Start Menu"(){
        given:
            State state = State.getInstance()
            when:
                state.UpdateState(GameStates.NEW_GAME)
                state.UpdateState(GameStates.GAME_OVER)
                state.UpdateState(GameStates.START_MENU)
            then:
                state.getCurrentState() == GameStates.START_MENU
                state.getPreviousState() == GameStates.START_MENU

    }

    def "Update to Previous"(){
        given:
            def state = State.getInstance()
            def stateSpy = Spy(state)
            when:
                stateSpy.UpdateState(GameStates.NEW_GAME)
                stateSpy.UpdateToPrevious()
            then:
                stateSpy.currentState == GameStates.START_MENU
                stateSpy.previousState == GameStates.NEW_GAME
                2 * stateSpy.StateActions()

    }


    def "State Step"() {
        given:
            GUI gui = Mock(GUI.class)
            Game game = Mock(Game.class)
            State state = State.getInstance()
            def controller = Mock(Controller)
            def viewer = Mock(Viewer)
            state.setController(controller)
            state.setViewer(viewer)
            def stateSpy = Spy(state)

        when: 'State Spy'
            stateSpy.step(gui, game, 0)
        then:
            1 * gui.getNextAction()
            1 * viewer.draw(_, _)
            1 * controller.step(_, _, _)
    }

    def "Get arena"(){
        given:
            def state = State.getInstance()
            state.setArena(new Arena(10,10))
        expect:
            state.getArena().getClass() == Arena.class
    }

}

