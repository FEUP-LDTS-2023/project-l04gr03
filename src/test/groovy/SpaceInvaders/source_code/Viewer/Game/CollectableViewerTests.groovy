package SpaceInvaders.source_code.Viewer.Game

import SpaceInvaders.source_code.GUI.GUI
import SpaceInvaders.source_code.Model.Game.Collectables.Collectable
import SpaceInvaders.source_code.Model.Game.Collectables.HealthCollectable
import SpaceInvaders.source_code.Viewer.Game.Collectables.CollectableViewer
import SpaceInvaders.source_code.Viewer.Game.Collectables.CollectableViewerInterface
import spock.lang.Specification

class CollectableViewerTests extends Specification{
    def "Collectable draw"(){
        given:
            def CollectableViewer = new CollectableViewer()
            def gui = Mock(GUI)
            def collectable = Mock(Collectable)
        when:
            CollectableViewer.draw(gui,collectable)
        then:
            1 * gui.drawElement(_,_,_)

    }
}

