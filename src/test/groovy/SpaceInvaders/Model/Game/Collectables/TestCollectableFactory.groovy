package SpaceInvaders.Model.Game.Collectables

import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Position
import spock.lang.Specification

class TestCollectableFactory extends Specification {

    def "CreateCollectable - HealthCollectable"(){
        given:
            def position = new Position(45,1)
            def ship = Mock(Ship)
            def collectableFactory = new CollectableFactory(position,CollectableType.HEALTH,2,ship)

        when: 'Health collectable'
            def collectable = collectableFactory.createCollectable()
        then:
            collectable.getPosition().equals(new Position(45,1))
            collectable.getAttackingElement() == ship
    }

    def "CreateCollectable - DamageCollectable"(){
        given:
            def position = new Position(34,1)
            def ship = Mock(Ship)
            def collectableFactory = new CollectableFactory(position,CollectableType.DAMAGE,2,ship)

        when:
            def collectable = collectableFactory.createCollectable() as CollectableWithMultiplier

        then:
            collectable.getPosition().equals(new Position(34,1))
            collectable.getAttackingElement() == ship
            collectable.getMultiplier() == 2
    }

    def "CreateCollectable - ScoreCollectable"(){
        given:
            def position = new Position(3,1)
            List<Alien> aliens = new ArrayList<>()
            def collectableFactory = new CollectableFactory(position,CollectableType.SCORE,5,aliens)

        when:
            def collectable = collectableFactory.createCollectable() as CollectableWithMultiplier

        then:
            collectable.getPosition().equals(new Position(3,1))
            collectable.getAttackingElement() == aliens
            collectable.getMultiplier() == 5
    }

    def "CreateCollectable - MachineGunCollectable"(){
        given:
            def position = new Position(19,1)
            def ship = Mock(Ship)
            def collectableFactory = new CollectableFactory(position,CollectableType.MACHINE_GUN_MODE,10,ship)

        when:
            def collectable = collectableFactory.createCollectable()

        then:
            collectable.getPosition().equals(new Position(19,1))
            collectable.getAttackingElement() == ship
    }

    def "CreateCollectable - GodModeCollectable"(){
        given:
            def position = new Position(2,1)
            def ship = Mock(Ship)
            def collectableFactory = new CollectableFactory(position,CollectableType.GOD_MODE,2,ship)

        when:
            def collectable = collectableFactory.createCollectable()

        then:
            collectable.getPosition().equals(new Position(2,1))
            collectable.getAttackingElement() == ship
    }
}
