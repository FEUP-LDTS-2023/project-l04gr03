package SpaceInvaders.source_code.Viewer.Menu;

import SpaceInvaders.source_code.GUI.GUI;
import SpaceInvaders.source_code.Model.Menu.Menu;
import SpaceInvaders.source_code.Model.Position;
import SpaceInvaders.source_code.Viewer.Viewer;

public abstract class MenuViewer<T extends Menu> extends Viewer<T> {

    private final int reference_x;
    private final int reference_y;
    public MenuViewer(T menu, Position position){
        super(menu);
        reference_x = position.getX();
        reference_y = position.getY();
    }

    static final String colorSelected = "#900020"; // this color is a type of red
    static final String colorTitle = "#006400" ; // this color is a dark green
    static final String  color = "#fffafa"; //this color is a type of white


    protected void drawOptions(GUI gui){
        for(int i = 0; i < getModel().getNumberOptions(); i++){
            if(getModel().isSelected(i)) {
                gui.drawText(new Position(reference_x, reference_y + i), "->" + getModel().getOption(i), colorSelected);
            }
            else{
                gui.drawText(new Position(reference_x, reference_y + i), getModel().getOption(i), color);
            }
        }
    }

    protected void drawMenuTitle(GUI gui, String title, String color, Position position){
        gui.drawText(position,title, colorTitle);
    }
}
