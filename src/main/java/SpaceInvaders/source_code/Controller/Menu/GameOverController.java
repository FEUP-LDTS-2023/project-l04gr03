package SpaceInvaders.source_code.Controller.Menu;

import SpaceInvaders.source_code.Controller.Controller;
import SpaceInvaders.source_code.Game;
import SpaceInvaders.source_code.Model.Menu.GameOverMenu;
import SpaceInvaders.source_code.Model.Menu.Menu;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;


public class GameOverController extends Controller<GameOverMenu> {

    public GameOverController(GameOverMenu menu){
        super(menu);
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
        switch (key.getKeyType()){

            case ArrowUp:
                getModel().previousOption();
                break;
            case ArrowDown:
                getModel().nextOption();
                break;
            case Enter:
                if(getModel().isSelectedRestart()){

                }

        }
    }
}


