package SpaceInvaders.Model.Game

import SpaceInvaders.Model.Game.RegularGameElements.Wall
import SpaceInvaders.Model.Menu.Leaderboard
import SpaceInvaders.Model.Position
import spock.lang.Specification

class TestElement extends Specification{
    def "Equals different class"(){
        given:
            def element = new Wall(Mock(Position))
            def other = new Leaderboard()
        expect:
            !element.equals(other)
    }

    def "Equals"(){
        given:
            def element = new Wall(new Position(1,1))
            def element2 = new Wall(new Position(1,1))
        expect:
            element.equals(element2)
    }

    def "Has code"(){
        given:
            def position = new Position(10,1)
            def element = new Wall(position)
        expect:
            element.hashCode() == position.hashCode()
    }

    def "Set position"(){
        given:
            def element = new Wall(Mock(Position))
            def position = new Position (10,1)

        when:
            element.setPosition(position)

        then:
            element.getPosition() == position
    }
}

