package SpaceInvaders.Controller.Menu

import SpaceInvaders.Controller.Sound.SoundManager
import SpaceInvaders.Game
import SpaceInvaders.Model.Menu.StartMenu
import SpaceInvaders.Model.Sound.Sound_Options
import SpaceInvaders.State.GameStates
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import org.mockito.MockedStatic
import org.mockito.Mockito
import spock.lang.Specification

class StartMenuControllerTests extends Specification{

    def soundManager = Mockito.mock(SoundManager.class)
    def startMenu = Mock(StartMenu.class)
    def startMenuController = new StartMenuController(startMenu)

    def setup(){
        startMenuController = Spy(startMenuController)
        startMenuController.getModel() >> startMenu
    }

    def "Start Menu"() {
        given:
        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)

                StartMenu startMenu = Mockito.mock(StartMenu)
                def StartMenuController = new StartMenuController(startMenu)
                def game = Mock(Game)

            when: 'ArrowDown Key'
                def key = new KeyStroke(KeyType.ArrowDown)
                StartMenuController.step(game, key, 0)

            then:
                Mockito.verify(soundManager, Mockito.times(1)).playSound(Sound_Options.MENU_SWITCH)
                Mockito.verify(startMenu, Mockito.times(1)).nextOption()
        }
    }

    def "Step ArrowUp Key"() {
        given:
        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)

                StartMenu startMenu = Mockito.mock(StartMenu)
                def StartMenuController = new StartMenuController(startMenu)
                def game = Mock(Game)

            when: 'ArrowUp Key'
                def key = new KeyStroke(KeyType.ArrowUp)
                StartMenuController.step(game, key, 0)

            then:
                Mockito.verify(soundManager, Mockito.times(1)).playSound(Sound_Options.MENU_SWITCH)
                Mockito.verify(startMenu, Mockito.times(1)).previousOption()
        }
    }

    def "Step Enter Key is selected Play"(){
        given:
            def game = Mock(Game)
            startMenu.isSelectedPlay() >> true

        when: 'Enter key'
            def key = new KeyStroke(KeyType.Enter)
            startMenuController.step(game,key,0)

        then:
            1 * game.setState(GameStates.NEW_GAME)
    }

    def "Step Enter Key is selected Leaderboard"(){
        given:
            def game = Mock(Game)
            startMenu.isSelectedPlay() >> false
            startMenu.isSelectedLeaderboard() >> true

        when: 'Enter key'
            def key = new KeyStroke(KeyType.Enter)
            startMenuController.step(game,key,0)

        then:
            1 * game.setState(GameStates.LEADERBOARD)
    }

    def "Step Enter Key is selected Leaderboard"(){
        given:
            def game = Mock(Game)
            startMenu.isSelectedPlay() >> false
            startMenu.isSelectedLeaderboard() >> false
            startMenu.isSelectedInstructions() >> true

        when: 'Enter key'
            def key = new KeyStroke(KeyType.Enter)
            startMenuController.step(game,key,0)

        then:
            1 * game.setState(GameStates.INSTRUCTIONS)
    }

    def "Step Enter Key is selected Leaderboard"(){
        given:
            def game = Mock(Game)
            StartMenuController.getModel() >> startMenu
            startMenu.isSelectedPlay() >> false
            startMenu.isSelectedLeaderboard() >> false
            startMenu.isSelectedInstructions() >> false
            startMenu.isSelectedExit() >> true

        when: 'Enter key'
            def key = new KeyStroke(KeyType.Enter)
            startMenuController.step(game,key,0)

        then:
            1 * game.setState(GameStates.QUIT_GAME)
    }

    def "Step no key pressed"(){
        given:
            def key = null
        when:
            startMenuController.step(Mock(Game), key, 0)
        then:
            0 * startMenuController.getModel()
    }


}

