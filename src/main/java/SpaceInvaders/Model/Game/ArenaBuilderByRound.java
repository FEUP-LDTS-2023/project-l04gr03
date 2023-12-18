package SpaceInvaders.Model.Game;

import SpaceInvaders.Model.Game.RegularGameElements.*;
import SpaceInvaders.Model.Menu.OnlyTextMenu;
import SpaceInvaders.Model.Position;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ArenaBuilderByRound extends ArenaBuilder {

    private int round;

    private List<String> arenaLines;

    public ArenaBuilderByRound(int round) throws IOException {
        this.round = round;
        if(round <= 5){
            Path resource = new File(ArenaBuilderByRound.class.getResource("/rounds/round" + round + ".txt").getFile()).toPath();
            Reader fileReader = Files.newBufferedReader(resource, Charset.defaultCharset());
            BufferedReader br = new BufferedReader(fileReader);
            arenaLines = readArenaLines(br);
        }
        else{
            Path resource = new File(ArenaBuilderByRound.class.getResource("/rounds/round3.txt").getFile()).toPath();
            Reader fileReader = Files.newBufferedReader(resource, Charset.defaultCharset());
            BufferedReader br = new BufferedReader(fileReader);
            arenaLines = readArenaLines(br);
        }
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

    @Override
    public int getRound(){
        return round;
    }

    public void setArenaLines(List<String> arenaLines) {
        this.arenaLines = arenaLines;
    }

    public List<String> getArenaLines(){
        return arenaLines;
    }

    private int getAliensAttackLine(){
        int i = 0;
        for(i = arenaLines.size() - 1; i >= 0; i--){
            if(arenaLines.get(i).indexOf('A') != -1){
                break;
            }
        }
        return i;
    }

    @Override
    public Ship createShip() {
        for(int x = 0; x < arenaLines.get(0).length(); x++){
            for(int y = 0; y < arenaLines.size(); y++){
                if(arenaLines.get(y).charAt(x) == 'S'){
                    return new Ship(new Position(x,y),getBaseShipHealth(), getBaseShipDamage());
                }
            }
        }
        return null;
    }

    @Override
    public List<Alien> createAliens() {
        List<Alien> aliens = new ArrayList<>();
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
                    coverWalls.add(new CoverWall(new Position(x,y),getBaseCoverWallHealth()));
                }
            }
        }
        return coverWalls;
    }

}
