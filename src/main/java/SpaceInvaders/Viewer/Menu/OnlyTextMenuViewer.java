package SpaceInvaders.Viewer.Menu;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Menu.OnlyTextMenu;
import SpaceInvaders.Model.Position;
import SpaceInvaders.Viewer.Viewer;

public abstract class OnlyTextMenuViewer<T extends OnlyTextMenu> extends Viewer<OnlyTextMenu> {
    private final int reference_x;
    private final int reference_y;

    public OnlyTextMenuViewer(T menu, Position position){
        super(menu);
        reference_x = position.getX();
        reference_y = position.getY();
    }

    static final String colorTitle = "#006400" ; // this color is a dark green
    static final String  color = "#fffafa"; //this color is a type of white


    protected void drawFileText(GUI gui){
        for(int i = 0; i < getModel().getText().size(); i++){
            gui.drawText(new Position(reference_x, reference_y + i + 1),getModel().getText().get(i),color);
        }
    }

    protected void drawMenuTitle(GUI gui, String title, String color, Position position){
        gui.drawText(position,title, colorTitle);
    }

    protected int getReference_x(){
        return reference_x;
    }

    protected int getReference_y(){
        return reference_y;
    }
}
