package SpaceInvaders.source_code.Viewer.Menu;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Menu.GameOverMenu;
import SpaceInvaders.source_code.Model.Position;

public class GameOverMenuViewer extends MenuViewer<GameOverMenu> {

    public GameOverMenuViewer(GameOverMenu menu) {
        super(menu, new Position());
    }

    @Override
    protected void drawElements(GUI gui) {
        super.drawElements(gui);
        super.drawMenuTitle(gui, "GAME OVER", colorTitle, new Position());
        gui.drawText(new Position(),"SCORE: ", color );
        gui.drawText(new Position(), "PLAYER NAME: ", color);
    }
}
