package SpaceInvaders.Controller.Game;

import SpaceInvaders.Controller.Controller;
import SpaceInvaders.Model.Game.ArenaModifier;
import SpaceInvaders.Model.Game.Arena;

public abstract class GameController extends Controller<Arena> {

    private ArenaModifier arenaModifier;
    public GameController(Arena arena){
        super(arena);
        this.arenaModifier = new ArenaModifier(arena);
    }

    public ArenaModifier getArenaModifier() {return arenaModifier;}
}
