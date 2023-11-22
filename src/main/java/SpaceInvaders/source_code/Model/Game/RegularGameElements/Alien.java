package SpaceInvaders.source_code.Model.Game.RegularGameElements;

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
}
