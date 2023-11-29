package SpaceInvaders.source_code.Model.Game.Collectables;

import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.ShipMode;
import SpaceInvaders.source_code.Model.Position;

public class MachineGunModeCollectable extends Collectable<Ship>{

    public MachineGunModeCollectable(Position position, Ship ship){super(position, ship);}

    @Override
    public void execute() {
        getAttackingElement().setShipMode(ShipMode.MACHINE_GUN_MODE);
    }
}
