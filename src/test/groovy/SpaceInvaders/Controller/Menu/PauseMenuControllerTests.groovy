package SpaceInvaders.Controller.Menu

import SpaceInvaders.Controller.Sound.SoundManager
import SpaceInvaders.Game
import SpaceInvaders.Model.Menu.PauseMenu
import SpaceInvaders.Model.Sound.Sound_Options
import SpaceInvaders.State.GameStates
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import org.mockito.MockedStatic
import org.mockito.Mockito
import spock.lang.Specification

class PauseMenuControllerTests extends Specification{

    def soundManager = Mockito.mock(SoundManager.class)
    def pauseMenu = Mock(PauseMenu.class)
    def pauseMenuController = new PauseMenuController(pauseMenu)

    def setup(){
        pauseMenuController = Spy(pauseMenuController)
        pauseMenuController.getModel() >> pauseMenu
    }

    def "step arrowDown Key"() {
        given:
        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)
                PauseMenu pauseMenu = Mockito.mock(PauseMenu)
                def PauseMenuController = new PauseMenuController(pauseMenu)
                def game = Mock(Game)

            when: 'ArrowDown Key'
                def key = new KeyStroke(KeyType.ArrowDown)
                PauseMenuController.step(game, key, 0)

            then:
                Mockito.verify(soundManager, Mockito.times(1)).playSound(Sound_Options.MENU_SWITCH)
                Mockito.verify(pauseMenu, Mockito.times(1)).nextOption()
        }
    }

    def "step ArrowUp key"() {
        given:
        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)
                PauseMenu pauseMenu = Mockito.mock(PauseMenu)
                def PauseMenuController = new PauseMenuController(pauseMenu)
                def game = Mock(Game)

            when: 'ArrowUp Key'
                def key = new KeyStroke(KeyType.ArrowUp)
                PauseMenuController.step(game, key, 0)

            then:
                Mockito.verify(soundManager, Mockito.times(1)).playSound(Sound_Options.MENU_SWITCH)
                Mockito.verify(pauseMenu, Mockito.times(1)).previousOption()
        }
    }

    def "step Enter Key is selected continue"(){
        given:
            def game = Mock(Game)
            pauseMenu.isSelectedContinue() >> true

        when: 'Enter key'
            def key = new KeyStroke(KeyType.Enter)
            pauseMenuController.step(game,key,0)

        then:
            1 * game.setState(GameStates.RESUME_GAME)
    }

    def "step Enter Key is selected instructions"(){
        given:
            def game = Mock(Game)
            pauseMenu.isSelectedContinue() >> false
            pauseMenu.isSelectedInstructions() >> true

        when: 'Enter key'
            def key = new KeyStroke(KeyType.Enter)
            pauseMenuController.step(game,key,0)
        then:
            1 * game.setState(GameStates.INSTRUCTIONS)
    }

    def "step Enter Key is selected Restart"(){
        given:
            def game = Mock(Game)
            pauseMenu.isSelectedContinue() >> false
            pauseMenu.isSelectedInstructions() >> false
            pauseMenu.isSelectedRestart() >> true

        when: 'Enter key'
            def key = new KeyStroke(KeyType.Enter)
            pauseMenuController.step(game,key,0)
        then:
            1 * game.setState(GameStates.NEW_GAME)
    }

    def "step Enter Key is selected Exit"(){
        given:
            def game = Mock(Game)
            pauseMenu.isSelectedContinue() >> false
            pauseMenu.isSelectedInstructions() >> false
            pauseMenu.isSelectedRestart() >> false
            pauseMenu.isSelectedExit() >> true

        when: 'Enter key'
            def key = new KeyStroke(KeyType.Enter)
            pauseMenuController.step(game,key,0)
        then:
            1 * game.setState(GameStates.START_MENU)
    }

    def "Step no key pressed"(){
        given:
            def key = null
        when:
            pauseMenuController.step(Mock(Game),key,0)

        then:
            0 * pauseMenuController.getModel()
    }
}

