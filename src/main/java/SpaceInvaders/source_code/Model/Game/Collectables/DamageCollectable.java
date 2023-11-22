package SpaceInvaders.source_code.Model.Game.Collectables;
import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.source_code.Model.Position;

<<<<<<< HEAD
public class DamageCollectable implements Collectable {
=======
public class DamageCollectable extends CollectableWithMultiplier<Ship> {

    public DamageCollectable(Position position, int multiplier){
        super(position,multiplier);
    }

    @Override
    public void execute(Ship ship) {
        ship.increaseDamage(this.getMultiplier());
    }
>>>>>>> 660561113c5e83e47358b78ba6339ddb5bfbdce8
}
