package SpaceInvaders.source_code.Model.Game.Collectables;

import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.source_code.Model.Position;

public class GodModeCollectable extends Collectable<Ship>{

    public GodModeCollectable(Position position){super(position);}
    @Override
    public void execute(Ship element) {}
}
