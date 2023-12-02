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


    public void resetShipMode(){
        arena.getShip().setShipMode(ShipMode.NORMAL_MODE);
    }

    public void resetAliensMode(){
        List<Alien> aliens = arena.getAliens();
        for(Alien alien : aliens){
            alien.setAlienMode(AlienMode.NORMAL_MODE);
        }
    }
    public void createCollectable() {
        List<Integer> columns = arena.getFreeArenaColumns();
        Random random = new Random();
        int randomElement = random.nextInt(2);
        int randomIndex = random.nextInt(columns.size());
        Position position = new Position(columns.get(randomIndex),1);
        if(randomElement == 0){
            createCollectableAffectingShip(position);
        }
        else{
            createCollectableAffectingAliens(position);
        }
    }

    public void createCollectableAffectingShip(Position position){
        Random random = new Random();
        List<CollectableType> collectableTypes =new ArrayList<>(Arrays.asList(CollectableType.HEALTH, CollectableType.DAMAGE, CollectableType.MACHINE_GUN_MODE, CollectableType.GOD_MODE));
        int randomCollectableTypeIndex = random.nextInt(collectableTypes.size());
        List<Integer> collectableMultiplier = new ArrayList<>(Arrays.asList(2,3,4,5,10));
        int randomCollectableMultiplierIndex = random.nextInt(collectableMultiplier.size());
        CollectableType collectableType = collectableTypes.get(randomCollectableTypeIndex);
        int multiplier = collectableMultiplier.get(randomCollectableMultiplierIndex);
        CollectableFactory<Ship> collectableFactory = new CollectableFactory<>(position,collectableType,multiplier,arena.getShip());
        arena.setActiveCollectable(collectableFactory.createCollectable());

    }


    public void createCollectableAffectingAliens(Position position){
        Random random = new Random();
        List<Integer> collectableMultiplier = new ArrayList<>(Arrays.asList(2,3,4,5,10));
        int randomCollectableMultiplierIndex = random.nextInt(collectableMultiplier.size());
        int multiplier = collectableMultiplier.get(randomCollectableMultiplierIndex);
        CollectableFactory<List<Alien>> collectableFactory = new CollectableFactory<>(position,CollectableType.SCORE,multiplier,arena.getAliens());
        arena.setActiveCollectable(collectableFactory.createCollectable());
    }

    public boolean hasAlienInFront(Alien alien, Alien excludedAlien){
        List<Alien> aliens = arena.getAliens();
        int i = 0;
        while (i < aliens.size() && aliens.get(i).getPosition().getX() <= alien.getPosition().getX()){
            if(aliens.get(i).getPosition().getX() == alien.getPosition().getX()){
                if(aliens.get(i).getPosition().getY() > alien.getPosition().getY() && !aliens.get(i).equals(excludedAlien)){
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    public void removeAlien(Alien alien) {
        List<Alien> aliens = arena.getAliens();
        for(int i = 0; i < aliens.size(); i++){
            if(aliens.get(i).equals(alien)){
                if(i > 0){
                    if(!hasAlienInFront(aliens.get(i - 1),aliens.get(i))){
                        aliens.get(i - 1).setAlienState(AlienState.ATTACKING);
                    }
                }
                aliens.remove(aliens.get(i));
                break;
            }
        }
    }

    public void removeCoverWall(CoverWall coverWall){arena.getCoverWalls().remove(coverWall);}


    public void removeActiveCollectable(){
        arena.setActiveCollectable(null);
    }

    public void addProjectile(Projectile projectile) {arena.getProjectiles().add(projectile);}

    public void removeProjectile(Projectile projectile) {arena.getProjectiles().remove(projectile);}

    public void removeAlienShip(){
        arena.setAlienShip(null);
    }


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
