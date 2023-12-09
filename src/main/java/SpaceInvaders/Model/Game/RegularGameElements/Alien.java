package SpaceInvaders.Model.Game.RegularGameElements;

import SpaceInvaders.Model.Position;

public class Alien extends AttackingElement {

    private int score;


    private int Type;


    private AlienMode alienMode;


    private AlienState alienState;

    public Alien(Position position,int health, int damagePerShot, int score, AlienState alienState, int Type) {
        super(position,health,damagePerShot);
        this.score = score;
        this.alienState = alienState;
        if(Type >= 3){
            this.Type = 0;
        }
        else {
            this.Type = Type;
        }
        this.alienMode = AlienMode.NORMAL_MODE;
    }

    public AlienMode getAlienMode() {return alienMode;}

    public void setAlienMode(AlienMode alienMode) {this.alienMode = alienMode;}

    public int getScore() {
        switch (alienMode){
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
            default: //no score collectable active
                return score;
        }
    }

    public void setScore(int score) {this.score = score;}

    public AlienState getAlienState() {return alienState;}

    public void setAlienState(AlienState alienState) {this.alienState = alienState;}

    public void increaseScore(int increaseRatio){
        score*=increaseRatio;
    }
    public int getType(){
        return Type;
    }
}
