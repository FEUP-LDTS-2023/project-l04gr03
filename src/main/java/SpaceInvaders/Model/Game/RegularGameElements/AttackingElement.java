package SpaceInvaders.Model.Game.RegularGameElements;

import SpaceInvaders.Model.Position;

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
        if(!(o instanceof AttackingElement)){
            return false;
        }
        return this.getPosition().equals(((AttackingElement) o).getPosition()) && this.getHealth() == ((AttackingElement) o).getHealth() && this.getDamagePerShot() == ((AttackingElement) o).getDamagePerShot();
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = prime + damagePerShot;
        result = prime * result + getHealth();
        result = prime * result + getPosition().hashCode();
        return result;
    }
}
