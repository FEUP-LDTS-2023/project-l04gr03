package SpaceInvaders.Controller.Menu

import SpaceInvaders.Game
import SpaceInvaders.Model.Menu.GameOverMenu
import SpaceInvaders.State.GameStates
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import spock.lang.Specification

class GameOverMenuTests extends Specification{
    def "Step arrow down key"() {
        given:
            def gameOverMenu = Mock(GameOverMenu)
            def gameOverController = new GameOverController(gameOverMenu)
            def game = Mock(Game)

        when: 'ArrowDown Key'
            def key = new KeyStroke(KeyType.ArrowDown)
            gameOverController.step(game, key, 0)

        then:
            1 * gameOverMenu.nextOption()
    }
        def "Step ArrowUp key" () {
            given:
                def gameOverMenu = Mock(GameOverMenu)
                def gameOverController = new GameOverController(gameOverMenu)
                def game = Mock(Game)
            when: 'ArrowUp Key'
                def key = new KeyStroke(KeyType.ArrowUp)
                gameOverController.step(game, key, 0)
            then:
                1 * gameOverMenu.previousOption()
        }
    def "Step Enter Key"() {
        given:
            def gameOverMenu = Mock(GameOverMenu)
            def gameOverController = Spy(GameOverController)
            gameOverController.getModel() >> gameOverMenu
            def game = Mock(Game)
        when: 'Enter key'
            def key = new KeyStroke(KeyType.Enter)
            gameOverController.step(game, key, 0)
        then:
            1 * gameOverController.getModel().isSelectedRestart()
            1 * gameOverController.getModel().isSelectedLeaderboard()
            1 * gameOverController.getModel().isSelectedExit()
    }

    def "Step Enter Key is selected Restart"(){
        given:
        def gameOverMenu = Mock(GameOverMenu)
        def gameOverController = Spy(GameOverController.class)
        gameOverController.getModel() >> gameOverMenu
        gameOverMenu.isSelectedRestart() >> true
        gameOverMenu.getUsername() >> "R"
        gameOverMenu.getScore() >> 0
        def game = Mock(Game)
        when: 'Enter key'
        def key = new KeyStroke(KeyType.Enter)
        gameOverController.step(game, key, 0)
        then:
            1 * game.setState(GameStates.NEW_GAME)
            1 * gameOverController.UpdateLeaderboard(0,_)
    }

    def "Step Enter Key is selected Exit"(){
        given:
            def gameOverMenu = Mock(GameOverMenu)
            def gameOverController = Spy(GameOverController.class)
            gameOverController.getModel() >> gameOverMenu
            gameOverMenu.isSelectedRestart() >> false
            gameOverMenu.isSelectedExit() >> true
            gameOverMenu.getUsername() >> ""
            gameOverMenu.getScore() >> 0
            def game = Mock(Game)
        when: 'Enter key'
            def key = new KeyStroke(KeyType.Enter)
            gameOverController.step(game, key, 0)
        then:
            1 * game.setState(GameStates.START_MENU)
            1 * gameOverController.UpdateLeaderboard(0,_)
    }

    def "Step Enter key is selected Leaderboard"(){
        given:
        def gameOverMenu = Mock(GameOverMenu)
        def gameOverController = Spy(GameOverController.class)
        gameOverController.getModel() >> gameOverMenu
        gameOverMenu.isSelectedRestart() >> false
        gameOverMenu.isSelectedExit() >> false
        gameOverMenu.isSelectedLeaderboard() >> true
        gameOverMenu.getUsername() >> ""
        def game = Mock(Game)
        when: 'Enter key'
        def key = new KeyStroke(KeyType.Enter)
        gameOverController.step(game, key, 0)
        then:
        1 * game.setState(GameStates.LEADERBOARD)
    }

    def "Step Character Key"() {
        given:
            def gameOverMenu = Mock(GameOverMenu)
            def gameOverController = new GameOverController(gameOverMenu)
            def game = Mock(Game)
        when: 'Character key'
            def key = new KeyStroke('A' as Character, false, false)
            gameOverController.step(game, key, 0)
        then:
            1 * gameOverMenu.addLetter(_)
    }

    def "Step No key pressed"(){
        given:
            def gameOverController = Spy(GameOverController.class)
            def game = Mock(Game)
        when: 'Enter key'
            def key = null
            gameOverController.step(game, key, 0)
        then:
            0 * gameOverController.getModel()
    }

    def "step backSpace key"(){
        given:
            def gameOverMenu = Mock(GameOverMenu)
            def gameOverController = new GameOverController(gameOverMenu)
            def game = Mock(Game)
        when: 'BackSpace key'
            def key = new KeyStroke(KeyType.Backspace)
            gameOverController.step(game,key,0)
        then:
            1 * gameOverMenu.removeLetter()
    }

}

