package SpaceInvaders.source_code.Model.Game.RegularGameElements;

import SpaceInvaders.source_code.Model.Game.Element;
<<<<<<< HEAD

public class Projectile extends Element {
=======
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
>>>>>>> 660561113c5e83e47358b78ba6339ddb5bfbdce8
}
