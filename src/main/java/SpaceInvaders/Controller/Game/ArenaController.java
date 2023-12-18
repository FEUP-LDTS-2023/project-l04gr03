package SpaceInvaders.Controller.Game;

import SpaceInvaders.Controller.Sound.SoundManager;
import SpaceInvaders.Game;
import SpaceInvaders.Model.Game.Arena;
import SpaceInvaders.Model.Game.ArenaModifier;
import SpaceInvaders.Model.Game.Collectables.Collectable;
import SpaceInvaders.Model.Game.Element;
import SpaceInvaders.Model.Game.RegularGameElements.*;
import SpaceInvaders.Model.Sound.Sound_Options;
import SpaceInvaders.State.GameStates;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.List;

public class ArenaController extends GameController {

    private ShipController shipController;

    private AlienController alienController;

    private ProjectileController projectileController;

    private CollectableController collectableController;

    private AlienShipController alienShipController;

    private ArenaModifier arenaModifier;

    private boolean needToUpdateTimers;

    private long pauseGameTime;

    public ArenaController(Arena arena) {
        super(arena);
        this.shipController = new ShipController(arena);
        this.alienController = new AlienController(arena);
        this.projectileController = new ProjectileController(arena);
        this.arenaModifier = new ArenaModifier(arena);
        this.collectableController = new CollectableController(arena);
        this.alienShipController = new AlienShipController(arena);
        this.needToUpdateTimers = false;
        this.pauseGameTime = 0;
    }

    public ShipController getShipController() {return shipController;}

    public AlienController getAlienController() {return alienController;}

    public AlienShipController getAlienShipController() {return alienShipController;}

    public long getPauseGameTime() {
        return pauseGameTime;
    }

    public void setArenaModifier(ArenaModifier arenaModifier){this.arenaModifier = arenaModifier;}

    public void setShipController(ShipController shipController) {
        this.shipController = shipController;
    }

    public void setAlienController(AlienController alienController) {
        this.alienController = alienController;
    }

    public void setCollectableController(CollectableController collectableController) {
        this.collectableController = collectableController;
    }

    public void setAlienShipController(AlienShipController alienShipController) {
        this.alienShipController = alienShipController;
    }

    public void setPauseGameTime(long pauseGameTime) {
        this.pauseGameTime = pauseGameTime;
    }

    public void setNeedToUpdateTimers(boolean needToUpdateTimers) {
        this.needToUpdateTimers = needToUpdateTimers;
    }

    public void setProjectileController(ProjectileController projectileController) {
        this.projectileController = projectileController;
    }

    public boolean isNeedToUpdateTimers() {
        return needToUpdateTimers;
    }

    public void setTimers(long time){
        long timeGameWasPaused = time - pauseGameTime;
        shipController.setMovementTime(shipController.getMovementTime() + timeGameWasPaused);
        shipController.setShootingTime(shipController.getShootingTime() + timeGameWasPaused);
        alienController.setLastMovementTime(alienController.getLastMovementTime() + timeGameWasPaused);
        alienController.setLastShotTime(alienController.getLastShotTime() + timeGameWasPaused);
        collectableController.setGenerateCollectableTime(collectableController.getGenerateCollectableTime() + timeGameWasPaused);
        alienShipController.setLastMovementTime(alienShipController.getLastMovementTime() + timeGameWasPaused);
        alienShipController.setLastAppearance(alienShipController.getLastAppearance() + timeGameWasPaused);
    }

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

    public boolean alienReachesBottomArenaWall(){
        List<Alien> aliens = getModel().getAliens();
        for(Alien alien : aliens){
            if(alien.getPosition().getY() >= getModel().getHeight() - 1){
                return true;
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
                    this.getArenaModifier().removeProjectile(projectiles.get(j));
                }
            }
        }
    }

    public void projectileCollisionsWithShip(){
        List<Projectile> projectiles = getModel().getProjectiles();
        Ship ship = getModel().getShip();
        for(int i = 0; i < projectiles.size(); i++){
            if(collisionBetween(ship,projectiles.get(i))){
                this.getShipController().hitByProjectile(projectiles.get(i));
                this.getArenaModifier().removeProjectile(projectiles.get(i));
            }
        }
    }


    public void projectileCollisionsWithAliens(){
        List<Projectile> projectiles = getModel().getProjectiles();
        List<Alien> aliens = getModel().getAliens();
        for(int i = 0; i < aliens.size(); i++){
            for (int j = 0; j < projectiles.size(); j++){
                if(collisionBetween(aliens.get(i), projectiles.get(j))){
                    this.getAlienController().hitByProjectile(aliens.get(i),projectiles.get(j));
                    this.getArenaModifier().removeProjectile(projectiles.get(j));
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
                    this.getArenaModifier().removeProjectile(projectiles.get(j));
                }
            }
        }
    }

    public void projectileCollisionWithAlienShip(){
        List<Projectile> projectiles = getModel().getProjectiles();
        AlienShip alienShip = getModel().getAlienShip();
        if(alienShip != null) {
            for (int i = 0; i < projectiles.size(); i++) {
                if (collisionBetween(projectiles.get(i), alienShip)) {
                    this.getAlienShipController().hitByProjectile(alienShip, projectiles.get(i));
                    this.getArenaModifier().removeProjectile(projectiles.get(i));
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
                this.getArenaModifier().removeActiveCollectable();
                SoundManager.getInstance().playSound(Sound_Options.COLLECTABLE);
            }
        }
    }

    public void collectableCollisionsWithWalls(){
        List<Wall> walls = getModel().getWalls();
        Collectable collectable = getModel().getActiveCollectable();
        if(collectable != null){
            for(Wall wall : walls){
                if(collisionBetween(wall,collectable)){
                    getModel().setActiveCollectable(null);
                }
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
        this.getAlienController().removeDestroyedAliens();
        removeDestroyedCoverWalls();
        this.getAlienShipController().removeAlienShip();
    }

    public void checkCollisions(){
        projectileCollisionsWithWalls();
        projectileCollisionsWithShip();
        projectileCollisionsWithAliens();
        shipCollisionsWithCollectables();
        collectableCollisionsWithWalls();
        projectileCollisionsWithCoverWalls();
        projectileCollisionWithAlienShip();
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
        if(needToUpdateTimers){
            setTimers(time);
            needToUpdateTimers = false;
        }
        if(key != null){
            if(key.getKeyType() == KeyType.Escape){
                pauseGameTime = time;
                needToUpdateTimers = true;
                game.setState(GameStates.PAUSE);
            }
        }
        if(getModel().getShip().isDestroyed() || shipCollidesWithAlien() || alienCollidesWithCoverWall() || alienReachesBottomArenaWall()){
            game.setState(GameStates.GAME_OVER);
        }
        if(getModel().getAliens().isEmpty()){
           game.setState(GameStates.NEW_GAME_ROUND);
        }
        shipController.step(game,key,time);
        alienController.step(game,key,time);
        alienShipController.step(game, key, time);
        projectileController.step(game,key,time);
        collectableController.step(game,key,time);
        checkCollisions();
        removeDestroyedElements();
    }
}
