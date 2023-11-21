package SpaceInvaders.source_code.Viewer.Menu;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Menu.PauseMenu;
import SpaceInvaders.source_code.Model.Position;

public class PauseMenuViewer extends MenuViewer<PauseMenu> {

    public PauseMenuViewer(PauseMenu menu){
        super(menu, new Position(5, 3));
    }

    @Override
    protected void drawElements(GUI gui) {
        drawOptions(gui);
        super.drawMenuTitle(gui, "PAUSE MENU", colorTitle, new Position(5, 2));
    }




}
