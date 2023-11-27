package SpaceInvaders.Model.Game.Collectables;

import SpaceInvaders.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.Model.Position;

public class HealthCollectable extends Collectable<Ship> {

    public HealthCollectable(Position position) {
        super(position);
    }

    @Override
    public void execute(Ship ship) {
        ship.restoreHealth();
    }
}
