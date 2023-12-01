package SpaceInvaders.Controller.Game;

import SpaceInvaders.Game;
import SpaceInvaders.Model.Game.Arena;
import SpaceInvaders.Model.Game.RegularGameElements.AlienShip;
import SpaceInvaders.Model.Game.RegularGameElements.Projectile;
import SpaceInvaders.Model.Position;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;


public class AlienShipController extends GameController{
    long lastAppearance;
    public AlienShipController(Arena arena){
        super(arena);
        this.lastAppearance = 0;
    }

    public void generateAlienShip(){
        getArenaModifier().createAlienShip();
    }

    public void moveAlienShip(){
        AlienShip alienShip = getModel().getAlienShip();
        if(canMoveAlienShip()) {
            alienShip.setPosition(new Position(alienShip.getPosition().getX() + alienShip.getMovementDirection(), alienShip.getPosition().getY()));
        }
        else{
            getArenaModifier().removeAlienShip(getModel().getAlienShip());
        }
    }

    public boolean canMoveAlienShip(){
        AlienShip alienShip = getModel().getAlienShip();
        return (alienShip.getPosition().getX() - 3 > 0) | (alienShip.getPosition().getX() + 3 < getModel().getWidth());
    }

    public void removeAlienShip(){
        if(getModel().getAlienShip() != null) {
            if (getModel().getAlienShip().isDestroyed()) {
                getArenaModifier().removeAlienShip(getModel().getAlienShip());
            }
        }
    }

    public void hitByProjectile(AlienShip alienShip, Projectile projectile){
        alienShip.decreaseHealth(projectile.getElement().getDamagePerShot());
        getModel().increaseScore(alienShip.getScore());
    }
    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
        if(time - lastAppearance > 100000){
            generateAlienShip();
            lastAppearance = time;
        }
        else if(getModel().getAlienShip() != null){
            moveAlienShip();
        }
    }


}
