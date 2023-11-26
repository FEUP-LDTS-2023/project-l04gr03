package SpaceInvaders.source_code.Model.Game.Collectables;

import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.source_code.Model.Position;

public class MachineGunModeCollectable extends Collectable<Ship>{

    public MachineGunModeCollectable(Position position){super(position);}

    @Override
    public void execute(Ship element) {}
}
