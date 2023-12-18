package SpaceInvaders

import SpaceInvaders.State.GameStates
import spock.lang.Specification

class GameTests extends Specification{
    def "getState"(){
        given:
            def game = Mock(Game)

        when:
            game.getState()

        then:
            1 * game.getState()
    }

    def "setState"(){
        given:
            def game = Mock(Game)

        when:
            game.setState(GameStates.RESUME_GAME)

        then:
            1 * game.setState(_)
    }

    def "setPrevState"(){
        given:
            def game = Mock(Game)

        when:
            game.setPrevState()

        then:
            1 * game.setPrevState()
    }
}

