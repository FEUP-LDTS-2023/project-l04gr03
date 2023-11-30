package SpaceInvaders.source_code.Model.Game.RegularGameElements;

import SpaceInvaders.source_code.Model.Position;

public class Alien extends AttackingElement {

    private int damagePerShot;

    private int score;


    private AlienMode alienMode;

    private AlienState alienState;

    public Alien(Position position,int health, int damagePerShot, int score, AlienState alienState) {
        super(position,health,damagePerShot);
        this.score = score;
        this.alienState = alienState;
        this.alienMode = AlienMode.NORMAL_MODE;
    }

    public AlienMode getAlienMode() {return alienMode;}

    public void setAlienMode(AlienMode alienMode) {this.alienMode = alienMode;}

    public int getScore() {
        switch (alienMode){
            case NORMAL_MODE:
                return score;
            case SCORE_2X:
                return score * 2;
            case SCORE_3X:
                return score * 3;
            case SCORE_4X:
                return score * 4;
            case SCORE_5X:
                return score * 5;
            case SCORE_10X:
                return score * 10;
        }
        return score;
    }

    public void setScore(int score) {this.score = score;}

    public AlienState getAlienState() {return alienState;}

    public void setAlienState(AlienState alienState) {this.alienState = alienState;}

    public void increaseScore(int increaseRatio){
        score*=increaseRatio;
    }
}
