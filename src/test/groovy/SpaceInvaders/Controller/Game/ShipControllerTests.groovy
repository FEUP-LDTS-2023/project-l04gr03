package SpaceInvaders.Controller.Game

import SpaceInvaders.Game
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Model.Game.ArenaModifier
import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Game.RegularGameElements.ShipMode
import SpaceInvaders.Model.Position
import spock.lang.Specification
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

class ShipControllerTests extends Specification {

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
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def ship = Mock(Ship.class)
        def position = Mock(Position.class)
        shipController.getModel() >> arena
        arena.getShip() >> ship
        ship.getPosition() >> position
        shipController.getArenaModifier() >> arenaModifier
        when:
        shipController.shootProjectile()
        then:
        1 * arenaModifier.addProjectile(_)
    }

    def "HitByProjectile"(){
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
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        def ship = Mock(Ship)
        def position = Mock(Position)
        def key = Mock(KeyStroke.class)
        ship.getPosition() >> position
        arena.getShip() >> ship
        shipController.getModel() >> arena
        when:
        key.getKeyType() >> KeyType.ArrowLeft
        shipController.step(game,key,50)
        then:
        0 * shipController.moveLeft()
    }

    def "ShipControllerStep - Pressed ArrowLeft and moved"(){
        given:
        def game = Mock(Game.class)
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        def ship = Mock(Ship)
        def position = Mock(Position)
        def key = Mock(KeyStroke.class)
        ship.getPosition() >> position
        arena.getShip() >> ship
        shipController.getModel() >> arena
        when:
        key.getKeyType() >> KeyType.ArrowLeft
        shipController.step(game,key,110)
        then:
        1 * shipController.moveLeft()
    }

    def "ShipControllerStep - Pressed ArrowRight and did not move"(){
        given:
        def game = Mock(Game.class)
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        def ship = Mock(Ship)
        def position = Mock(Position)
        def key = Mock(KeyStroke.class)
        ship.getPosition() >> position
        arena.getShip() >> ship
        shipController.getModel() >> arena
        when:
        key.getKeyType() >> KeyType.ArrowRight
        shipController.step(game,key,50)
        then:
        0 * shipController.moveRight()
    }

    def "ShipControllerStep - Pressed ArrowRight and moved"(){
        given:
        def game = Mock(Game.class)
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        def ship = Mock(Ship)
        def position = Mock(Position)
        def key = Mock(KeyStroke.class)
        ship.getPosition() >> position
        arena.getShip() >> ship
        shipController.getModel() >> arena
        when:
        key.getKeyType() >> KeyType.ArrowRight
        shipController.step(game,key,200)
        then:
        1 * shipController.moveRight()
    }

    def "ShipControllerStep - Pressed ArrowUp and did not shoot in NormalMode"(){
        def game = Mock(Game.class)
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def ship = Mock(Ship.class)
        def position = Mock(Position)
        def key = Mock(KeyStroke.class)
        ship.getPosition() >> position
        ship.getShipMode() >> ShipMode.NORMAL_MODE
        arena.getShip() >> ship
        shipController.getModel() >> arena
        shipController.getArenaModifier() >> arenaModifier
        when:
        key.getKeyType() >> KeyType.ArrowUp
        shipController.step(game,key,300)
        then:
        0 * shipController.shootProjectile()
    }

    def "ShipControllerStep - Pressed ArrowUp and successfully shot in NormalMode"(){
        given:
        def game = Mock(Game.class)
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def ship = Mock(Ship)
        def position = Mock(Position)
        def key = Mock(KeyStroke.class)
        ship.getPosition() >> position
        ship.getShipMode() >> ShipMode.NORMAL_MODE
        arena.getShip() >> ship
        shipController.getModel() >> arena
        shipController.getArenaModifier() >> arenaModifier
        when:
        key.getKeyType() >> KeyType.ArrowUp
        shipController.step(game,key,340)
        then:
        1 * shipController.shootProjectile()
    }

    def "ShipControllerStep - Pressed ArrowUp and did not shoot in MachineGunMode"(){
        given:
        def game = Mock(Game.class)
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def ship = Mock(Ship.class)
        def position = Mock(Position.class)
        def key = Mock(KeyStroke.class)
        ship.getPosition() >> position
        ship.getShipMode() >> ShipMode.MACHINE_GUN_MODE
        arena.getShip() >> ship
        shipController.getModel() >> arena
        shipController.getArenaModifier() >> arenaModifier
        when:
        key.getKeyType() >> KeyType.ArrowUp
        shipController.step(game,key,75)
        then:
        0 * shipController.shootProjectile()
    }

    def "ShipControllerStep - Pressed ArrowUp and successfully shot in MachineGunMode"(){
        given:
        def game = Mock(Game.class)
        def shipController = Spy(ShipController.class)
        def arena = Mock(Arena.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def ship = Mock(Ship.class)
        def position = Mock(Position.class)
        def key = Mock(KeyStroke.class)
        ship.getPosition() >> position
        ship.getShipMode() >> ShipMode.MACHINE_GUN_MODE
        arena.getShip() >> ship
        shipController.getModel() >> arena
        shipController.getArenaModifier() >> arenaModifier
        when:
        key.getKeyType() >> KeyType.ArrowUp
        shipController.step(game,key,150)
        then:
        1 * shipController.shootProjectile()
    }
}
