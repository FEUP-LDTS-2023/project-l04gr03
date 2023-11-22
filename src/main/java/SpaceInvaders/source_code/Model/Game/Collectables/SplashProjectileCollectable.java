package SpaceInvaders.source_code.Model.Game.Collectables;

<<<<<<< HEAD
public class SplashProjectileCollectable implements Collectable {
=======
import SpaceInvaders.source_code.Model.Position;

public class SplashProjectileCollectable<T> extends CollectableWithMultiplier<T> {
    public SplashProjectileCollectable(Position position, int multiplier) {
        super(position, multiplier);
    }

    @Override
    public void execute(T element) {

    }
>>>>>>> 660561113c5e83e47358b78ba6339ddb5bfbdce8
}
