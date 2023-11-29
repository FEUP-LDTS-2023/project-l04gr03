package SpaceInvaders.source_code.Model.Game;

import SpaceInvaders.source_code.Model.Game.Collectables.Collectable;
import SpaceInvaders.source_code.Model.Game.Collectables.CollectableFactory;
import SpaceInvaders.source_code.Model.Game.Collectables.CollectableType;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.*;
import SpaceInvaders.source_code.Model.Position;

import java.util.*;

public class ArenaModifier {

    private Arena arena;

    public ArenaModifier(Arena arena){
        this.arena = arena;
    }

    public void restoreShipDamage() {
        arena.getShip().setDamagePerShot(arena.getRound() * 50);
    }

    public void restoreAlienScore() {
        for(Alien alien : arena.getAliens()){
            alien.setScore(arena.getRound() * 20);
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


    public void removeActiveCollectable(){
        arena.setActiveCollectable(null);
    }

    public void addProjectile(Projectile projectile) {arena.getProjectiles().add(projectile);}

    public void removeProjectile(Projectile projectile) {arena.getProjectiles().remove(projectile);}
}
