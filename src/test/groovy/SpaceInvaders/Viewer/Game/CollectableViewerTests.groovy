package SpaceInvaders.Viewer.Game

import SpaceInvaders.GUI.GUI
import SpaceInvaders.Model.Game.Collectables.Collectable
import SpaceInvaders.Model.Game.Collectables.HealthCollectable
import SpaceInvaders.Model.Game.Collectables.MachineGunModeCollectable
import SpaceInvaders.Model.Position
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
    def "Health collectable draw"(){
        given:
            def CollectableViewer = new CollectableViewer()
            def gui = Mock(GUI)
            def collectable = new HealthCollectable(Mock(Position))
        when:
            CollectableViewer.draw(gui, collectable)
        then:
            1 *  gui.drawElement(collectable.getPosition(), '\u00c1', "#ff0000")
    }

    def "Other collectables draw"(){
        given:
            def CollectableViewer = new CollectableViewer()
            def gui = Mock(GUI)
            def collectable = new MachineGunModeCollectable(Mock(Position))
        when:
            CollectableViewer.draw(gui, collectable)
        then:
            1 * gui.drawElement(collectable.getPosition(), '\u0024', "#009000");

    }

}

