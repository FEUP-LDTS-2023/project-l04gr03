package SpaceInvaders.Controller

import SpaceInvaders.Controller.Game.AlienController
import SpaceInvaders.Controller.Game.AlienShipController
import SpaceInvaders.Controller.Game.ArenaController
import SpaceInvaders.Controller.Game.CollectableController
import SpaceInvaders.Controller.Game.ProjectileController
import SpaceInvaders.Controller.Game.ShipController
import SpaceInvaders.Controller.Sound.SoundManager
import SpaceInvaders.Game
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Model.Game.ArenaModifier
import SpaceInvaders.Model.Game.Collectables.Collectable
import SpaceInvaders.Model.Game.Collectables.HealthCollectable
import SpaceInvaders.Model.Game.Element
import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.AlienShip
import SpaceInvaders.Model.Game.RegularGameElements.AttackingElement
import SpaceInvaders.Model.Game.RegularGameElements.CoverWall
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Game.RegularGameElements.Wall
import SpaceInvaders.Model.Position
import SpaceInvaders.Model.Sound.Sound_Options
import SpaceInvaders.State.GameStates
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import org.mockito.MockedStatic
import org.mockito.Mockito
import spock.lang.Specification

class ArenaControllerTests extends Specification{

    def "get Ship controller"(){
        given:
            def arena = new ArenaController(Mock(Arena))
            def shipController = new ShipController(Mock(Arena))
        when:
            arena.setShipController(shipController)
        then:
            arena.getShipController() == shipController
    }

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

    def "Cover Wall hit by projectile"(){
        given:
            def arenaController = new ArenaController(Mock(Arena))
            def arenaControllerSpy = Spy(arenaController)
            def alien = Mock(Alien.class)
            alien.getDamagePerShot() >> 10
            def projectile = new Projectile(new Position(2,2),alien )
            def projectileSpy = Spy(projectile)
            def coverWall = new CoverWall(new Position(2,2),10)
            def coverWallSpy = Spy(coverWall)

        when:
            arenaControllerSpy.coverWallHitByProjectile(coverWallSpy, projectileSpy)
        then:
            1 * coverWallSpy.decreaseHealth(projectileSpy.getElement().getDamagePerShot())
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

    def "RemoveDestroyedElements"(){
        def arenaController = Spy(ArenaController.class)
        def alienController = Mock(AlienController.class)
        def alienShipController = Mock(AlienShipController.class)
        def arena = Mock(Arena.class)
        def alien = Mock(Alien.class)
        def alienShip = Mock(AlienShip.class)
        def coverWall = Mock(CoverWall.class)
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
        List<CoverWall> coverWalls = new ArrayList<>(Arrays.asList(coverWall))
        arenaController.getAlienController() >> alienController
        arenaController.getAlienShipController() >> alienShipController
        arenaController.getModel() >> arena
        arena.getCoverWalls() >> coverWalls
        arena.getAliens() >> aliens
        arena.getAlienShip() >> alienShip
        when:
        arenaController.removeDestroyedElements()
        then:
        1 * alienController.removeDestroyedAliens()
        1 * arenaController.removeDestroyedCoverWalls()
        1 * alienShipController.removeAlienShip()
    }

    def "CheckCollisions"(){
        def arenaController = Spy(ArenaController.class)
        def arena = Mock(Arena.class)
        def wall = Mock(Wall.class)
        wall.getPosition() >> Mock(Position.class)
        def coverWall = Mock(CoverWall.class)
        coverWall.getPosition() >> Mock(Position.class)
        def ship = Mock(Ship.class)
        ship.getPosition() >> Mock(Position.class)
        def alien = Mock(Alien.class)
        alien.getPosition() >> Mock(Position.class)
        def alienShip = Mock(AlienShip.class)
        alienShip.getPosition() >> Mock(Position.class)
        def projectile = Mock(Projectile.class)
        projectile.getPosition() >> Mock(Position.class)
        def collectable = Mock(Collectable.class)
        collectable.getPosition() >> Mock(Position.class)
        arenaController.getModel() >> arena
        arenaController.getShipController() >> Mock(ShipController.class)
        arenaController.getAlienController() >> Mock(AlienController.class)
        arenaController.getAlienShipController() >> Mock(AlienShipController.class)
        List<Wall> walls = new ArrayList<>(Arrays.asList(wall))
        List<CoverWall> coverWalls = new ArrayList<>(Arrays.asList(coverWall))
        List<Alien> aliens = new ArrayList<>()
        List<Projectile> projectiles = new ArrayList<>(Arrays.asList(projectile))
        arena.getWalls() >> walls
        arena.getCoverWalls() >> coverWalls
        arena.getShip() >> ship
        arena.getAliens() >> aliens
        arena.getProjectiles() >> projectiles
        arena.getActiveCollectable() >> collectable
        arena.getAlienShip() >> alienShip
        when:
        arenaController.checkCollisions()
        then:
        1 * arenaController.projectileCollisionsWithWalls()
        1 * arenaController.projectileCollisionsWithShip()
        1 * arenaController.projectileCollisionsWithAliens()
        1 * arenaController.shipCollisionsWithCollectables()
        1 * arenaController.collectableCollisionsWithWalls()
        1 * arenaController.projectileCollisionsWithCoverWalls()
        1 * arenaController.projectileCollisionWithAlienShip()
    }

    def "set Timers"(){
        given:
            def arena = Mock(Arena.class)
            def arenaController = new ArenaController(arena)
            def shipController = new ShipController(arena)
            def alienController = new AlienController(arena)
            def collectableController = new CollectableController(arena)
            def alienShipController =  new AlienShipController(arena)
            shipController.setMovementTime(10)
            shipController.setShootingTime(10)
            alienShipController.setLastMovementTime(10)
            alienShipController.setLastAppearance(10)
            alienController.setLastMovementTime(10)
            alienController.setLastShotTime(10)
            collectableController.setGenerateCollectableTime(10)
            arenaController.setShipController(shipController)
            arenaController.setAlienController(alienController)
            arenaController.setAlienShipController(alienShipController)
            arenaController.setCollectableController(collectableController)
            arenaController.setPauseGameTime(20)

        when:
            arenaController.setTimers(100)

        then:
            shipController.getMovementTime() == 90
            shipController.getShootingTime() == 90
            alienShipController.getLastMovementTime() == 90
            alienShipController.getLastAppearance() == 90
            alienController.getLastMovementTime() == 90
            alienController.getLastShotTime() == 90
            collectableController.getGenerateCollectableTime() == 90
    }

    def "Ship collision collision with collectables"(){
        given:
            def soundManager = Mockito.mock(SoundManager.class)

        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)

            def arena = Mockito.mock(Arena.class)
            def ship = Mockito.mock(Ship.class)
            def collectable = new HealthCollectable(Mock(Position),Mock(Ship))
            def collectableSpy = Mockito.spy(collectable)
            def arenaModifier = Mockito.mock(ArenaModifier.class)
            def arenaController = new ArenaController(arena)
            def arenaControllerSpy = Mockito.spy(arenaController)
            def position = new Position(1,1)

            Mockito.when(arena.getShip()).thenReturn(ship)
            Mockito.when(arena.getActiveCollectable()).thenReturn(collectableSpy)
            Mockito.when(ship.getPosition()).thenReturn(position)
            Mockito.when(collectableSpy.getPosition()).thenReturn(position)
            Mockito.when(arenaControllerSpy.getModel()).thenReturn(arena)
            Mockito.when(arenaControllerSpy.getArenaModifier()).thenReturn(arenaModifier)

            when:
                arenaControllerSpy.shipCollisionsWithCollectables()

            then:
                Mockito.verify(arena,Mockito.times(2)).getActiveCollectable()
                Mockito.verify(arenaModifier, Mockito.times(1)).removeActiveCollectable()
                Mockito.verify(soundManager, Mockito.times(1)).playSound(Sound_Options.COLLECTABLE)
                Mockito.verify(collectableSpy, Mockito.times(1)).execute()
        }
    }

    def "Collectable collision with wall"(){
        given:
            def position = new Position(1,1)
            def arena = Mock(Arena.class)
            def arenaController = new ArenaController(arena)
            def arenaControllerSpy = Spy(arenaController)
            def wall = new Wall(position)
            def walls = Arrays.asList(wall)
            def collectable = new HealthCollectable(position, Mock(Ship))
            arena.getWalls() >> walls
            arena.getActiveCollectable() >> collectable

        when:
            arenaControllerSpy.collectableCollisionsWithWalls()

        then:
            1 * arena.setActiveCollectable(null)

    }

    def "Remove coverWall"(){
        given:
        def position = new Position(1,1)
        def arena = Mock(Arena.class)
        def arenaController = new ArenaController(arena)
        def arenaControllerSpy = Spy(arenaController)
        def CoverWall = new CoverWall(position,0)
        def CoverWalls = Arrays.asList(CoverWall)
        def arenaModifier = Mock(ArenaModifier)
        arena.getCoverWalls()>> CoverWalls
        arenaControllerSpy.setArenaModifier(arenaModifier)

        when:
        arenaControllerSpy.removeDestroyedCoverWalls()

        then:
        1 * arenaModifier.removeCoverWall(CoverWall)
    }

    def "Step need to update timers"(){
        given:
        def arenaController = Spy(ArenaController.class)
        def arena = Mock(Arena.class)
        def wall = Mock(Wall.class)
        wall.getPosition() >> Mock(Position.class)
        def coverWall = Mock(CoverWall.class)
        coverWall.getPosition() >> Mock(Position.class)
        def ship = Mock(Ship.class)
        ship.getPosition() >> Mock(Position.class)
        def alien = Mock(Alien.class)
        alien.getPosition() >> Mock(Position.class)
        def alienShip = Mock(AlienShip.class)
        alienShip.getPosition() >> Mock(Position.class)
        def projectile = Mock(Projectile.class)
        projectile.getPosition() >> Mock(Position.class)
        def collectable = Mock(Collectable.class)
        collectable.getPosition() >> Mock(Position.class)
        arenaController.getModel() >> arena
        def shipController = new ShipController(arena)
        def alienController = new AlienController(arena)
        def collectableController = new CollectableController(arena)
        def alienShipController =  new AlienShipController(arena)
        def projectileController = new ProjectileController(arena)
        arenaController.setShipController(shipController)
        arenaController.setAlienController(alienController)
        arenaController.setAlienShipController(alienShipController)
        arenaController.setCollectableController(collectableController)
        arenaController.setProjectileController(projectileController)
        List<Wall> walls = new ArrayList<>(Arrays.asList(wall))
        List<CoverWall> coverWalls = new ArrayList<>(Arrays.asList(coverWall))
        List<Alien> aliens = new ArrayList<>()
        List<Projectile> projectiles = new ArrayList<>(Arrays.asList(projectile))
        arena.getWalls() >> walls
        arena.getCoverWalls() >> coverWalls
        arena.getShip() >> ship
        arena.getAliens() >> aliens
        arena.getProjectiles() >> projectiles
        arena.getActiveCollectable() >> collectable
        arena.getAlienShip() >> alienShip
        arena.getRound() >> 1

        when:
            arenaController.setNeedToUpdateTimers(true)
            arenaController.step(Mock(Game), Mock(KeyStroke), 10)
        then:
            1 * arenaController.setTimers(10)
            !arenaController.isNeedToUpdateTimers()
    }

    def "Step key is escape"(){
        given:
            def arenaController = Spy(ArenaController.class)
            def arena = Mock(Arena.class)
            def wall = Mock(Wall.class)
            def game = Mock(Game.class)
            wall.getPosition() >> Mock(Position.class)
            def coverWall = Mock(CoverWall.class)
            coverWall.getPosition() >> Mock(Position.class)
            def ship = Mock(Ship.class)
            ship.getPosition() >> Mock(Position.class)
            def alien = Mock(Alien.class)
            alien.getPosition() >> Mock(Position.class)
            def alienShip = Mock(AlienShip.class)
            alienShip.getPosition() >> Mock(Position.class)
            def projectile = Mock(Projectile.class)
            projectile.getPosition() >> Mock(Position.class)
            def collectable = Mock(Collectable.class)
            collectable.getPosition() >> Mock(Position.class)
            arenaController.getModel() >> arena
            def shipController = new ShipController(arena)
            def alienController = new AlienController(arena)
            def collectableController = new CollectableController(arena)
            def alienShipController =  new AlienShipController(arena)
            def projectileController = new ProjectileController(arena)
            arenaController.setShipController(shipController)
            arenaController.setAlienController(alienController)
            arenaController.setAlienShipController(alienShipController)
            arenaController.setCollectableController(collectableController)
            arenaController.setProjectileController(projectileController)
            List<Wall> walls = new ArrayList<>(Arrays.asList(wall))
            List<CoverWall> coverWalls = new ArrayList<>(Arrays.asList(coverWall))
            List<Alien> aliens = new ArrayList<>()
            List<Projectile> projectiles = new ArrayList<>(Arrays.asList(projectile))
            arena.getWalls() >> walls
            arena.getCoverWalls() >> coverWalls
            arena.getShip() >> ship
            arena.getAliens() >> aliens
            arena.getProjectiles() >> projectiles
            arena.getActiveCollectable() >> collectable
            arena.getAlienShip() >> alienShip
            arena.getRound() >> 1

        when:
            arenaController.step(game, new KeyStroke(KeyType.Escape), 10)
        then:
            1 * game.setState(GameStates.PAUSE)
            arenaController.isNeedToUpdateTimers()
            arenaController.getPauseGameTime() == 10
    }

    def "Step key ship collision alien"(){
        given:
        def arenaController = Spy(ArenaController.class)
        def arena = Mock(Arena.class)
        def wall = Mock(Wall.class)
        def game = Mock(Game.class)
        wall.getPosition() >> Mock(Position.class)
        def coverWall = Mock(CoverWall.class)
        coverWall.getPosition() >> Mock(Position.class)
        def ship = Mock(Ship.class)
        ship.getPosition() >> Mock(Position.class)
        def alien = Mock(Alien.class)
        alien.getPosition() >> Mock(Position.class)
        def alienShip = Mock(AlienShip.class)
        alienShip.getPosition() >> Mock(Position.class)
        def projectile = Mock(Projectile.class)
        projectile.getPosition() >> Mock(Position.class)
        def collectable = Mock(Collectable.class)
        collectable.getPosition() >> Mock(Position.class)
        arenaController.getModel() >> arena
        def shipController = new ShipController(arena)
        def alienController = new AlienController(arena)
        def collectableController = new CollectableController(arena)
        def alienShipController =  new AlienShipController(arena)
        def projectileController = new ProjectileController(arena)
        arenaController.setShipController(shipController)
        arenaController.setAlienController(alienController)
        arenaController.setAlienShipController(alienShipController)
        arenaController.setCollectableController(collectableController)
        arenaController.setProjectileController(projectileController)
        List<Wall> walls = new ArrayList<>(Arrays.asList(wall))
        List<CoverWall> coverWalls = new ArrayList<>(Arrays.asList(coverWall))
        List<Alien> aliens = new ArrayList<>()
        List<Projectile> projectiles = new ArrayList<>(Arrays.asList(projectile))
        arena.getWalls() >> walls
        arena.getCoverWalls() >> coverWalls
        arena.getShip() >> ship
        arena.getAliens() >> aliens
        arena.getProjectiles() >> projectiles
        arena.getActiveCollectable() >> collectable
        arena.getAlienShip() >> alienShip
        arena.getRound() >> 1

        when:
            arenaController.shipCollidesWithAlien() >> true
            arenaController.step(game, Mock(KeyStroke), 10)
        then:
        1 * game.setState(GameStates.GAME_OVER)
    }

    def "Step no aliens"(){
        given:
        def arenaController = Spy(ArenaController.class)
        def arena = Mock(Arena.class)
        def wall = Mock(Wall.class)
        def game = Mock(Game.class)
        wall.getPosition() >> Mock(Position.class)
        def coverWall = Mock(CoverWall.class)
        coverWall.getPosition() >> Mock(Position.class)
        def ship = Mock(Ship.class)
        ship.getPosition() >> Mock(Position.class)
        def alien = Mock(Alien.class)
        alien.getPosition() >> Mock(Position.class)
        def alienShip = Mock(AlienShip.class)
        alienShip.getPosition() >> Mock(Position.class)
        def projectile = Mock(Projectile.class)
        projectile.getPosition() >> Mock(Position.class)
        def collectable = Mock(Collectable.class)
        collectable.getPosition() >> Mock(Position.class)
        arenaController.getModel() >> arena
        def shipController = new ShipController(arena)
        def alienController = new AlienController(arena)
        def collectableController = new CollectableController(arena)
        def alienShipController =  new AlienShipController(arena)
        def projectileController = new ProjectileController(arena)
        def shipControllerSpy = Spy(shipController)
        def alienControllerSpy = Spy(alienController)
        def collectableControllerSpy = Spy(collectableController)
        def alienShipControllerSpy = Spy(alienShipController)
        def projectileControllerSpy = Spy(projectileController)
        arenaController.setShipController(shipControllerSpy)
        arenaController.setAlienController(alienControllerSpy)
        arenaController.setAlienShipController(alienShipControllerSpy)
        arenaController.setCollectableController(collectableControllerSpy)
        arenaController.setProjectileController(projectileControllerSpy)
        List<Wall> walls = new ArrayList<>(Arrays.asList(wall))
        List<CoverWall> coverWalls = new ArrayList<>(Arrays.asList(coverWall))
        List<Alien> aliens = new ArrayList<>()
        List<Projectile> projectiles = new ArrayList<>(Arrays.asList(projectile))
        arena.getWalls() >> walls
        arena.getCoverWalls() >> coverWalls
        arena.getShip() >> ship
        arena.getAliens() >> aliens
        arena.getProjectiles() >> projectiles
        arena.getActiveCollectable() >> collectable
        arena.getAlienShip() >> alienShip
        arena.getRound() >> 1

        when:
            arenaController.step(game, Mock(KeyStroke), 10)
        then:
            1 * game.setState(GameStates.NEW_GAME_ROUND)
            1 * arenaController.checkCollisions();
            1 * arenaController.removeDestroyedElements()
            1 * shipControllerSpy.step(game,_,_)
            1 * alienControllerSpy.step(game,_,_)
            1 * alienShipControllerSpy.step(game, _, _)
            1 * projectileControllerSpy.step(game,_,_)
            1 * collectableControllerSpy.step(game,_,_)
    }

    def "Step ship destroyed"(){
        given:
        def arenaController = Spy(ArenaController.class)
        def arena = Mock(Arena.class)
        def wall = Mock(Wall.class)
        def game = Mock(Game.class)
        wall.getPosition() >> Mock(Position.class)
        def coverWall = Mock(CoverWall.class)
        coverWall.getPosition() >> Mock(Position.class)
        def ship = Mock(Ship.class)
        ship.getPosition() >> Mock(Position.class)
        ship.isDestroyed() >> true
        def alien = Mock(Alien.class)
        alien.getPosition() >> Mock(Position.class)
        def alienShip = Mock(AlienShip.class)
        alienShip.getPosition() >> Mock(Position.class)
        def projectile = Mock(Projectile.class)
        projectile.getPosition() >> Mock(Position.class)
        def collectable = Mock(Collectable.class)
        collectable.getPosition() >> Mock(Position.class)
        arenaController.getModel() >> arena
        def shipController = new ShipController(arena)
        def alienController = new AlienController(arena)
        def collectableController = new CollectableController(arena)
        def alienShipController =  new AlienShipController(arena)
        def projectileController = new ProjectileController(arena)
        def shipControllerSpy = Spy(shipController)
        def alienControllerSpy = Spy(alienController)
        def collectableControllerSpy = Spy(collectableController)
        def alienShipControllerSpy = Spy(alienShipController)
        def projectileControllerSpy = Spy(projectileController)
        arenaController.setShipController(shipControllerSpy)
        arenaController.setAlienController(alienControllerSpy)
        arenaController.setAlienShipController(alienShipControllerSpy)
        arenaController.setCollectableController(collectableControllerSpy)
        arenaController.setProjectileController(projectileControllerSpy)
        List<Wall> walls = new ArrayList<>(Arrays.asList(wall))
        List<CoverWall> coverWalls = new ArrayList<>(Arrays.asList(coverWall))
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
        List<Projectile> projectiles = new ArrayList<>(Arrays.asList(projectile))
        arena.getWalls() >> walls
        arena.getCoverWalls() >> coverWalls
        arena.getShip() >> ship
        arena.getAliens() >> aliens
        arena.getProjectiles() >> projectiles
        arena.getActiveCollectable() >> collectable
        arena.getAlienShip() >> alienShip
        arena.getRound() >> 1

        when:
        arenaController.step(game, Mock(KeyStroke), 10)
        then:
        1 * game.setState(GameStates.GAME_OVER)
        1 * arenaController.checkCollisions();
        1 * arenaController.removeDestroyedElements()
        1 * shipControllerSpy.step(game,_,_)
        1 * alienControllerSpy.step(game,_,_)
        1 * alienShipControllerSpy.step(game, _, _)
        1 * projectileControllerSpy.step(game,_,_)
        1 * collectableControllerSpy.step(game,_,_)
    }

    def "Step alien collides with cover wall"(){
        given:
        def arenaController = Spy(ArenaController.class)
        def arena = Mock(Arena.class)
        def wall = Mock(Wall.class)
        def game = Mock(Game.class)
        wall.getPosition() >> Mock(Position.class)
        def coverWall = Mock(CoverWall.class)
        coverWall.getPosition() >> Mock(Position.class)
        def ship = Mock(Ship.class)
        ship.getPosition() >> Mock(Position.class)
        def alien = Mock(Alien.class)
        alien.getPosition() >> Mock(Position.class)
        def alienShip = Mock(AlienShip.class)
        alienShip.getPosition() >> Mock(Position.class)
        def projectile = Mock(Projectile.class)
        projectile.getPosition() >> Mock(Position.class)
        def collectable = Mock(Collectable.class)
        collectable.getPosition() >> Mock(Position.class)
        arenaController.getModel() >> arena
        def shipController = new ShipController(arena)
        def alienController = new AlienController(arena)
        def collectableController = new CollectableController(arena)
        def alienShipController =  new AlienShipController(arena)
        def projectileController = new ProjectileController(arena)
        def shipControllerSpy = Spy(shipController)
        def alienControllerSpy = Spy(alienController)
        def collectableControllerSpy = Spy(collectableController)
        def alienShipControllerSpy = Spy(alienShipController)
        def projectileControllerSpy = Spy(projectileController)
        arenaController.setShipController(shipControllerSpy)
        arenaController.setAlienController(alienControllerSpy)
        arenaController.setAlienShipController(alienShipControllerSpy)
        arenaController.setCollectableController(collectableControllerSpy)
        arenaController.setProjectileController(projectileControllerSpy)
        List<Wall> walls = new ArrayList<>(Arrays.asList(wall))
        List<CoverWall> coverWalls = new ArrayList<>(Arrays.asList(coverWall))
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
        List<Projectile> projectiles = new ArrayList<>(Arrays.asList(projectile))
        arena.getWalls() >> walls
        arena.getCoverWalls() >> coverWalls
        arena.getShip() >> ship
        arena.getAliens() >> aliens
        arena.getProjectiles() >> projectiles
        arena.getActiveCollectable() >> collectable
        arena.getAlienShip() >> alienShip
        arena.getRound() >> 1
        arenaController.alienCollidesWithCoverWall() >> true

        when:
            arenaController.step(game, Mock(KeyStroke), 10)
        then:
            1 * game.setState(GameStates.GAME_OVER)
            1 * arenaController.checkCollisions();
            1 * arenaController.removeDestroyedElements()
            1 * shipControllerSpy.step(game,_,_)
            1 * alienControllerSpy.step(game,_,_)
            1 * alienShipControllerSpy.step(game, _, _)
            1 * projectileControllerSpy.step(game,_,_)
            1 * collectableControllerSpy.step(game,_,_)
    }
}
