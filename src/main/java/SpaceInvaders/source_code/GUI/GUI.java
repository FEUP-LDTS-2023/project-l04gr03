package SpaceInvaders.source_code.GUI;

import SpaceInvaders.source_code.Model.Position;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public interface GUI {

    public void drawAlien(Position position);
    public void drawShip(Position position);
    public void drawWall(Position position);
    public void drawAlienShip(Position position);
    public void drawProjectile(Position position);
    public void drawCoverWall(Position position);
    public void drawCollectable(Position position, String type);
    public void drawText(Position position, String text, String color);

    public KeyStroke getNextAction() throws IOException;
    public void refresh() throws IOException;
    public void close() throws IOException;


}
