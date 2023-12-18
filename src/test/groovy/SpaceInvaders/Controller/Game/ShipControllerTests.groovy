package SpaceInvaders.Controller.Game

import SpaceInvaders.Controller.Sound.SoundManager
import SpaceInvaders.Game
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Model.Game.ArenaModifier
import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Game.RegularGameElements.ShipMode
import SpaceInvaders.Model.Position
import SpaceInvaders.Model.Sound.Sound_Options
import org.mockito.MockedStatic
import org.mockito.Mockito
import spock.lang.Specification
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

class ShipControllerTests extends Specification {

    def soundManager = Mockito.mock(SoundManager.class)

    def "SetMovementTime"(){
      given:
      ShipController shipController = new ShipController(Mock(Arena.class))
      when:
      shipController.setMovementTime(time)
      then:
      shipController.getMovementTime() == expectedTime
      where:
      time  | expectedTime
       0    |      0
       50   |      50
       100  |      100
       500  |      500
    }

    def "SetShootingTime"(){
        given:
        ShipController shipController = new ShipController(Mock(Arena.class))
        when:
        shipController.setShootingTime(time)
        then:
        shipController.getShootingTime() == expectedTime
        where:
        time  | expectedTime
        0    |      0
        50   |      50
        100  |      100
        500  |      500
    }

    def "CanMoveShip - True"(){
        given:
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        shipController.getModel() >> arena
        arena.getWidth() >> 74
        arena.getHeight() >> 30
        expect:
        shipController.canMoveShip(new Position(15,27))
    }

    def "CanMoveShip - False : Collision with left wall of the Arena"(){
        given:
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        shipController.getModel() >> arena
        arena.getWidth() >> 74
        arena.getHeight() >> 30
        expect:
        !shipController.canMoveShip(new Position(0,27))
    }

    def "CanMoveShip - False : Collision with right wall of the Arena"(){
        given:
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        shipController.getModel() >> arena
        arena.getWidth() >> 74
        arena.getHeight() >> 30
        expect:
        !shipController.canMoveShip(new Position(73,10))
    }

    def "CanMoveShip - False : Collision with bottom wall of the Arena"(){
        given:
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        shipController.getModel() >> arena
        arena.getWidth() >> 74
        arena.getHeight() >> 30
        expect:
        !shipController.canMoveShip(new Position(5,29))
    }

    def "CanMoveShip - False : Collision with top wall of the Arena"(){
        given:
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        shipController.getModel() >> arena
        arena.getWidth() >> 74
        arena.getHeight() >> 30
        expect:
        !shipController.canMoveShip(new Position(47,0))
    }

    def "MoveLeft - Cannot Move"(){
        given:
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        def ship = Mock(Ship.class)
        shipController.getModel() >> arena
        arena.getShip() >> ship
        ship.getPosition() >> new Position(1,27)
        when:
        shipController.canMoveShip(new Position(0,27)) >> false
        shipController.moveLeft()
        then:
        0 * ship.setPosition(_)
    }

    def "MoveLeft - Can Move"(){
        given:
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        def ship = Mock(Ship.class)
        shipController.getModel() >> arena
        arena.getShip() >> ship
        ship.getPosition() >> new Position(34,27)
        when:
        shipController.canMoveShip(new Position(33,27)) >> true
        shipController.moveLeft()
        then:
        1 * ship.setPosition(new Position(33,27))
    }

    def "MoveRight - Cannot Move"(){
        given:
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        def ship = Mock(Ship.class)
        shipController.getModel() >> arena
        arena.getShip() >> ship
        ship.getPosition() >> new Position(72,27)
        when:
        shipController.canMoveShip(new Position(73,27)) >> false
        shipController.moveRight()
        then:
        0 * ship.setPosition(_)
    }

    def "MoveRight - Can Move"(){
        given:
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        def ship = Mock(Ship.class)
        shipController.getModel() >> arena
        arena.getShip() >> ship
        ship.getPosition() >> new Position(51,27)
        when:
        shipController.canMoveShip(new Position(52,27)) >> true
        shipController.moveRight()
        then:
        1 * ship.setPosition(new Position(52,27))
    }

    def "ShootProjectile"(){
        given:
            def shipController = Spy(ShipController.class)
            def arena = Mock(Arena.class)
            def arenaModifier = Mockito.mock(ArenaModifier.class)
            def ship = Mock(Ship.class)
            def position = new Position(10,10)
            def soundManager = Mockito.mock(SoundManager.class)
            def projectile = new Projectile(position,ship)
            shipController.getModel() >> arena
            arena.getShip() >> ship
            ship.getPosition() >> position
            shipController.getArenaModifier() >> arenaModifier
        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)
            when:
            shipController.shootProjectile()
            then:
            Mockito.verify(arenaModifier, Mockito.times(1)).addProjectile(projectile)
        }
    }

    def "ShootProjectile - Checking play sound invocation"(){
        given:
            def soundManager = Mockito.mock(SoundManager.class)
            def shipController = Spy(ShipController.class)
            def arena = Mock(Arena.class)
            def arenaModifier = Mock(ArenaModifier.class)
            def ship = Mock(Ship.class)
            def position = Mock(Position.class)
            shipController.getModel() >> arena
            arena.getShip() >> ship
            ship.getPosition() >> position
            shipController.getArenaModifier() >> arenaModifier

        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)

            when:
                shipController.shootProjectile()
            then:

                Mockito.verify(soundManager, Mockito.times(1)).playSound(Sound_Options.LASER)
        }

    }

    def "HitByProjectile"(){
        given:
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        def ship = Mock(Ship.class)
        def projectile = Mock(Projectile.class)
        def alien = Mock(Alien.class)
        shipController.getModel() >> arena
        arena.getShip() >> ship
        projectile.getElement() >> alien
        when:
        shipController.hitByProjectile(projectile)
        then:
        1 * ship.decreaseHealth(_)
        1 * alien.getDamagePerShot()
    }

    def "ShipControllerStep - Did not press any key"() {
        given:
        def game = Mock(Game.class)
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def ship = Mock(Ship)
        def position = Mock(Position)
        def key = Mock(KeyStroke.class)
        ship.getPosition() >> position
        arena.getShip() >> ship
        shipController.getModel() >> arena
        shipController.getArenaModifier() >> arenaModifier
        when:
        key == null
        shipController.step(game, key, 500)
        then:
        0 * shipController.moveLeft()
        0 * shipController.moveRight()
        0 * shipController.shootProjectile()
    }

    def "ShipControllerStep - Pressed ArrowLeft and did not move"(){
        given:
        def game = Mock(Game.class)
        def arena = Mockito.mock(Arena.class)
        def shipController = new ShipController(arena)
        def shipControllerSpy = Mockito.spy(shipController)
        def arenaModifier = Mockito.mock(ArenaModifier.class)
        def ship = Mockito.mock(Ship.class)
        def position = Mockito.mock(Position.class)
        def key = Mock(KeyStroke.class)
        Mockito.when(shipControllerSpy.getModel()).thenReturn(arena)
        Mockito.when(shipControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
        Mockito.when(arena.getShip()).thenReturn(ship)
        Mockito.when(ship.getPosition()).thenReturn(position)
        shipControllerSpy.setMovementTime(19950)
        shipControllerSpy.setShootingTime(19990)
        when:
        key.getKeyType() >> KeyType.ArrowLeft
        shipControllerSpy.step(game,key,20000)
        then:
        Mockito.verify(shipControllerSpy,Mockito.times(0)).moveLeft()
    }

    def "ShipControllerStep - Pressed ArrowLeft and moved"(){
        given:
        def game = Mock(Game.class)
        def arena = Mockito.mock(Arena.class)
        def shipController = new ShipController(arena)
        def shipControllerSpy = Mockito.spy(shipController)
        def arenaModifier = Mockito.mock(ArenaModifier.class)
        def ship = Mockito.mock(Ship.class)
        def position = Mockito.mock(Position.class)
        def key = Mock(KeyStroke.class)
        Mockito.when(shipControllerSpy.getModel()).thenReturn(arena)
        Mockito.when(shipControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
        Mockito.when(arena.getShip()).thenReturn(ship)
        Mockito.when(ship.getPosition()).thenReturn(position)
        shipControllerSpy.setMovementTime(19700)
        shipControllerSpy.setShootingTime(19650)
        when:
        key.getKeyType() >> KeyType.ArrowLeft
        shipControllerSpy.step(game,key,20000)
        then:
        Mockito.verify(shipControllerSpy,Mockito.times(1)).moveLeft()
    }

    def "ShipControllerStep - Pressed ArrowRight and did not move"(){
        given:
        def game = Mock(Game.class)
        def arena = Mockito.mock(Arena.class)
        def shipController = new ShipController(arena)
        def shipControllerSpy = Mockito.spy(shipController)
        def arenaModifier = Mockito.mock(ArenaModifier.class)
        def ship = Mockito.mock(Ship.class)
        def position = Mockito.mock(Position.class)
        def key = Mock(KeyStroke.class)
        Mockito.when(shipControllerSpy.getModel()).thenReturn(arena)
        Mockito.when(shipControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
        Mockito.when(arena.getShip()).thenReturn(ship)
        Mockito.when(ship.getPosition()).thenReturn(position)
        shipControllerSpy.setMovementTime(19950)
        shipControllerSpy.setShootingTime(19900)
        when:
        key.getKeyType() >> KeyType.ArrowRight
        shipControllerSpy.step(game,key,20000)
        then:
        Mockito.verify(shipControllerSpy,Mockito.times(0)).moveRight()
    }

    def "ShipControllerStep - Pressed ArrowRight and moved"(){
        given:
        def game = Mock(Game.class)
        def arena = Mockito.mock(Arena.class)
        def shipController = new ShipController(arena)
        def shipControllerSpy = Mockito.spy(shipController)
        def arenaModifier = Mockito.mock(ArenaModifier.class)
        def ship = Mockito.mock(Ship.class)
        def position = Mockito.mock(Position.class)
        def key = Mock(KeyStroke.class)
        Mockito.when(shipControllerSpy.getModel()).thenReturn(arena)
        Mockito.when(shipControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
        Mockito.when(arena.getShip()).thenReturn(ship)
        Mockito.when(ship.getPosition()).thenReturn(position)
        shipControllerSpy.setMovementTime(19000)
        shipControllerSpy.setShootingTime(19600)
        when:
        key.getKeyType() >> KeyType.ArrowRight
        shipControllerSpy.step(game,key,20000)
        then:
        Mockito.verify(shipControllerSpy,Mockito.times(1)).moveRight()
    }

    def "ShipControllerStep - Pressed ArrowUp and did not shoot in NormalMode"(){
        given:
        def game = Mock(Game.class)
        def arena = Mockito.mock(Arena.class)
        def shipController = new ShipController(arena)
        def shipControllerSpy = Mockito.spy(shipController)
        def arenaModifier = Mockito.mock(ArenaModifier.class)
        def ship = Mockito.mock(Ship.class)
        def position = Mockito.mock(Position.class)
        def key = Mock(KeyStroke.class)
        Mockito.when(shipControllerSpy.getModel()).thenReturn(arena)
        Mockito.when(shipControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
        Mockito.when(arena.getShip()).thenReturn(ship)
        Mockito.when(ship.getShipMode()).thenReturn(ShipMode.NORMAL_MODE)
        Mockito.when(ship.getPosition()).thenReturn(position)
        shipControllerSpy.setMovementTime(24700)
        shipControllerSpy.setShootingTime(24700)
        when:
        key.getKeyType() >> KeyType.ArrowUp
        shipControllerSpy.step(game,key,25000)
        then:
        Mockito.verify(shipControllerSpy,Mockito.times(0)).shootProjectile()
    }

    def "ShipControllerStep - Pressed ArrowUp and successfully shot in NormalMode"(){
        given:
        def game = Mock(Game.class)
        def arena = Mockito.mock(Arena.class)
        def shipController = new ShipController(arena)
        def shipControllerSpy = Mockito.spy(shipController)
        def arenaModifier = Mockito.mock(ArenaModifier.class)
        def ship = Mockito.mock(Ship.class)
        def position = Mockito.mock(Position.class)
        def key = Mock(KeyStroke.class)
        Mockito.when(shipControllerSpy.getModel()).thenReturn(arena)
        Mockito.when(shipControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
        Mockito.when(arena.getShip()).thenReturn(ship)
        Mockito.when(ship.getShipMode()).thenReturn(ShipMode.NORMAL_MODE)
        Mockito.when(ship.getPosition()).thenReturn(position)
        shipControllerSpy.setMovementTime(24990)
        shipControllerSpy.setShootingTime(24250)
        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)
            when:
            key.getKeyType() >> KeyType.ArrowUp
            shipControllerSpy.step(game, key, 25000)
            then:
            Mockito.verify(shipControllerSpy, Mockito.times(1)).shootProjectile()
        }
    }

    def "ShipControllerStep - Pressed ArrowUp and did not shoot in MachineGunMode"(){
        given:
        def game = Mock(Game.class)
        def arena = Mockito.mock(Arena.class)
        def shipController = new ShipController(arena)
        def shipControllerSpy = Mockito.spy(shipController)
        def arenaModifier = Mockito.mock(ArenaModifier.class)
        def ship = Mockito.mock(Ship.class)
        def position = Mockito.mock(Position.class)
        def key = Mock(KeyStroke.class)
        Mockito.when(shipControllerSpy.getModel()).thenReturn(arena)
        Mockito.when(shipControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
        Mockito.when(arena.getShip()).thenReturn(ship)
        Mockito.when(ship.getShipMode()).thenReturn(ShipMode.MACHINE_GUN_MODE)
        Mockito.when(ship.getPosition()).thenReturn(position)
        shipControllerSpy.setMovementTime(39900)
        shipControllerSpy.setShootingTime(39925)
        when:
        key.getKeyType() >> KeyType.ArrowUp
        shipControllerSpy.step(game,key,40000)
        then:
        Mockito.verify(shipControllerSpy,Mockito.times(0)).shootProjectile()
    }

    def "ShipControllerStep - Pressed ArrowUp and successfully shot in MachineGunMode"(){
        given:
        def game = Mock(Game.class)
        def arena = Mockito.mock(Arena.class)
        def shipController = new ShipController(arena)
        def shipControllerSpy = Mockito.spy(shipController)
        def arenaModifier = Mockito.mock(ArenaModifier.class)
        def ship = Mockito.mock(Ship.class)
        def position = Mockito.mock(Position.class)
        def key = Mock(KeyStroke.class)
        Mockito.when(shipControllerSpy.getModel()).thenReturn(arena)
        Mockito.when(shipControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
        Mockito.when(arena.getShip()).thenReturn(ship)
        Mockito.when(ship.getShipMode()).thenReturn(ShipMode.MACHINE_GUN_MODE)
        Mockito.when(ship.getPosition()).thenReturn(position)
        shipControllerSpy.setMovementTime(39500)
        shipControllerSpy.setShootingTime(39900)
        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)
            when:
            key.getKeyType() >> KeyType.ArrowUp
            shipControllerSpy.step(game, key, 40000)
            then:
            Mockito.verify(shipControllerSpy, Mockito.times(1)).shootProjectile()
        }
    }
}
