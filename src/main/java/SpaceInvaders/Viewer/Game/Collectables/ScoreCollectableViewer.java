package SpaceInvaders.Viewer.Game.Collectables;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.Collectables.ScoreCollectable;
import SpaceInvaders.Viewer.Game.RegularElements.ElementViewer;

public class ScoreCollectableViewer implements ElementViewer<ScoreCollectable> {
    @Override
    public void draw(GUI gui, ScoreCollectable collectable) {
        gui.drawElement(collectable.getPosition(), '$', "#009000");
    }
}
