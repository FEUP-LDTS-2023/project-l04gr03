package SpaceInvaders.Model.Game.Collectables;

import SpaceInvaders.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.Model.Game.RegularGameElements.ShipMode;
import SpaceInvaders.Model.Position;

public class GodModeCollectable extends Collectable<Ship>{

    public GodModeCollectable(Position position, Ship ship){super(position, ship);}
    @Override
    public void execute() {
        getAttackingElement().setShipMode(ShipMode.GOD_MODE);
    }
}
