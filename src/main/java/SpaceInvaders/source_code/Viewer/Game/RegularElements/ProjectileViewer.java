package SpaceInvaders.source_code.Viewer.Game.RegularElements;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Projectile;

public class ProjectileViewer implements ElementViewer<Projectile> {

    @Override
    public void draw(GUI gui, Projectile element) {
        char projectileViewerChar = '*';
        String projectileColor = "#5353F1";
    //    gui.drawElement(element.getPosition, projectileViewerChar,projectileColor);
    }
}
