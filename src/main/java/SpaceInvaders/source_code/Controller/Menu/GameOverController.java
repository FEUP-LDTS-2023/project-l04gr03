package SpaceInvaders.source_code.Controller.Menu;

import SpaceInvaders.source_code.Controller.Controller;
import SpaceInvaders.source_code.Game;
import SpaceInvaders.source_code.Model.Menu.GameOverMenu;
import SpaceInvaders.source_code.Model.Menu.Menu;
import SpaceInvaders.source_code.State.GameStates;
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
                    game.setState(GameStates.GAME);
                }
                else if(getModel().isSelectedExit()){
                    game.setState(GameStates.START_MENU);
                }
                else if(getModel().isSelectedLeaderboard()) {
                    game.setState(GameStates.INSTRUCTIONS);
                }
            case Character:
                getModel().addLetter(key.getCharacter());
            case Backspace:
                getModel().removeLetter();
        }
    }
}


