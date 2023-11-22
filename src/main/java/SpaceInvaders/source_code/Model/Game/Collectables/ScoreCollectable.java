package SpaceInvaders.source_code.Model.Game.Collectables;

import SpaceInvaders.source_code.Model.Game.RegularGameElements.Alien;
import SpaceInvaders.source_code.Model.Position;

public class ScoreCollectable extends CollectableWithMultiplier<Alien> {

    public ScoreCollectable(Position position, int multiplier){
        super(position,multiplier);
    }
    @Override
    public void execute(Alien alien) {
        alien.increaseScore(this.getMultiplier());
    }
}
