package SpaceInvaders.source_code.Viewer.Game

import SpaceInvaders.source_code.GUI.GUI
import SpaceInvaders.source_code.Model.Game.Collectables.HealthCollectable
import SpaceInvaders.source_code.Viewer.Game.Collectables.HealthCollectableViewer
import spock.lang.Specification

class CollectableViewerTests extends Specification{
    def "Health Collectable Draw"(){
        given:
            def viewer = new HealthCollectableViewer()
            def HealthCollectable = Mock(HealthCollectable)
            def gui = Mock(GUI)
        when:
            viewer.draw(gui,HealthCollectable)
        then:
            1 * gui.drawElement(_,_,_)
    }
}

