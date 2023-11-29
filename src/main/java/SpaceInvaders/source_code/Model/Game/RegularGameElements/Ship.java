package SpaceInvaders.source_code.Model.Game.RegularGameElements;

import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Position;

public class Ship extends AttackingElement {

    private int damagePerShot;

    private ShipMode shipMode;

    public Ship(Position position, int health, int damagePerShot){
        super(position,health,damagePerShot);
        this.shipMode = shipMode;
    }

    public void increaseDamage(int increaseRatio){
        this.setDamagePerShot(getDamagePerShot() * increaseRatio);
    }

    public void restoreHealth(){
        this.setHealth(100);
    }

    public void setShipMode(ShipMode shipMode) {this.shipMode = shipMode;}
}
