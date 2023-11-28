package SpaceInvaders.Viewer.Menu;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Menu.StartMenu;
import SpaceInvaders.Model.Position;

public class StartMenuViewer extends MenuViewer<StartMenu> {
    public StartMenuViewer(StartMenu menu){
        super(menu, new Position(35, 13));
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        drawOptions(gui);
        drawMenuTitle(gui, "START MENU", colorTitle, new Position(35,10));
    }

}
