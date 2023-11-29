package SpaceInvaders.source_code.Controller.Game;

import SpaceInvaders.source_code.Game;
import SpaceInvaders.source_code.Model.Game.Arena;
import SpaceInvaders.source_code.Model.Game.Collectables.*;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.AttackingElement;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.ShipMode;
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

    private CollectableEffect collectableEffect;

    public CollectableController(Arena arena) {
        super(arena);
        this.generateCollectorTime = 0;
        this.movementTime = 0;
        this.collectableEffect = null;
    }

    public void generateCollectable(){
        getArenaModifier().createCollectable();
    }

    public void moveCollectable(){
        Collectable collectable = getModel().getActiveCollectable();
        collectable.setPosition(new Position(collectable.getPosition().getX(),collectable.getPosition().getY() + 1));
    }

    public void defineCollectableEffect(Class class1){
        if (class1.equals(DamageCollectable.class)) {
            collectableEffect = CollectableEffect.DAMAGE;
        } else if (class1.equals(ScoreCollectable.class)) {
            collectableEffect = CollectableEffect.SCORE;
        } else if (class1.equals(MachineGunModeCollectable.class)) {
            collectableEffect = CollectableEffect.MACHINE_GUN_MODE;
        } else if (class1.equals(GodModeCollectable.class)) {
            collectableEffect = CollectableEffect.GOD_MODE;
        }
    }

    public void endCollectableEffect(){
        switch (collectableEffect){
            case MACHINE_GUN_MODE, GOD_MODE -> getModel().getShip().setShipMode(ShipMode.NORMAL_MODE);
            case DAMAGE -> getArenaModifier().restoreShipDamage();
            case SCORE -> getArenaModifier().restoreAlienScore();
        }
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
        if(collectableEffect != null && time - generateCollectorTime > 15000){
            endCollectableEffect();
        }
    }
}
