package SpaceInvaders.Viewer.Game.RegularElements;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.Element;

public interface ElementViewer<T extends Element> {
    public void draw(GUI gui, T element);


}
