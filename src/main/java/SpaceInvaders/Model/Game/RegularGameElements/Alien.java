package SpaceInvaders.Model.Game.RegularGameElements;

import SpaceInvaders.Model.Position;

public class Alien extends AttackingElement {

    private int damagePerShot;

    private int score;

    private int Type;

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
    }

    public int getScore() {
        return score;
    }

    public AlienState getAlienState() {return alienState;}

    public void setAlienState(AlienState alienState) {this.alienState = alienState;}

    public void increaseScore(int increaseRatio){
        score*=increaseRatio;
    }
    public int getType(){
        return Type;
    }
}
