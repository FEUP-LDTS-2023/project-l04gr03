package SpaceInvaders.Model.Game

import spock.lang.Specification

class TestArenaBuilder extends Specification {


    def "ArenaBuilder"() {
        given:
        int round = 1
        ArenaBuilderByRound arenaBuilderByRound = new ArenaBuilderByRound(round)
        def spyArenaBuilderByRound = Spy(arenaBuilderByRound)
        when:
        spyArenaBuilderByRound.buildArena()
        then:
        spyArenaBuilderByRound.getBaseShipHealth() == 100
        spyArenaBuilderByRound.getBaseShipDamage() == 50
        spyArenaBuilderByRound.getBaseAlienHealth() == 10
        spyArenaBuilderByRound.getBaseAlienDamage() == 10
        spyArenaBuilderByRound.getBaseAlienScore() == 20
        spyArenaBuilderByRound.getBaseCoverWallHealth() == 100
        spyArenaBuilderByRound.getRound() == 1
        1 * spyArenaBuilderByRound.createShip()
        1 * spyArenaBuilderByRound.createAliens()
        1 * spyArenaBuilderByRound.createWalls()
        1 * spyArenaBuilderByRound.createCoverWalls()
    }
}
