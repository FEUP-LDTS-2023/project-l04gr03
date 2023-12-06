package SpaceInvaders.Viewer.Game.RegularElements;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.RegularGameElements.Projectile;

public class ProjectileViewer implements ElementViewer<Projectile> {

    @Override
    public void draw(GUI gui, Projectile element) {
        char projectileViewerChar = '*';
        String projectileColor = "#5353F1";
        gui.drawElement(element.getPosition(), projectileViewerChar,projectileColor);
    }
}
