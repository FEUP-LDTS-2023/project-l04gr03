package SpaceInvaders.source_code.Model.Game

import SpaceInvaders.source_code.Model.Position
import spock.lang.Specification

class TestArenaModifier extends Specification{

    def "CreateCollectableTest"(){
        given:
        Arena arena = Mock(Arena)
        ArenaModifier arenaModifier = new ArenaModifier(arena)
        when:
        arenaModifier.createCollectable(new Position(0,0),"health",1)
        then:
        1 * arena.setCollectable()
    }
}
