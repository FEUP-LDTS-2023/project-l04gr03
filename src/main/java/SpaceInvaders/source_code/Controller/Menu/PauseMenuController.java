package SpaceInvaders.source_code.Controller.Menu;

import SpaceInvaders.source_code.Controller.Controller;
import SpaceInvaders.source_code.Game;
import SpaceInvaders.source_code.Model.Menu.PauseMenu;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class PauseMenuController extends Controller<PauseMenu> {
    PauseMenu menu;
    public PauseMenuController(PauseMenu menu){
        super(menu);
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {

    }
}
