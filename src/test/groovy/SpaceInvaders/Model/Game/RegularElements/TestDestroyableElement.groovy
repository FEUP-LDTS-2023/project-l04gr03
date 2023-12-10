package SpaceInvaders.Model.Game.RegularElements

import SpaceInvaders.Model.Game.Collectables.Collectable
import SpaceInvaders.Model.Game.Collectables.HealthCollectable
import SpaceInvaders.Model.Game.RegularGameElements.DestroyableElement
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Position
import spock.lang.Specification

class TestDestroyableElement extends Specification {
    def "DecreaseHealth"(){
        given:
        def position = Mock(Position)
        DestroyableElement destroyableElement = new DestroyableElement(position,initialHealth)
        expect:
        destroyableElement.decreaseHealth(damageTaken)
        finalHealth == destroyableElement.getHealth()
        where:
        initialHealth | finalHealth | damageTaken
        100     |    100      |   0
        200     |    60       |   140
        250     |    80       |   170
        100     |    0        |   100
    }

    def "isDestroyed"(){
        given:
        def position = Mock(Position)
        DestroyableElement destroyableElement = new DestroyableElement(position,health)
        expect:
        isDestroyed == destroyableElement.isDestroyed()
        where:
        health | isDestroyed
        0    |   true
        -10  |   true
        100  |   false
    }

    def "DestroyableElementEquals - False different positions"(){
        given:
        def position1 = Mock(Position)
        def position2 = Mock(Position)
        DestroyableElement destroyableElement1 = new DestroyableElement(position1,0)
        DestroyableElement destroyableElement2 = new DestroyableElement(position2,0)
        when:
        position1.equals(position2) >> false
        then:
        !destroyableElement1.equals(destroyableElement2)
    }

    def "DestroyableElementEquals - False different health"(){
        given:
        def position1 = Mock(Position)
        def position2 = Mock(Position)
        DestroyableElement destroyableElement1 = new DestroyableElement(position1,100)
        DestroyableElement destroyableElement2 = new DestroyableElement(position2,20)
        when:
        position1.equals(position2) >> true
        then:
        !destroyableElement1.equals(destroyableElement2)
    }

    def "DestroyableElementEquals - False different object types"(){
        given:
        def position = Mock(Position)
        def ship = Mock(Ship)
        DestroyableElement destroyableElement1 = new DestroyableElement(position,0)
        when:
        HealthCollectable healthCollectable = new HealthCollectable(position,ship)
        then:
        !destroyableElement1.equals(healthCollectable)
    }

    def "DestroyableElementEquals - True"(){
        given:
        def position1 = Mock(Position)
        def position2 = Mock(Position)
        DestroyableElement destroyableElement1 = new DestroyableElement(position1,100)
        DestroyableElement destroyableElement2 = new DestroyableElement(position2,100)
        when:
        position1.equals(position2) >> true
        then:
        destroyableElement1.equals(destroyableElement2)
    }

    def "DestroyableElementEquals - True same objects"(){
        given:
        def position = Mock(Position)
        DestroyableElement destroyableElement1 = new DestroyableElement(position,100)
        when:
        DestroyableElement destroyableElement2 = destroyableElement1
        then:
        destroyableElement1.equals(destroyableElement2)
    }

    def "Hash code"(){
        given:
            def position = new Position(10,10)
            def destroyableElement = new DestroyableElement(position, 100)
        expect:
            destroyableElement.hashCode() == (31 + 100) * 31 + position.hashCode()
    }
}