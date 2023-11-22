package SpaceInvaders.source_code.Model.Game.Collectables;

import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.source_code.Model.Position;

public class HealthCollectable extends Collectable<Ship> {

    public HealthCollectable(Position position) {
        super(position);
    }

    @Override
    public void execute(Ship ship) {
        ship.restoreHealth();
    }
}
