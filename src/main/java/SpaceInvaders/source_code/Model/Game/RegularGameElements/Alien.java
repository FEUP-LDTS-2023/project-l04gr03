package SpaceInvaders.source_code.Model.Game.RegularGameElements;

<<<<<<< HEAD
import SpaceInvaders.source_code.Model.Game.Element;

public class Alien extends Element {
=======
import SpaceInvaders.source_code.Model.Position;

public class Alien extends DestroyableElement {

    private int damagePerShot;

    private int score;

    public Alien(Position position,int health, int damagePerShot, int score) {
        super(position,health);
        this.damagePerShot = damagePerShot;
        this.score = score;
    }


    public int getDamagePerShot() {
        return damagePerShot;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int increaseRatio){
        score*=increaseRatio;
    }
>>>>>>> 660561113c5e83e47358b78ba6339ddb5bfbdce8
}
