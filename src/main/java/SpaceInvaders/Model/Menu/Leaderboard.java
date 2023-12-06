package SpaceInvaders.Model.Menu;

import java.io.IOException;

public class Leaderboard extends OnlyTextMenu {

    public Leaderboard() throws IOException {
        super("/text/Leaderboard.txt");
        sortByScore();
    }

    @SuppressWarnings("StringSplitter")
    public void sortByScore(){
        text.sort((s1, s2) -> {
            String[] splitS1 = s1.split(" ");
            String[] splitS2 = s2.split(" ");
            return Integer.parseInt(splitS2[1]) - Integer.parseInt(splitS1[1]);
                });
    }

}
