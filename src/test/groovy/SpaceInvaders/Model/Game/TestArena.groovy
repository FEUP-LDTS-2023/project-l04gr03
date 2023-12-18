package SpaceInvaders.Model.Game

import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.AlienShip
import SpaceInvaders.Model.Game.RegularGameElements.AlienState
import SpaceInvaders.Model.Game.RegularGameElements.CoverWall
import SpaceInvaders.Model.Game.RegularGameElements.Wall
import SpaceInvaders.Model.Position
import spock.lang.Specification

class TestArena extends Specification{

    def "width"(){
        given:
            def arena = new Arena(10,10)

        expect:
            arena.getWidth() == 10
    }

    def "height"(){
        given:
            def arena = new Arena(10,10)

        expect:
            arena.getHeight() == 10
    }

    def "Round"(){
        given:
            def arena = new Arena(10,10)

        when:
            arena.setRound(2)
        then:
            arena.getRound() == 2
    }

    def "Score"(){
        given:
            def arena = new Arena(10,10)

        when:
            arena.increaseScore(10)
        then:
            arena.getScore() == 10
    }

    def "get walls"(){
        given:
            def arena = new Arena(10,10)
            def wall = Mock(Wall)
            def walls = Arrays.asList(wall)

        when:
            arena.setWalls(walls)

        then:
            arena.getWalls() == walls
    }

    def "get attackingAliens"(){
        given:
            def arena = new Arena(10,10)
            def alien1 = new Alien(new Position(1,1), 1,1,1, AlienState.PASSIVE,1)
            def alien2 = new Alien(new Position(1,2), 1,1,1, AlienState.ATTACKING,1)
            def alien3 = new Alien(new Position(1,5), 1,1,1, AlienState.ATTACKING,1)
            def aliens = Arrays.asList(alien1,alien2, alien3)
            def attackingAliens = Arrays.asList(alien2, alien3)

            arena.setAliens(aliens)

        expect:
            arena.getAttackingAliens() == attackingAliens
    }

    def "free arena position - has alien"(){
        given:
            def arena = new Arena(10,10)
            def alien1 = new Alien(new Position(1,1), 1,1,1, AlienState.PASSIVE,1)
            def aliens = Arrays.asList(alien1)

            arena.setAliens(aliens)

        expect:
            !arena.freeArenaPosition(new Position(1,1))
    }

    def "free arena position - has coverWall"(){
        given:
            def arena = new Arena(10,10)
            def alien1 = new Alien(new Position(1,1), 1,1,1, AlienState.PASSIVE,1)
            def aliens = Arrays.asList(alien1)
            def coverWall = new CoverWall(new Position(2,2),1)
            def coverWalls = Arrays.asList(coverWall)

            arena.setAliens(aliens)
            arena.setCoverWalls(coverWalls)

        expect:
            !arena.freeArenaPosition(new Position(2,2))
    }

    def "free arena position - has alien ship"(){
        given:
            def arena = new Arena(10,10)
            def alien1 = new Alien(new Position(1,1), 1,1,1, AlienState.PASSIVE,1)
            def aliens = Arrays.asList(alien1)
            def coverWall = new CoverWall(new Position(2,2),1)
            def coverWalls = Arrays.asList(coverWall)
            def alienShip = new AlienShip(new Position(4,4), 10,10,1)

            arena.setAlienShip(alienShip)
            arena.setAliens(aliens)
            arena.setCoverWalls(coverWalls)

        expect:
            !arena.freeArenaPosition(new Position(4,4))
    }

    def "free arena position"(){
        given:
            def arena = new Arena(10,10)
            def alien1 = new Alien(new Position(1,1), 1,1,1, AlienState.PASSIVE,1)
            def aliens = Arrays.asList(alien1)
            def coverWall = new CoverWall(new Position(2,2),1)
            def coverWalls = Arrays.asList(coverWall)
            def alienShip = new AlienShip(new Position(4,4), 10,10,1)

            arena.setAlienShip(alienShip)
            arena.setAliens(aliens)
            arena.setCoverWalls(coverWalls)

        expect:
            arena.freeArenaPosition(new Position(3,4))
    }

    def "free arena position alien ship null"(){
        given:
            def arena = new Arena(10,10)
            def alien1 = new Alien(new Position(1,1), 1,1,1, AlienState.PASSIVE,1)
            def aliens = Arrays.asList(alien1)
            def coverWall = new CoverWall(new Position(2,2),1)
            def coverWalls = Arrays.asList(coverWall)

            arena.setCoverWalls(coverWalls)
            arena.setAlienShip(null)
            arena.setAliens(aliens)

        expect:
            arena.freeArenaPosition(new Position(3,4))
    }

    def "Free columns"(){
        given:
            def arena = new Arena(5,5)
            def alien1 = new Alien(new Position(1,1), 1,1,1, AlienState.PASSIVE,1)
            def aliens = Arrays.asList(alien1)
            def coverWall = new CoverWall(new Position(2,2),1)
            def coverWalls = Arrays.asList(coverWall)
            def alienShip = new AlienShip(new Position(4,4), 10,10,1)

            arena.setAlienShip(alienShip)
            arena.setAliens(aliens)
            arena.setCoverWalls(coverWalls)

        expect:
            arena.getFreeArenaColumns() == Arrays.asList(0,3)
    }

    def "Free columns kill boundary changed mutations"(){
        given:
        def arena = new Arena(5,5)
        def arenaSpy = Spy(arena)
        def alien1 = new Alien(new Position(1,1), 1,1,1, AlienState.PASSIVE,1)
        def aliens = Arrays.asList(alien1)
        def coverWall = new CoverWall(new Position(2,2),1)
        def coverWalls = Arrays.asList(coverWall)
        def alienShip = new AlienShip(new Position(4,4), 10,10,1)

        arenaSpy.setAlienShip(alienShip)
        arenaSpy.setAliens(aliens)
        arenaSpy.setCoverWalls(coverWalls)

        when:
            arenaSpy.getFreeArenaColumns()
        then:
            0 * arenaSpy.freeArenaPosition(new Position(5,0))
            0 * arenaSpy.freeArenaPosition(new Position(0,5))

    }
}

