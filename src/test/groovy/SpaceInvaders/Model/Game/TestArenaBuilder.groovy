package SpaceInvaders.Model.Game

import spock.lang.Specification

class TestArenaBuilder extends Specification {

    def "ArenaBuilder"() {
        given:
            int round = 1
            ArenaBuilderByRound arenaBuilderByRound = new ArenaBuilderByRound(round)
        when:
            arenaBuilderByRound.buildArena()
        then:
            arenaBuilderByRound.getBaseShipHealth() == 100
            arenaBuilderByRound.getBaseShipDamage() == 50
            arenaBuilderByRound.getBaseAlienHealth() == 20
            arenaBuilderByRound.getBaseAlienDamage() == 20
            arenaBuilderByRound.getBaseAlienScore() == 20
    }
}
