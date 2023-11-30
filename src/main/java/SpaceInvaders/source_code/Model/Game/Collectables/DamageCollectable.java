package SpaceInvaders.source_code.Model.Game.Collectables;
import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.ShipMode;
import SpaceInvaders.source_code.Model.Position;

public class DamageCollectable extends CollectableWithMultiplier<Ship> {

    public DamageCollectable(Position position, Ship ship, int multiplier){
        super(position,ship,multiplier);
    }

    @Override
    public void execute() {
        switch (getMultiplier()){
            case 2:
                getAttackingElement().setShipMode(ShipMode.DAMAGE_2X);
                break;
            case 3:
                getAttackingElement().setShipMode(ShipMode.DAMAGE_3X);
                break;
            case 4:
                getAttackingElement().setShipMode(ShipMode.DAMAGE_4X);
                break;
            case 5:
                getAttackingElement().setShipMode(ShipMode.DAMAGE_5X);
                break;
            case 10:
                getAttackingElement().setShipMode(ShipMode.DAMAGE_10X);
                break;
        }
    }
}
