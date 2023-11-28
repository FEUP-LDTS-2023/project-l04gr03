package SpaceInvaders.Viewer.Menu;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Menu.PauseMenu;
import SpaceInvaders.Model.Position;

public class PauseMenuViewer extends MenuViewer<PauseMenu> {

    public PauseMenuViewer(PauseMenu menu){
        super(menu, new Position(35, 13));
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        drawOptions(gui);
        super.drawMenuTitle(gui, "PAUSE MENU", colorTitle, new Position(35, 10));
    }




}
