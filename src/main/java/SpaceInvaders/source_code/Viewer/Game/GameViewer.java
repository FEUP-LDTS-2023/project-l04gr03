package SpaceInvaders.source_code.Viewer.Game;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Game.Arena;
import SpaceInvaders.source_code.Model.Game.Collectables.*;
import SpaceInvaders.source_code.Model.Game.Element;
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
        drawCollectable(gui, getModel().getCollectable());
        gui.drawText(new Position(0,19), "SCORE= ", "#F8F8FF");
        gui.drawText(new Position(8,19), String.valueOf(getModel().getScore()),"#F8F8FF" );

    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(gui, element);
    }

    private void drawCollectable(GUI gui, Collectable collectable){
        if(collectable.getClass() == HealthCollectable.class){
            new HealthCollectableViewer().draw(gui, (HealthCollectable) collectable);
        }
        else if(collectable.getClass() == DamageCollectable.class){
            new DamageCollectableViewer().draw(gui, (DamageCollectable) collectable);
        }
        else if(collectable.getClass() == ScoreCollectable.class){
            new ScoreCollectableViewer().draw(gui, (ScoreCollectable) collectable);
        }
        else if(collectable.getClass() == SpedUpProjectileCollectable.class){
            new SpedUpProjectileCollectableViewer().draw(gui, (SpedUpProjectileCollectable) collectable);
        }
        else if(collectable.getClass() == SplashProjectileCollectable.class){
            new SplashDamageProjectileCollectableViewer().draw(gui, (SplashProjectileCollectable) collectable);
        }
    }

}