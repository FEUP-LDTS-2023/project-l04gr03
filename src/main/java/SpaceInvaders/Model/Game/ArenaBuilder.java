package SpaceInvaders.Model.Game;

import SpaceInvaders.Model.Game.RegularGameElements.*;
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

    private int baseAlienShipHealth;

    private int baseAlienShipScore;


    public Arena buildArena(){
        Arena newArena = new Arena(getWidth(),getHeight());
        baseShipHealth = 100;
        baseShipDamage = 50;
        baseAlienHealth = 20;
        baseAlienDamage = 20;
        baseAlienScore = 20;
        baseCoverWallHealth = 100;
        baseAlienShipHealth = 200;
        baseAlienShipScore = 500;
        newArena.setShip(createShip());
        newArena.setAliens(createAliens());
        newArena.setWalls(createWalls());
        newArena.setCoverWalls(createCoverWalls());
        newArena.setAlienShip(createAlienShip());
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

    public int getBaseAlienShipHealth(){ return baseAlienShipHealth; }

    public int getBaseAlienShipScore() { return baseAlienShipScore; }

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract Ship createShip();

    public abstract List<Alien> createAliens();

    public abstract List<Wall> createWalls();

    public abstract List<CoverWall> createCoverWalls();

    public abstract AlienShip createAlienShip();
}
