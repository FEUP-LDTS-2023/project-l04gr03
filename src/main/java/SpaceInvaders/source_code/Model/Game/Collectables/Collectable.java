package SpaceInvaders.source_code.Model.Game.Collectables;

import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Position;

public abstract class Collectable<T> extends Element {
    public Collectable(Position position) {
        super(position);
    }

    public abstract void execute(T element);
}
