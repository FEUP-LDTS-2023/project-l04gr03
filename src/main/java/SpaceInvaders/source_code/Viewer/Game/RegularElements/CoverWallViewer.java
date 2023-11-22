package SpaceInvaders.source_code.Viewer.Game.RegularElements;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.CoverWall;

public class CoverWallViewer implements ElementViewer<CoverWall> {

    @Override
    public void draw(GUI gui, CoverWall element) {
        char coverWallChar = '\u00d2';
        String coverWallColor = "#F83B3A";
        gui.drawElement(element.getPosition(), coverWallChar, coverWallColor);
    }
}
