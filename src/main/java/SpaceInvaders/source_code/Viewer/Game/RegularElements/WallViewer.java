package SpaceInvaders.source_code.Viewer.Game.RegularElements;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Wall;

public class WallViewer implements ElementViewer<Wall> {


    @Override
    public void draw(GUI gui, Wall element) {
        char coverWallChar = '\u00d2';
        String coverWallColor = "#000080";
        gui.drawElement(element.getPosition(), coverWallChar, coverWallColor);
    }
}
