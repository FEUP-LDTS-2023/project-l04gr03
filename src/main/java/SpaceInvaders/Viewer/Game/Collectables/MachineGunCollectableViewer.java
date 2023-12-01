package SpaceInvaders.Viewer.Game.Collectables;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.Collectables.Collectable;
import SpaceInvaders.Model.Game.Collectables.HealthCollectable;
import SpaceInvaders.Model.Game.Collectables.MachineGunModeCollectable;
import SpaceInvaders.Viewer.Game.RegularElements.ElementViewer;


public class MachineGunCollectableViewer implements ElementViewer<MachineGunModeCollectable> {
    public void draw(GUI gui, MachineGunModeCollectable collectable) {
        gui.drawElement(collectable.getPosition(), '\u0024', "#009000");
            }
}

