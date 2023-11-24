package SpaceInvaders.source_code.Model.Game.RegularGameElements;

import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Position;

public class Projectile<T> extends Element {

    private int speed;

    private final T element;

    private int damage;

    public Projectile(Position position, T element, int speed) {
        super(position);
        this.element = element;
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public T getElement(){ return element; }

    public void increaseSpeed(int increaseRatio){
        speed*=increaseRatio;
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }
}
