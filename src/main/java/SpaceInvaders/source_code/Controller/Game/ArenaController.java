package SpaceInvaders.source_code.Controller.Game;

import SpaceInvaders.source_code.Game;
import SpaceInvaders.source_code.Model.Game.Arena;
import SpaceInvaders.source_code.Model.Game.ArenaModifier;
import SpaceInvaders.source_code.Model.Game.Collectables.*;
import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.*;
import SpaceInvaders.source_code.State.GameStates;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.List;

public class ArenaController extends GameController {

    private ShipController shipController;

    private AlienController alienController;

    private ProjectileController projectileController;

    private CollectableController collectableController;

    private ArenaModifier arenaModifier;

    public ArenaController(Arena arena) {
        super(arena);
        this.shipController = new ShipController(arena);
        this.alienController = new AlienController(arena);
        this.projectileController = new ProjectileController(arena);
        this.collectableController = new CollectableController(arena);
        this.arenaModifier = new ArenaModifier(arena);
    }

    public ShipController getShipController() {return shipController;}

    public AlienController getAlienController() {return alienController;}

    public ProjectileController getProjectileController() {return projectileController;}


    public ArenaModifier getArenaModifier() {return arenaModifier;}


    public boolean collisionBetween(Element element1, Element element2){
        return element1.getPosition().equals(element2.getPosition());
    }

    public boolean shipCollidesWithAlien(){
        Ship ship = getModel().getShip();
        List<Alien> aliens = getModel().getAliens();
        for(Alien alien : aliens){
            if(collisionBetween(ship,alien)){
                return true;
            }
        }
        return false;
    }

    public boolean alienCollidesWithCoverWall(){
        List<CoverWall> coverWalls = getModel().getCoverWalls();
        List<Alien> aliens = getModel().getAliens();
        for(Alien alien : aliens){
            for(CoverWall coverWall : coverWalls){
                if(collisionBetween(alien,coverWall)){
                    return true;
                }
            }
        }
        return false;
    }

    public void projectileCollisionsWithWalls(){
        List<Projectile> projectiles = getModel().getProjectiles();
        List<Wall> walls = getModel().getWalls();
        for(int i = 0; i < walls.size(); i++){
            for(int j = 0; j < projectiles.size(); j++){
                if(collisionBetween(walls.get(i),projectiles.get(j))){
                    arenaModifier.removeProjectile(projectiles.get(j));
                }
            }
        }
    }

    public void projectileCollisionsWithShip(){
        List<Projectile> projectiles = getModel().getProjectiles();
        Ship ship = getModel().getShip();
        for(int i = 0; i < projectiles.size(); i++){
            if(collisionBetween(ship,projectiles.get(i))){
                getShipController().hitByProjectile(projectiles.get(i));
                arenaModifier.removeProjectile(projectiles.get(i));
            }
        }
    }


    public void projectileCollisionsWithAliens(){
        List<Projectile> projectiles = getModel().getProjectiles();
        List<Alien> aliens = getModel().getAliens();
        for(int i = 0; i < aliens.size(); i++){
            for (int j = 0; j < projectiles.size(); j++){
                if(collisionBetween(aliens.get(i), projectiles.get(j))){
                    getAlienController().hitByProjectile(aliens.get(i),projectiles.get(j));
                    arenaModifier.removeProjectile(projectiles.get(j));
                }
            }
        }
    }

    public void projectileCollisionsWithCoverWalls(){
        List<Projectile> projectiles = getModel().getProjectiles();
        List<CoverWall> coverWalls = getModel().getCoverWalls();
        for(int i = 0; i < coverWalls.size(); i++){
            for (int j = 0; j < projectiles.size(); j++){
                if(collisionBetween(coverWalls.get(i),projectiles.get(j))){
                    coverWallHitByProjectile(coverWalls.get(i),projectiles.get(j));
                    arenaModifier.removeProjectile(projectiles.get(j));
                }
            }
        }
    }

    public void shipCollisionsWithCollectables(){
        Ship ship = getModel().getShip();
        Collectable collectable = getModel().getActiveCollectable();
        if(collectable != null){
            if(collisionBetween(ship, collectable)){
                getModel().getActiveCollectable().execute();
                collectableController.defineCollectableEffect(getModel().getActiveCollectable().getClass());
                getArenaModifier().removeActiveCollectable();
            }
        }
    }

    public void coverWallHitByProjectile(CoverWall coverWall, Projectile projectile){
        coverWall.decreaseHealth(projectile.getElement().getDamagePerShot());
    }

    public void removeDestroyedCoverWalls(){
        List<CoverWall> coverWalls = getModel().getCoverWalls();
        for (int i = 0; i < coverWalls.size(); i++){
            if(coverWalls.get(i).isDestroyed()){
               arenaModifier.removeCoverWall(coverWalls.get(i));
            }
        }
    }

    public void removeDestroyedElements(){
        alienController.removeDestroyedAliens();
        removeDestroyedCoverWalls();
    }

    public void checkCollisions(){
        projectileCollisionsWithWalls();
        projectileCollisionsWithShip();
        projectileCollisionsWithAliens();
        projectileCollisionsWithCoverWalls();
        shipCollisionsWithCollectables();
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
        if(key != null){
            if(key.getKeyType() == KeyType.Escape){
                game.setState(GameStates.PAUSE);
            }
        }
        if(getModel().getShip().getHealth() == 0 || shipCollidesWithAlien() || alienCollidesWithCoverWall() || getModel().getAliens().isEmpty()){
            game.setState(GameStates.GAME_OVER);
        }
        removeDestroyedElements();
        checkCollisions();
        shipController.step(game,key,time);
        alienController.step(game,key,time);
        projectileController.step(game,key,time);
        collectableController.step(game,key,time);
    }
}
