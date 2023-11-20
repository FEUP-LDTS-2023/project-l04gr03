package SpaceInvaders.source_code.Model.Game;

import SpaceInvaders.source_code.Model.Game.Collectables.Collectable;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Alien;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.CoverWall;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Wall;
import SpaceInvaders.source_code.Model.Position;

import java.util.List;

public abstract class ArenaBuilder{

    private int baseShipHealth;

    private int baseShipDamage;

    private int baseAlienHealth;

    private int baseAlienDamage;

    private int baseAlienScore;

    private int baseCoverWallHealth;

    public Arena buildArena(){
        Arena newArena = new Arena(getWidth(),getHeight());
        baseShipHealth = 100;
        baseShipDamage = 50;
        baseAlienHealth = 20;
        baseAlienDamage = 20;
        baseAlienScore = 20;
        baseCoverWallHealth = 100;
        newArena.setShip(createShip());
        newArena.setAliens(createAliens());
        newArena.setWalls(createWalls());
        newArena.setCoverWalls(createCoverWalls());
        return newArena;
    }

    public int getBaseShipHealth() {
        return baseShipHealth;
    }

    public int getBaseShipDamage() {
        return baseShipDamage;
    }

    public int getBaseAlienHealth() {
        return baseAlienHealth;
    }

    public int getBaseAlienDamage() {
        return baseAlienDamage;
    }

    public int getBaseAlienScore() {
        return baseAlienScore;
    }

    public int getBaseCoverWallHealth() {
        return baseCoverWallHealth;
    }

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract Ship createShip();

    public abstract List<Alien> createAliens();

    public abstract List<Wall> createWalls();

    public abstract List<CoverWall> createCoverWalls();

    public abstract Collectable createCollectable(Position position, String type, int multiplier);
}
