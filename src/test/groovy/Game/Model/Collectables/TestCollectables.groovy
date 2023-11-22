package Game.Model.Collectables

import SpaceInvaders.source_code.Model.Game.Collectables.DamageCollectable
import SpaceInvaders.source_code.Model.Game.Collectables.HealthCollectable
import SpaceInvaders.source_code.Model.Game.Collectables.ScoreCollectable
import SpaceInvaders.source_code.Model.Game.Collectables.SpedUpProjectileCollectable
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Alien
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship
import SpaceInvaders.source_code.Model.Position
import spock.lang.Specification

class TestCollectables extends Specification {

    def "HealthCollectable"(){
        given:
        Position position = Mock(Position)
        Ship ship = Mock(Ship)
        HealthCollectable healthCollectable = new HealthCollectable(position)
        when:
        healthCollectable.execute(ship)
        then:
        1 * ship.restoreHealth()
    }

    def "DamageCollectable"(){
        given:
        Position position = Mock(Position)
        Ship ship = Mock(Ship)
        int multiplier = 2
        DamageCollectable damageCollectable = new DamageCollectable(position,multiplier)
        when:
        damageCollectable.execute(ship)
        then:
        1 * ship.increaseDamage(multiplier)
    }

    def "ScoreCollectable"(){
        given:
        Position position = Mock(Position)
        Alien alien = Mock(Alien)
        int multiplier = 3
        ScoreCollectable scoreCollectable = new ScoreCollectable(position,multiplier)
        when:
        scoreCollectable.execute(alien)
        then:
        1 * alien.increaseScore(multiplier)
    }

    def "SpedUpProjectileCollectable"(){
        given:
        Position position = Mock(Position)
        Projectile projectile = Mock(Projectile)
        int multiplier = 4
        SpedUpProjectileCollectable spedUpProjectileCollectable = new SpedUpProjectileCollectable(position,multiplier)
        when:
        spedUpProjectileCollectable.execute(projectile)
        then:
        1 * projectile.increaseSpeed(multiplier)
    }
}
