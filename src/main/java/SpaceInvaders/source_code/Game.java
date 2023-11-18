package SpaceInvaders.source_code;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.GUI.GUILanterna;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        GUI testing = new GUILanterna(20,20);
        ((GUILanterna) testing).drawText(10,10, "Lucas","#0000ff" );
        ((GUILanterna) testing).refresh();

    }
}