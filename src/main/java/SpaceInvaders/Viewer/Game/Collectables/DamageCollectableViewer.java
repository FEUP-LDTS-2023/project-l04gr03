package SpaceInvaders.Viewer.Game.Collectables;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.Collectables.DamageCollectable;
import SpaceInvaders.Viewer.Game.RegularElements.ElementViewer;

public class DamageCollectableViewer implements ElementViewer<DamageCollectable> {
    @Override
    public void draw(GUI gui, DamageCollectable collectable) {
        gui.drawElement(collectable.getPosition(), '\u00C8', "#FF4500");
    }
}
