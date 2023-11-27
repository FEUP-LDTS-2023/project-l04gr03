package SpaceInvaders.Model.Game.RegularGameElements;

import SpaceInvaders.Model.Position;

public class Ship extends AttackingElement {

    private int damagePerShot;

    public Ship(Position position, int health, int damagePerShot){
        super(position,health,damagePerShot);
    }

    public void increaseDamage(int increaseRatio){
        this.setDamagePerShot(getDamagePerShot() * increaseRatio);
    }

    public void restoreHealth(){
        this.setHealth(100);
    }
}
