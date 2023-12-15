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

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.nio.file.Files
import java.nio.file.Paths

class GameOverMenuControllerTests extends Specification {
    def soundManager = Mockito.mock(SoundManager.class)

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
            def gameOverMenu = Mock(GameOverMenu)
            def gameOverController = Spy(GameOverController)
            gameOverController.getModel() >> gameOverMenu
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

            def gameOverMenu = Mock(GameOverMenu)
            def gameOverController = Spy(GameOverController.class)
            def bufferMock = Mock(BufferedWriter.class)
            def file = new File("src/main/resources/text/Leaderboard.txt")
            def game = Mock(Game)

            gameOverController.getModel() >> gameOverMenu
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

            def gameOverMenu = Mock(GameOverMenu)
            def gameOverController = Spy(GameOverController.class)
            def game = Mock(Game)
            def bufferMock = Mock(BufferedWriter.class)
            def file = new File("src/main/resources/text/Leaderboard.txt")
            def username = ""

            gameOverController.getModel() >> gameOverMenu
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
            def gameOverMenu = Mock(GameOverMenu)
            def gameOverController = Spy(GameOverController.class)
            gameOverController.getModel() >> gameOverMenu
            gameOverMenu.isSelectedRestart() >> false
            gameOverMenu.isSelectedExit() >> false
            gameOverMenu.isSelectedLeaderboard() >> true
            def username = ""
            gameOverMenu.getUsername() >> username
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

    def "update Leaderboard"(){
        given:
            def controller = new GameOverController(Mock(GameOverMenu))
            def controllerSpy = Spy(controller)
            def bufferMock = Mock(BufferedWriter.class)
            def file = new File("src/main/resources/text/Leaderboard.txt");
            controllerSpy.createBuffer(file) >> bufferMock

        when:
            controllerSpy.updateLeaderboard(0,"")
        then:
            1 * bufferMock.write("Unknown 0\n")
            1 * bufferMock.flush()
            1 * bufferMock.close()
    }

    def "createBuffer"(){
        given:
            def controller = new GameOverController(Mock(GameOverMenu))
            def file = new File("src/main/resources/text/Leaderboard.txt")
        expect:
            controller.createBuffer(file).getClass() == BufferedWriter.class
            controller.createBuffer(file) != null
    }


}

