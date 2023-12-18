package SpaceInvaders.Model.Game.Collectables;

import SpaceInvaders.Model.Game.RegularGameElements.Alien;
import SpaceInvaders.Model.Game.RegularGameElements.AlienMode;
import SpaceInvaders.Model.Position;


import java.util.List;

public class ScoreCollectable extends CollectableWithMultiplier<List<Alien>> {

    public ScoreCollectable(Position position, List<Alien> aliens, int multiplier){
        super(position, aliens, multiplier);
    }
    @Override
    public void execute() {
        AlienMode alienMode = AlienMode.NORMAL_MODE;
        switch(getMultiplier()){
            case 2:
                alienMode = AlienMode.SCORE_2X;
                break;
            case 3:
                alienMode = AlienMode.SCORE_3X;
                break;
            case 4:
                alienMode = AlienMode.SCORE_4X;
                break;
            case 5:
                alienMode = AlienMode.SCORE_5X;
                break;
            case 10:
                alienMode = AlienMode.SCORE_10X;
                break;
        }
        List<Alien> aliens = getAttackingElement();
        for(Alien alien : aliens){
            alien.setAlienMode(alienMode);
        }
    }
}
