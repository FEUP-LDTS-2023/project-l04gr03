package SpaceInvaders.source_code.Controller.Game;

import SpaceInvaders.source_code.Game;
import SpaceInvaders.source_code.Model.Game.Arena;
import SpaceInvaders.source_code.Model.Game.ArenaModifier;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Projectile;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.source_code.Model.Position;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class ShipController extends ArenaController {

    public ShipController(Arena arena) {
        super(arena);
    }

    public void moveLeft(){
        Ship ship = getModel().getShip();
        Position shipPosition = ship.getPosition();
        ship.setPosition(new Position(shipPosition.getX() - 1, shipPosition.getY()));
    }

    public void moveRight(){
        Ship ship = getModel().getShip();
        Position shipPosition = ship.getPosition();
        ship.setPosition(new Position(shipPosition.getX() + 1, shipPosition.getY()));
    }

    public void shootProjectile(){
        Ship ship = getModel().getShip();
        ArenaModifier arenaModifier = new ArenaModifier(getModel());
        Position projectilePosition = new Position(ship.getPosition().getX(),ship.getPosition().getY() + 1);
        Projectile<Ship> projectile = new Projectile<>(projectilePosition, ship, 10);
        arenaModifier.addShipProjectile(projectile);
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
        if(key.getKeyType() == KeyType.ArrowLeft || key.getCharacter() == 'A'){
            moveLeft();
        }
        if(key.getKeyType() == KeyType.ArrowRight || key.getCharacter() == 'D'){
            moveRight();
        }
        if(key.getKeyType() == KeyType.ArrowUp || key.getCharacter() == 'W'){
            shootProjectile();
        }
    }
}
