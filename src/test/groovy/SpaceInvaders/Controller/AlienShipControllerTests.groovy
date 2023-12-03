package SpaceInvaders.Controller

import SpaceInvaders.Controller.Game.AlienShipController
import SpaceInvaders.Game
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Model.Game.ArenaModifier
import SpaceInvaders.Model.Game.RegularGameElements.AlienShip
import SpaceInvaders.Model.Game.RegularGameElements.AttackingElement
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Position
import com.googlecode.lanterna.input.KeyStroke
import spock.lang.Specification

class AlienShipControllerTests extends Specification{
    def "Generate Alien Ship"(){
        given:
            def arena = Mock(Arena)
            def controller = new AlienShipController(arena)
        when:
            controller.generateAlienShip()
        then:
            1 * arena.setAlienShip(_)

    }

    def "Move AlienShip - can move"() {
        given:
        def arena = Mock(Arena.class)
        def controller = Spy(AlienShipController.class)
        def alienShip = Mock(AlienShip.class)
        arena.getAlienShip() >> alienShip
        controller.getModel() >> arena

        when:
        controller.canMoveAlienShip() >> true
        alienShip.getPosition() >> Mock(Position)
        controller.moveAlienShip()
        then:
        1 * alienShip.setPosition(_)
    }


    def "Move AlienShip - can't move"() {
        given:
            def arena = Mock(Arena.class)
            def controller = Spy(AlienShipController.class)
            def alienShip = Mock(AlienShip.class)
            def arenaModifier = Mock(ArenaModifier.class)
            arena.getAlienShip() >> alienShip
            controller.getModel() >> arena
            controller.getArenaModifier() >> arenaModifier
        when:
            controller.canMoveAlienShip() >> false
            controller.moveAlienShip()
        then:
            1 * arenaModifier.removeAlienShip()

    }

    def "Can move alien ship Correct position"() {
        given:
            def controller = Spy(AlienShipController.class)
            def arena = Mock(Arena.class)
            controller.getModel() >> arena
            arena.getWidth() >> 74
        when:
            def alienShip = new AlienShip(new Position(20, 10), 100, 100, 1)
            arena.getAlienShip() >> alienShip
        then:
            controller.canMoveAlienShip() == true
    }

    def "Can move alien ship wrong position x-1 < 0  "() {
        given:
            def controller = Spy(AlienShipController.class)
            def arena = Mock(Arena.class)
            controller.getModel() >> arena
            arena.getWidth() >> 74
        when:
            def alienShip = new AlienShip(new Position(1, 10), 100, 100, 1)
            arena.getAlienShip() >> alienShip
        then:
            controller.canMoveAlienShip() == false
    }

    def "Can move alien ship wrong position x+2 > arena.width() "(){
        given:
            def controller = Spy(AlienShipController.class)
            def arena = Mock(Arena.class)
            controller.getModel() >> arena
            arena.getWidth() >> 74
        when:
            def alienShip = new AlienShip(new Position(73, 10), 100, 100, 1)
            arena.getAlienShip() >> alienShip
        then:
            controller.canMoveAlienShip() == false
    }

    def "Can move alien ship wrong position x-1 < 0 "(){
        given:
        def controller = Spy(AlienShipController.class)
        def arena = Mock(Arena.class)
        controller.getModel() >> arena
        arena.getWidth() >> 74
        when:
        def alienShip = new AlienShip(new Position(0, 10), 100, 100, 1)
        arena.getAlienShip() >> alienShip
        then:
        controller.canMoveAlienShip() == false
    }

    def "Can move alien ship wrong position x + 2 > arena.width() "(){
        given:
        def controller = Spy(AlienShipController.class)
        def arena = Mock(Arena.class)
        controller.getModel() >> arena
        arena.getWidth() >> 74
        when:
        def alienShip = new AlienShip(new Position(74, 10), 100, 100, 1)
        arena.getAlienShip() >> alienShip
        then:
        controller.canMoveAlienShip() == false
    }

    def "remove alien ship - ship not null and is destroyed"(){
        given:
            def controller = Spy(AlienShipController.class)
            def arena = Mock(Arena.class)
            def arenaModifier = Mock(ArenaModifier.class)
            controller.getModel() >> arena
            controller.getArenaModifier() >> arenaModifier
            def alienShip = Mock(AlienShip.class)
            arena.getAlienShip() >> alienShip
            alienShip.isDestroyed() >> true
        when:
            controller.removeAlienShip()
        then:
            1 * arenaModifier.removeAlienShip()

    }

    def "remove alien ship - ship is null "(){
        given:
            def controller = Spy(AlienShipController.class)
            def arena = Mock(Arena.class)
            def arenaModifier = Mock(ArenaModifier.class)
            controller.getModel() >> arena
            controller.getArenaModifier() >> arenaModifier
            arena.getAlienShip() >> null
        when:
            controller.removeAlienShip()
        then:
            0 * arenaModifier.removeAlienShip(_)

    }

    def "remove alien ship - ship not null and not destroyed"(){
        given:
            def controller = Spy(AlienShipController.class)
            def arena = Mock(Arena.class)
            def arenaModifier = Mock(ArenaModifier.class)
            controller.getModel() >> arena
            controller.getArenaModifier() >> arenaModifier
            def alienShip = Mock(AlienShip.class)
            arena.getAlienShip() >> alienShip
            alienShip.isDestroyed() >> false
        when:
            controller.removeAlienShip()
        then:
            0 * arenaModifier.removeAlienShip(alienShip)
    }

    def "hitByProjectile and ship is destroyed"() {
        given:
            def controller = Spy(AlienShipController.class)
            def arena = Mock(Arena.class)
            def alienShip = Mock(AlienShip)
            def projectile = Mock(Projectile)
            def element = Mock(AttackingElement)
            projectile.getElement() >> element
            arena.getAlienShip() >> alienShip
            controller.getModel() >> arena
            alienShip.isDestroyed() >> true
        when:
            controller.hitByProjectile(alienShip, projectile)
        then:
            1 * alienShip.decreaseHealth(_)
            1 * arena.increaseScore(_)

    }

    def "hitByProjectile and ship is not destroyed"() {
        given:
            def controller = Spy(AlienShipController.class)
            def arena = Mock(Arena.class)
            def alienShip = Mock(AlienShip)
            def projectile = Mock(Projectile)
            def element = Mock(AttackingElement)
            projectile.getElement() >> element
            arena.getAlienShip() >> alienShip
            controller.getModel() >> arena
            alienShip.isDestroyed() >> false
        when:
            controller.hitByProjectile(alienShip, projectile)
        then:
            1 * alienShip.decreaseHealth(_)
            0 * arena.increaseScore(_)

    }

    def "Alien Ship Step"(){
        given:
            def controller = Spy(AlienShipController.class)
            def arena = Mock(Arena.class)
            def arenaModifier = Mock(ArenaModifier.class)
            def alienShip = Mock(AlienShip.class)
            controller.getArenaModifier() >> arenaModifier
            controller.getModel() >> arena
            arena.getAlienShip() >> alienShip
            alienShip.getPosition() >> Mock(Position)

        when: "Generate alien ship"
            controller.step(Mock(Game), Mock(KeyStroke), 50001 )
        then:
            1 * controller.generateAlienShip()
            controller.lastAppearance == 50001

        when: "Move Alien Ship"
            controller.step(Mock(Game), Mock(KeyStroke), 50002)
        then:
            1 * controller.moveAlienShip()

        when: "Move Alien Ship kill mutation replaced long subtraction with addition"
            controller.step(Mock(Game), Mock(KeyStroke), 50205)
        then:
            1 * controller.moveAlienShip()

        when: "Generate alien ship 2 kill mutation Replaced long subtraction with addition"
            controller.step(Mock(Game), Mock(KeyStroke), 100002 )
        then:
            1 * controller.generateAlienShip()
            controller.lastAppearance == 100002
    }



}

