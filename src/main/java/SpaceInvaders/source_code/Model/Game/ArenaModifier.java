package SpaceInvaders.source_code.Model.Game;

import SpaceInvaders.source_code.Model.Game.Collectables.Collectable;
import SpaceInvaders.source_code.Model.Game.Collectables.CollectableFactory;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.*;
import SpaceInvaders.source_code.Model.Position;

import java.util.List;

public class ArenaModifier {

    private Arena arena;

    public ArenaModifier(Arena arena){
        this.arena = arena;
    }

    public void createCollectable(Position position,String type, int multiplier) {
        CollectableFactory collectableFactory = new CollectableFactory(position,type,multiplier);
        arena.setCollectable(collectableFactory.createCollectable());
    }

    public void removeAlien(Alien alien) {
        arena.getAliens().remove(alien);
    }

    public void removeCoverWall(CoverWall coverWall){
        arena.getCoverWalls().remove(coverWall);
    }

    public void removeAlienShip(AlienShip alienShip){
        arena.setAlienShip(null);
    }

    public void removeCollectable(){
        arena.setCollectable(null);
    }

    public void addShipProjectile(Projectile<Ship> projectile) {arena.getShipProjectiles().add(projectile);}

    public void addAlienProjectile(Projectile<Alien> projectile) {arena.getAlienProjectiles().add(projectile);}
}
