package SpaceInvaders.source_code.Viewer.Menu;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Menu.Instructions;
import SpaceInvaders.source_code.Model.Menu.OnlyTextMenu;
import SpaceInvaders.source_code.Model.Position;

public class InstructionsViewer extends OnlyTextMenuViewer<Instructions> {
    public InstructionsViewer(Instructions menu){
        super(menu, new Position());
    }

    @Override
    protected void drawElements(GUI gui) {
        super.drawElements(gui);
        super.drawMenuTitle(gui, colorTitle, new Position());
    }
}
