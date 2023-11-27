package SpaceInvaders.Controller.Menu;

import SpaceInvaders.Controller.Controller;
import SpaceInvaders.Game;
import SpaceInvaders.Model.Menu.OnlyTextMenu;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class OnlyTextMenuController extends Controller<OnlyTextMenu> {

    OnlyTextMenu menu;
    public OnlyTextMenuController(OnlyTextMenu menu){
        super(menu);
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
        if(key == null){
            return;
        }
       if(key.getKeyType() == KeyType.Escape){
           game.setPrevState();
       }
    }
}
