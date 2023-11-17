package SpaceInvaders.source_code.Model.Game.RegularGameElements;

import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Position;

public class AlienShip extends DestroyableElement {

    final int score = 500;

    public AlienShip(Position position,int health) {
        super(position,health);
    }

}
