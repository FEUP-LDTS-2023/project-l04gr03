package SpaceInvaders.source_code.Controller.Game;

import SpaceInvaders.source_code.Game;
import SpaceInvaders.source_code.Model.Game.Arena;
import SpaceInvaders.source_code.Model.Game.Collectables.Collectable;
import SpaceInvaders.source_code.Model.Game.Collectables.CollectableFactory;
import SpaceInvaders.source_code.Model.Game.Collectables.CollectableType;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.AttackingElement;
import SpaceInvaders.source_code.Model.Position;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    }
}
