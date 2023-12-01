package SpaceInvaders.Viewer.Game.Collectables;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.Collectables.HealthCollectable;
import SpaceInvaders.Viewer.Game.RegularElements.ElementViewer;

public class HealthCollectableViewer implements ElementViewer<HealthCollectable> {
    @Override
    public void draw(GUI gui, HealthCollectable collectable) {
        gui.drawElement(collectable.getPosition(), '\u00c1', "#ff0000");
    }
}
