package SpaceInvaders.source_code.Controller.Game;

import SpaceInvaders.source_code.Game;
import SpaceInvaders.source_code.Model.Game.Arena;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class ArenaController extends GameController {
    public ArenaController(Arena arena) {
        super(arena);
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {

    }
}
