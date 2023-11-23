package SpaceInvaders.source_code.State

import SpaceInvaders.source_code.Controller.Game.ArenaController
import SpaceInvaders.source_code.Controller.Menu.GameOverController
import SpaceInvaders.source_code.Controller.Menu.OnlyTextMenuController
import SpaceInvaders.source_code.Controller.Menu.PauseMenuController
import SpaceInvaders.source_code.Controller.Menu.StartMenuController
import SpaceInvaders.source_code.GUI.GUI
import SpaceInvaders.source_code.Game
import SpaceInvaders.source_code.Model.Menu.StartMenu
import SpaceInvaders.source_code.Viewer.Game.GameViewer
import SpaceInvaders.source_code.Viewer.Menu.GameOverMenuViewer
import SpaceInvaders.source_code.Viewer.Menu.InstructionsViewer
import SpaceInvaders.source_code.Viewer.Menu.LeaderboardViewer
import SpaceInvaders.source_code.Viewer.Menu.PauseMenuViewer
import SpaceInvaders.source_code.Viewer.Menu.StartMenuViewer
import spock.lang.Specification

public class StateTests extends Specification{
    def "State Constructor"(){
        given:
            State state = new State();
        expect:
            state.viewer.getClass() == StartMenuViewer.class
            state.controller.getClass() == StartMenuController.class
            state.currentState == GameStates.START_MENU
            state.previousState == GameStates.START_MENU
    }

    def "Update State"(){
        given:
            State state = new State();
        when:
            state.UpdateState(GameStates.GAME)
        then:
            state.currentState == GameStates.GAME
            state.previousState == GameStates.START_MENU
    }

    def "Update State When New State is Start Menu"(){
        given:
            State state = new State()
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
            State state = new State()
        when:
            state.UpdateState(GameStates.GAME)
            state.UpdateToPrevious()
        then:
            state.currentState == GameStates.START_MENU
            state.currentState == GameStates.START_MENU

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

    def "StateActions" (){
        given:
            State state = new State()

        when: 'Start Menu State'
            state.StateActions()
        then:
            state.controller.getClass() == StartMenuController.class
            state.viewer.getClass() == StartMenuViewer.class

        when: 'Pause Menu State'
            state.UpdateState(GameStates.PAUSE)
            state.StateActions()
        then:
            state.controller.getClass() == PauseMenuController.class
            state.viewer.getClass() == PauseMenuViewer.class

        when: 'Game State'
            state.UpdateState(GameStates.GAME)
            state.StateActions()
        then:
            state.controller.getClass() == ArenaController.class
            state.viewer.getClass() == GameViewer.class

        when: 'Leaderboard State'
            state.UpdateState(GameStates.LEADERBOARD)
            state.StateActions()
        then:
            state.controller.getClass() == OnlyTextMenuController.class
            state.viewer.getClass() == LeaderboardViewer.class

        when: 'Game Over State'
            state.UpdateState(GameStates.GAME_OVER)
            state.StateActions()
        then:
            state.controller.getClass() == GameOverController.class
            state.viewer.getClass() == GameOverMenuViewer.class

        when: 'Resume Game State'
            state.UpdateState(GameStates.RESUME_GAME)
            state.StateActions()
        then:
            state.controller.getClass() == ArenaController.class
            state.viewer.getClass() == GameViewer.class

        when: 'Instructions State'
            state.UpdateState(GameStates.INSTRUCTIONS)
            state.StateActions()
        then:
            state.controller.getClass() == OnlyTextMenuController.class
            state.viewer.getClass() == InstructionsViewer.class

    }
}

