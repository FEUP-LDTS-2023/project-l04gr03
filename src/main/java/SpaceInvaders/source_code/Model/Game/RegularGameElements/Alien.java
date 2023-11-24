package SpaceInvaders.source_code.Model.Game.RegularGameElements;

import SpaceInvaders.source_code.Model.Position;

public class Alien extends AttackingElement {

    private int damagePerShot;

    private int score;

    private AlienState alienState;

    public Alien(Position position,int health, int damagePerShot, int score, AlienState alienState) {
        super(position,health,damagePerShot);
        this.score = score;
        this.alienState = alienState;
    }

    public int getScore() {
        return score;
    }

    public AlienState getAlienState() {return alienState;}

    public void increaseScore(int increaseRatio){
        score*=increaseRatio;
    }

}
