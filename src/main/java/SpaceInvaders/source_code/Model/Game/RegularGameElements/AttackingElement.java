package SpaceInvaders.source_code.Model.Game.RegularGameElements;

import SpaceInvaders.source_code.Model.Position;

public class AttackingElement extends DestroyableElement {

    private int damagePerShot;

    public AttackingElement(Position position, int health, int damagePerShot) {
        super(position, health);
        this.damagePerShot = damagePerShot;
    }

    public int getDamagePerShot() {return damagePerShot;}
}
