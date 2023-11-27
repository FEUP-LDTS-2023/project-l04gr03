package SpaceInvaders.Controller.Menu;

import SpaceInvaders.Controller.Controller;
import SpaceInvaders.Game;
import SpaceInvaders.Model.Menu.GameOverMenu;
import SpaceInvaders.State.GameStates;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.*;


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
                break;
            case ArrowDown:
                getModel().nextOption();
                break;
            case Enter:
                if (getModel().isSelectedRestart()) {
                    UpdateLeaderboard(getModel().getScore(), getModel().getUsername());
                    game.setState(GameStates.GAME);
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
        }
    }

    private void UpdateLeaderboard(Integer score, String username) throws IOException {
        File file = new File("src/main/resources/text/Leaderboard.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath(),true));
        if(username.isEmpty()){
            username = "Unknown";
        }
        bw.write(username + " " + score + '\n');
        bw.close();
    }
}


