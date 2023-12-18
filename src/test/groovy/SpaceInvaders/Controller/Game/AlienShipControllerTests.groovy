package SpaceInvaders.Controller.Game

import SpaceInvaders.Controller.Sound.SoundManager
import SpaceInvaders.Game
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Model.Game.ArenaModifier
import SpaceInvaders.Model.Game.RegularGameElements.AlienShip
import SpaceInvaders.Model.Game.RegularGameElements.AttackingElement
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Position
import SpaceInvaders.Model.Sound.Sound_Options
import com.googlecode.lanterna.input.KeyStroke
import org.mockito.MockedStatic
import org.mockito.Mockito
import spock.lang.Specification

class AlienShipControllerTests extends Specification{

    def soundManager = Mockito.mock(SoundManager.class)
    def arena = Mock(Arena)
    def controller = new AlienShipController(arena)
    def alienShip = Mock(AlienShip.class)

    def setup(){
        controller = Spy(controller)
        controller.getModel() >> arena
        arena.getAlienShip() >> alienShip
    }

    def "Generate Alien Ship"(){
        given:
            def arena = Mockito.mock(Arena)
            def controller = new AlienShipController(arena)
            def controllerSpy = Spy(controller)
            def arenaModifierMock =  Mockito.mock(ArenaModifier)

            controllerSpy.getArenaModifier() >> arenaModifierMock

        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)

            when: 'alien ship is generated'
                controllerSpy.generateAlienShip()

            then:
                Mockito.verify(soundManager, Mockito.times(1)).playSound(Sound_Options.ALIEN_SHIP_HIGH)
                Mockito.verify(soundManager, Mockito.times(1)).playSound(Sound_Options.ALIEN_SHIP_LOW)
                Mockito.verify(arenaModifierMock, Mockito.times(1)).createAlienShip()
        }
    }

    def "Move AlienShip - can move"() {
        given:

            alienShip.getPosition() >> new Position(10,10)
            alienShip.getMovementDirection() >> 1
            def newPosition = new Position(alienShip.getPosition().getX() + alienShip.getMovementDirection(),alienShip.getPosition().getY())

        when: 'AlienShip can move'
            controller.canMoveAlienShip() >> true
            controller.moveAlienShip()

        then:
            1 * alienShip.setPosition(newPosition)
            newPosition == new Position(11, 10)

    }


    def "Move AlienShip - can't move"() {
        given:
                def arenaModifier = Mockito.mock(ArenaModifier.class)

                controller.getArenaModifier() >> arenaModifier

            try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
                utilities.when(SoundManager::getInstance).thenReturn(soundManager)

            when: 'AlienShip can not move'
                controller.canMoveAlienShip() >> false
                controller.moveAlienShip()

            then:
                Mockito.verify(soundManager, Mockito.times(1)).stopSound(Sound_Options.ALIEN_SHIP_HIGH)
                Mockito.verify(soundManager, Mockito.times(1)).stopSound(Sound_Options.ALIEN_SHIP_LOW)
                Mockito.verify(arenaModifier, Mockito.times(1)).removeAlienShip()
        }
    }

    def "Can move alien ship Correct position"() {
        given:
            arena.getWidth() >> 74
            alienShip.getPosition() >> new Position(20, 10)

        expect:
            controller.canMoveAlienShip()
    }

    def "Can move alien ship wrong position x-1 < 0  "() {
        given:
            arena.getWidth() >> 74
            alienShip.getPosition() >> new Position(1, 10)

        expect:
            !controller.canMoveAlienShip()
    }

    def "Can move alien ship wrong position x+2 > arena.width() "(){
        given:
            alienShip.getPosition() >> new Position(73, 10)
            arena.getWidth() >> 74

        expect:
            !controller.canMoveAlienShip()
    }

    def "remove alien ship - ship not null and is destroyed"(){
        given:

            def arenaModifier = Mockito.mock(ArenaModifier.class)

            controller.getArenaModifier() >> arenaModifier

            'alienShip is destroyed'
            alienShip.isDestroyed() >> true


        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)

            when:'Ship is not null and is not destroyed'
                controller.removeAlienShip()

            then:
                Mockito.verify(soundManager, Mockito.times(1)).playSound(Sound_Options.DESTRUCTION)
                Mockito.verify(soundManager, Mockito.times(1)).stopSound(Sound_Options.ALIEN_SHIP_HIGH)
                Mockito.verify(soundManager, Mockito.times(1)).stopSound(Sound_Options.ALIEN_SHIP_LOW)
                Mockito.verify(arenaModifier, Mockito.times(1)).removeAlienShip()

        }
    }

    def "remove alien ship - ship is null "(){
        given:

            def controller = Spy(AlienShipController.class)
            def arena = Mock(Arena.class)
            def arenaModifier = Mock(ArenaModifier.class)


            controller.getModel() >> arena
            controller.getArenaModifier() >> arenaModifier

            'Ship is null'
            arena.getAlienShip() >> null

        when:'Ship is null'
            controller.removeAlienShip()

        then:
            0 * arenaModifier.removeAlienShip(_)

    }

    def "remove alien ship - ship not null and not destroyed"(){
        given:

            def arenaModifier = Mock(ArenaModifier.class)
            controller.getArenaModifier() >> arenaModifier

            'Ship is not destroyed'
            alienShip.isDestroyed() >> false

            when: 'Ship is not destroyed and is not null'
                controller.removeAlienShip()

            then:
                0 * arenaModifier.removeAlienShip()
    }

    def "hitByProjectile and ship is destroyed"() {
        given:
            def projectile = Mock(Projectile)
            def element = Mock(AttackingElement)


            projectile.getElement() >> element

            'alienShip is destroyed'
            alienShip.isDestroyed() >> true

        when: 'alienShip is hit by a projectile and is destroyed'
            controller.hitByProjectile(alienShip, projectile)

        then:
            1 * alienShip.decreaseHealth(_)
            1 * arena.increaseScore(_)

    }

    def "hitByProjectile and ship is not destroyed"() {
        given:
            def projectile = Mock(Projectile)
            def element = Mock(AttackingElement)


            projectile.getElement() >> element

            'alienShip is destroyed'
            alienShip.isDestroyed() >> false

        when: 'alienShip is hit by a projectile and is not destroyed'
            controller.hitByProjectile(alienShip, projectile)

        then:
            1 * alienShip.decreaseHealth(_)
            0 * arena.increaseScore(_)

    }

    def "Alien Ship Step generate"() {
        given:
            def controller = new AlienShipController(arena)
            def controllerSpy = Mockito.spy(controller)


        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)

            when: "Generate alien ship"
            controllerSpy.step(Mock(Game), Mock(KeyStroke), 50001)
            then:

            Mockito.verify(controllerSpy, Mockito.times(1)).generateAlienShip()
            controller.lastAppearance == 50001
        }
    }

    def " Alien ship step move"(){
        given:
            def arena = Mockito.mock(Arena.class)
            def controller = new AlienShipController(arena)
            def controllerSpy = Mockito.spy(controller)


            Mockito.when(controllerSpy.getModel()).thenReturn(arena)
            Mockito.when(arena.getAlienShip()).thenReturn(new AlienShip(Mock(Position), 0, 0, 1))


            controllerSpy.setLastMovementTime(0)
            controllerSpy.setLastAppearance(50002)


        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)

            when: "Move Alien Ship"
                controllerSpy.step(Mock(Game), Mock(KeyStroke), 50002)

            then:
                Mockito.verify(controllerSpy, Mockito.times(1)).moveAlienShip()
        }
    }

    def "Alien ship step kill mutations long subtractions with addition move alien"() {
        given:
            def arena = Mockito.mock(Arena.class)
            def controller = new AlienShipController(arena)
            def controllerSpy = Mockito.spy(controller)


            Mockito.when(controllerSpy.getModel()).thenReturn(arena)
            Mockito.when(arena.getAlienShip()).thenReturn(new AlienShip(Mock(Position), 0, 0, 1))


            controllerSpy.setLastAppearance(50001)
            controllerSpy.setLastMovementTime(50002)


        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)

            when: "Move Alien Ship kill mutation replaced long subtraction with addition"
                controllerSpy.step(Mock(Game), Mock(KeyStroke), 50002)

            then:
                Mockito.verify(controllerSpy, Mockito.times(0)).moveAlienShip()
        }
    }

    def "Alien ship step kill mutations long subtractions with addition generate alien"(){
        given:
            def arena = Mockito.mock(Arena.class)
            def controller = new AlienShipController(arena)
            def controllerSpy = Mockito.spy(controller)


            Mockito.when(controllerSpy.getModel()).thenReturn(arena)
            Mockito.when(arena.getAlienShip()).thenReturn(new AlienShip(Mock(Position), 0, 0, 1))


            controllerSpy.setLastAppearance(50001)


        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)
            when: "Generate alien ship kill mutation Replaced long subtraction with addition"
                controller.step(Mock(Game), Mock(KeyStroke), 50001)
            then:
                Mockito.verify(controllerSpy, Mockito.times(0)).generateAlienShip()
        }
    }

    def"Alien ship step kill mutations change conditional boundary move alien"() {
        given:
        def arena = Mockito.mock(Arena.class)
        def controller = new AlienShipController(arena)
        def controllerSpy = Mockito.spy(controller)

        Mockito.when(controllerSpy.getModel()).thenReturn(arena)
        Mockito.when(arena.getAlienShip()).thenReturn(new AlienShip(Mock(Position), 0, 0, 1))

        controllerSpy.setLastAppearance(50001)
        controllerSpy.setLastMovementTime(50002)

        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)

            when: "Move Alien Ship kill mutation replaced long subtraction with addition"
            controllerSpy.step(Mock(Game), Mock(KeyStroke), 50102)
            then:
            Mockito.verify(controllerSpy, Mockito.times(0)).moveAlienShip()
        }
    }

    def "Alien ship step kill mutations change conditional boundary generate alien"(){
        given:
        def arena = Mockito.mock(Arena.class)
        def controller = new AlienShipController(arena)
        def controllerSpy = Mockito.spy(controller)

        Mockito.when(controllerSpy.getModel()).thenReturn(arena)
        Mockito.when(arena.getAlienShip()).thenReturn(new AlienShip(Mock(Position), 0, 0, 1))

        controllerSpy.setLastAppearance(50001)

        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)
            when: "Generate alien ship kill mutation Replaced long subtraction with addition"
            controllerSpy.step(Mock(Game), Mock(KeyStroke), 100001)
            then:
            Mockito.verify(controllerSpy, Mockito.times(0)).generateAlienShip()
        }
    }

    def "Get lastMovement"(){
        when:
            controller.setLastMovementTime(10)
        then:
            controller.getLastMovementTime() == 10
    }

    def "Get lastAppearance"(){
        when:
            controller.setLastAppearance(10)
        then:
            controller.getLastAppearance() == 10
    }
}

