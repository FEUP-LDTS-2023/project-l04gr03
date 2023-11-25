package SpaceInvaders.source_code.Viewer.Game.RegularElements

import SpaceInvaders.source_code.Model.Game.RegularGameElements.AttackingElement;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.source_code.Model.Position
import spock.lang.Specification

class TestProjectile extends Specification{
    def "IncreaseSpeed"(){
        given:
        Position position = Mock(Position)
        AttackingElement attackingElement = Mock(AttackingElement)
        Projectile projectile = new Projectile(position,attackingElement,initialSpeed)
        expect:
        projectile.increaseSpeed(multiplier)
        projectile.getSpeed() == finalSpeed
        where:
        initialSpeed | multiplier | finalSpeed
        50      |     2      |     100
        20      |     3      |     60
        25      |     5      |     125
        10      |     10     |     100

    }
}