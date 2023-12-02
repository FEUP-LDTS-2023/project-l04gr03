package SpaceInvaders.Viewer.Game.RegularElements;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.RegularGameElements.AlienShip;

public class AlienShipViewer implements ElementViewer<AlienShip> {

    @Override
    public void draw(GUI gui, AlienShip element) {
        gui.drawElement(element.getPosition(), '\u00e0', "#DC143C");
    }
}
