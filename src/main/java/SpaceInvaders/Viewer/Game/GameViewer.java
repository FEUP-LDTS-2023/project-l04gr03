package SpaceInvaders.Viewer.Game;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.Collectables.Collectable;
import SpaceInvaders.Model.Game.Element;
import SpaceInvaders.Model.Game.Arena;
import SpaceInvaders.Viewer.Game.Collectables.CollectableViewer;
import SpaceInvaders.Viewer.Game.RegularElements.*;
import SpaceInvaders.Model.Position;
import SpaceInvaders.Viewer.Viewer;

import java.util.List;

public class GameViewer extends Viewer<Arena> {

    private int alienCharChoice = 0;
    private long lastCharChange = 0;
    public GameViewer(Arena arena){
        super(arena);
    }

    @Override
    public void drawElements(GUI gui, long time) {
        drawElements(gui, getModel().getAliens(), new AlienViewer(alienCharChoice));

        //Changes char for the next frame (only if needed)
        ChangeChar(time);

        drawElements(gui,getModel().getCoverWalls(), new CoverWallViewer());
        drawElements(gui, getModel().getWalls(), new WallViewer());
        drawElement(gui, getModel().getShip(), new ShipViewer());
        drawElements(gui, getModel().getProjectiles(), new ProjectileViewer());
        drawCollectable(gui, getModel().getCollectable(), new CollectableViewer());
        gui.drawText(new Position(5,5), "SCORE = ", "#F8F8FF");
        gui.drawText(new Position(15,5), String.valueOf(getModel().getScore()),"#F8F8FF" );
        gui.drawText(new Position(55,5), "HEALTH = ", "#F8F8FF");
        gui.drawText(new Position(65,5), String.valueOf(getModel().getShip().getHealth()),"#F8F8FF" );
    }

    private void ChangeChar(long time){
        if(time - lastCharChange > 300) {
            alienCharChoice++;
            alienCharChoice = alienCharChoice % 2;
            lastCharChange = time;
        }
    }

    public int getAlienCharChoice(){
        return alienCharChoice;
    }

    public long getLastCharChange(){
        return lastCharChange;
    }




    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(gui, element);
    }

    private void drawCollectable(GUI gui, Collectable collectable, CollectableViewer viewer ){
            viewer.draw(gui,collectable);
    }

}
