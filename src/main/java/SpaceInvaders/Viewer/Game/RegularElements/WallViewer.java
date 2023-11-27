package SpaceInvaders.Viewer.Game.RegularElements;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.RegularGameElements.Wall;

public class WallViewer implements ElementViewer<Wall> {


    @Override
    public void draw(GUI gui, Wall element) {
        char coverWallChar = '\u00d2';
        String coverWallColor = "#000080";
        gui.drawElement(element.getPosition(), coverWallChar, coverWallColor);
    }
}
