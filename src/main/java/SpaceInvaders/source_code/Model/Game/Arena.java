package SpaceInvaders.source_code.Model.Game;

import SpaceInvaders.source_code.Model.Game.Collectables.Collectable;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Alien;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.CoverWall;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Wall;

import java.util.List;

public class Arena {

    private int width;

    private int height;

    private Ship ship;

    private List<Alien> aliens;

    private List<Wall> walls;

    private List<CoverWall> coverWalls;

    private Collectable collectable;


    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Ship getShip() {
        return ship;
    }

    public List<Alien> getAliens() {
        return aliens;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<CoverWall> getCoverWalls() {
        return coverWalls;
    }

    public Collectable getCollectable() {
        return collectable;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setAliens(List<Alien> aliens) {
        this.aliens = aliens;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public void setCoverWalls(List<CoverWall> coverWalls) {
        this.coverWalls = coverWalls;
    }

    public void setCollectable(Collectable collectable) {
        this.collectable = collectable;
    }
}
