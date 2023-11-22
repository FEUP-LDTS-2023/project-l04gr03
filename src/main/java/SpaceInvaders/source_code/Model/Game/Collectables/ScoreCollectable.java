package SpaceInvaders.source_code.Model.Game.Collectables;

<<<<<<< HEAD
public class ScoreCollectable implements Collectable {
=======
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
>>>>>>> 660561113c5e83e47358b78ba6339ddb5bfbdce8
}
