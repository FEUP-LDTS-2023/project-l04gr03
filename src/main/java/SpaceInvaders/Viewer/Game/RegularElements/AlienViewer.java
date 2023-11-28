package SpaceInvaders.Viewer.Game.RegularElements;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.RegularGameElements.Alien;

public class AlienViewer implements ElementViewer<Alien> {
    final private  char[] AlienChars = {'\u00ca','\u00cc','\u00ce'}; // Ê, Ì, Î

    final private char[] AlienChars2 = {'\u00cb','\u00cd','\u00cf'};

    final private String [] AlienColors = {"#DB55DD", "#EBDF64", "#62DE6D"};
    private int charChoice = 0;
    public AlienViewer(int charChoice){
        this.charChoice = charChoice;
    }
    @Override
    public void draw(GUI gui, Alien alien){
        int AlienType = alien.getType();
        if(charChoice == 0) {
            gui.drawElement(alien.getPosition(), AlienChars[AlienType], AlienColors[AlienType]);
        }
        else if(charChoice == 1){
            gui.drawElement(alien.getPosition(),AlienChars2[AlienType], AlienColors[AlienType]);

        }
    }
}
