package SpaceInvaders.Model.Game.Collectables;

import SpaceInvaders.Model.Game.RegularGameElements.Alien;
import SpaceInvaders.Model.Position;

public class ScoreCollectable extends CollectableWithMultiplier<Alien> {

    public ScoreCollectable(Position position, int multiplier){
        super(position,multiplier);
    }
    @Override
    public void execute(Alien alien) {
        alien.increaseScore(this.getMultiplier());
    }
}
