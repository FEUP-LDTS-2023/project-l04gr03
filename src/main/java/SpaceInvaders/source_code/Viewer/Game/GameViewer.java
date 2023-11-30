package SpaceInvaders.source_code.Viewer.Game;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Game.Arena;
import SpaceInvaders.source_code.Model.Game.Collectables.*;
import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.AlienMode;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.ShipMode;
import SpaceInvaders.source_code.Model.Position;
import SpaceInvaders.source_code.Viewer.Game.Collectables.*;
import SpaceInvaders.source_code.Viewer.Game.RegularElements.*;
import SpaceInvaders.source_code.Viewer.Viewer;

import java.util.List;

public class GameViewer extends Viewer<Arena> {

    public GameViewer(Arena arena){
        super(arena);
    }

    @Override
    public void drawElements(GUI gui) {
        drawElements(gui, getModel().getAliens(), new AlienViewer(0));
        drawElements(gui,getModel().getCoverWalls(), new CoverWallViewer());
        drawElements(gui, getModel().getWalls(), new WallViewer());
        drawElement(gui, getModel().getShip(), new ShipViewer());
        drawElements(gui, getModel().getProjectiles(), new ProjectileViewer());
        drawCollectable(gui, getModel().getActiveCollectable(), new CollectableViewer());
        gui.drawText(new Position(5,5), "SCORE = ", "#F8F8FF");
        gui.drawText(new Position(15,5), String.valueOf(getModel().getScore()),"#F8F8FF" );
        gui.drawText(new Position(55,5), "HEALTH = ", "#F8F8FF");
        gui.drawText(new Position(65,5), String.valueOf(getModel().getShip().getHealth()),"#F8F8FF" );
        if(getModel().getShip().getShipMode() != ShipMode.NORMAL_MODE){
            gui.drawText(new Position(55,8), String.valueOf(getModel().getShip().getShipMode()),"#F8F8FF");
        }
        if(!getModel().getAliens().isEmpty()){
            if(getModel().getAliens().get(0).getAlienMode() != AlienMode.NORMAL_MODE){
                gui.drawText(new Position(55,10), String.valueOf(getModel().getAliens().get(0).getAlienMode()),"#F8F8FF");
            }
        }
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(gui, element);
    }

    private void drawCollectable(GUI gui, Collectable collectable, CollectableViewer viewer){
        if(collectable != null){
            viewer.draw(gui, collectable);
        }
    }

}
