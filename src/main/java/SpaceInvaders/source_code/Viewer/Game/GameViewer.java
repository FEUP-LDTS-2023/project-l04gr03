package SpaceInvaders.source_code.Viewer.Game;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Game.Arena;
import SpaceInvaders.source_code.Model.Game.Collectables.Collectable;
import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Viewer.Game.RegularElements.ElementViewer;
import SpaceInvaders.source_code.Viewer.Viewer;

import java.util.List;

public class GameViewer extends Viewer<Arena> {

    public GameViewer(Arena arena){
        super(arena);
    }

    @Override
    public void drawElements(GUI gui) {

    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(gui, element);
    }

    private void drawCollectables(GUI gui, List<Collectable> collectables){

    }

}
