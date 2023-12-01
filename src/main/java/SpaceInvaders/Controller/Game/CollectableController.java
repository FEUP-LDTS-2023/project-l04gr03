package SpaceInvaders.Controller.Game;

import SpaceInvaders.Game;
import SpaceInvaders.Model.Game.Arena;
import SpaceInvaders.Model.Game.Collectables.Collectable;
import SpaceInvaders.Model.Game.RegularGameElements.AlienMode;
import SpaceInvaders.Model.Game.RegularGameElements.ShipMode;
import SpaceInvaders.Model.Position;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class CollectableController extends GameController {

    private long generateCollectorTime;

    private long movementTime;

    public CollectableController(Arena arena) {
        super(arena);
        this.generateCollectorTime = 0;
        this.movementTime = 0;
    }

    public void generateCollectable(){
        getArenaModifier().createCollectable();
    }

    public void moveCollectable(){
        Collectable collectable = getModel().getActiveCollectable();
        collectable.setPosition(new Position(collectable.getPosition().getX(),collectable.getPosition().getY() + 1));
    }


    public void endCollectableEffect(){
        getArenaModifier().resetShipMode();
        getArenaModifier().resetAliensMode();
    }


    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
        if(time - generateCollectorTime > 30000){
            generateCollectable();
            generateCollectorTime = time;
        }
        if(getModel().getActiveCollectable() != null && time - movementTime > 150){
            moveCollectable();
            movementTime = time;
        }
        if(!getModel().getAliens().isEmpty()){
            if((getModel().getShip().getShipMode() != ShipMode.NORMAL_MODE || getModel().getAliens().get(0).getAlienMode() != AlienMode.NORMAL_MODE) && time - generateCollectorTime > 15000){
                endCollectableEffect();
            }
        }
    }
}
