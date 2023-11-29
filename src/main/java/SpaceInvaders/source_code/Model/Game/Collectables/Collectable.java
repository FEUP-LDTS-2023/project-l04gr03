package SpaceInvaders.source_code.Model.Game.Collectables;

import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.AttackingElement;
import SpaceInvaders.source_code.Model.Position;

public abstract class Collectable<T> extends Element {

    private T attackingElement;

    public Collectable(Position position, T attackingElement) {
        super(position);
        this.attackingElement = attackingElement;
    }

    public T getAttackingElement() {return attackingElement;}

    public abstract void execute();
}
