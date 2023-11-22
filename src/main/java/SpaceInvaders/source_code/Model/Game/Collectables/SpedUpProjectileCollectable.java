package SpaceInvaders.source_code.Model.Game.Collectables;

<<<<<<< HEAD
public class SpedUpProjectileCollectable implements Collectable {
=======
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Projectile;
import SpaceInvaders.source_code.Model.Position;

public class SpedUpProjectileCollectable extends CollectableWithMultiplier<Projectile> {

    public SpedUpProjectileCollectable(Position position, int multiplier){
        super(position, multiplier);
    }

    @Override
    public void execute(Projectile projectile) {
        projectile.increaseSpeed(this.getMultiplier());
    }
>>>>>>> 660561113c5e83e47358b78ba6339ddb5bfbdce8
}
