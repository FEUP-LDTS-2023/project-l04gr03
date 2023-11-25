package SpaceInvaders.source_code.Model.Game.RegularGameElements;

import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Position;

public class Projectile extends Element {

    private int speed;

    private final AttackingElement attackingElement;

    private int damage;

    public Projectile(Position position, AttackingElement attackingElement, int speed) {
        super(position);
        this.attackingElement = attackingElement;
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public AttackingElement getElement(){ return attackingElement; }

    public void increaseSpeed(int increaseRatio){
        speed*=increaseRatio;
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }
}
