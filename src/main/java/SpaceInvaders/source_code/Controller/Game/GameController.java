package SpaceInvaders.source_code.Controller.Game;

import SpaceInvaders.source_code.Controller.Controller;
import SpaceInvaders.source_code.Model.Game.Arena;

public abstract class GameController extends Controller<Arena> {
    public GameController(Arena arena){
        super(arena);
    }
}
