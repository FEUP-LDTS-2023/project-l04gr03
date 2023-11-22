package SpaceInvaders.source_code.Model.Game.RegularGameElements;

import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Position;

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
