package SpaceInvaders.source_code.Model.Game.Collectables;

import SpaceInvaders.source_code.Model.Game.RegularGameElements.Alien;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.AttackingElement;
import SpaceInvaders.source_code.Model.Position;

import java.util.List;

public class ScoreCollectable extends CollectableWithMultiplier<List<Alien>> {

    public ScoreCollectable(Position position, List<Alien> aliens, int multiplier){
        super(position, aliens, multiplier);
    }
    @Override
    public void execute() {
        List<Alien> aliens = getAttackingElement();
        for(Alien alien : aliens){
            alien.increaseScore(getMultiplier());
        }
    }
}
