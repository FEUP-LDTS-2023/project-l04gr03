package SpaceInvaders.Controller.Menu;

import SpaceInvaders.Controller.Controller;
import SpaceInvaders.Game;
import SpaceInvaders.Model.Menu.PauseMenu;
import SpaceInvaders.State.GameStates;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

import static SpaceInvaders.Controller.Sound.SoundManager.playSwitchOptionSound;

public class PauseMenuController extends Controller<PauseMenu> {
    PauseMenu menu;
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
                playSwitchOptionSound();
                break;
            case ArrowDown:
                getModel().nextOption();
                playSwitchOptionSound();
                break;
            case Enter:
                if(getModel().isSelectedContinue()){
                    game.setState(GameStates.RESUME_GAME);
                    playSwitchOptionSound();
                }
                else if(getModel().isSelectedInstructions()){
                    game.setState(GameStates.INSTRUCTIONS);
                    playSwitchOptionSound();
                }
                else if(getModel().isSelectedRestart()){
                    game.setState(GameStates.GAME);
                    playSwitchOptionSound();
                }
                else if(getModel().isSelectedExit()){
                    game.setState(GameStates.START_MENU);
                    playSwitchOptionSound();
                }
                break;
        }
    }
}
