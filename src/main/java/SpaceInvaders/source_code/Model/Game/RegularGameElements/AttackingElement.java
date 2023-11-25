package SpaceInvaders.source_code.Model.Game.RegularGameElements;

import SpaceInvaders.source_code.Model.Position;

public class AttackingElement extends DestroyableElement {

    private int damagePerShot;

    public AttackingElement(Position position, int health, int damagePerShot) {
        super(position, health);
        this.damagePerShot = damagePerShot;
    }

    public int getDamagePerShot() {return damagePerShot;}

    public void setDamagePerShot(int damagePerShot){this.damagePerShot = damagePerShot;}

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(this.getClass() != o.getClass() || this == null){
            return false;
        }
        return this.getPosition().equals(((AttackingElement) o).getPosition()) && this.getHealth() == ((AttackingElement) o).getHealth() && this.getDamagePerShot() == ((AttackingElement) o).getDamagePerShot();
    }
}
