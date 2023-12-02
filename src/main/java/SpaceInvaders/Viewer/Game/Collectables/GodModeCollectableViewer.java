package SpaceInvaders.Viewer.Game.Collectables;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.Collectables.GodModeCollectable;
import SpaceInvaders.Viewer.Game.RegularElements.ElementViewer;

public class GodModeCollectableViewer implements ElementViewer<GodModeCollectable> {
    @Override
    public void draw(GUI gui, GodModeCollectable collectable) {
        gui.drawElement(collectable.getPosition(), '\u00c7', "#FFFF00");
    }
}
