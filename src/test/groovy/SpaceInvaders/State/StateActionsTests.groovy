package SpaceInvaders.State


import SpaceInvaders.Controller.Game.ArenaController
import SpaceInvaders.Controller.Sound.SoundManager
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Model.Game.RegularGameElements.AlienShip
import SpaceInvaders.Model.Sound.Sound_Options
import SpaceInvaders.Viewer.Game.GameViewer
import SpaceInvaders.Viewer.Menu.GameOverMenuViewer
import SpaceInvaders.Viewer.Menu.InstructionsViewer
import SpaceInvaders.Viewer.Menu.LeaderboardViewer
import SpaceInvaders.Viewer.Menu.PauseMenuViewer
import SpaceInvaders.Viewer.Menu.StartMenuViewer
import SpaceInvaders.Controller.Menu.*
import org.mockito.MockedStatic
import org.mockito.Mockito
import spock.lang.Specification

class StateActionsTests extends Specification{


    def soundManager = Mockito.mock(SoundManager.class)

    def "StateActions Start Menu State" () {
        given:
            State state = State.getInstance()
        when:
            state.UpdateState(GameStates.START_MENU)
            state.StateActions()
        then:
            state.controller.getClass() == StartMenuController.class
            state.viewer.getClass() == StartMenuViewer.class
        }


    def "StateActions Pause Menu State" () {
        given:
            State state = State.getInstance()
            try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
                utilities.when(SoundManager::getInstance).thenReturn(soundManager)

                when:
                    state.UpdateState(GameStates.PAUSE)
                    state.StateActions()
                then:
                    Mockito.verify(soundManager, Mockito.times(2)).stopAllSounds()
                    state.controller.getClass() == PauseMenuController.class
                    state.viewer.getClass() == PauseMenuViewer.class
            }
    }

    def "StateActions New Game State" () {
        given:
            State state = State.getInstance()
        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)

            when:
                state.UpdateState(GameStates.NEW_GAME)
                state.StateActions()
            then:
                Mockito.verify(soundManager, Mockito.times(2)).playSound(Sound_Options.MUSIC)
                state.controller.getClass() == ArenaController.class
                state.viewer.getClass() == GameViewer.class

        }
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
        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
                utilities.when(SoundManager::getInstance).thenReturn(soundManager)

            when: 'Game Over State'
                state.UpdateState(GameStates.GAME_OVER)
                state.StateActions()
            then:
                Mockito.verify(soundManager, Mockito.times(2)).stopAllSounds()
                state.controller.getClass() == GameOverController.class
                state.viewer.getClass() == GameOverMenuViewer.class
        }
    }


    def "StateActions Resume Game State alien ship null"() {
        given:
            State state = State.getInstance()
            try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
                    utilities.when(SoundManager::getInstance).thenReturn(soundManager)

                when: 'Resume Game State'
                    state.UpdateState(GameStates.RESUME_GAME)
                    state.StateActions()
                then:
                    Mockito.verify(soundManager, Mockito.times(2)).resumePlayingMusic()
                    state.controller.getClass() == ArenaController.class
                    state.viewer.getClass() == GameViewer.class
            }
    }

    def "StateActions Resume Game State alien ship not null"() {
        given:
            State state = State.getInstance()
            def arena = Mock(Arena)
            arena.getAlienShip() >> Mock(AlienShip)
            state.setArena(arena)
        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
                utilities.when(SoundManager::getInstance).thenReturn(soundManager)
            when: 'Resume Game State'
                state.UpdateState(GameStates.RESUME_GAME)
                state.StateActions()
            then:
                state.controller.getClass() == ArenaController.class
                state.viewer.getClass() == GameViewer.class
                Mockito.verify(soundManager, Mockito.times(2)).resumePlayingMusic()
                Mockito.verify(soundManager, Mockito.times(2)).resumePlayingAlienShipSound()
        }
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

    def "StateActions New_Game_Round State"() {
        given:
        State state = State.getInstance()
        def arena = Mock(Arena)
        arena.getScore() >> 10
        state.setArena(arena)
        when: 'New_Game_Round State'
        state.UpdateState(GameStates.NEW_GAME_ROUND)
        then:
        state.controller.getClass() == ArenaController.class
        state.viewer.getClass() == GameViewer.class
        state.getArena().getScore() != 0
    }

}

