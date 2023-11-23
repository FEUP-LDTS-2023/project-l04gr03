package SpaceInvaders.source_code.Controller.Menu;

import SpaceInvaders.source_code.Controller.Controller;
import SpaceInvaders.source_code.Game;
import SpaceInvaders.source_code.Model.Menu.OnlyTextMenu;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class OnlyTextMenuController extends Controller<OnlyTextMenu> {

    OnlyTextMenu menu;
    public OnlyTextMenuController(OnlyTextMenu menu){
        super(menu);
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {

    }
}
