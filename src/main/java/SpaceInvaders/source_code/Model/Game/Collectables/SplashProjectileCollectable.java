package SpaceInvaders.source_code.Model.Game.Collectables;

import SpaceInvaders.source_code.Model.Position;

public class SplashProjectileCollectable<T> extends CollectableWithMultiplier<T> {
    public SplashProjectileCollectable(Position position, int multiplier) {
        super(position, multiplier);
    }

    @Override
    public void execute(T element) {

    }
}
