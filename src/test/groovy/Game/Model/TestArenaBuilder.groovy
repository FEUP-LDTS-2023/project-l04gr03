package Game.Model

import SpaceInvaders.source_code.Model.Game.Arena
import SpaceInvaders.source_code.Model.Game.ArenaBuilderByRound
import spock.lang.Specification

class TestArenaBuilder extends Specification{

    def "ArenaBuilder"(){
        given:
        int round = 1
        Arena arena
        ArenaBuilderByRound arenaBuilderByRound = new ArenaBuilderByRound(round)
        when:
        arenaBuilderByRound.buildArena()
        then:
        arenaBuilderByRound.getBaseShipHealth() == 100
        arenaBuilderByRound.getBaseShipDamage() == 50
        arenaBuilderByRound.getBaseAlienHealth() == 20
        arenaBuilderByRound.getBaseAlienDamage() == 20
        arenaBuilderByRound.getBaseAlienScore() == 20
        arenaBuilderByRound.getBaseAlienShipHealth() == 200
        arenaBuilderByRound.getBaseAlienShipScore() == 500
        1 * arenaBuilderByRound.createShip()
        1 * arenaBuilderByRound.createAliens()
        1 * arenaBuilderByRound.createWalls()
        1 * arenaBuilderByRound.createCoverWalls()
        1 * arenaBuilderByRound.createAlienShip()
    }
}
