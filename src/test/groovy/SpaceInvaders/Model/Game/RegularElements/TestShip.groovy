package SpaceInvaders.Model.Game.RegularElements

import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Game.RegularGameElements.ShipMode
import SpaceInvaders.Model.Position
import spock.lang.Specification;

public class TestShip extends Specification {

    def "GetShipDamagePerShot"(){
        given:
        Ship ship = new Ship(Mock(Position),100,20)
        when:
        ship.setShipMode(shipMode)
        then:
        ship.getDamagePerShot() == expectedDamage
        where:
        shipMode                 || expectedDamage
        ShipMode.NORMAL_MODE     || 20
        ShipMode.DAMAGE_2X       || 40
        ShipMode.DAMAGE_3X       || 60
        ShipMode.DAMAGE_4X       || 80
        ShipMode.DAMAGE_5X       || 100
        ShipMode.DAMAGE_10X      || 200
        ShipMode.GOD_MODE        || 20
    }

    def "RestoreHealth"(){
        given:
        def position= Mock(Position)
        Ship ship = new Ship(position,70,50)
        when:
        ship.restoreHealth()
        then:
        ship.getHealth() == 100
    }

    def "DecreaseHealthInNormalMode"(){
        given:
        Ship ship = new Ship(Mock(Position),100,50)
        newHealth = ship.getHealth()
        expect:
        ship.decreaseHealth(damage)
        where:
        damage | newHealth
          20   |   80
          30   |   70
          100  |   0
          120  |  -20

    }

    def "DecreaseHealthInGodMode"(){
        given:
        Ship ship = new Ship(Mock(Position),100,50)
        when:
        ship.setShipMode(ShipMode.GOD_MODE)
        ship.decreaseHealth(damage)
        then:
        ship.getHealth() == 100
        where:
        damage << [0, 20, 40, 60, 80, 100, 200]
    }

    def "IncreaseDamage"(){
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

    def "SetShipMode"(){
        given:
        Ship ship = new Ship(Mock(Position),100,20)
        when:
        ship.setShipMode(shipMode)
        then:
        ship.getShipMode() == shipMode
        where:
        shipMode << [ShipMode.NORMAL_MODE,ShipMode.DAMAGE_2X,ShipMode.DAMAGE_3X,ShipMode.DAMAGE_4X,ShipMode.DAMAGE_5X,ShipMode.DAMAGE_10X,
                     ShipMode.MACHINE_GUN_MODE,ShipMode.GOD_MODE]
    }
}
