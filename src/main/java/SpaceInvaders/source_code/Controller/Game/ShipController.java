package SpaceInvaders.source_code.Controller.Game;

import SpaceInvaders.source_code.Game;
import SpaceInvaders.source_code.Model.Game.Arena;
import SpaceInvaders.source_code.Model.Game.ArenaModifier;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Alien;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.AttackingElement;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Projectile;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.source_code.Model.Position;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class ShipController extends GameController {

    private long inputTime;

    public ShipController(Arena arena) {
        super(arena);
        this.inputTime = 0;
    }

    public boolean canMoveShip(Position position){
        return position.getX() >= 0 && position.getX() < getModel().getWidth() && position.getY() >= 0 && position.getY() < getModel().getHeight();
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
        ArenaModifier arenaModifier = new ArenaModifier(getModel());
        Position projectilePosition = new Position(ship.getPosition().getX(),ship.getPosition().getY());
        Projectile projectile = new Projectile(projectilePosition, ship, 5);
        arenaModifier.addProjectile(projectile);
    }

    public void hitByProjectile(Projectile projectile){
        getModel().getShip().decreaseHealth(projectile.getElement().getDamagePerShot());
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
        if(key == null){
            return;
        }
        if(key.getKeyType() == KeyType.ArrowLeft && time - inputTime > 100){
            moveLeft();
            inputTime = time;
        }
        if(key.getKeyType() == KeyType.ArrowRight && time - inputTime > 100){
            moveRight();
            inputTime = time;
        }
        if(key.getKeyType() == KeyType.ArrowUp && time - inputTime > 300){
            shootProjectile();
            inputTime = time;
        }
    }
}
