package SpaceInvaders.Model.Menu;

import java.util.Arrays;

public class PauseMenu extends Menu {

    public PauseMenu(){
        options = Arrays.asList("Continue", "Instructions", "Restart", "Exit");
    }

    public boolean isSelectedContinue(){
        return isSelected(0);
    }

    public boolean isSelectedInstructions(){
        return isSelected(1);
    }

    public boolean isSelectedRestart(){
        return isSelected(2);
    }

    public boolean isSelectedExit(){
        return isSelected(3);
    }
}
