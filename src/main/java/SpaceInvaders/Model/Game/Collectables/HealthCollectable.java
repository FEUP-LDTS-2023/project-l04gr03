package SpaceInvaders.Model.Game.Collectables;

import SpaceInvaders.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.Model.Position;

public class HealthCollectable extends Collectable<Ship> {


    public HealthCollectable(Position position, Ship ship) {
        super(position,ship);
    }

    @Override
    public void execute() {
        getAttackingElement().restoreHealth();
    }
}
