package SpaceInvaders.source_code.Controller.Game;

import SpaceInvaders.source_code.Game;
import SpaceInvaders.source_code.Model.Game.Arena;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Alien;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.CoverWall;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.source_code.Model.Position;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.List;

public class AlienController extends ArenaController {

    private MovementDirection movementDirection;

    private long lastMovementTime;

    private long time;

    public AlienController(Arena arena) {
        super(arena);
        this.movementDirection = MovementDirection.RIGHT;
        this.lastMovementTime = 0;
    }

    public boolean canMoveAlien(Alien alien) {
        switch (movementDirection) {
            case LEFT:
               return alien.getPosition().getX() - 3 > 0;
            case RIGHT:
                return alien.getPosition().getX() + 3 < getModel().getWidth();
            case DOWN:
                return true;
        }
        return false;
    }

    public boolean canMoveAliens(){
        List<Alien> aliens = getModel().getAliens();
        for(Alien alien : aliens){
            if(!canMoveAlien(alien)){
                return false;
            }
        }
        return true;
    }

    public void moveAlien(Alien alien){
        Position alienPosition = new Position(alien.getPosition().getX(),alien.getPosition().getY());
        switch(movementDirection){
            case LEFT:
                alien.setPosition(new Position(alienPosition.getX() - 3,alienPosition.getY()));
            case RIGHT:
                alien.setPosition(new Position(alienPosition.getX() + 3,alienPosition.getY()));
            case DOWN:
                alien.setPosition(new Position(alienPosition.getX(),alienPosition.getY() + 3));
        }
    }

    public void moveAliens(){
        List<Alien> aliens = getModel().getAliens();
        for(Alien alien : aliens){
            moveAlien(alien);
        }
    }

    public void updateMovementDirection(){
        switch (movementDirection){
            case LEFT:
                if(!canMoveAliens()){
                    this.movementDirection = MovementDirection.DOWN;
                }
                break;
            case RIGHT:
                if(!canMoveAliens()){
                    this.movementDirection = MovementDirection.LEFT;
                }
                break;
            case DOWN:
                this.movementDirection = MovementDirection.RIGHT;
                break;
        }
    }

    public void step(Game game, KeyStroke key, long time) {
        updateMovementDirection();
        if(lastMovementTime - time > 200){
            moveAliens();
        }
        this.lastMovementTime = time;
    }
}
