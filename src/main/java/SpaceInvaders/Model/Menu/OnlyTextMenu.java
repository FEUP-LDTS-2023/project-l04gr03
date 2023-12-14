package SpaceInvaders.Model.Menu;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public abstract class OnlyTextMenu {
    protected List<String> text;


    public OnlyTextMenu(String filename) throws IOException {
        this.text = readFile(filename);
    }

    public List<String> getText(){
        return text;
    }

    private List<String> readFile(String filename) throws IOException {
        Path resource = new File(filename).toPath();
        Reader fileReader = Files.newBufferedReader(resource, Charset.defaultCharset());
        BufferedReader br = new BufferedReader(fileReader);
        List<String> newText = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            newText.add(line);
        return newText;
    }


}
