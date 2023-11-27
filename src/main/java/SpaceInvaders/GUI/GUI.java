package SpaceInvaders.GUI;

import SpaceInvaders.Model.Position;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public interface GUI {

   public void drawElement(Position position, char character, String Color);
    public void drawText(Position position, String text, String color);

    public KeyStroke getNextAction() throws IOException;
    public void clear();
    public void refresh() throws IOException;
    public void close() throws IOException;


}
