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

import java.util.List;

public class ProjectileController extends GameController {


    public ProjectileController(Arena arena) {
        super(arena);
    }

    public void moveProjectiles(){
        List<Projectile> projectiles = getModel().getProjectiles();
        for(Projectile projectile : projectiles){
            Position projectilePosition = projectile.getPosition();
            projectile.setPosition(new Position(projectilePosition.getX(),projectilePosition.getY() + projectile.getSpeed()));
        }
    }

    public void step(Game game, KeyStroke key, long time) {
        moveProjectiles();
    }
}
