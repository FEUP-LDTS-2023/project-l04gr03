package SpaceInvaders.source_code.Viewer.Menu;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Menu.StartMenu;
import SpaceInvaders.source_code.Model.Position;

public class StartMenuViewer extends MenuViewer<StartMenu> {
    public StartMenuViewer(StartMenu menu){
        super(menu, new Position(35, 13));
    }

    @Override
    protected void drawElements(GUI gui) {
        drawOptions(gui);
        drawMenuTitle(gui, "START MENU", colorTitle, new Position(35,10));
    }

}
