package SpaceInvaders.Model.Game.RegularElements

import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.AlienState
import SpaceInvaders.Model.Position
import spock.lang.Specification

class TestAlien extends Specification {
    def "IncreaseScore"(){
        given:
        Position position = Mock(Position)
        AlienState alienState = AlienState.ATTACKING
        Alien alien = new Alien(position,100,50,initialScore,alienState,0)
        expect:
        alien.increaseScore(multiplier)
        finalScore == alien.getScore()
        where:
        initialScore | multiplier | finalScore
             50      |      2     |    100
             200     |      4     |    800
             100     |      3     |    300


    }

    def "Test Alien type bigger or equal to 3" (){
        given:
            Position position = Mock(Position)
            AlienState alienState = AlienState.ATTACKING
            Alien alien = new Alien(position,100,50,0,alienState,3)
            Alien alien2 = new Alien(position,100,50,0,alienState,4)
        expect:
            alien.getType() == 0
            alien2.getType() == 0
    }
}
