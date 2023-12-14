package SpaceInvaders.Model.Menu;

import java.io.IOException;

public class Leaderboard extends OnlyTextMenu {

    public Leaderboard() throws IOException {
        super("src/main/resources/text/Leaderboard.txt");
        sortByScore();
    }

    public void sortByScore(){
        text.sort((s1, s2) -> {
            String[] splitS1 = s1.split(" ", -1);
            String[] splitS2 = s2.split(" ", -1);
            return Integer.parseInt(splitS2[1]) - Integer.parseInt(splitS1[1]);
                });
    }

}
