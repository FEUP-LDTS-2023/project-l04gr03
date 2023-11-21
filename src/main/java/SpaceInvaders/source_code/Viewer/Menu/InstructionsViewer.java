package SpaceInvaders.source_code.Viewer.Menu;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Menu.Instructions;
import SpaceInvaders.source_code.Model.Menu.OnlyTextMenu;
import SpaceInvaders.source_code.Model.Position;

public class InstructionsViewer extends OnlyTextMenuViewer<Instructions> {
    public InstructionsViewer(Instructions menu){
        super(menu, new Position(5,3));
    }

    @Override
    protected void drawElements(GUI gui) {
        drawFileText(gui);
        super.drawMenuTitle(gui,"INSTRUCTIONS", colorTitle, new Position(5,2));
    }
}
