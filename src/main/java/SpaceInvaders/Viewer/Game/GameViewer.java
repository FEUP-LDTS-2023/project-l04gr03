package SpaceInvaders.Viewer.Game;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.Collectables.Collectable;
import SpaceInvaders.Model.Game.Element;
import SpaceInvaders.Model.Game.Arena;
import SpaceInvaders.Viewer.Game.RegularElements.*;
import SpaceInvaders.Model.Game.Collectables.*;
import SpaceInvaders.Model.Position;
import SpaceInvaders.Viewer.Game.RegularElements.*;
import SpaceInvaders.Viewer.Viewer;

import java.util.List;

public class GameViewer extends Viewer<Arena> {

    private int alienCharChoice = 0;
    public GameViewer(Arena arena){
        super(arena);
    }

    @Override
    public void drawElements(GUI gui, long time) {
        drawElements(gui, getModel().getAliens(), new AlienViewer(alienCharChoice));
        alienCharChoice++;
        alienCharChoice = alienCharChoice % 2;
        drawElements(gui,getModel().getCoverWalls(), new CoverWallViewer());
        drawElements(gui, getModel().getWalls(), new WallViewer());
        drawElement(gui, getModel().getShip(), new ShipViewer());
        drawElements(gui, getModel().getProjectiles(), new ProjectileViewer());
        drawCollectable(gui, getModel().getCollectable());
        gui.drawText(new Position(5,5), "SCORE = ", "#F8F8FF");
        gui.drawText(new Position(15,5), String.valueOf(getModel().getScore()),"#F8F8FF" );
        gui.drawText(new Position(55,5), "HEALTH = ", "#F8F8FF");
        gui.drawText(new Position(65,5), String.valueOf(getModel().getShip().getHealth()),"#F8F8FF" );
    }


    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(gui, element);
    }

    private void drawCollectable(GUI gui, Collectable collectable){

    }

}
