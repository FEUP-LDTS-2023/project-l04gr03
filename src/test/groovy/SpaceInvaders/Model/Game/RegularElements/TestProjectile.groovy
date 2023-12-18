package SpaceInvaders.Model.Game.RegularElements

import SpaceInvaders.Model.Game.RegularGameElements.AttackingElement
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Position
import org.mockito.Mock
import spock.lang.Specification

class TestProjectile extends Specification {

    def "GetElement"(){
        given:
            def element = Mock(AttackingElement)
            def projectile = new Projectile(Mock(Position),element)

        when:
            def attackingElement = projectile.getElement()

        then:
            attackingElement == element
    }
}
