package SpaceInvaders.Viewer.Game.Collectables;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.Collectables.MachineGunModeCollectable;
import SpaceInvaders.Viewer.Game.RegularElements.ElementViewer;


public class MachineGunCollectableViewer implements ElementViewer<MachineGunModeCollectable> {
   @Override
    public void draw(GUI gui, MachineGunModeCollectable collectable) {
        gui.drawElement(collectable.getPosition(), '\u00c9', "#B0E0E6");
            }
}

