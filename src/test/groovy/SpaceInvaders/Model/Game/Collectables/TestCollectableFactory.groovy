package SpaceInvaders.Model.Game.Collectables

import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Position
import spock.lang.Specification

class TestCollectableFactory extends Specification {

    def "CreateCollectable - HealthCollectable"(){
        given:
        Position position = new Position(45,1)
        Ship ship = Mock(Ship)
        CollectableFactory collectableFactory = new CollectableFactory(position,CollectableType.HEALTH,2,ship)
        when:
        Collectable collectable = collectableFactory.createCollectable()
        then:
        collectable.getPosition().equals(new Position(45,1))
        collectable.getAttackingElement() == ship
    }

    def "CreateCollectable - DamageCollectable"(){
        given:
        Position position = new Position(34,1)
        Ship ship = Mock(Ship)
        CollectableFactory collectableFactory = new CollectableFactory(position,CollectableType.DAMAGE,2,ship)
        when:
        CollectableWithMultiplier collectable = collectableFactory.createCollectable() as CollectableWithMultiplier
        then:
        collectable.getPosition().equals(new Position(34,1))
        collectable.getAttackingElement() == ship
        collectable.getMultiplier() == 2
    }

    def "CreateCollectable - ScoreCollectable"(){
        given:
        Position position = new Position(3,1)
        List<Alien> aliens = new ArrayList<>()
        CollectableFactory collectableFactory = new CollectableFactory(position,CollectableType.SCORE,5,aliens)
        when:
        CollectableWithMultiplier collectable = collectableFactory.createCollectable() as CollectableWithMultiplier
        then:
        collectable.getPosition().equals(new Position(3,1))
        collectable.getAttackingElement() == aliens
        collectable.getMultiplier() == 5
    }

    def "CreateCollectable - MachineGunCollectable"(){
        given:
        Position position = new Position(19,1)
        Ship ship = Mock(Ship)
        CollectableFactory collectableFactory = new CollectableFactory(position,CollectableType.MACHINE_GUN_MODE,10,ship)
        when:
        Collectable collectable = collectableFactory.createCollectable()
        then:
        collectable.getPosition().equals(new Position(19,1))
        collectable.getAttackingElement() == ship
    }

    def "CreateCollectable - GodModeCollectable"(){
        given:
        Position position = new Position(2,1)
        Ship ship = Mock(Ship)
        CollectableFactory collectableFactory = new CollectableFactory(position,CollectableType.GOD_MODE,2,ship)
        when:
        Collectable collectable = collectableFactory.createCollectable()
        then:
        collectable.getPosition().equals(new Position(2,1))
        collectable.getAttackingElement() == ship
    }
}
