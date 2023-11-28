package SpaceInvaders.Viewer.Game

import SpaceInvaders.GUI.GUI
import spock.lang.Specification

class GameViewerTests extends Specification{
    def "Draw Elements"(){
        given:
            def gameViewerMocked = Mock(GameViewer)
            def gui = Mock(GUI)
        when:
            gameViewerMocked.drawElements(gui,0)
        then:
            1 * gameViewerMocked.drawElements(_,_)
    }


}

