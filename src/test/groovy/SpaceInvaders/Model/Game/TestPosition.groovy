package SpaceInvaders.Model.Game

import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Menu.PauseMenu
import SpaceInvaders.Model.Position
import spock.lang.Specification

class TestPosition extends Specification {
    def "equals true"() {
        given:
        Position pos = new Position(2, 3)
        Position pos2 = new Position(2, 3)
        when:
        boolean result = pos.equals(pos2)
        then:
        result
    }

    def "equals false"(){
        given:
        Position pos = new Position(3,3)
        Position pos2 = new Position(4,4)
        when:
        boolean result = pos.equals(pos2)
        then:
        !result
    }

    def "equals false different class"(){
        given:
            def position = new Position(2,2)
            def other = new PauseMenu()
        expect:
            !position.equals(other)
    }

    def "equals same object"(){
        given:
            def position = new Position(2,2)
        expect:
            position.equals(position)
    }

    def "Hash code"(){
        given:
            def position = new Position(2,3)
        expect:
            position.hashCode() == (31 + 2) * 31 + 3
    }
}
