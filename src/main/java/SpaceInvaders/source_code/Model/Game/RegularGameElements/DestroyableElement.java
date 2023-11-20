package SpaceInvaders.source_code.Model.Game.RegularGameElements;

import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Position;

public class DestroyableElement extends Element {

    private int health;

    private final int maxHealth = 100;
    public DestroyableElement(Position position, int health) {
        super(position);
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health){ this.health = health; }

    public void decreaseHealth(int damage){
        health-=damage;
    }

    public boolean isDestroyed(){
        return health <= 0;
    }
}