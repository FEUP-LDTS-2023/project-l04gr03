package SpaceInvaders.source_code.Model.Game.RegularGameElements;

import SpaceInvaders.source_code.Model.Game.Element;
<<<<<<< HEAD

public class Ship extends Element {
=======
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
>>>>>>> 660561113c5e83e47358b78ba6339ddb5bfbdce8
}
