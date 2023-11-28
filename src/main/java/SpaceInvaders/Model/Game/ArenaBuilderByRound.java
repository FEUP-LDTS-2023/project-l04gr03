package SpaceInvaders.Model.Game;

import SpaceInvaders.Model.Game.RegularGameElements.*;
import SpaceInvaders.Model.Game.RegularGameElements.*;
import SpaceInvaders.Model.Position;

import java.io.BufferedReader;
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
        URL resource = ArenaBuilderByRound.class.getResource("/rounds/round" + round + ".txt");
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        arenaLines = readArenaLines(br);
    }

    public List<String> readArenaLines(BufferedReader br) throws IOException {
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

    public int getRound(){
        return round;
    }

    public List<String> getArenaLines(){
        return arenaLines;
    }

    private int getAliensAttackLine(){
        int i = 0;
        for(i = arenaLines.size() - 1; i >= 0; i--){
            if(arenaLines.get(i).indexOf('A') != -1){
                return i;
            }
        }
        return i;
    }

    @Override
    public Ship createShip() {
        for(int x = 0; x < arenaLines.get(0).length(); x++){
            for(int y = 0; y < arenaLines.size(); y++){
                if(arenaLines.get(y).charAt(x) == 'S'){
                    return new Ship(new Position(x,y),round * getBaseShipHealth(), round * getBaseShipDamage());
                }
            }
        }
        return null;
    }

    @Override
    public List<Alien> createAliens() {
        List<Alien> aliens = new ArrayList<>();
        AlienState alienState;
        for(int x = 0; x < arenaLines.get(0).length(); x++){
            int type = 0;
            for (int y = 0; y < arenaLines.size(); y++){
                if(arenaLines.get(y).charAt(x) == 'A'){
                    if(y == getAliensAttackLine()){
                        aliens.add(new Alien(new Position(x,y),getBaseAlienHealth() * (int) Math.pow(2,round - 1),getBaseAlienDamage() * (int) Math.pow(2, round - 1),getBaseAlienScore() * round,AlienState.ATTACKING, type));
                        type++;
                    }
                    else{
                        aliens.add(new Alien(new Position(x,y),getBaseAlienHealth() * (int) Math.pow(2,round - 1),getBaseAlienDamage() * (int) Math.pow(2, round - 1),getBaseAlienScore() * round,AlienState.PASSIVE,type));
                        type++;
                    }
                }

                if(type >= 3 ){
                    type = 0;
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
                    coverWalls.add(new CoverWall(new Position(x,y),getBaseCoverWallHealth() * (round * round)));
                }
            }
        }
        return coverWalls;
    }

    @Override
    public AlienShip createAlienShip() {
        return new AlienShip(new Position(-73,5),getBaseAlienHealth() * round, getBaseAlienScore() * round);
    }
}
