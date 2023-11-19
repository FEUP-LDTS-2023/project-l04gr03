package SpaceInvaders.source_code.Viewer.Game.RegularElements;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Game.Element;

public interface ElementViewer<T extends Element> {
    public void draw(GUI gui, T element);
}
