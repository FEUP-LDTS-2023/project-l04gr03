package SpaceInvaders.source_code.Model.Game

import SpaceInvaders.source_code.Model.Game.RegularGameElements.Alien
import SpaceInvaders.source_code.Model.Game.RegularGameElements.AlienState
import SpaceInvaders.source_code.Model.Game.RegularGameElements.CoverWall
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship
import SpaceInvaders.source_code.Model.Position
import spock.lang.Specification

class TestArenaModifier extends Specification{

    def "RemoveAlien"(){
        given:
        Arena arena = new Arena(75,30)
        List<Alien> aliens = new ArrayList<>();
        aliens.add(new Alien(new Position(13,11),30,20,20,AlienState.PASSIVE))
        aliens.add(new Alien(new Position(13,13),30,20,20,AlienState.PASSIVE))
        aliens.add(new Alien(new Position(13,15),30,20,20,AlienState.ATTACKING))
        aliens.add(new Alien(new Position(16,11),30,20,20,AlienState.ATTACKING))
        arena.setAliens(aliens);
        ArenaModifier arenaModifier = new ArenaModifier(arena)
        when:
        arenaModifier.removeAlien(aliens.get(3))
        then:
        arena.getAliens().size() == 3
        arena.getAliens().get(2).getAlienState() == AlienState.ATTACKING
        arena.getAliens().get(1).getAlienState() == AlienState.PASSIVE
        when:
        arenaModifier.removeAlien(aliens.get(2))
        then:
        arena.getAliens().size() == 2
        arena.getAliens().get(1).getAlienState() == AlienState.ATTACKING
    }

    def "RemoveCoverWall"(){
        given:
        Arena arena = new Arena(75,30)
        List<CoverWall> coverWalls = new ArrayList<>()
        coverWalls.add(new CoverWall(new Position(15,24),100))
        coverWalls.add(new CoverWall(new Position(45,24),100))
        arena.setCoverWalls(coverWalls)
        ArenaModifier arenaModifier = new ArenaModifier((arena))
        when:
        arenaModifier.removeCoverWall(coverWalls.get(0))
        then:
        arena.getCoverWalls().size() == 1
        arena.getCoverWalls().get(0).getPosition().equals(new Position(45,24))
    }

    def "AddProjectile"(){
        given:
        Arena arena = new Arena(75,30)
        List<Projectile> projectiles = new ArrayList<>()
        arena.setProjectiles(projectiles)
        Alien alien = Mock(Alien)
        Projectile projectile1 = new Projectile(new Position(13,15),alien,5)
        Projectile projectile2 = new Projectile(new Position(19,15),alien,5)
        ArenaModifier arenaModifier = new ArenaModifier(arena)
        when:
        arenaModifier.addProjectile(projectile1)
        arenaModifier.addProjectile(projectile2)
        then:
        arena.getProjectiles().size() == 2
    }

    def "RemoveProjectile"(){
        given:
        Arena arena = new Arena(75,30)
        List<Projectile> projectiles = new ArrayList<>()
        Alien alien = Mock(Alien)
        Ship ship = Mock(Ship)
        projectiles.add(new Projectile(new Position(13,15),alien,5))
        projectiles.add(new Projectile(new Position(35,26),ship,5))
        projectiles.add(new Projectile(new Position(10,10),ship,5))
        arena.setProjectiles(projectiles)
        ArenaModifier arenaModifier = new ArenaModifier((arena))
        when:
        arenaModifier.removeProjectile(projectiles.get(2))
        arenaModifier.removeProjectile(projectiles.get(0))
        then:
        arena.getProjectiles().size() == 1
        arena.getProjectiles().get(0).getPosition().equals(new Position(35,26))
        arena.getProjectiles().get(0).getSpeed() == 5
    }
}