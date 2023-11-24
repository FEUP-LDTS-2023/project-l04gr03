package SpaceInvaders.source_code.Model.Game.RegularGameElements;

import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Position;

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
