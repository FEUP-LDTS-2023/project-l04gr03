package SpaceInvaders.source_code.Model.Game.Collectables;

import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.ShipMode;
import SpaceInvaders.source_code.Model.Position;

public class GodModeCollectable extends Collectable<Ship>{

    public GodModeCollectable(Position position, Ship ship){super(position, ship);}
    @Override
    public void execute() {
        getAttackingElement().setShipMode(ShipMode.GOD_MODE);
    }
}
