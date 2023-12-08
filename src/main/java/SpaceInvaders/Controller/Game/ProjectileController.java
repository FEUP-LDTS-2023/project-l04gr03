package SpaceInvaders.Controller.Game;

import SpaceInvaders.Game;
import SpaceInvaders.Model.Game.Arena;
import SpaceInvaders.Model.Game.RegularGameElements.Alien;
import SpaceInvaders.Model.Game.RegularGameElements.Projectile;
import SpaceInvaders.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.Model.Position;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.List;

public class ProjectileController extends GameController {


    public ProjectileController(Arena arena) {
        super(arena);
    }

    public void moveProjectiles(){
        List<Projectile> projectiles = getModel().getProjectiles();
        for(Projectile projectile : projectiles){
            Position projectilePosition = projectile.getPosition();
            if(projectile.getElement() instanceof Ship){
                projectile.setPosition(new Position(projectilePosition.getX(),projectilePosition.getY() - 1));
            }
            if(projectile.getElement() instanceof Alien){
                projectile.setPosition(new Position(projectilePosition.getX(),projectilePosition.getY() + 1));
            }
        }
    }
    @Override
    public void step(Game game, KeyStroke key, long time) {
        moveProjectiles();
    }
}
