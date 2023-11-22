package SpaceInvaders.source_code.Model.Game.RegularGameElements;

import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Position;

public class Ship extends DestroyableElement {

    private int damagePerShot;

    public Ship(Position position, int health, int damagePerShot){
        super(position,health);
        this.damagePerShot = damagePerShot;
    }

    public int getDamagePerShot() {
        return damagePerShot;
    }

    public void increaseDamage(int increaseRatio){
        damagePerShot*=increaseRatio;
    }

    public void restoreHealth(){
        this.setHealth(100);
    }
}
