package SpaceInvaders.source_code.Model.Game.Collectables;

import SpaceInvaders.source_code.Model.Game.RegularGameElements.AttackingElement;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.source_code.Model.Position;

public class HealthCollectable extends Collectable<Ship> {


    public HealthCollectable(Position position, Ship ship) {
        super(position,ship);
    }

    @Override
    public void execute() {
        getAttackingElement().restoreHealth();
    }
}
