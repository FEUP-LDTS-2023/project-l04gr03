package SpaceInvaders.Model.Game.Collectables;

import SpaceInvaders.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.Model.Position;

public class GodModeCollectable extends Collectable<Ship>{

    public GodModeCollectable(Position position){super(position);}
    @Override
    public void execute(Ship element) {}
}
