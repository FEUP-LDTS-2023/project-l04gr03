package SpaceInvaders.Viewer.Game.RegularElements;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.RegularGameElements.CoverWall;

public class CoverWallViewer implements ElementViewer<CoverWall> {

    final static char[] coverWallChars= {'\u00d2','\u00d3','\u00d5', '\u00d4'};

    private int selectCharByHealth(int health){
        if(health > 75){
            return 0;
        }

        else if(health > 50){
            return 1;
        }

        else if(health > 25){
            return 2;
        }

        else if(health > 0){
            return 3;
        }
        return 0;
    }
    @Override
    public void draw(GUI gui, CoverWall element) {
        String coverWallColor = "#F83B3A";
        gui.drawElement(element.getPosition(), coverWallChars[selectCharByHealth(element.getHealth())], coverWallColor);
    }
}
