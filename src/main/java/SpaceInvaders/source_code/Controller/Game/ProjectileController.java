package SpaceInvaders.source_code.Controller.Game;

import SpaceInvaders.source_code.Game;
import SpaceInvaders.source_code.Model.Game.Arena;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Alien;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Projectile;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.source_code.Model.Position;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.List;

public class ProjectileController extends ArenaController {


    public ProjectileController(Arena arena) {
        super(arena);
    }

    public void moveShipProjectiles(){
        List<Projectile<Ship>> projectiles = getModel().getShipProjectiles();
        for(Projectile<Ship> projectile : projectiles){
            Position projectilePosition = projectile.getPosition();
            projectile.setPosition(new Position(projectilePosition.getX(),projectilePosition.getY() + projectile.getSpeed()));
        }
    }

    public void moveAlienProjectiles(){
        List<Projectile<Alien>> projectiles = getModel().getAlienProjectiles();
        for(Projectile<Alien> projectile : projectiles){
            Position projectilePosition = projectile.getPosition();
            projectile.setPosition(new Position(projectilePosition.getX(),projectilePosition.getY() + projectile.getSpeed()));
        }
    }


    public void checkCollisions(){}


    public void step(Game game, KeyStroke key, long time) {
        moveShipProjectiles();
        moveAlienProjectiles();
    }
}
