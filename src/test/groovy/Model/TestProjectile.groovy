package Model

import SpaceInvaders.source_code.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.source_code.Model.Position
import spock.lang.Specification

class TestProjectile extends Specification{
    def "increase speed"(){
        given:
        Position position = Mock(Position)
        Projectile projectile = new Projectile(position,initialSpeed)
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
