package SpaceInvaders.Viewer.Game.Collectables;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.Collectables.Collectable;

public interface CollectableViewerInterface<T extends Collectable>  {
    public void draw(GUI gui, T collectable);
}
