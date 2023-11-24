package SpaceInvaders.source_code.Viewer.Game.RegularElements;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Alien;

public class AlienViewer implements ElementViewer<Alien> {
    final private  char[] AlienChars = {'\u00ca','\u00cc','\u00ce'}; // Ê, Ì, Î

    final private String [] AlienColors = {"#DB55DD", "#EBDF64", "#62DE6D"};
    int AlienType;
    public AlienViewer(int AlienType){
        if(AlienType < 3) {
            this.AlienType = AlienType;
        }
        else{
            this.AlienType = 0;
        }
    }
    @Override
    public void draw(GUI gui, Alien alien){
        gui.drawElement(alien.getPosition(), AlienChars[AlienType], AlienColors[AlienType]);
    }
}
