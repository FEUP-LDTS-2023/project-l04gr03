package SpaceInvaders.Model.Game.RegularGameElements;

import SpaceInvaders.Model.Game.Element;
import SpaceInvaders.Model.Position;

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

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(this.getClass() != o.getClass() || this == null){
            return false;
        }
        return this.getPosition().equals(((DestroyableElement) o).getPosition()) && this.health == ((DestroyableElement) o).getHealth();
    }
}
