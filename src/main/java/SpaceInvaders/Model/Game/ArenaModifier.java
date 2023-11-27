package SpaceInvaders.Model.Game;

import SpaceInvaders.Model.Game.Collectables.CollectableFactory;
import SpaceInvaders.Model.Game.Collectables.CollectableType;
import SpaceInvaders.Model.Game.RegularGameElements.*;
import SpaceInvaders.Model.Game.RegularGameElements.*;
import SpaceInvaders.Model.Position;

import java.util.List;

public class ArenaModifier {

    private Arena arena;

    public ArenaModifier(Arena arena){
        this.arena = arena;
    }

    public void createCollectable(Position position, CollectableType type, int multiplier) {
        CollectableFactory collectableFactory = new CollectableFactory(position,type,multiplier);
        arena.setCollectable(collectableFactory.createCollectable());
    }

    public void removeAlien(Alien alien) {
        List<Alien> aliens = arena.getAliens();
        for(int i = 0; i < aliens.size(); i++){
            if(aliens.get(i).equals(alien)){
                if(i > 0){
                    aliens.get(i - 1).setAlienState(AlienState.ATTACKING);
                }
                aliens.remove(aliens.get(i));
                break;
            }
        }
    }

    public void removeCoverWall(CoverWall coverWall){arena.getCoverWalls().remove(coverWall);}

    public void removeAlienShip(AlienShip alienShip){
        arena.setAlienShip(null);
    }

    public void removeCollectable(){
        arena.setCollectable(null);
    }

    public void addProjectile(Projectile projectile) {arena.getProjectiles().add(projectile);}

    public void removeProjectile(Projectile projectile) {arena.getProjectiles().remove(projectile);}
}