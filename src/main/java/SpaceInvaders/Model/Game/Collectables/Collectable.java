package SpaceInvaders.Model.Game.Collectables;

import SpaceInvaders.Model.Game.Element;
import SpaceInvaders.Model.Position;

public abstract class Collectable<T> extends Element {
    public Collectable(Position position) {
        super(position);
    }

    public abstract void execute(T element);
}
