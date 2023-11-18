package Model.RegularElements

import SpaceInvaders.source_code.Model.Game.RegularGameElements.DestroyableElement
import SpaceInvaders.source_code.Model.Position
import spock.lang.Specification

class TestDestroyableElement extends Specification {
    def "decrease health"(){
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
}
