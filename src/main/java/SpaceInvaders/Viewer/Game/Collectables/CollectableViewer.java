package SpaceInvaders.Viewer.Game.Collectables;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.Collectables.Collectable;

public class CollectableViewer implements CollectableViewerInterface<Collectable> {
    @Override
    public void draw(GUI gui, Collectable collectable) {
        gui.drawElement(collectable.getPosition(), '\u00c1', "#ff0000");
    }
}
