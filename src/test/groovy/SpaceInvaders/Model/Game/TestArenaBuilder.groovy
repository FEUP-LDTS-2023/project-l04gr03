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
        spyArenaBuilderByRound.getBaseAlienHealth() == 20
        spyArenaBuilderByRound.getBaseAlienDamage() == 20
        spyArenaBuilderByRound.getBaseAlienScore() == 20
        1 * spyArenaBuilderByRound.createShip()
        1 * spyArenaBuilderByRound.createAliens()
        1 * spyArenaBuilderByRound.createWalls()
        1 * spyArenaBuilderByRound.createCoverWalls()
    }
}
