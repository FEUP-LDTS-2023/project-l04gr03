package SpaceInvaders.Model.Game.Collectables;


import SpaceInvaders.Model.Game.Element;
import SpaceInvaders.Model.Position;

public abstract class Collectable<T> extends Element {

    private T attackingElement;

    public Collectable(Position position, T attackingElement) {
        super(position);
        this.attackingElement = attackingElement;
    }

    public T getAttackingElement() {return attackingElement;}

    public abstract void execute();
}
