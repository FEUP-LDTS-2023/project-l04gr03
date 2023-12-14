package SpaceInvaders.Model.Menu;

import java.io.IOException;

public class Instructions extends OnlyTextMenu {

    public Instructions() throws IOException {
        super("src/main/resources/text/Instructions.txt");
    }
}
