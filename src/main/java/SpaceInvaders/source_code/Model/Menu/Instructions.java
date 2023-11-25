package SpaceInvaders.source_code.Model.Menu;

import java.io.IOException;

public class Instructions extends OnlyTextMenu {

    public Instructions() throws IOException {
        super("/text/Instructions.txt");
    }
}
