package SpaceInvaders.Model.Game.Collectables;

import SpaceInvaders.Model.Position;

public abstract class CollectableWithMultiplier<T> extends Collectable<T> {

    private int multiplier;

    public CollectableWithMultiplier(Position position,int multiplier) {
        super(position);
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }

    @Override
    public abstract void execute(T element);
}
