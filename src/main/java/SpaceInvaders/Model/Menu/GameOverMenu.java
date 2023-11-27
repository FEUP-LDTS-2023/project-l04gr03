package SpaceInvaders.Model.Menu;

import java.util.Arrays;

public class GameOverMenu extends Menu {
    private StringBuilder username;
    private final Integer score;

    public GameOverMenu(int score){
        this.score = score;
        this.username = new StringBuilder("");
        this.options = Arrays.asList("Restart", "Leaderboard", "Exit");
    }

    public Integer getScore(){
        return score;
    }

    public String getUsername(){
        return username.toString();
    }


    public void addLetter(Character Letter){
        if(username.length() < 6) {
            username.append(Letter);
        }
    }

    public void removeLetter(){
        if(username.length() > 0) {
            username.deleteCharAt(username.length()-1);
        }
    }

    public boolean isSelectedRestart(){
        return isSelected(0);
    }

    public boolean isSelectedLeaderboard(){
        return isSelected(1);
    }

    public boolean isSelectedExit(){
        return isSelected(2);
    }

    public void setUsername(StringBuilder username) {
        this.username = username;
    }
}
