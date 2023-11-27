package SpaceInvaders.Model.Game.Collectables;
import SpaceInvaders.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.Model.Position;

public class DamageCollectable extends CollectableWithMultiplier<Ship> {

    public DamageCollectable(Position position, int multiplier){
        super(position,multiplier);
    }

    @Override
    public void execute(Ship ship) {
        ship.increaseDamage(this.getMultiplier());
    }
}
