package SpaceInvaders.Controller.Menu

import SpaceInvaders.Controller.Sound.SoundManager
import SpaceInvaders.Game
import SpaceInvaders.Model.Menu.GameOverMenu
import SpaceInvaders.Model.Sound.Sound_Options
import SpaceInvaders.State.GameStates
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import org.mockito.MockedStatic
import org.mockito.Mockito
import spock.lang.Specification


class GameOverMenuControllerTests extends Specification {

    def soundManager = Mockito.mock(SoundManager.class)
    def gameOverMenu = Mock(GameOverMenu.class)
    def gameOverController = new GameOverController(gameOverMenu)

    def setup(){
        gameOverController = Spy(gameOverController)
        gameOverController.getModel() >> gameOverMenu
    }

    def "Step arrow down key"() {
        given:

        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)
                def gameOverMenu = Mockito.mock(GameOverMenu)
                def gameOverController = new GameOverController(gameOverMenu)
                def game = Mock(Game)

            when: 'ArrowDown Key'
                def key = new KeyStroke(KeyType.ArrowDown)
                gameOverController.step(game, key, 0)

            then:
                Mockito.verify(soundManager, Mockito.times(1)).playSound(Sound_Options.MENU_SWITCH)
                Mockito.verify(gameOverMenu, Mockito.times(1)).nextOption()
        }
    }

        def "Step ArrowUp key" () {
            given:

            try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
                utilities.when(SoundManager::getInstance).thenReturn(soundManager)
                        def gameOverMenu = Mockito.mock(GameOverMenu)
                        def gameOverController = new GameOverController(gameOverMenu)
                        def game = Mock(Game)

                    when: 'ArrowUp Key'
                        def key = new KeyStroke(KeyType.ArrowUp)
                        gameOverController.step(game, key, 0)

                    then:
                        Mockito.verify(soundManager, Mockito.times(1)).playSound(Sound_Options.MENU_SWITCH)
                        Mockito.verify(gameOverMenu, Mockito.times(1)).previousOption()
            }
        }
    def "Step Enter Key"() {
        given:
            def game = Mock(Game)
            gameOverMenu.getUsername() >> "R"
            gameOverMenu.getScore() >> 0

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
            def bufferMock = Mock(BufferedWriter.class)
            def file = new File("src/main/resources/text/Leaderboard.txt")
            def game = Mock(Game)

            gameOverMenu.isSelectedRestart() >> true
            gameOverMenu.getUsername() >> "R"
            gameOverMenu.getScore() >> 0
            gameOverController.createBuffer(file) >> bufferMock

        when: 'Enter key'
            def key = new KeyStroke(KeyType.Enter)
            gameOverController.step(game, key, 0)

        then:
            1 * game.setState(GameStates.NEW_GAME)
            1 * gameOverController.updateLeaderboard(0,_)
    }

    def "Step Enter Key is selected Exit"(){
        given:

            def game = Mock(Game)
            def bufferMock = Mock(BufferedWriter.class)
            def file = new File("src/main/resources/text/Leaderboard.txt")
            def username = ""

            gameOverMenu.isSelectedRestart() >> false
            gameOverMenu.isSelectedExit() >> true
            gameOverMenu.getUsername() >> username
            gameOverMenu.getScore() >> 0
            gameOverController.createBuffer(file) >> bufferMock

        when: 'Enter key'
            def key = new KeyStroke(KeyType.Enter)
            gameOverController.step(game, key, 0)

        then:
            1 * game.setState(GameStates.START_MENU)
            1 * gameOverController.updateLeaderboard(0,_)
            username.isEmpty()
    }

    def "Step Enter key is selected Leaderboard"(){
        given:
            def game = Mock(Game)
            def username = ""

            gameOverMenu.isSelectedRestart() >> false
            gameOverMenu.isSelectedExit() >> false
            gameOverMenu.isSelectedLeaderboard() >> true
            gameOverMenu.getUsername() >> username

        when: 'Enter key'
            def key = new KeyStroke(KeyType.Enter)
            gameOverController.step(game, key, 0)

        then:
            1 * game.setState(GameStates.LEADERBOARD)
    }

    def "Step Character Key"() {
        given:
            def game = Mock(Game)

        when: 'Character key'
            def key = new KeyStroke('A' as Character, false, false)
            gameOverController.step(game, key, 0)

        then:
            1 * gameOverMenu.addLetter(_)
    }

    def "Step No key pressed"(){
        given:
            def game = Mock(Game)

        when: 'Enter key'
            def key = null
            gameOverController.step(game, key, 0)

        then:
            0 * gameOverController.getModel()
    }

    def "step backSpace key"(){
        given:
            def game = Mock(Game)

        when: 'BackSpace key'
            def key = new KeyStroke(KeyType.Backspace)
            gameOverController.step(game,key,0)

        then:
            1 * gameOverMenu.removeLetter()
    }

    def "update Leaderboard"(){
        given:
            def bufferMock = Mock(BufferedWriter.class)
            def file = new File("src/main/resources/text/Leaderboard.txt");
            gameOverController.createBuffer(file) >> bufferMock

        when:
            gameOverController.updateLeaderboard(0,"")
        then:
            1 * bufferMock.write("Unknown 0\n")
            1 * bufferMock.flush()
            1 * bufferMock.close()
    }

    def "createBuffer"(){
        given:
            def file = new File("src/main/resources/text/Leaderboard.txt")
        expect:
            gameOverController.createBuffer(file).getClass() == BufferedWriter.class
            gameOverController.createBuffer(file) != null
    }


}

