package Model.RegularElements

import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship
import SpaceInvaders.source_code.Model.Position
import spock.lang.Specification;

public class TestShip extends Specification {
    def "restore health"(){
        given:
        def position= Mock(Position)
        Ship ship = new Ship(position,70,50)
        when:
        ship.restoreHealth()
        then:
        ship.getHealth() == 100
    }

    def "increase damage"(){
        given:
        def position = Mock(Position)
        Ship ship = new Ship(position,100,50)
        expect:
        ship.increaseDamage(increaseRatio)
        damagePerShot == ship.getDamagePerShot()
        where:
        increaseRatio | damagePerShot
               2      |       100
               4      |       200
               5      |       250
               10     |       500
    }
}
