package SpaceInvaders.source_code.Model.Menu;

import SpaceInvaders.source_code.Model.Game.ArenaBuilderByRound;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class OnlyTextMenu {
    protected List<String> text;

    public OnlyTextMenu(String filename) throws IOException {
        this.text = readFile(filename);
    }

    public List<String> getText(){
        return text;
    }

    private List<String> readFile(String filename) throws IOException {
        URL resource = ArenaBuilderByRound.class.getResource(filename);
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        List<String> newText = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            newText.add(line);
        return newText;
    }


}
