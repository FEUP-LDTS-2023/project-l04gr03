package SpaceInvaders.Model.Game;

import SpaceInvaders.Model.Game.Collectables.CollectableFactory;
import SpaceInvaders.Model.Game.Collectables.CollectableType;
import SpaceInvaders.Model.Game.RegularGameElements.*;
import SpaceInvaders.Model.Game.RegularGameElements.*;
import SpaceInvaders.Model.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    public void createAlienShip() {
        Random random = new Random();
        List<Integer> movementOptions = new ArrayList<>(Arrays.asList(-1,1));
        int movementChoiceIndex = random.nextInt(movementOptions.size());
        int movement = movementOptions.get(movementChoiceIndex);
        if(movement == -1){
            AlienShip alienShip = new AlienShip(new Position(arena.getWidth() - 4, 8),50 , 500, movement);
            arena.setAlienShip(alienShip);
        }
        else {
            AlienShip alienShip = new AlienShip(new Position(4, 8),50 , 500, movement);
            arena.setAlienShip(alienShip);
        }
    }
}
