package SpaceInvaders.source_code.Viewer.Game.RegularElements;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;

public class ShipViewer implements ElementViewer<Ship> {

    final private String ShipChar = "À";
    @Override
    public void draw(GUI gui, Ship element) {
       // gui.drawShip();
    }
}
