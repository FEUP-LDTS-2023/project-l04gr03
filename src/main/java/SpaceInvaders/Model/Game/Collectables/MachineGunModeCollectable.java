package SpaceInvaders.Model.Game.Collectables;

import SpaceInvaders.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.Model.Position;

public class MachineGunModeCollectable extends Collectable<Ship>{

    public MachineGunModeCollectable(Position position){super(position);}

    @Override
    public void execute(Ship element) {}
}
