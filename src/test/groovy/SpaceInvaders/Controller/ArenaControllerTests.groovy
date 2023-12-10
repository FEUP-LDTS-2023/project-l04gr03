package SpaceInvaders.Controller

import SpaceInvaders.Controller.Game.AlienController
import SpaceInvaders.Controller.Game.AlienShipController
import SpaceInvaders.Controller.Game.ArenaController
import SpaceInvaders.Controller.Game.ShipController
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Model.Game.ArenaModifier
import SpaceInvaders.Model.Game.Element
import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.AlienShip
import SpaceInvaders.Model.Game.RegularGameElements.AttackingElement
import SpaceInvaders.Model.Game.RegularGameElements.CoverWall
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Game.RegularGameElements.Wall
import SpaceInvaders.Model.Position
import spock.lang.Specification

class ArenaControllerTests extends Specification{

    def "Collision between - False"(){
        given:
        def arenaController = Spy(ArenaController.class)
        def element1 = Mock(Element.class)
        def element2 = Mock(Element.class)
        element1.getPosition() >> new Position(24,14)
        element2.getPosition() >> new Position(30,14)
        when:
        boolean isColliding = arenaController.collisionBetween(element1,element2)
        then:
        !isColliding
    }

    def "Collision between - True"(){
        given:
        def arenaController = Spy(ArenaController.class)
        def element1 = Mock(Element.class)
        def element2 = Mock(Element.class)
        element1.getPosition() >> new Position(10,27)
        element2.getPosition() >> new Position(10,27)
        when:
        boolean isColliding = arenaController.collisionBetween(element1,element2)
        then:
        isColliding
    }

    def "ShipCollidesWithAlien - True"(){
      given:
      def arenaController = Spy(ArenaController.class)
      def arena = Mock(Arena.class)
      def arenaModifier = Mock(ArenaModifier.class)
      def ship = Mock(Ship.class)
      def alien1 = Mock(Alien.class)
      def alien2 = Mock(Alien.class)
      def alien3 = Mock(Alien.class)
      def position = Mock(Position.class)
      List<Alien> aliens = new ArrayList<>()
      alien1.getPosition() >> position
      alien2.getPosition() >> position
      alien3.getPosition() >> position
      ship.getPosition() >> position
      aliens.add(alien1)
      aliens.add(alien2)
      aliens.add(alien3)
      arenaController.getModel() >> arena
      arenaController.getArenaModifier() >> arenaModifier
      arena.getShip() >> ship
      arena.getAliens() >> aliens
      when:
      arenaController.collisionBetween(ship, alien1) >> true
      arenaController.collisionBetween(ship, alien2) >> false
      arenaController.collisionBetween(ship, alien3) >> false
      boolean isColliding = arenaController.shipCollidesWithAlien()
      then:
      isColliding
    }


    def "ShipCollidesWithAlien - False"(){
        given:
        def arenaController = Spy(ArenaController.class)
        def arena = Mock(Arena.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def ship = Mock(Ship.class)
        def alien1 = Mock(Alien.class)
        def alien2 = Mock(Alien.class)
        def alien3 = Mock(Alien.class)
        def alien4 = Mock(Alien.class)
        def position = Mock(Position.class)
        List<Alien> aliens = new ArrayList<>()
        alien1.getPosition() >> position
        alien2.getPosition() >> position
        alien3.getPosition() >> position
        alien4.getPosition() >> position
        ship.getPosition() >> position
        aliens.add(alien1)
        aliens.add(alien2)
        aliens.add(alien3)
        aliens.add(alien4)
        arenaController.getModel() >> arena
        arenaController.getArenaModifier() >> arenaModifier
        arena.getShip() >> ship
        arena.getAliens() >> aliens
        when:
        arenaController.collisionBetween(ship,alien1) >> false
        arenaController.collisionBetween(ship,alien2) >> false
        arenaController.collisionBetween(ship, alien3) >> false
        arenaController.collisionBetween(ship, alien4) >> false
        boolean isColliding = arenaController.shipCollidesWithAlien()
        then:
        !isColliding
    }

    def "AlienCollidesWithCoverWalls - True"(){
        given:
        def arenaController = Spy(ArenaController.class)
        def arena = Mock(Arena.class)
        def coverWall1 = Mock(CoverWall.class)
        def coverWall2 = Mock(CoverWall.class)
        def alien1 = Mock(Alien.class)
        def alien2 = Mock(Alien.class)
        def alien3 = Mock(Alien.class)
        def position = Mock(Position.class)
        alien1.getPosition() >> position
        alien2.getPosition() >> position
        alien3.getPosition() >> position
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien1,alien2,alien3))
        List<CoverWall> coverWalls = new ArrayList<>(Arrays.asList(coverWall1,coverWall2))
        arenaController.getModel() >> arena
        arena.getAliens() >> aliens
        arena.getCoverWalls() >> coverWalls
        when:
        arenaController.collisionBetween(alien1,coverWall1) >> false
        arenaController.collisionBetween(alien1,coverWall2) >> false
        arenaController.collisionBetween(alien2,coverWall1) >> false
        arenaController.collisionBetween(alien2,coverWall2) >> true
        arenaController.collisionBetween(alien3,coverWall1) >> false
        arenaController.collisionBetween(alien3,coverWall2) >> false
        then:
        arenaController.alienCollidesWithCoverWall()
    }

    def "AlienCollidesWithCoverWalls - False"(){
        given:
        def arenaController = Spy(ArenaController.class)
        def arena = Mock(Arena.class)
        def coverWall1 = Mock(CoverWall.class)
        def coverWall2 = Mock(CoverWall.class)
        def alien1 = Mock(Alien.class)
        def alien2 = Mock(Alien.class)
        def alien3 = Mock(Alien.class)
        def position = Mock(Position.class)
        alien1.getPosition() >> position
        alien2.getPosition() >> position
        alien3.getPosition() >> position
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien1,alien2,alien3))
        List<CoverWall> coverWalls = new ArrayList<>(Arrays.asList(coverWall1,coverWall2))
        arenaController.getModel() >> arena
        arena.getAliens() >> aliens
        arena.getCoverWalls() >> coverWalls
        when:
        arenaController.collisionBetween(alien1,coverWall1) >> false
        arenaController.collisionBetween(alien1,coverWall2) >> false
        arenaController.collisionBetween(alien2,coverWall1) >> false
        arenaController.collisionBetween(alien2,coverWall2) >> false
        arenaController.collisionBetween(alien3,coverWall1) >> false
        arenaController.collisionBetween(alien3,coverWall2) >> false
        then:
        !arenaController.alienCollidesWithCoverWall()
    }

    def "ProjectileCollisionsWithCoverWalls - Collision detected"(){
        def arenaController = Spy(ArenaController.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def arena = Mock(Arena.class)
        def wall1 = Mock(Wall.class)
        def wall2 = Mock(Wall.class)
        def wall3 = Mock(Wall.class)
        def wall4 = Mock(Wall.class)
        def projectile1 = Mock(Projectile.class)
        projectile1.getElement() >> Mock(AttackingElement.class)
        List<Wall> walls = new ArrayList<>(Arrays.asList(wall1,wall2,wall3,wall4))
        List<Projectile> projectiles = new ArrayList<>(Arrays.asList(projectile1))
        arenaController.getArenaModifier() >> arenaModifier
        arenaController.getModel() >> arena
        arena.getWalls() >> walls
        arena.getProjectiles() >> projectiles
        when:
        arenaController.collisionBetween(wall1,projectile1) >> true
        arenaController.collisionBetween(wall2,projectile1) >> false
        arenaController.collisionBetween(wall3,projectile1) >> false
        arenaController.collisionBetween(wall4,projectile1) >> false
        arenaController.projectileCollisionsWithWalls()
        then:
        1 * arenaModifier.removeProjectile(projectile1)
    }

    def "ProjectileCollisionsWithCoverWalls - Collision detected"(){
        def arenaController = Spy(ArenaController.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def arena = Mock(Arena.class)
        def coverWall1 = Mock(CoverWall.class)
        def coverWall2 = Mock(CoverWall.class)
        def projectile1 = Mock(Projectile.class)
        def projectile2 = Mock(Projectile.class)
        def projectile3 = Mock(Projectile.class)
        projectile1.getElement() >> Mock(AttackingElement.class)
        projectile2.getElement() >> Mock(AttackingElement.class)
        List<CoverWall> coverWalls = new ArrayList<>(Arrays.asList(coverWall1,coverWall2))
        List<Projectile> projectiles = new ArrayList<>(Arrays.asList(projectile1,projectile2,projectile3))
        arenaController.getArenaModifier() >> arenaModifier
        arenaController.getModel() >> arena
        arena.getCoverWalls() >> coverWalls
        arena.getProjectiles() >> projectiles
        when:
        arenaController.collisionBetween(coverWall1,projectile1) >> true
        arenaController.collisionBetween(coverWall1,projectile2) >> false
        arenaController.collisionBetween(coverWall1,projectile3) >> false
        arenaController.collisionBetween(coverWall2,projectile1) >> false
        arenaController.collisionBetween(coverWall2,projectile2) >> true
        arenaController.collisionBetween(coverWall2,projectile3) >> false
        arenaController.projectileCollisionsWithCoverWalls()
        then:
        1 * arenaController.coverWallHitByProjectile(coverWall1,projectile1)
        1 * arenaController.coverWallHitByProjectile(coverWall2,projectile2)
        1 * arenaModifier.removeProjectile(projectile1)
        1 * arenaModifier.removeProjectile(projectile2)
    }

    def "ProjectileCollisionsWithShip - Collision detected"(){
        def arenaController = Spy(ArenaController.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def shipController = Mock(ShipController.class)
        def arena = Mock(Arena.class)
        def ship = Mock(Ship.class)
        def projectile1 = Mock(Projectile.class)
        def projectile2 = Mock(Projectile.class)
        List<Projectile> projectiles = new ArrayList<>(Arrays.asList(projectile1,projectile2))
        arenaController.getArenaModifier() >> arenaModifier
        arenaController.getShipController() >> shipController
        arenaController.getModel() >> arena
        arena.getShip() >> ship
        arena.getProjectiles() >> projectiles
        when:
        arenaController.collisionBetween(ship,projectile1) >> true
        arenaController.collisionBetween(ship,projectile2) >> false
        arenaController.projectileCollisionsWithShip()
        then:
        1 * shipController.hitByProjectile(projectile1)
        1 * arenaModifier.removeProjectile(projectile1)
    }

    def "ProjectileCollisionsWithShip - No collision detected"(){
        def arenaController = Spy(ArenaController.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def shipController = Mock(ShipController.class)
        def arena = Mock(Arena.class)
        def ship = Mock(Ship.class)
        def projectile1 = Mock(Projectile.class)
        def projectile2 = Mock(Projectile.class)
        List<Projectile> projectiles = new ArrayList<>(Arrays.asList(projectile1,projectile2))
        arenaController.getArenaModifier() >> arenaModifier
        arenaController.getShipController() >> shipController
        arenaController.getModel() >> arena
        arena.getShip() >> ship
        arena.getProjectiles() >> projectiles
        when:
        arenaController.collisionBetween(ship,projectile1) >> false
        arenaController.collisionBetween(ship,projectile2) >> false
        arenaController.projectileCollisionsWithShip()
        then:
        0 * shipController.hitByProjectile(_)
        0 * arenaModifier.removeProjectile(_)
    }

    def "ProjectileCollisionsWithAliens - Collision detected"(){
        def arenaController = Spy(ArenaController.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def alienController = Mock(AlienController.class)
        def arena = Mock(Arena.class)
        def alien1 = Mock(Alien.class)
        def alien2 = Mock(Alien.class)
        def projectile1 = Mock(Projectile.class)
        def projectile2 = Mock(Projectile.class)
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien1,alien2))
        List<Projectile> projectiles = new ArrayList<>(Arrays.asList(projectile1,projectile2))
        arenaController.getArenaModifier() >> arenaModifier
        arenaController.getAlienController() >> alienController
        arenaController.getModel() >> arena
        arena.getAliens() >> aliens
        arena.getProjectiles() >> projectiles
        when:
        arenaController.collisionBetween(alien1,projectile1) >> false
        arenaController.collisionBetween(alien1,projectile2) >> true
        arenaController.collisionBetween(alien2,projectile1) >> false
        arenaController.collisionBetween(alien2,projectile2) >> false
        arenaController.projectileCollisionsWithAliens()
        then:
        1 * alienController.hitByProjectile(alien1,projectile2)
        1 * arenaModifier.removeProjectile(projectile2)
    }

    def "ProjectileCollisionsWithAliens - No Collision detected"(){
        def arenaController = Spy(ArenaController.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def alienController = Mock(AlienController.class)
        def arena = Mock(Arena.class)
        def alien1 = Mock(Alien.class)
        def alien2 = Mock(Alien.class)
        def projectile1 = Mock(Projectile.class)
        def projectile2 = Mock(Projectile.class)
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien1,alien2))
        List<Projectile> projectiles = new ArrayList<>(Arrays.asList(projectile1,projectile2))
        arenaController.getArenaModifier() >> arenaModifier
        arenaController.getAlienController() >> alienController
        arenaController.getModel() >> arena
        arena.getAliens() >> aliens
        arena.getProjectiles() >> projectiles
        when:
        arenaController.collisionBetween(alien1,projectile1) >> false
        arenaController.collisionBetween(alien1,projectile2) >> false
        arenaController.collisionBetween(alien2,projectile1) >> false
        arenaController.collisionBetween(alien2,projectile2) >> false
        arenaController.projectileCollisionsWithAliens()
        then:
        0 * alienController.hitByProjectile(_,_)
        0 * arenaModifier.removeProjectile(_)
    }

    def "ProjectileCollisionsWithAlienShip - Collision detected"(){
        def arenaController = Spy(ArenaController.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def alienShipController = Mock(AlienShipController.class)
        def arena = Mock(Arena.class)
        def alienShip = Mock(AlienShip.class)
        def projectile1 = Mock(Projectile.class)
        def projectile2 = Mock(Projectile.class)
        def projectile3 = Mock(Projectile.class)
        List<Projectile> projectiles = new ArrayList<>(Arrays.asList(projectile1,projectile2,projectile3))
        arenaController.getArenaModifier() >> arenaModifier
        arenaController.getAlienShipController() >> alienShipController
        arenaController.getModel() >> arena
        arena.getAlienShip() >> alienShip
        arena.getProjectiles() >> projectiles
        when:
        arenaController.collisionBetween(projectile1,alienShip) >> false
        arenaController.collisionBetween(projectile2,alienShip) >> true
        arenaController.collisionBetween(projectile3,alienShip) >> false
        arenaController.projectileCollisionWithAlienShip()
        then:
        1 * alienShipController.hitByProjectile(alienShip,projectile2)
        1 * arenaModifier.removeProjectile(projectile2)
    }

    def "ProjectileCollisionsWithAlienShip - No Collision detected"(){
        def arenaController = Spy(ArenaController.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def alienShipController = Mock(AlienShipController.class)
        def arena = Mock(Arena.class)
        def alienShip = Mock(AlienShip.class)
        def projectile1 = Mock(Projectile.class)
        def projectile2 = Mock(Projectile.class)
        def projectile3 = Mock(Projectile.class)
        List<Projectile> projectiles = new ArrayList<>(Arrays.asList(projectile1,projectile2,projectile3))
        arenaController.getArenaModifier() >> arenaModifier
        arenaController.getAlienShipController() >> alienShipController
        arenaController.getModel() >> arena
        arena.getAlienShip() >> alienShip
        arena.getProjectiles() >> projectiles
        when:
        arenaController.collisionBetween(projectile1,alienShip) >> false
        arenaController.collisionBetween(projectile2,alienShip) >> false
        arenaController.collisionBetween(projectile3,alienShip) >> false
       arenaController.projectileCollisionWithAlienShip()
        then:
        0 * alienShipController.hitByProjectile(_,_)
        0 * arenaModifier.removeProjectile(_)
    }
}
