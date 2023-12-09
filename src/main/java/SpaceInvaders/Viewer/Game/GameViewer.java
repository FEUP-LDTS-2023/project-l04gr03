package SpaceInvaders.Viewer.Game;

import SpaceInvaders.GUI.GUI;
import SpaceInvaders.Model.Game.Collectables.*;
import SpaceInvaders.Model.Game.Element;
import SpaceInvaders.Model.Game.Arena;
import SpaceInvaders.Model.Game.RegularGameElements.AlienMode;
import SpaceInvaders.Model.Game.RegularGameElements.ShipMode;
import SpaceInvaders.Viewer.Game.Collectables.*;
import SpaceInvaders.Viewer.Game.RegularElements.*;
import SpaceInvaders.Model.Position;
import SpaceInvaders.Viewer.Viewer;

import java.util.List;

public class GameViewer extends Viewer<Arena> {

    private int alienCharChoice = 0;
    private long lastCharChange = 0;
    public GameViewer(Arena arena){
        super(arena);
    }

    @Override
    public void drawElements(GUI gui, long time) {
        drawElements(gui, getModel().getAliens(), new AlienViewer(alienCharChoice));
        //Changes alien char for the next frame (only if needed)
        ChangeChar(time);
        drawElements(gui,getModel().getCoverWalls(), new CoverWallViewer());
        drawElements(gui, getModel().getWalls(), new WallViewer());
        drawElement(gui, getModel().getShip(), new ShipViewer());
        drawElements(gui, getModel().getProjectiles(), new ProjectileViewer());
        drawElement(gui,getModel().getAlienShip(),new AlienShipViewer());
        drawCollectable(gui, getModel().getActiveCollectable());
        drawGameHUD(gui);
    }

    private void ChangeChar(long time){
        if(time - lastCharChange > 300) {
            alienCharChoice++;
            alienCharChoice = alienCharChoice % 2;
            lastCharChange = time;
        }
    }

    public int getAlienCharChoice(){
        return alienCharChoice;
    }

    public long getLastCharChange(){
        return lastCharChange;
    }

    private void drawGameHUD(GUI gui){
        gui.drawText(new Position(5,3), "SCORE = ", "#F8F8FF");
        gui.drawText(new Position(15,3), String.valueOf(getModel().getScore()),"#F8F8FF" );
        gui.drawText(new Position(55,3), "HEALTH = ", "#F8F8FF");
        gui.drawText(new Position(65,3), String.valueOf(getModel().getShip().getHealth()),"#F8F8FF" );
        gui.drawText(new Position(5,5),"ROUND ","#F8F8FF");
        gui.drawText(new Position(11,5),String.valueOf(getModel().getRound()),"#F8F8FF");
        if(getModel().getShip().getShipMode() != ShipMode.NORMAL_MODE){
            gui.drawText(new Position(55,5), String.valueOf(getModel().getShip().getShipMode()),"#F8F8FF");
        }
        if(!getModel().getAliens().isEmpty()){
            if(getModel().getAliens().get(0).getAlienMode() != AlienMode.NORMAL_MODE){
                gui.drawText(new Position(55,5), String.valueOf(getModel().getAliens().get(0).getAlienMode()),"#F8F8FF");
            }
        }
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        if(element != null) {
            viewer.draw(gui, element);
        }
    }

    private<T extends Element> void drawCollectable(GUI gui, T collectable){
        if(collectable != null) {
            if (collectable.getClass() == GodModeCollectable.class) {
                new GodModeCollectableViewer().draw(gui, (GodModeCollectable) collectable);

            } else if (collectable.getClass() == MachineGunModeCollectable.class) {
                new MachineGunCollectableViewer().draw(gui, (MachineGunModeCollectable) collectable);

            } else if (collectable.getClass() == HealthCollectable.class) {
                new HealthCollectableViewer().draw(gui, (HealthCollectable) collectable);

            } else if (collectable.getClass() == ScoreCollectable.class) {
                new ScoreCollectableViewer().draw(gui, (ScoreCollectable) collectable);
            }
            else if(collectable.getClass() == DamageCollectable.class) {
                new DamageCollectableViewer().draw(gui, (DamageCollectable)  collectable);
            }
        }
    }

}
