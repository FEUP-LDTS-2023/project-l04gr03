package SpaceInvaders.Viewer.Menu;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Menu.GameOverMenu;
import SpaceInvaders.Model.Position;

public class GameOverMenuViewer extends MenuViewer<GameOverMenu> {

    public GameOverMenuViewer(GameOverMenu menu) {
        super(menu, new Position(35,13));
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        drawOptions(gui);
        drawMenuTitle(gui, "GAME OVER", colorTitle, new Position(35,10));
        gui.drawText(new Position(5,4),"SCORE: " + getModel().getScore(), color );
        gui.drawText(new Position(5,5), "PLAYER NAME: " + getModel().getUsername(), color);
    }
}
