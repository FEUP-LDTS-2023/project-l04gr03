package Model.RegularElements

import SpaceInvaders.source_code.Model.Game.RegularGameElements.Alien
import SpaceInvaders.source_code.Model.Position
import spock.lang.Specification

class TestAlien extends Specification {
    def "increase score"(){
        given:
        Position position = Mock(Position)
        Alien alien = new Alien(position,100,50,initialScore)
        expect:
        alien.increaseScore(multiplier)
        finalScore == alien.getScore()
        where:
        initialScore | multiplier | finalScore
             50      |      2     |    100
             200     |      4     |    800
             100     |      3     |    300


    }
}
