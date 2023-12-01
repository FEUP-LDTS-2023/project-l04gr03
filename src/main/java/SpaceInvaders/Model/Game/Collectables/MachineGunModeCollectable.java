package SpaceInvaders.Model.Game.Collectables;


import SpaceInvaders.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.Model.Game.RegularGameElements.ShipMode;
import SpaceInvaders.Model.Position;

public class MachineGunModeCollectable extends Collectable<Ship>{

    public MachineGunModeCollectable(Position position, Ship ship){super(position, ship);}

    @Override
    public void execute() {
        getAttackingElement().setShipMode(ShipMode.MACHINE_GUN_MODE);
    }
}
