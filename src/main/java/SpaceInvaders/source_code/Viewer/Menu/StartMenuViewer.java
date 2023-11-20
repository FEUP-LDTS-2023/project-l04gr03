package SpaceInvaders.source_code.Viewer.Menu;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Menu.StartMenu;
import SpaceInvaders.source_code.Model.Position;

public class StartMenuViewer extends MenuViewer<StartMenu> {
    public StartMenuViewer(StartMenu menu){
        super(menu, new Position());
    }

    @Override
    protected void drawElements(GUI gui) {
        super.drawElements(gui);
        super.drawMenuTitle(gui, "START MENU", colorTitle, new Position());
    }

}
