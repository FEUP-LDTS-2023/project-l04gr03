package SpaceInvaders.Model.Game.RegularGameElements;

import SpaceInvaders.Model.Position;

public class AlienShip extends DestroyableElement {

    private int score;

    public AlienShip(Position position,int health,int score) {
        super(position,health);
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
