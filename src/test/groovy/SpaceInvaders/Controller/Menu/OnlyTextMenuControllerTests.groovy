package SpaceInvaders.Controller.Menu

import SpaceInvaders.Game
import SpaceInvaders.Model.Menu.Leaderboard
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import spock.lang.Specification

class OnlyTextMenuControllerTests extends Specification{

    def "Only Text Menu Controller escape key"(){
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

    def "Only text Menu Controller No key pressed"(){
        given:
            def onlyTextMenuController = Spy(OnlyTextMenuController)
            def key = null
            def game = Mock(Game)
        when:
            onlyTextMenuController.step(game, key, 0)
        then:
            0 * game.setPrevState()
    }
}

