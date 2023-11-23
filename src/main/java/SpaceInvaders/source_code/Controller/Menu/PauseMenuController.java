package SpaceInvaders.source_code.Controller.Menu;

import SpaceInvaders.source_code.Controller.Controller;
import SpaceInvaders.source_code.Game;
import SpaceInvaders.source_code.Model.Menu.PauseMenu;
import SpaceInvaders.source_code.State.GameStates;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class PauseMenuController extends Controller<PauseMenu> {
    PauseMenu menu;
    public PauseMenuController(PauseMenu menu){
        super(menu);
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
        switch(key.getKeyType()){
            case ArrowUp:
                getModel().previousOption();
                break;
            case ArrowDown:
                getModel().nextOption();
                break;
            case Enter:
                if(getModel().isSelectedContinue()){
                    game.setState(GameStates.RESUME_GAME);
                }
                else if(getModel().isSelectedInstructions()){
                    game.setState(GameStates.INSTRUCTIONS);
                }
                else if(getModel().isSelectedRestart()){
                    game.setState(GameStates.GAME);
                }
                else if(getModel().isSelectedExit()){
                    game.setState(GameStates.START_MENU);
                }
                break;
        }
    }
}
