package SpaceInvaders.Controller.Menu

import SpaceInvaders.Game
import SpaceInvaders.Model.Menu.StartMenu
import SpaceInvaders.State.GameStates
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import spock.lang.Specification

class StartMenuControllerTests extends Specification{
    def "Start Menu"() {
        given:
        StartMenu startMenu = Mock(StartMenu)
        def StartMenuController = new StartMenuController(startMenu)
        def game = Mock(Game)

        when: 'ArrowDown Key'
        def key = new KeyStroke(KeyType.ArrowDown)
        StartMenuController.step(game, key, 0)
        then:
        1 * startMenu.nextOption()
    }

    def "Step ArrowUp Key"() {
        given:
            StartMenu startMenu = Mock(StartMenu)
            def StartMenuController = new StartMenuController(startMenu)
            def game = Mock(Game)
        when: 'ArrowUp Key'
            def key = new KeyStroke(KeyType.ArrowUp)
            StartMenuController.step(game, key, 0)
        then:
            1 * startMenu.previousOption()
    }

    def "Step Enter Key is selected Play"(){
        given:
            StartMenu startMenu = Mock(StartMenu)
            def StartMenuController = Spy(StartMenuController)
            def game = Mock(Game)
            StartMenuController.getModel() >> startMenu
            startMenu.isSelectedPlay() >> true
        when: 'Enter key'
            def key = new KeyStroke(KeyType.Enter)
            StartMenuController.step(game,key,0)
        then:
            1 * game.setState(GameStates.GAME)
    }

    def "Step Enter Key is selected Leaderboard"(){
        given:
            StartMenu startMenu = Mock(StartMenu)
            def StartMenuController = Spy(StartMenuController)
            def game = Mock(Game)
            StartMenuController.getModel() >> startMenu
            startMenu.isSelectedPlay() >> false
            startMenu.isSelectedLeaderboard() >> true
        when: 'Enter key'
            def key = new KeyStroke(KeyType.Enter)
            StartMenuController.step(game,key,0)
        then:
            1 * game.setState(GameStates.LEADERBOARD)
    }

    def "Step Enter Key is selected Leaderboard"(){
        given:
            StartMenu startMenu = Mock(StartMenu)
            def StartMenuController = Spy(StartMenuController)
            def game = Mock(Game)
            StartMenuController.getModel() >> startMenu
            startMenu.isSelectedPlay() >> false
            startMenu.isSelectedLeaderboard() >> false
            startMenu.isSelectedInstructions() >> true
        when: 'Enter key'
            def key = new KeyStroke(KeyType.Enter)
            StartMenuController.step(game,key,0)
        then:
            1 * game.setState(GameStates.INSTRUCTIONS)
    }

    def "Step Enter Key is selected Leaderboard"(){
        given:
            StartMenu startMenu = Mock(StartMenu)
            def StartMenuController = Spy(StartMenuController)
            def game = Mock(Game)
            StartMenuController.getModel() >> startMenu
            startMenu.isSelectedPlay() >> false
            startMenu.isSelectedLeaderboard() >> false
            startMenu.isSelectedInstructions() >> false
            startMenu.isSelectedExit() >> true
        when: 'Enter key'
            def key = new KeyStroke(KeyType.Enter)
            StartMenuController.step(game,key,0)
        then:
            1 * game.setState(GameStates.QUIT_GAME)
    }

    def "Step no key pressed"(){
        given:
            def startMenuController = Spy(StartMenuController)
            def key = null
        when:
            startMenuController.step(Mock(Game), key, 0)
        then:
            0 * startMenuController.getModel()
    }


}

