package SpaceInvaders.Controller.Menu;

import SpaceInvaders.Controller.Controller;
import SpaceInvaders.Controller.Sound.SoundManager;
import SpaceInvaders.Game;
import SpaceInvaders.Model.Menu.PauseMenu;
import SpaceInvaders.Model.Sound.Sound_Options;
import SpaceInvaders.State.GameStates;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;


public class PauseMenuController extends Controller<PauseMenu> {

    public PauseMenuController(PauseMenu menu){
        super(menu);
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
        if(key == null){
            return;
        }
        switch(key.getKeyType()){
            case ArrowUp:
                getModel().previousOption();
                SoundManager.getInstance().playSound(Sound_Options.MENU_SWITCH);
                break;
            case ArrowDown:
                getModel().nextOption();
                SoundManager.getInstance().playSound(Sound_Options.MENU_SWITCH);
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
