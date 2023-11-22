package SpaceInvaders.source_code.Model.Game.Collectables;

import SpaceInvaders.source_code.Model.Position;

public class CollectableFactory {

    private Position position;

    private String type;

    private int multiplier;


    public CollectableFactory(Position position,String type,int multiplier){
        this.position = position;
        this.type = type;
        this.multiplier = multiplier;
    }

    public Collectable createCollectable(){
        return switch (type) {
            case "health" -> new HealthCollectable(position);
            case "damage" -> new DamageCollectable(position, multiplier);
            case "score" -> new ScoreCollectable(position, multiplier);
            case "sped up projectile" -> new SpedUpProjectileCollectable(position, multiplier);
            case "splash projectile" -> new SplashProjectileCollectable(position, multiplier);
            default -> null;
        };
    }

}
