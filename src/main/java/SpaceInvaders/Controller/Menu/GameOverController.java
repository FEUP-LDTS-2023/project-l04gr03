package SpaceInvaders.Controller.Menu;

import SpaceInvaders.Controller.Controller;
import SpaceInvaders.Controller.Sound.SoundManager;
import SpaceInvaders.Game;
import SpaceInvaders.Model.Menu.GameOverMenu;
import SpaceInvaders.Model.Sound.Sound_Options;
import SpaceInvaders.State.GameStates;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;


public class GameOverController extends Controller<GameOverMenu> {

    public GameOverController(GameOverMenu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
        if(key == null){
            return;
        }
        switch (key.getKeyType()) {
            case ArrowUp:
                getModel().previousOption();
                SoundManager.getInstance().playSound(Sound_Options.MENU_SWITCH);
                break;
            case ArrowDown:
                getModel().nextOption();
                SoundManager.getInstance().playSound(Sound_Options.MENU_SWITCH);
                break;
            case Enter:
                if (getModel().isSelectedRestart()) {
                    UpdateLeaderboard(getModel().getScore(), getModel().getUsername());
                    game.setState(GameStates.NEW_GAME);
                } else if (getModel().isSelectedExit()) {
                    UpdateLeaderboard(getModel().getScore(), getModel().getUsername());
                    game.setState(GameStates.START_MENU);
                } else if (getModel().isSelectedLeaderboard()) {
                    game.setState(GameStates.LEADERBOARD);
                }
                break;
            case Character:
                getModel().addLetter(key.getCharacter());
                break;
            case Backspace:
                getModel().removeLetter();
                break;

            default: //Other keys don´t have actions
        }
    }

    public void UpdateLeaderboard(Integer score, String username) throws IOException {
        File file = new File("src/main/resources/text/Leaderboard.txt");
        BufferedWriter bw =  Files.newBufferedWriter(Paths.get(file.getAbsolutePath()), UTF_8, CREATE, APPEND);
        if(username.isEmpty()){
            username = "Unknown";
        }
        bw.write(username + " " + score + '\n');
        bw.close();
    }
}


