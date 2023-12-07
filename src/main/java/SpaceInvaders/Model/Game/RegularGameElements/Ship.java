package SpaceInvaders.Model.Game.RegularGameElements;

import SpaceInvaders.Model.Position;

public class Ship extends AttackingElement {

    private final int maxHealth;
    private ShipMode shipMode;

    public Ship(Position position, int health, int damagePerShot){
        super(position,health,damagePerShot);
        this.maxHealth = 100;
        this.shipMode = ShipMode.NORMAL_MODE;
    }

    public ShipMode getShipMode() {return shipMode;}

    public int getMaxHealth() {return maxHealth;}

    @Override
    public int getDamagePerShot(){
        return switch (shipMode) {
            case NORMAL_MODE -> super.getDamagePerShot();
            case DAMAGE_2X -> super.getDamagePerShot() * 2;
            case DAMAGE_3X -> super.getDamagePerShot() * 3;
            case DAMAGE_4X -> super.getDamagePerShot() * 4;
            case DAMAGE_5X -> super.getDamagePerShot() * 5;
            case DAMAGE_10X -> super.getDamagePerShot() * 10;
            default -> super.getDamagePerShot();
        };
    }

    @Override
    public void decreaseHealth(int damage){
        if(shipMode != ShipMode.GOD_MODE){
            this.setHealth(getHealth() - damage);
        }
    }

    public void increaseDamage(int increaseRatio){
        this.setDamagePerShot(getDamagePerShot() * increaseRatio);
    }

    public void restoreHealth(){
        this.setHealth(maxHealth);
    }

    public void setShipMode(ShipMode shipMode) {this.shipMode = shipMode;}
}
