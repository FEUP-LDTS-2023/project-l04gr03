package SpaceInvaders.source_code.Controller.Game;

import SpaceInvaders.source_code.Game;
import SpaceInvaders.source_code.Model.Game.Arena;
import SpaceInvaders.source_code.Model.Game.ArenaModifier;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Alien;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Projectile;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;
import java.util.List;

public class CollisionController extends ArenaController {
    public CollisionController(Arena arena) {
        super(arena);
    }

    public void projectileCollisionsWithShip(){
        List<Projectile> projectiles = getModel().getProjectiles();
        ArenaModifier arenaModifier = new ArenaModifier(getModel());
        Ship ship = getModel().getShip();
        for(Projectile projectile : projectiles){
            if(collisionBetween(ship,projectile)){
                getShipController().hitByProjectile(projectile);
                arenaModifier.removeProjectile(projectile);
            }
        }
    }


    public void projectileCollisionsWithAliens(){
        List<Projectile> projectiles = getModel().getProjectiles();
        ArenaModifier arenaModifier = new ArenaModifier(getModel());
        List<Alien> aliens = getModel().getAliens();
        for(Alien alien : aliens){
            for (Projectile projectile : projectiles){
                if(collisionBetween(alien,projectile)){
                    getAlienController().hitByProjectile(alien,projectile);
                    arenaModifier.removeProjectile(projectile);
                }
            }
        }
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
       projectileCollisionsWithShip();
       projectileCollisionsWithAliens();
    }
}
