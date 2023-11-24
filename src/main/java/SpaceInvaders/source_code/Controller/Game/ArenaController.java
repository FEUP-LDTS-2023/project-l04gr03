package SpaceInvaders.source_code.Controller.Game;

import SpaceInvaders.source_code.Game;
import SpaceInvaders.source_code.Model.Game.Arena;
import SpaceInvaders.source_code.Model.Game.Element;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Alien;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.CoverWall;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.source_code.State.GameStates;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.List;

public class ArenaController extends GameController {

    private ShipController shipController;

    private AlienController alienController;

    private ProjectileController projectileController;

    public ArenaController(Arena arena) {
        super(arena);
        this.shipController = new ShipController(arena);
        this.alienController = new AlienController(arena);
        this.projectileController = new ProjectileController(arena);
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

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
        if(getModel().getShip().getHealth() == 0 || shipCollidesWithAlien() || alienCollidesWithCoverWall()){
            game.getState().UpdateState(GameStates.GAME_OVER);
        }
        else if(key.getKeyType() == KeyType.Escape){
            game.getState().UpdateState(GameStates.PAUSE);
        }
        else{
            shipController.step(game,key,time);
            alienController.step(game,key,time);
            projectileController.step(game,key,time);
        }

    }
}
