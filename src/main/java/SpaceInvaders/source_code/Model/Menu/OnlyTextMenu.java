package SpaceInvaders.source_code.Model.Menu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class OnlyTextMenu {
    protected List<String> text;

    public OnlyTextMenu(String filename){
        this.text = readFile(filename);
    }

    private List<String> readFile(String filename) {
        File file = new File(filename);
        List<String> newText = new ArrayList<>();
        Scanner scanner = new Scanner(filename);

        while (scanner.hasNextLine()){
            newText.add(scanner.nextLine());
        }
        return newText;
    }
}
