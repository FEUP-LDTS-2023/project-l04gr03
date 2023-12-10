package SpaceInvaders.Viewer.Menu;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Menu.Leaderboard;
import SpaceInvaders.Model.Position;

public class LeaderboardViewer extends OnlyTextMenuViewer<Leaderboard> {
    public LeaderboardViewer(Leaderboard menu){
        super(menu, new Position(35, 13));
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        drawFileText(gui);
        drawMenuTitle(gui, "LEADERBOARD", colorTitle, new Position(35, 10));
    }


    @Override
    protected void drawFileText(GUI gui) {
        for(int i = 0; i < 5; i++){
            Integer position = i + 1;
            if(i < getModel().getText().size()) {
                gui.drawText(new Position(getReference_x(), getReference_y() + 1+  i), position.toString() + " - " + getModel().getText().get(i), color);
            }
        }
    }
}
