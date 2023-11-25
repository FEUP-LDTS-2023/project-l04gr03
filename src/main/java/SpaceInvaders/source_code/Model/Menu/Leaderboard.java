package SpaceInvaders.source_code.Model.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Leaderboard extends OnlyTextMenu {

    public Leaderboard() throws IOException {
        super("/text/Leaderboard.txt");
        sortByScore();
    }
    public void sortByScore(){
        text.sort((s1, s2) -> {
            String[] splitS1 = s1.split(" ");
            String[] splitS2 = s2.split(" ");
            return Integer.parseInt(splitS2[1]) - Integer.parseInt(splitS1[1]);
                });
    }

}
