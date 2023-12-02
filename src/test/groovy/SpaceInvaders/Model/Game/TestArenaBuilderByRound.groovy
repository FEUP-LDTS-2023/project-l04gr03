package SpaceInvaders.Model.Game


import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.AlienShip
import SpaceInvaders.Model.Game.RegularGameElements.CoverWall
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Game.RegularGameElements.Wall
import SpaceInvaders.Model.Position
import spock.lang.Specification

class TestArenaBuilderByRound extends Specification{

    def 'ArenaBuilder'(){
        given:
        int round = 1
        when:
        ArenaBuilderByRound arenaBuilderByRound = new ArenaBuilderByRound(round)
        then:
        arenaBuilderByRound.getRound() == round
        !arenaBuilderByRound.getArenaLines().isEmpty()
    }

    def 'ReadArenaLines'(){
        given:
        int round = 1;
        ArenaBuilderByRound arenaBuilderByRound = new ArenaBuilderByRound(1)
        when:
        List<String> arenaLines = arenaBuilderByRound.getArenaLines()
        then:
        arenaLines.size() == 30
        arenaLines.get(0).length() == arenaLines.get(arenaLines.size() - 1).length()
        arenaLines.get(19).length() == arenaLines.get(arenaLines.size() - 1).length()
        arenaLines.get(arenaLines.size() - 1).length() == 74
    }

    def 'CreateShip'(){
        given:
        ArenaBuilderByRound arenaBuilderByRound = new ArenaBuilderByRound(1)
        when:
        Ship ship = arenaBuilderByRound.createShip()
        then:
        ship.getPosition().equals(new Position(35,27))
        ship.getHealth() == 1 * arenaBuilderByRound.getBaseShipHealth()
        ship.getDamagePerShot() == 1 * arenaBuilderByRound.getBaseShipDamage()
    }

    def 'CreateAliens'(){
        given:
        int round = 1
        ArenaBuilderByRound arenaBuilderByRound = new ArenaBuilderByRound(round)
        when:
        List<Alien> aliens = arenaBuilderByRound.createAliens()
        then:
        aliens.size() == 64
        aliens.get(0).getPosition().equals(new Position(13,10))
        aliens.get(0).getType() == 0
        aliens.get(3).getPosition().equals(new Position(13,16))
        aliens.get(3).getType() == 0
        aliens.get(29).getPosition().equals(new Position(34,12))
        aliens.get(29).getType() == 1
        aliens.get(60).getPosition().equals(new Position(58,10))
        aliens.get(60).getType() == 0
        aliens.get(aliens.size() - 1).getPosition().equals(new Position(58,16))
        aliens.get(0).getHealth() == arenaBuilderByRound.getBaseAlienHealth() * (int) Math.pow(2,round - 1)
        aliens.get(0).getDamagePerShot() == arenaBuilderByRound.getBaseAlienDamage() * (int) Math.pow(2,round - 1)
        aliens.get(0).getScore() == arenaBuilderByRound.getBaseAlienScore() * round
    }

    def 'CreateWalls'(){
        given:
        ArenaBuilderByRound arenaBuilderByRound = new ArenaBuilderByRound(1)
        when:
        List<Wall> walls = arenaBuilderByRound.createWalls()
        then:
        walls.size() == 204
        walls.get(0).getPosition().equals(new Position(0,0))
        walls.get(100).getPosition().equals(new Position(36,0))
        walls.get(walls.size() - 1).getPosition().equals(new Position(73,29))
    }

    def 'CreateCoverWalls'(){
        given:
        int round = 1
        ArenaBuilderByRound arenaBuilderByRound = new ArenaBuilderByRound(round)
        when:
        List<CoverWall> coverWalls = arenaBuilderByRound.createCoverWalls()
        then:
        coverWalls.size() == 24;
        coverWalls.get(0).getPosition().equals(new Position(14,24))
        coverWalls.get(1).getPosition().equals(new Position(14,25))
        coverWalls.get(0).getHealth() == arenaBuilderByRound.getBaseCoverWallHealth() * round * round
    }

}
