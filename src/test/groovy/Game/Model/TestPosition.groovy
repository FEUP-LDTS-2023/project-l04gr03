package Game.Model

import SpaceInvaders.source_code.Model.Position
import spock.lang.Specification

class TestPosition extends Specification {
    def "equals true"() {
        given:
        Position pos = new Position(2, 3)
        Position pos2 = new Position(2, 3)
        when:
        boolean result = pos.equals(pos2)
        then:
        result == true
    }

    def "equals false"(){
        given:
        Position pos = new Position(3,3)
        Position pos2 = new Position(4,4)
        when:
        boolean result = pos.equals(pos2)
        then:
        result == false
    }
}
