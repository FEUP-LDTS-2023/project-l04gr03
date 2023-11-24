package SpaceInvaders.source_code.Viewer.Game.Collectables;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Game.Collectables.Collectable;

public class CollectableViewer implements CollectableViewerInterface<Collectable> {
    @Override
    public void draw(GUI gui, Collectable collectable) {
        gui.drawElement(collectable.getPosition(), '\u00c1', "#ff0000");
    }
}
