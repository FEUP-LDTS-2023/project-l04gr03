package SpaceInvaders.source_code.Model.Game.Collectables;
import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.source_code.Model.Position;

public class DamageCollectable extends CollectableWithMultiplier<Ship> {

    public DamageCollectable(Position position, Ship ship, int multiplier){
        super(position,ship,multiplier);
    }

    @Override
    public void execute() {
        getAttackingElement().increaseDamage(this.getMultiplier());
    }
}
