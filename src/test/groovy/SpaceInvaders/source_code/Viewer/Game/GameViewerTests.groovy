package SpaceInvaders.source_code.Viewer.Game

import SpaceInvaders.source_code.GUI.GUI
import SpaceInvaders.source_code.Model.Game.Arena
import spock.lang.Specification

class GameViewerTests extends Specification{
    def "Draw Elements"(){
        given:
            def gameViewerMocked = Mock(GameViewer)
            def gui = Mock(GUI)
        when:
            gameViewerMocked.drawElements(gui)
        then:
            1 * gameViewerMocked.drawElements(_)
    }


}

