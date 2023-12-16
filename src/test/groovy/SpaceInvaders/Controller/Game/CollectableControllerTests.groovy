package SpaceInvaders.Controller.Game

import SpaceInvaders.Game
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Model.Game.ArenaModifier
import SpaceInvaders.Model.Game.Collectables.Collectable
import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.AlienMode
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Game.RegularGameElements.ShipMode
import SpaceInvaders.Model.Position
import com.googlecode.lanterna.input.KeyStroke
import spock.lang.Specification


class CollectableControllerTests extends Specification {

    def "setGenerateCollectableTime"(){
        given:
        CollectableController collectableController = new CollectableController(Mock(Arena.class))
        when:
        collectableController.setGenerateCollectableTime(time)
        then:
        collectableController.getGenerateCollectableTime() == expectedTime
        where:
        time | expectedTime
         0   |      0
        350  |     350
        700  |     700
        1200 |     1200
    }

    def "generateCollectable"(){
        def collectableController = Spy(CollectableController.class)
        def arena = Mock(Arena.class)
        def arenaModifier = Mock(ArenaModifier.class)
        collectableController.getModel() >> arena
        collectableController.getArenaModifier() >> arenaModifier
        when:
        collectableController.generateCollectable()
        then:
        1 * arenaModifier.createCollectable()
    }

    def "moveCollectable"(){
        given:
        def collectableController = Spy(CollectableController.class)
        def arena = Mock(Arena.class)
        def collectable = Mock(Collectable.class)
        collectableController.getModel() >> arena
        arena.getActiveCollectable() >> collectable
        collectable.getPosition() >> new Position(4,5)
        when:
        collectableController.moveCollectable()
        then:
        1 * collectable.setPosition(new Position(4,6))
    }

    def "endCollectableEffect"(){
        given:
        def collectableController = Spy(CollectableController.class)
        def arena = Mock(Arena.class)
        def arenaModifier = Mock(ArenaModifier.class)
        collectableController.getModel() >> arena
        collectableController.getArenaModifier() >> arenaModifier
        when:
        collectableController.endCollectableEffect()
        then:
        1 * arenaModifier.resetShipMode()
        1 * arenaModifier.resetAliensMode()
    }

    def "CollectableControllerStep - Generated and moved collectable"(){
        given:
        def collectableController = Spy(CollectableController.class)
        def arena = Mock(Arena.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def ship = Mock(Ship.class)
        def alien = Mock(Alien.class)
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
        def collectable = Mock(Collectable.class)
        def position = Mock(Position.class)
        def game = Mock(Game.class)
        def key = Mock(KeyStroke)
        collectableController.getModel() >> arena
        collectableController.getArenaModifier() >> arenaModifier
        arena.getShip() >> ship
        arena.getAliens() >> aliens
        arena.getActiveCollectable() >> collectable
        collectable.getPosition() >> position
        when:
        collectableController.step(game,key,23000)
        then:
        1 * collectableController.generateCollectable()
        1 * collectableController.moveCollectable()
    }

    def "CollectableControllerStep - Only moving a collectable"(){
        given:
        def collectableController = Spy(CollectableController.class)
        def arena = Mock(Arena.class)
        def ship = Mock(Ship.class)
        def alien = Mock(Alien.class)
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
        def collectable = Mock(Collectable.class)
        def position = Mock(Position.class)
        def game = Mock(Game.class)
        def key = Mock(KeyStroke)
        collectableController.getModel() >> arena
        arena.getShip() >> ship
        arena.getAliens() >> aliens
        arena.getActiveCollectable() >> collectable
        collectable.getPosition() >> position
        when:
        collectableController.step(game,key,200)
        then:
        0 * collectableController.generateCollectable()
        1 * collectableController.moveCollectable()
    }

    def "CollectableControllerStep - Collectable not moving"(){
        given:
        def collectableController = Spy(CollectableController.class)
        def arena = Mock(Arena.class)
        def ship = Mock(Ship.class)
        def alien = Mock(Alien.class)
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
        def collectable = Mock(Collectable.class)
        def position = Mock(Position.class)
        def game = Mock(Game.class)
        def key = Mock(KeyStroke)
        collectableController.getModel() >> arena
        arena.getShip() >> ship
        arena.getAliens() >> aliens
        arena.getActiveCollectable() >> collectable
        collectable.getPosition() >> position
        ship.getShipMode() >> ShipMode.NORMAL_MODE
        alien.getAlienMode() >> AlienMode.NORMAL_MODE
        when:
        collectableController.step(game,key,150)
        then:
        0 * collectableController.generateCollectable()
        0 * collectableController.moveCollectable()
    }

    def "CollectableControllerStep - Only moving a collectable - killing changing conditional boundary"(){
        given:
        def collectableController = Spy(CollectableController.class)
        def arena = Mock(Arena.class)
        def ship = Mock(Ship.class)
        def alien = Mock(Alien.class)
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
        def collectable = Mock(Collectable.class)
        def position = Mock(Position.class)
        def game = Mock(Game.class)
        def key = Mock(KeyStroke)
        collectableController.getModel() >> arena
        arena.getShip() >> ship
        arena.getAliens() >> aliens
        arena.getActiveCollectable() >> collectable
        collectable.getPosition() >> position
        ship.getShipMode() >> ShipMode.NORMAL_MODE
        alien.getAlienMode() >> AlienMode.NORMAL_MODE
        when:
        collectableController.step(game,key,20000)
        then:
        0 * collectableController.generateCollectable()
        1 * collectableController.moveCollectable()
    }

    def "CollectableControllerStep - No collectable active"(){
        given:
        def collectableController = Spy(CollectableController.class)
        def arena = Mock(Arena.class)
        def ship = Mock(Ship.class)
        def alien = Mock(Alien.class)
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
        def game = Mock(Game.class)
        def key = Mock(KeyStroke)
        collectableController.getModel() >> arena
        arena.getShip() >> ship
        arena.getAliens() >> aliens
        arena.getActiveCollectable() >> null
        when:
        collectableController.step(game,key,10000)
        then:
        0 * collectableController.generateCollectable()
        0 * collectableController.moveCollectable()
    }

    def "CollectableControllerStep - Ship collectable effect active"(){
        given:
        def collectableController = Spy(CollectableController.class)
        def arena = Mock(Arena.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def ship = Mock(Ship.class)
        def alien = Mock(Alien.class)
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
        def game = Mock(Game.class)
        def key = Mock(KeyStroke)
        collectableController.getModel() >> arena
        collectableController.getArenaModifier() >> arenaModifier
        ship.getShipMode() >> ShipMode.GOD_MODE
        arena.getShip() >> ship
        arena.getAliens() >> aliens
        arena.getActiveCollectable() >> null
        when:
        collectableController.step(game,key,19900)
        then:
        0 * collectableController.endCollectableEffect()
    }

    def "CollectableControllerStep - Ending alien collectable effect"(){
        given:
        def collectableController = Spy(CollectableController.class)
        def arena = Mock(Arena.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def ship = Mock(Ship.class)
        def alien = Mock(Alien.class)
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
        def game = Mock(Game.class)
        def key = Mock(KeyStroke)
        collectableController.getModel() >> arena
        collectableController.getArenaModifier() >> arenaModifier
        alien.getAlienMode() >> AlienMode.SCORE_10X
        arena.getShip() >> ship
        arena.getAliens() >> aliens
        arena.getActiveCollectable() >> null
        when:
        collectableController.step(game,key,19900)
        then:
        0 * collectableController.endCollectableEffect()
    }

    def "CollectableControllerStep - Ending ship collectable effect"(){
        given:
        def collectableController = Spy(CollectableController.class)
        def arena = Mock(Arena.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def ship = Mock(Ship.class)
        def alien = Mock(Alien.class)
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
        def game = Mock(Game.class)
        def key = Mock(KeyStroke)
        collectableController.getModel() >> arena
        collectableController.getArenaModifier() >> arenaModifier
        ship.getShipMode() >> ShipMode.MACHINE_GUN_MODE
        arena.getShip() >> ship
        arena.getAliens() >> aliens
        arena.getActiveCollectable() >> null
        when:
        collectableController.step(game,key,19950)
        then:
        1 * collectableController.endCollectableEffect()
    }

    def "CollectableControllerStep - Ending alien collectable effect"(){
        given:
        def collectableController = Spy(CollectableController.class)
        def arena = Mock(Arena.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def ship = Mock(Ship.class)
        def alien = Mock(Alien.class)
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
        def game = Mock(Game.class)
        def key = Mock(KeyStroke)
        collectableController.getModel() >> arena
        collectableController.getArenaModifier() >> arenaModifier
        alien.getAlienMode() >> AlienMode.SCORE_4X
        arena.getShip() >> ship
        arena.getAliens() >> aliens
        arena.getActiveCollectable() >> null
        when:
        collectableController.step(game,key,19950)
        then:
        1 * collectableController.endCollectableEffect()
    }
}
