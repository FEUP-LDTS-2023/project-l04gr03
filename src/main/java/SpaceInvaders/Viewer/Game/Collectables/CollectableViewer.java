package SpaceInvaders.Viewer.Game.Collectables;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.Collectables.Collectable;
import SpaceInvaders.Model.Game.Collectables.HealthCollectable;

public class CollectableViewer {
    public void draw(GUI gui, Collectable collectable) {
        if(collectable.getClass() == HealthCollectable.class){
            gui.drawElement(collectable.getPosition(), '\u00c1', "#ff0000");
        }
        else {
            gui.drawElement(collectable.getPosition(), '\u0024', "#009000");
        }
    }
}
