package SpaceInvaders.Controller.Game;

import SpaceInvaders.Controller.Sound.SoundManager;
import SpaceInvaders.Game;
import SpaceInvaders.Model.Game.Arena;
import SpaceInvaders.Model.Game.RegularGameElements.AlienShip;
import SpaceInvaders.Model.Game.RegularGameElements.Projectile;
import SpaceInvaders.Model.Position;
import SpaceInvaders.Model.Sound.Sound_Options;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;



public class AlienShipController extends GameController{
    long lastAppearance;
    long lastMovementTime;
    public AlienShipController(Arena arena){
        super(arena);
        this.lastAppearance = 0;
        this.lastMovementTime = 0;
    }

    public long getLastMovementTime() {return lastMovementTime;}

    public long getLastAppearance() {return lastAppearance;}

    public void setLastMovementTime(long lastMovementTime) {this.lastMovementTime = lastMovementTime;}

    public void setLastAppearance(long lastAppearance) {this.lastAppearance = lastAppearance;}


    public void generateAlienShip(){

        getArenaModifier().createAlienShip();
        SoundManager.getInstance().playSound(Sound_Options.ALIEN_SHIP_HIGH);
        SoundManager.getInstance().playSound(Sound_Options.ALIEN_SHIP_LOW);
    }

    public void moveAlienShip(){
        AlienShip alienShip = getModel().getAlienShip();
        if(canMoveAlienShip()) {
            alienShip.setPosition(new Position(alienShip.getPosition().getX() + alienShip.getMovementDirection(), alienShip.getPosition().getY()));
        }
        else{
            getArenaModifier().removeAlienShip();
            SoundManager.getInstance().stopSound(Sound_Options.ALIEN_SHIP_HIGH);
            SoundManager.getInstance().stopSound(Sound_Options.ALIEN_SHIP_LOW);
        }
    }

    public boolean canMoveAlienShip(){
        AlienShip alienShip = getModel().getAlienShip();
        return (alienShip.getPosition().getX() - 1 > 0) && (alienShip.getPosition().getX() + 2 < getModel().getWidth());
    }

    public void removeAlienShip(){
        if(getModel().getAlienShip() != null) {
            if (getModel().getAlienShip().isDestroyed()) {
                getArenaModifier().removeAlienShip();
                SoundManager.getInstance().playSound(Sound_Options.DESTRUCTION);
                SoundManager.getInstance().stopSound(Sound_Options.ALIEN_SHIP_HIGH);
                SoundManager.getInstance().stopSound(Sound_Options.ALIEN_SHIP_LOW);
            }
        }
    }

    public void hitByProjectile(AlienShip alienShip, Projectile projectile){
        alienShip.decreaseHealth(projectile.getElement().getDamagePerShot());
        if(getModel().getAlienShip().isDestroyed()) {
            getModel().increaseScore(alienShip.getScore());
        }
    }
    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
        if(time - lastAppearance > 50000){
            generateAlienShip();
            lastAppearance = time;
        }
        else if(getModel().getAlienShip() != null){
            if(time - lastMovementTime > 100) {
                moveAlienShip();
                lastMovementTime = time;
            }
        }
    }


}
