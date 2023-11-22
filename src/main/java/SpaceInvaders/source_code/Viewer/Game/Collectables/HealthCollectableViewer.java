package SpaceInvaders.source_code.Viewer.Game.Collectables;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Game.Collectables.HealthCollectable;

public class HealthCollectableViewer implements CollectableViewer<HealthCollectable> {
    private final char HealthChar = '\u00c1';
    private final String HealthColor = "##8B0000";
    @Override
    public void draw(GUI gui, HealthCollectable collectable) {
        gui.drawElement(collectable.getPosition(), HealthChar, HealthColor);
    }
}
