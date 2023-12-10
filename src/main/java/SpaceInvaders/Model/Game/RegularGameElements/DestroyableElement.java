package SpaceInvaders.Model.Game.RegularGameElements;

import SpaceInvaders.Model.Game.Element;
import SpaceInvaders.Model.Position;

public class DestroyableElement extends Element {

    private int health;

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
        if(!(o instanceof DestroyableElement)){
            return false;
        }
        return this.getPosition().equals(((DestroyableElement) o).getPosition()) && this.health == ((DestroyableElement) o).getHealth();
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = prime + health;
        result = prime * result + getPosition().hashCode();
        return result;
    }
}
