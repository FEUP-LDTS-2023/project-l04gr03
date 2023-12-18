package SpaceInvaders.Model.Game;

import SpaceInvaders.Model.Game.RegularGameElements.*;

import java.util.ArrayList;
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
        baseAlienHealth = 10;
        baseAlienDamage = 10;
        baseAlienScore = 20;
        baseCoverWallHealth = 100;
        newArena.setRound(getRound());
        newArena.setShip(createShip());
        newArena.setAliens(createAliens());
        newArena.setWalls(createWalls());
        newArena.setCoverWalls(createCoverWalls());
        newArena.setProjectiles(new ArrayList<>());
        return newArena;
    }

    public int getBaseShipHealth() {return baseShipHealth;}

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

    public abstract int getRound();

    public abstract Ship createShip();

    public abstract List<Alien> createAliens();

    public abstract List<Wall> createWalls();

    public abstract List<CoverWall> createCoverWalls();

}
