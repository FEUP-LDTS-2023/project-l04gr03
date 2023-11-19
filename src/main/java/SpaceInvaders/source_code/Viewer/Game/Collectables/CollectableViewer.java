package SpaceInvaders.source_code.Viewer.Game.Collectables;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Game.Collectables.Collectable;

public interface CollectableViewer<T extends Collectable>  {
    public void draw(GUI gui, T collectable);
}
