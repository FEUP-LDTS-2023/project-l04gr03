package SpaceInvaders.source_code.Model.Game.RegularGameElements;

import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Position;

public class Projectile extends Element {

    private int speed;
    public Projectile(Position position, int speed) {
        super(position);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void increaseSpeed(int increaseRatio){
        speed*=increaseRatio;
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }
}
