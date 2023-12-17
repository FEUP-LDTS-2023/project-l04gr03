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

    private long generateCollectableTime;

    private long movementTime;

    public CollectableController(Arena arena) {
        super(arena);
        this.generateCollectableTime = 0;
        this.movementTime = 0;
    }

    public long getGenerateCollectableTime() {return generateCollectableTime;}

    public void setGenerateCollectableTime(long generateCollectableTime) {this.generateCollectableTime = generateCollectableTime;}

    public void setMovementTime(long movementTime) {this.movementTime = movementTime;}

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
        if(time - generateCollectableTime > 20000){
            generateCollectable();
            generateCollectableTime = time;
        }
        if(getModel().getActiveCollectable() != null && time - movementTime > 150){
            moveCollectable();
            movementTime = time;
        }
        if(!getModel().getAliens().isEmpty()){
            if((getModel().getShip().getShipMode() != ShipMode.NORMAL_MODE || getModel().getAliens().get(0).getAlienMode() != AlienMode.NORMAL_MODE) && time - generateCollectableTime > 19900){
                endCollectableEffect();
            }
        }
    }
}
