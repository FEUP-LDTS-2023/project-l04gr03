package SpaceInvaders.Model.Game.RegularElements

import SpaceInvaders.Model.Game.RegularGameElements.AlienShip
import SpaceInvaders.Model.Position
import spock.lang.Specification

class TestAlienShip extends Specification{

    def "GetScore"(){
        given:
            def alienShip = new AlienShip(Mock(Position),50,500,0)

        when:
            int score = alienShip.getScore()

        then:
            score == 500
    }

    def "GetMovementDirection"(){
        given:
            def alienShip = new AlienShip(Mock(Position),50,500,1)

        when:
            int movementDirection = alienShip.getMovementDirection()

        then:
            movementDirection == 1
    }
}
