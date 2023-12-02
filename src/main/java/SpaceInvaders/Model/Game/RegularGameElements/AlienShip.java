package SpaceInvaders.Model.Game.RegularGameElements;

import SpaceInvaders.Model.Position;

public class AlienShip extends DestroyableElement {

    private int score;
    private int movementDirection;

    public AlienShip(Position position,int health,int score, int movementDirection) {
        super(position,health);
        this.score = score;
        this.movementDirection = movementDirection;
    }

    public int getScore() {
        return score;
    }

    public int getMovementDirection() {
        return movementDirection;
    }
}
