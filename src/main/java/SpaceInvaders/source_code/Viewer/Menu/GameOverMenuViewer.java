package SpaceInvaders.source_code.Viewer.Menu;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Menu.GameOverMenu;
import SpaceInvaders.source_code.Model.Position;

public class GameOverMenuViewer extends MenuViewer<GameOverMenu> {

    public GameOverMenuViewer(GameOverMenu menu) {
        super(menu, new Position(5,5));
    }

    @Override
    protected void drawElements(GUI gui) {
        super.drawElements(gui);
        super.drawMenuTitle(gui, "GAME OVER", colorTitle, new Position(5,2));
        gui.drawText(new Position(5,3),"SCORE: ", color );
        gui.drawText(new Position(5,4), "PLAYER NAME: ", color);
    }
}
