package SpaceInvaders.Model.Game.Collectables


import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Position
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
}
