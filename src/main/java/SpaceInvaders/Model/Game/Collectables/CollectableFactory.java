package SpaceInvaders.Model.Game.Collectables;

import SpaceInvaders.Model.Position;

public class CollectableFactory {

    private Position position;

    private CollectableType type;

    private int multiplier;


    public CollectableFactory(Position position,CollectableType type,int multiplier){
        this.position = position;
        this.type = type;
        this.multiplier = multiplier;
    }

    public Collectable createCollectable(){
        return switch (type) {
            case HEALTH -> new HealthCollectable(position);
            case DAMAGE -> new DamageCollectable(position, multiplier);
            case SCORE -> new ScoreCollectable(position, multiplier);
            default -> null;
        };
    }

}
