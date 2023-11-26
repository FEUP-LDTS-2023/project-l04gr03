package SpaceInvaders.source_code.Model.Game.RegularGameElements;

import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Position;

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
