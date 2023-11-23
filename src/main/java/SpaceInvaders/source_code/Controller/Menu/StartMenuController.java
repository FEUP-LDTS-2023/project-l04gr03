package SpaceInvaders.source_code.Controller.Menu;

import SpaceInvaders.source_code.Controller.Controller;
import SpaceInvaders.source_code.Game;
import SpaceInvaders.source_code.Model.Menu.StartMenu;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class StartMenuController extends Controller<StartMenu> {

    public StartMenuController(StartMenu menu){
        super(menu);
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {

    }
}
