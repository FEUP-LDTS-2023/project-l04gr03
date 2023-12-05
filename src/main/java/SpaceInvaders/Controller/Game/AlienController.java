package SpaceInvaders.Controller.Game;

import SpaceInvaders.Game;
import SpaceInvaders.Model.Game.Arena;
import SpaceInvaders.Model.Game.RegularGameElements.Alien;
import SpaceInvaders.Model.Game.RegularGameElements.Projectile;
import SpaceInvaders.Model.Position;
import SpaceInvaders.Model.Game.RegularGameElements.*;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.List;
import java.util.Random;

import static SpaceInvaders.Controller.Sound.SoundManager.playExplosionSound;

public class AlienController extends GameController {

    private MovementDirection movementDirection;

    private long lastMovimentTime;

    private long lastShotTime;

    public AlienController(Arena arena) {
        super(arena);
        this.movementDirection = MovementDirection.RIGHT;
        this.lastMovimentTime = 0;
        this.lastShotTime = 0;
    }

    public boolean canMoveAlien(Alien alien) {
        switch (movementDirection) {
            case LEFT:
               return alien.getPosition().getX() - 3 > 0;
            case RIGHT:
                return alien.getPosition().getX() + 3 < getModel().getWidth();
            case DOWN:
                return true;
        }
        return false;
    }

    public boolean canMoveAliens(){
        List<Alien> aliens = getModel().getAliens();
        for(Alien alien : aliens){
            if(!canMoveAlien(alien)){
                return false;
            }
        }
        return true;
    }

    public void moveAlien(Alien alien){
        Position alienPosition = new Position(alien.getPosition().getX(),alien.getPosition().getY());
        switch(movementDirection){
            case LEFT:
                alien.setPosition(new Position(alienPosition.getX() - 1,alienPosition.getY()));
                break;
            case RIGHT:
                alien.setPosition(new Position(alienPosition.getX() + 1,alienPosition.getY()));
                break;
            case DOWN:
                alien.setPosition(new Position(alienPosition.getX(),alienPosition.getY() + 1));
                break;
        }
    }

    public void moveAliens(){
        List<Alien> aliens = getModel().getAliens();
        for(Alien alien : aliens){
            moveAlien(alien);
        }
    }

    public void shootProjectile(){
        List<Alien> attackingAliens = getModel().getAttackingAliens();
        Random random = new Random();
        int randomIndex = random.nextInt(attackingAliens.size());
        Alien randomAlien = attackingAliens.get(randomIndex);
        getArenaModifier().addProjectile(new Projectile(randomAlien.getPosition(),randomAlien));
    }

    public void hitByProjectile(Alien alien, Projectile projectile){
        alien.decreaseHealth(projectile.getElement().getDamagePerShot());
        getModel().increaseScore(alien.getScore());
    }

    public void removeDestroyedAliens(){
        List<Alien> aliens = getModel().getAliens();
        for(int i = 0; i < aliens.size(); i++){
            if(aliens.get(i).isDestroyed()){
               getArenaModifier().removeAlien(aliens.get(i));
                playExplosionSound();
            }
        }
    }



    public void updateMovementDirection(){
        switch (movementDirection){
            case LEFT:
                if(!canMoveAliens()){
                    this.movementDirection = MovementDirection.DOWN;
                }
                break;
            case RIGHT:
                if(!canMoveAliens()){
                    this.movementDirection = MovementDirection.LEFT;
                }
                break;
            case DOWN:
                this.movementDirection = MovementDirection.RIGHT;
                break;
        }
    }

    public void step(Game game, KeyStroke key, long time) {
        if(time - lastShotTime > 800){
            shootProjectile();
            lastShotTime = time;
        }
        if(time - lastMovimentTime > 300){
            updateMovementDirection();
            moveAliens();
            lastMovimentTime = time;
        }
    }
}
