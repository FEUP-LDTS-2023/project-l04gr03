package SpaceInvaders.Model.Game.RegularGameElements;

import SpaceInvaders.Model.Game.Element;
import SpaceInvaders.Model.Position;

public class Projectile extends Element {


    private final AttackingElement attackingElement;


    public Projectile(Position position, AttackingElement attackingElement) {
        super(position);
        this.attackingElement = attackingElement;
    }


    public AttackingElement getElement(){ return attackingElement;}


    @Override
    public Position getPosition() {
        return super.getPosition();
    }
}
