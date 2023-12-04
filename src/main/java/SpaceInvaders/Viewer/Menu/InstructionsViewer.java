package SpaceInvaders.Viewer.Menu;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Menu.Instructions;
import SpaceInvaders.Model.Position;

public class InstructionsViewer extends OnlyTextMenuViewer<Instructions> {
    public InstructionsViewer(Instructions menu){
        super(menu, new Position(1,2));
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        drawFileText(gui);
        super.drawMenuTitle(gui,"INSTRUCTIONS", colorTitle, new Position(1,1));
    }
}
