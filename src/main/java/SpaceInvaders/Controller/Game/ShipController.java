package SpaceInvaders.Controller.Game;


import SpaceInvaders.Controller.Sound.SoundManager;
import SpaceInvaders.Game;
import SpaceInvaders.Model.Game.ArenaModifier;
import SpaceInvaders.Model.Game.RegularGameElements.Projectile;
import SpaceInvaders.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.Model.Game.RegularGameElements.ShipMode;
import SpaceInvaders.Model.Position;
import SpaceInvaders.Model.Game.Arena;

import SpaceInvaders.Model.Sound.Sound_Options;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;


public class ShipController extends GameController {

    private long movementTime;

    private long shootingTime;

    public ShipController(Arena arena) {
        super(arena);
        this.movementTime = 0;
        this.shootingTime = 0;
    }

    public long getMovementTime() {return movementTime;}

    public void setMovementTime(long movementTime) {this.movementTime = movementTime;}

    public long getShootingTime() {return shootingTime;}

    public void setShootingTime(long shootingTime) {this.shootingTime = shootingTime;}

    public boolean canMoveShip(Position position){
        return position.getX() > 0 && position.getX() < getModel().getWidth() - 1 && position.getY() > 0 && position.getY() < getModel().getHeight() - 1;
    }

    public void moveLeft(){
        Ship ship = getModel().getShip();
        Position shipPosition = ship.getPosition();
        if(canMoveShip(new Position(ship.getPosition().getX() - 1, shipPosition.getY()))){
            ship.setPosition(new Position(shipPosition.getX() - 1, shipPosition.getY()));
        }
    }

    public void moveRight(){
        Ship ship = getModel().getShip();
        Position shipPosition = ship.getPosition();
        if(canMoveShip(new Position(shipPosition.getX() + 1, shipPosition.getY()))){
            ship.setPosition(new Position(shipPosition.getX() + 1, shipPosition.getY()));
        }
    }

    public void shootProjectile(){
        Ship ship = getModel().getShip();
        Position projectilePosition = new Position(ship.getPosition().getX(),ship.getPosition().getY());
        Projectile projectile = new Projectile(projectilePosition, ship);
        getArenaModifier().addProjectile(projectile);
        SoundManager.getInstance().playSound(Sound_Options.LASER);
    }

    public void hitByProjectile(Projectile projectile){
        getModel().getShip().decreaseHealth(projectile.getElement().getDamagePerShot());
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
        if(key == null){
            return;
        }
        if(key.getKeyType() == KeyType.ArrowLeft && time - movementTime > 50){
            moveLeft();
            movementTime = time;
        }
        if(key.getKeyType() == KeyType.ArrowRight && time - movementTime > 50){
            moveRight();
            movementTime = time;
        }
        if(key.getKeyType() == KeyType.ArrowUp && getModel().getShip().getShipMode() == ShipMode.MACHINE_GUN_MODE && time - shootingTime > 75){
            shootProjectile();
            shootingTime = time;
            return;
        }
        if(key.getKeyType() == KeyType.ArrowUp && time - shootingTime > 300){
            shootProjectile();
            shootingTime = time;
        }
    }
}
