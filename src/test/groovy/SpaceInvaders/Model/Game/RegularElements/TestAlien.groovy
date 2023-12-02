package SpaceInvaders.Model.Game.RegularElements

import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.AlienMode
import SpaceInvaders.Model.Game.RegularGameElements.AlienState
import SpaceInvaders.Model.Position
import spock.lang.Specification

class TestAlien extends Specification {

    def "getScore"(){
        given:
        Alien alien = new Alien(Mock(Position),30,20,20,AlienState.PASSIVE,0)
        when:
        alien.setAlienMode(alienMode)
        then:
        alien.getScore() == expectedScore
        where:
        alienMode                ||     expectedScore
        AlienMode.NORMAL_MODE    ||     20
        AlienMode.SCORE_2X       ||     40
        AlienMode.SCORE_3X       ||     60
        AlienMode.SCORE_4X       ||     80
        AlienMode.SCORE_5X       ||     100
        AlienMode.SCORE_10X      ||     200
    }

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

    def "SetScore"(){
        given:
        Alien alien = new Alien(Mock(Position),20,20,20,AlienState.PASSIVE,0)
        when:
        alien.setScore(score)
        then:
        alien.getScore() == score;
        where:
        score << [20, 40, 60, 80, 100, 200]
    }

    def "SetAlienState"(){
        given:
        Alien alien = new Alien(Mock(Position),20,20,20,AlienState.ATTACKING,0)
        when:
        alien.setAlienState(alienState)
        then:
        alien.getAlienState() == alienState;
        where:
        alienState << [AlienState.ATTACKING, AlienState.PASSIVE]
    }

    def "SetAlienMode"(){
        given:
        Alien alien = new Alien(Mock(Position),20,20,20,AlienState.ATTACKING,0)
        when:
        alien.setAlienMode(alienMode)
        then:
        alien.getAlienMode() == alienMode;
        where:
        alienMode << [AlienMode.NORMAL_MODE, AlienMode.SCORE_2X, AlienMode.SCORE_3X,AlienMode.SCORE_4X,AlienMode.SCORE_5X,AlienMode.SCORE_10X]
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
