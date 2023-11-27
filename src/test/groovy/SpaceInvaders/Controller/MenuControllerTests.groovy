package SpaceInvaders.Controller

import SpaceInvaders.Controller.Menu.GameOverController
import SpaceInvaders.Controller.Menu.OnlyTextMenuController
import SpaceInvaders.Controller.Menu.PauseMenuController
import SpaceInvaders.Controller.Menu.StartMenuController
import SpaceInvaders.Game
import SpaceInvaders.Model.Menu.GameOverMenu
import SpaceInvaders.Model.Menu.Leaderboard
import SpaceInvaders.Model.Menu.PauseMenu
import SpaceInvaders.Model.Menu.StartMenu
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import spock.lang.Specification

class MenuControllerTests extends Specification{
    def "Game Over Menu"(){
        given:
            def gameOverMenu = Mock(GameOverMenu)
            def gameOverController = new GameOverController(gameOverMenu)
            def game = Mock(Game)

        when: 'ArrowDown Key'
            def key = new KeyStroke(KeyType.ArrowDown)
            gameOverController.step(game,key,0)
        then:
            1 * gameOverMenu.nextOption()

        when: 'ArrowUp Key'
            key = new KeyStroke(KeyType.ArrowUp)
            gameOverController.step(game, key, 0)
        then:
            1 * gameOverMenu.previousOption()

        when: 'Enter key'
            key = new KeyStroke(KeyType.Enter)
            gameOverController.step(game,key,0)
        then:
            1 * gameOverController.getModel().isSelectedRestart()
            1 * gameOverController.getModel().isSelectedLeaderboard()
            1 * gameOverController.getModel().isSelectedExit()

        when: 'Character key'
            key = new KeyStroke('A' as Character, false, false)
            gameOverController.step(game,key,0)
        then:
            1 * gameOverMenu.addLetter(_)

        when: 'BackSpace key'
            key = new KeyStroke(KeyType.Backspace)
            gameOverController.step(game,key,0)
        then:
            1 * gameOverMenu.removeLetter()
    }

    def "Pause Menu"(){
        given:
        PauseMenu pauseMenu = Mock(PauseMenu)
            def PauseMenuController = new PauseMenuController(pauseMenu)
            def game = Mock(Game)

        when: 'ArrowDown Key'
            def key = new KeyStroke(KeyType.ArrowDown)
            PauseMenuController.step(game,key,0)
        then:
            1 * pauseMenu.nextOption()

        when: 'ArrowUp Key'
            key = new KeyStroke(KeyType.ArrowUp)
            PauseMenuController.step(game, key, 0)
        then:
            1 * pauseMenu.previousOption()

        when: 'Enter key'
            key = new KeyStroke(KeyType.Enter)
            def PauseMenu2 = new PauseMenu()
            def PauseMenuController2 = new SpaceInvaders.Controller.Menu.PauseMenuController(PauseMenu2)
            PauseMenuController2.step(game,key,0)
        then:
            1 * game.setState(_)

    }

    def "Start Menu"(){
        given:
            StartMenu startMenu = Mock(StartMenu)
            def StartMenuController = new StartMenuController(startMenu)
            def game = Mock(Game)

        when: 'ArrowDown Key'
            def key = new KeyStroke(KeyType.ArrowDown)
           StartMenuController.step(game,key,0)
        then:
            1 * startMenu.nextOption()

        when: 'ArrowUp Key'
            key = new KeyStroke(KeyType.ArrowUp)
            StartMenuController.step(game, key, 0)
        then:
            1 * startMenu.previousOption()

        when: 'Enter key'
            key = new KeyStroke(KeyType.Enter)
            def StartMenu2 = new StartMenu()
            def StartMenuController2 = new SpaceInvaders.Controller.Menu.StartMenuController(StartMenu2)
            StartMenuController2.step(game,key,0)
        then:
            1 * game.setState(_)

    }

    def "Only Text Menu Controller"(){
        given:
            def onlyTextMenu = new Leaderboard()
            def onlyTextMenuController = new OnlyTextMenuController(onlyTextMenu)
            def key = new KeyStroke(KeyType.Escape)
            def game = Mock(Game)
        when:
            onlyTextMenuController.step(game,key,0)
        then:
            1 * game.setPrevState()


    }
}

