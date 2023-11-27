package SpaceInvaders.Model.Menu;

import java.util.Arrays;

public class StartMenu extends Menu {

    public StartMenu(){
        options = Arrays.asList("Play", "Leaderboard", "Instructions", "Exit");
    }

    public boolean isSelectedPlay(){
        return isSelected(0);
    }

    public boolean isSelectedLeaderboard(){
        return isSelected(1);
    }

    public boolean isSelectedInstructions(){
        return  isSelected(2);
    }

    public boolean isSelectedExit(){
        return isSelected(3);
    }
}
