package SpaceInvaders.Viewer.Game

import SpaceInvaders.GUI.GUI
import SpaceInvaders.Model.Game.Collectables.Collectable
import SpaceInvaders.Viewer.Game.Collectables.CollectableViewer
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

