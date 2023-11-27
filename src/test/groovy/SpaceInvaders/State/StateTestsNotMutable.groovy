package SpaceInvaders.State


import SpaceInvaders.Controller.Game.ArenaController
import SpaceInvaders.Viewer.Game.GameViewer
import SpaceInvaders.Viewer.Menu.GameOverMenuViewer
import SpaceInvaders.Viewer.Menu.InstructionsViewer
import SpaceInvaders.Viewer.Menu.LeaderboardViewer
import SpaceInvaders.Viewer.Menu.PauseMenuViewer
import SpaceInvaders.Viewer.Menu.StartMenuViewer
import SpaceInvaders.Controller.Menu.*
import spock.lang.Specification

class StateTestsNotMutable extends Specification{


    def "StateActions Start Menu State" () {
        given:
            State state = State.getInstance()
        when:
            state.StateActions()
        then:
            state.controller.getClass() == StartMenuController.class
            state.viewer.getClass() == StartMenuViewer.class
        }



    def "StateActions Pause Menu State" () {
        given:
            State state = State.getInstance()
        when:
            state.UpdateState(GameStates.PAUSE)
            state.StateActions()
        then:
            state.controller.getClass() == PauseMenuController.class
            state.viewer.getClass() == PauseMenuViewer.class
    }

    def "StateActions Game State" () {
        given:
            State state = State.getInstance()
        when:
            state.UpdateState(GameStates.GAME)
            state.StateActions()
        then:
            state.controller.getClass() == ArenaController.class
            state.viewer.getClass() == GameViewer.class
    }

    def "StateActions Leaderboard State" () {
        given:
            State state = State.getInstance()
        when:
            state.UpdateState(GameStates.LEADERBOARD)
            state.StateActions()
        then:
            state.controller.getClass() == OnlyTextMenuController.class
            state.viewer.getClass() == LeaderboardViewer.class
    }



    def "StateActions Game Over State" () {
        given:
            State state = State.getInstance()
        when: 'Game Over State'
            state.UpdateState(GameStates.GAME_OVER)
            state.StateActions()
        then:
            state.controller.getClass() == GameOverController.class
            state.viewer.getClass() == GameOverMenuViewer.class
    }


    def "StateActions Resume Game State"() {
        given:
            State state = State.getInstance()
        when: 'Resume Game State'
            state.UpdateState(GameStates.RESUME_GAME)
            state.StateActions()
        then:
            state.controller.getClass() == ArenaController.class
            state.viewer.getClass() == GameViewer.class
    }


    def "StateActions Instructions State" () {
        given:
        State state = State.getInstance()
        when: 'Instructions State'
        state.UpdateState(GameStates.INSTRUCTIONS)
        state.StateActions()
        then:
        state.controller.getClass() == OnlyTextMenuController.class
        state.viewer.getClass() == InstructionsViewer.class

    }

}

