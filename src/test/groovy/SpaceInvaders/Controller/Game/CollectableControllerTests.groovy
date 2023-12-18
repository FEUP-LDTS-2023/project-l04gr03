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
import org.mockito.Mockito
import spock.lang.Specification


class CollectableControllerTests extends Specification {

    def arena = Mock(Arena.class)
    def collectableController = new CollectableController(arena)

    def setup(){
        collectableController = Spy(collectableController)
        collectableController.getModel() >> arena
    }

    def "setGenerateCollectableTime"(){
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
        given:
            def arenaModifier = Mock(ArenaModifier.class)
            collectableController.getArenaModifier() >> arenaModifier

        when:
            collectableController.generateCollectable()

        then:
            1 * arenaModifier.createCollectable()
    }

    def "moveCollectable"(){
        given:
            def collectable = Mock(Collectable.class)
            arena.getActiveCollectable() >> collectable
            collectable.getPosition() >> new Position(4,5)

        when:
            collectableController.moveCollectable()

        then:
            1 * collectable.setPosition(new Position(4,6))
    }

    def "endCollectableEffect"(){
        given:
            def arenaModifier = Mock(ArenaModifier.class)
            collectableController.getArenaModifier() >> arenaModifier

        when:
            collectableController.endCollectableEffect()

        then:
            1 * arenaModifier.resetShipMode()
            1 * arenaModifier.resetAliensMode()
    }

    def "CollectableControllerStep - Generated new collectable"(){
        given:
            def game = Mock(Game.class)
            def key = Mock(KeyStroke)
            def arena = Mockito.mock(Arena.class)
            def arenaModifier = Mockito.mock(ArenaModifier.class)
            def collectableController = new CollectableController(arena)
            def collectableControllerSpy = Mockito.spy(collectableController)
            def ship = Mockito.mock(Ship.class)
            def alien = Mockito.mock(Alien.class)

            List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
            List<Integer> columns = new ArrayList<>(Arrays.asList(23,34,55,56,71))

            Mockito.when(collectableControllerSpy.getModel()).thenReturn(arena)
            Mockito.when(collectableControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
            Mockito.when(arena.getShip()).thenReturn(ship)
            Mockito.when(arena.getAliens()).thenReturn(aliens)
            Mockito.doReturn(columns).when(arena).getFreeArenaColumns()
            Mockito.doReturn(aliens).when(arena).getAttackingAliens()
            Mockito.when(arena.getActiveCollectable()).thenReturn(null)
            Mockito.when(ship.getShipMode()).thenReturn(ShipMode.NORMAL_MODE)
            Mockito.when(alien.getAlienMode()).thenReturn(AlienMode.NORMAL_MODE)

        collectableControllerSpy.setGenerateCollectableTime(35000)
        collectableControllerSpy.setMovementTime(45000)

        when:' new collectable is generated'
            collectableControllerSpy.step(game,key,60000)

        then:
            Mockito.verify(collectableControllerSpy,Mockito.times(1)).generateCollectable()
    }

    def "CollectableControllerStep - Only moving a collectable"(){
        given:
            def game = Mock(Game.class)
            def key = Mock(KeyStroke)
            def arena = Mockito.mock(Arena.class)
            def arenaModifier = Mockito.mock(ArenaModifier.class)
            def collectableController = new CollectableController(arena)
            def collectableControllerSpy = Mockito.spy(collectableController)
            def ship = Mockito.mock(Ship.class)
            def alien = Mockito.mock(Alien.class)
            def collectable = Mockito.mock(Collectable.class)
            def position = Mockito.mock(Position.class)

            List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
            List<Integer> columns = new ArrayList<>(Arrays.asList(10,22,29,45,46,47,69,10,71,72))

            Mockito.when(collectableControllerSpy.getModel()).thenReturn(arena)
            Mockito.when(collectableControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
            Mockito.when(arena.getShip()).thenReturn(ship)
            Mockito.when(arena.getAliens()).thenReturn(aliens)
            Mockito.doReturn(columns).when(arena).getFreeArenaColumns()
            Mockito.doReturn(aliens).when(arena).getAttackingAliens()
            Mockito.when(arena.getActiveCollectable()).thenReturn(collectable)
            Mockito.when(ship.getShipMode()).thenReturn(ShipMode.NORMAL_MODE)
            Mockito.when(alien.getAlienMode()).thenReturn(AlienMode.NORMAL_MODE)
            Mockito.when(collectable.getPosition()).thenReturn(position)

            collectableControllerSpy.setGenerateCollectableTime(56000)
            collectableControllerSpy.setMovementTime(59800)

        when: 'Only moving one collectable'
            collectableControllerSpy.step(game,key,60000)

        then:
            Mockito.verify(collectableControllerSpy,Mockito.times(0)).generateCollectable()
            Mockito.verify(collectableControllerSpy,Mockito.times(1)).moveCollectable()
    }

    def "CollectableControllerStep - Collectable not moving"(){
        given:
            def game = Mock(Game.class)
            def key = Mock(KeyStroke)
            def arena = Mockito.mock(Arena.class)
            def arenaModifier = Mockito.mock(ArenaModifier.class)
            def collectableController = new CollectableController(arena)
            def collectableControllerSpy = Mockito.spy(collectableController)
            def ship = Mockito.mock(Ship.class)
            def alien = Mockito.mock(Alien.class)
            def collectable = Mockito.mock(Collectable.class)
            def position = Mockito.mock(Position.class)

            List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
            List<Integer> columns = new ArrayList<>(Arrays.asList(10,22,29,45,46,47,69,10,71,72))

            Mockito.when(collectableControllerSpy.getModel()).thenReturn(arena)
            Mockito.when(collectableControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
            Mockito.when(arena.getShip()).thenReturn(ship)
            Mockito.when(arena.getAliens()).thenReturn(aliens)
            Mockito.doReturn(columns).when(arena).getFreeArenaColumns()
            Mockito.doReturn(aliens).when(arena).getAttackingAliens()
            Mockito.when(arena.getActiveCollectable()).thenReturn(collectable)
            Mockito.when(ship.getShipMode()).thenReturn(ShipMode.NORMAL_MODE)
            Mockito.when(alien.getAlienMode()).thenReturn(AlienMode.NORMAL_MODE)
            Mockito.when(collectable.getPosition()).thenReturn(position)

            collectableControllerSpy.setGenerateCollectableTime(50000)
            collectableControllerSpy.setMovementTime(59850)

        when:'collectable is not moving'
            collectableControllerSpy.step(game,key,60000)

        then:
            Mockito.verify(collectableControllerSpy,Mockito.times(0)).generateCollectable()
            Mockito.verify(collectableControllerSpy,Mockito.times(0)).moveCollectable()
    }

    def "CollectableControllerStep - Only moving a collectable - killing changing conditional boundary for generateCollectable condition"(){
        given:
            def game = Mock(Game.class)
            def key = Mock(KeyStroke)
            def arena = Mockito.mock(Arena.class)
            def arenaModifier = Mockito.mock(ArenaModifier.class)
            def collectableController = new CollectableController(arena)
            def collectableControllerSpy = Mockito.spy(collectableController)
            def ship = Mockito.mock(Ship.class)
            def alien = Mockito.mock(Alien.class)
            def collectable = Mockito.mock(Collectable.class)
            def position = Mockito.mock(Position.class)

            List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
            List<Integer> columns = new ArrayList<>(Arrays.asList(10,22,29,45,46,47,69,10,71,72))

            Mockito.when(collectableControllerSpy.getModel()).thenReturn(arena)
            Mockito.when(collectableControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
            Mockito.when(arena.getShip()).thenReturn(ship)
            Mockito.when(arena.getAliens()).thenReturn(aliens)
            Mockito.doReturn(columns).when(arena).getFreeArenaColumns()
            Mockito.doReturn(aliens).when(arena).getAttackingAliens()
            Mockito.when(arena.getActiveCollectable()).thenReturn(collectable)
            Mockito.when(ship.getShipMode()).thenReturn(ShipMode.NORMAL_MODE)
            Mockito.when(alien.getAlienMode()).thenReturn(AlienMode.NORMAL_MODE)
            Mockito.when(collectable.getPosition()).thenReturn(position)

            collectableControllerSpy.setGenerateCollectableTime(40000)
            collectableControllerSpy.setMovementTime(59849)

        when: 'Only moving a collectable'
            collectableControllerSpy.step(game,key,60000)

        then:
            Mockito.verify(collectableControllerSpy,Mockito.times(0)).generateCollectable()
            Mockito.verify(collectableControllerSpy,Mockito.times(1)).moveCollectable()
    }

    def "CollectableControllerStep - No collectable active"(){
        given:
            def ship = Mock(Ship.class)
            def alien = Mock(Alien.class)
            def game = Mock(Game.class)
            def key = Mock(KeyStroke)

            List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))

            arena.getShip() >> ship
            arena.getAliens() >> aliens
            arena.getActiveCollectable() >> null

        when: 'no collectable is active'
            collectableController.step(game,key,10000)
        then:
            0 * collectableController.generateCollectable()
            0 * collectableController.moveCollectable()
    }

    def "CollectableControllerStep - Ship collectable effect active"(){
        given:
            def game = Mock(Game.class)
            def key = Mock(KeyStroke)
            def arena = Mockito.mock(Arena.class)
            def arenaModifier = Mockito.mock(ArenaModifier.class)
            def collectableController = new CollectableController(arena)
            def collectableControllerSpy = Mockito.spy(collectableController)
            def ship = Mockito.mock(Ship.class)
            def alien = Mockito.mock(Alien.class)

            List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
            List<Integer> columns = new ArrayList<>(Arrays.asList(1,2,3,5,7,12,17,44,60,70))

            Mockito.when(collectableControllerSpy.getModel()).thenReturn(arena)
            Mockito.when(collectableControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
            Mockito.when(arena.getShip()).thenReturn(ship)
            Mockito.when(arena.getAliens()).thenReturn(aliens)
            Mockito.doReturn(columns).when(arena).getFreeArenaColumns()
            Mockito.doReturn(aliens).when(arena).getAttackingAliens()
            Mockito.when(arena.getActiveCollectable()).thenReturn(null)
            Mockito.when(ship.getShipMode()).thenReturn(ShipMode.GOD_MODE)
            Mockito.when(alien.getAlienMode()).thenReturn(AlienMode.NORMAL_MODE)

            collectableControllerSpy.setGenerateCollectableTime(48960)

        when: 'ship collectable effect is active'
            collectableControllerSpy.step(game,key,60000)

        then:
            Mockito.verify(collectableControllerSpy,Mockito.times(0)).endCollectableEffect()
    }

    def "CollectableControllerStep - Alien collectable effect active"(){
        given:
            def game = Mock(Game.class)
            def key = Mock(KeyStroke)
            def arena = Mockito.mock(Arena.class)
            def arenaModifier = Mockito.mock(ArenaModifier.class)
            def collectableController = new CollectableController(arena)
            def collectableControllerSpy = Mockito.spy(collectableController)
            def ship = Mockito.mock(Ship.class)
            def alien = Mockito.mock(Alien.class)

            List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
            List<Integer> columns = new ArrayList<>(Arrays.asList(1,2,3,5,7,12,17,44,60,70))

            Mockito.when(collectableControllerSpy.getModel()).thenReturn(arena)
            Mockito.when(collectableControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
            Mockito.when(arena.getShip()).thenReturn(ship)
            Mockito.when(arena.getAliens()).thenReturn(aliens)
            Mockito.doReturn(columns).when(arena).getFreeArenaColumns()
            Mockito.doReturn(aliens).when(arena).getAttackingAliens()
            Mockito.when(arena.getActiveCollectable()).thenReturn(null)
            Mockito.when(ship.getShipMode()).thenReturn(ShipMode.NORMAL_MODE)
            Mockito.when(alien.getAlienMode()).thenReturn(AlienMode.SCORE_10X)

            collectableControllerSpy.setGenerateCollectableTime(40100)

        when: 'alien collectable effect is active'
            collectableControllerSpy.step(game,key,60000)
        then:
            Mockito.verify(collectableControllerSpy,Mockito.times(0)).endCollectableEffect()
    }

    def "CollectableControllerStep - Ending ship collectable effect"(){
        given:
            def game = Mock(Game.class)
            def key = Mock(KeyStroke)
            def arena = Mockito.mock(Arena.class)
            def arenaModifier = Mockito.mock(ArenaModifier.class)
            def collectableController = new CollectableController(arena)
            def collectableControllerSpy = Mockito.spy(collectableController)
            def ship = Mockito.mock(Ship.class)
            def alien = Mockito.mock(Alien.class)

            List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
            List<Integer> columns = new ArrayList<>(Arrays.asList(3,45,56,57,58,59,64,71))

            Mockito.when(collectableControllerSpy.getModel()).thenReturn(arena)
            Mockito.when(collectableControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
            Mockito.when(arena.getShip()).thenReturn(ship)
            Mockito.when(arena.getAliens()).thenReturn(aliens)
            Mockito.doReturn(columns).when(arena).getFreeArenaColumns()
            Mockito.doReturn(aliens).when(arena).getAttackingAliens()
            Mockito.when(arena.getActiveCollectable()).thenReturn(null)
            Mockito.when(ship.getShipMode()).thenReturn(ShipMode.MACHINE_GUN_MODE)
            Mockito.when(alien.getAlienMode()).thenReturn(AlienMode.NORMAL_MODE)

            collectableControllerSpy.setGenerateCollectableTime(40000)

        when: 'end ship collectable effect'
            collectableControllerSpy.step(game,key,60000)

        then:
            Mockito.verify(collectableControllerSpy,Mockito.times(1)).endCollectableEffect()
    }

    def "CollectableControllerStep - Ending alien collectable effect"(){
        given:
            def game = Mock(Game.class)
            def key = Mock(KeyStroke)
            def arena = Mockito.mock(Arena.class)
            def arenaModifier = Mockito.mock(ArenaModifier.class)
            def collectableController = new CollectableController(arena)
            def collectableControllerSpy = Mockito.spy(collectableController)
            def ship = Mockito.mock(Ship.class)
            def alien = Mockito.mock(Alien.class)

            List<Alien> aliens = new ArrayList<>(Arrays.asList(alien))
            List<Integer> columns = new ArrayList<>(Arrays.asList(3,45,56,57,58,59,64,71))

            Mockito.when(collectableControllerSpy.getModel()).thenReturn(arena)
            Mockito.when(collectableControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
            Mockito.when(arena.getShip()).thenReturn(ship)
            Mockito.when(arena.getAliens()).thenReturn(aliens)
            Mockito.doReturn(columns).when(arena).getFreeArenaColumns()
            Mockito.doReturn(aliens).when(arena).getAttackingAliens()
            Mockito.when(arena.getActiveCollectable()).thenReturn(null)
            Mockito.when(ship.getShipMode()).thenReturn(ShipMode.NORMAL_MODE)
            Mockito.when(alien.getAlienMode()).thenReturn(AlienMode.SCORE_4X)

            collectableControllerSpy.setGenerateCollectableTime(40090)

        when: 'end alien collectable effect'
            collectableControllerSpy.step(game,key,60000)

        then:
            Mockito.verify(collectableControllerSpy,Mockito.times(1)).endCollectableEffect()
    }
}
