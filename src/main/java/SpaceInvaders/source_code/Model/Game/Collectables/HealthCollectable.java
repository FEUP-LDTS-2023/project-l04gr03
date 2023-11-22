package SpaceInvaders.source_code.Model.Game.Collectables;

<<<<<<< HEAD
public class HealthCollectable implements Collectable {
=======
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
>>>>>>> 660561113c5e83e47358b78ba6339ddb5bfbdce8
}
