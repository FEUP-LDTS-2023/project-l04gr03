package SpaceInvaders.Model.Game.Collectables;

import SpaceInvaders.Model.Position;
import SpaceInvaders.Model.Game.RegularGameElements.Alien;
import SpaceInvaders.Model.Game.RegularGameElements.Ship;


import java.util.List;

public class CollectableFactory<T> {

    private Position position;

    private CollectableType type;

    private int multiplier;

    private T attackingElement;


    public CollectableFactory(Position position, CollectableType type, int multiplier, T attackingElement){
        this.position = position;
        this.type = type;
        this.multiplier = multiplier;
        this.attackingElement = attackingElement;
    }

    public Collectable createCollectable(){
        return switch (type) {
            case HEALTH -> new HealthCollectable(position, (Ship) attackingElement);
            case DAMAGE -> new DamageCollectable(position, (Ship) attackingElement, multiplier);
            case SCORE -> new ScoreCollectable(position, (List<Alien>) attackingElement, multiplier);
            case MACHINE_GUN_MODE -> new MachineGunModeCollectable(position, (Ship) attackingElement);
            case GOD_MODE -> new GodModeCollectable(position,(Ship) attackingElement);
        };
    }

}
