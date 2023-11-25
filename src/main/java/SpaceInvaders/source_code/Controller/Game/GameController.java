package SpaceInvaders.source_code.Controller.Game;

import SpaceInvaders.source_code.Controller.Controller;
import SpaceInvaders.source_code.Model.Game.Arena;
import SpaceInvaders.source_code.Model.Game.ArenaModifier;

public abstract class GameController extends Controller<Arena> {

    private ArenaModifier arenaModifier;
    public GameController(Arena arena){
        super(arena);
        this.arenaModifier = new ArenaModifier(arena);
    }

    public ArenaModifier getArenaModifier() {return arenaModifier;}
}
