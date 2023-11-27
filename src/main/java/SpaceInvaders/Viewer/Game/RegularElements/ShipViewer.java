package SpaceInvaders.Viewer.Game.RegularElements;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.RegularGameElements.Ship;

public class ShipViewer implements ElementViewer<Ship> {

    final private char ShipChar = '\u00c0';
    final private String ShipColor = "#42E9F4";
    @Override
    public void draw(GUI gui, Ship element) {
        gui.drawElement(element.getPosition(), ShipChar, ShipColor );
    }
}
