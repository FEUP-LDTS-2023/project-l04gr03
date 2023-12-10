package SpaceInvaders.Model.Game.RegularElements

import SpaceInvaders.Model.Game.RegularGameElements.AlienShip
import SpaceInvaders.Model.Game.RegularGameElements.AttackingElement
import SpaceInvaders.Model.Position
import spock.lang.Specification

class TestAttackingElement extends Specification{
    def "Equals different class"(){
        given:
            def element = new AttackingElement(Mock(Position), 0, 0)
            def other = new AlienShip(Mock(Position),0,0,0)
        expect:
            !element.equals(other)
    }

    def "Equals true"(){
        given:
            def element = new AttackingElement(new Position(1,1), 0, 0)
            def element2 = new AttackingElement(new Position(1,1), 0, 0)
        expect:
            element.equals(element2)
    }

    def"Hash code"(){
        given:
            def element = new AttackingElement(Mock(Position), 10, 20)
        expect:
            element.hashCode() == ((31 + 20) * 31 + 10) * 31 + element.getPosition().hashCode()
    }
}

