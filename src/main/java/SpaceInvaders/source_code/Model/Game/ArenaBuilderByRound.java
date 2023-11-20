package SpaceInvaders.source_code.Model.Game;

import SpaceInvaders.source_code.Model.Game.Collectables.Collectable;
import SpaceInvaders.source_code.Model.Game.Collectables.CollectableFactory;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Alien;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.CoverWall;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship;
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Wall;
import SpaceInvaders.source_code.Model.Position;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ArenaBuilderByRound extends ArenaBuilder {

    private int round;

    private List<String> arenaLines;

    public ArenaBuilderByRound(int round) throws IOException {
        this.round = round;
        URL resource = ArenaBuilderByRound.class.getResource("/Rounds/round" + round + ".txt");
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        arenaLines = readArenaLines(br);
    }

    private List<String> readArenaLines(BufferedReader br) throws IOException {
        List<String> arenaLines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            arenaLines.add(line);
        return arenaLines;
    }

    @Override
    public int getWidth() {
        return arenaLines.get(0).length();
    }

    @Override
    public int getHeight() {
        return arenaLines.size();
    }

    @Override
    public Ship createShip() {
        for(int x = 0; x < arenaLines.get(0).length(); x++){
            for(int y = 0; y < arenaLines.size(); y++){
                if(arenaLines.get(x).charAt(y) == 'S'){
                    return new Ship(new Position(x,y),round * getBaseShipHealth(), round* getBaseShipDamage());
                }
            }
        }
        return null;
    }

    @Override
    public List<Alien> createAliens() {
        List<Alien> aliens = new ArrayList<>();
        for(int x = 0; x < arenaLines.get(0).length(); x++){
            for (int y = 0; y < arenaLines.size(); y++){
                if(arenaLines.get(y).charAt(x) == 'A'){
                    aliens.add(new Alien(new Position(x,y),getBaseAlienHealth() * 2^(round - 1),getBaseAlienHealth() * 2^(round - 1),getBaseAlienScore()*round));
                }
            }
        }
        return aliens;
    }

    @Override
    public List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for(int x = 0; x < arenaLines.get(0).length(); x++){
            for(int y = 0; y < arenaLines.size(); y++){
                if(arenaLines.get(y).charAt(x) == '#'){
                    walls.add(new Wall(new Position(x,y)));
                }
            }
        }
        return walls;
    }

    @Override
    public List<CoverWall> createCoverWalls() {
        List<CoverWall> coverWalls = new ArrayList<>();
        for(int x = 0; x < arenaLines.get(0).length(); x++){
            for(int y = 0; y < arenaLines.size(); y++){
                if(arenaLines.get(y).charAt(x) == 'W'){
                    coverWalls.add(new CoverWall(new Position(x,y),getBaseCoverWallHealth()*(round^2)));
                }
            }
        }
        return coverWalls;
    }

    @Override
    public Collectable createCollectable(Position position,String type, int multiplier) {
        CollectableFactory collectableFactory = new CollectableFactory(position,type,multiplier);
        Collectable collectable = collectableFactory.createCollectable();
        return collectable;
    }
}
