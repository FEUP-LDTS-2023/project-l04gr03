package SpaceInvaders.Controller.Game

import SpaceInvaders.Controller.Sound.SoundManager
import SpaceInvaders.Game
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Model.Game.ArenaModifier
import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.AlienState
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Position
import SpaceInvaders.Model.Sound.Sound_Options
import com.googlecode.lanterna.input.KeyStroke
import org.mockito.MockedStatic
import org.mockito.Mockito
import spock.lang.Specification

class AlienControllerTests extends Specification {

    def alienController =  new AlienController(arena)
    def arena =  Mock(Arena.class)

    def setup(){
        alienController = Spy(alienController)
        alienController.getModel() >> arena
    }

    def "Get last Movement"(){
        given:
            alienController.setLastMovementTime(10)

        expect:
            alienController.getLastMovementTime() == 10
    }

    def "last Shot time"(){
        given:
            alienController.setLastShotTime(1)

        expect:
            alienController.getLastShotTime() == 1
    }

    def "CanMoveAlien - True Case Left"(){
        given:
            def alien = Mock(Alien.class)
            arena.getWidth() >> 74

        when:
            alienController.getMovementDirection() >> MovementDirection.LEFT
            alien.getPosition() >> new Position(15,10)

        then:
            alienController.canMoveAlien(alien)
    }

    def "CanMoveAlien - False Case Left"(){
        given:
            def alien = Mock(Alien.class)
            arena.getWidth() >> 74

        when:
            alienController.getMovementDirection() >> MovementDirection.LEFT
            alien.getPosition() >> new Position(3,14)

        then:
            !alienController.canMoveAlien(alien)
    }

    def "CanMoveAlien - True Case Right"(){
        given:
            def alien = Mock(Alien.class)
            arena.getWidth() >> 74

        when:
            alienController.getMovementDirection() >> MovementDirection.RIGHT
            alien.getPosition() >> new Position(57,16)

        then:
            alienController.canMoveAlien(alien)
    }

    def "CanMoveAlien - False Case Right"(){
        given:
            def alien = Mock(Alien.class)
            arena.getWidth() >> 74

        when:
            alienController.getMovementDirection() >> MovementDirection.RIGHT
            alien.getPosition() >> new Position(70,12)

        then:
            !alienController.canMoveAlien(alien)
    }

    def "CanMoveAlien - Case Down"(){
        given:
            def alien = Mock(Alien.class)

        when:
            alienController.getMovementDirection() >> MovementDirection.DOWN

        then:
            alienController.canMoveAlien(alien)
    }


    def "CanMoveAliens - True"(){
        given:
            def arenaModifier = Mock(ArenaModifier.class)
            def position = Mock(Position.class)
            def alien1 = Mock(Alien)
            def alien2 = Mock(Alien)
            def alien3 = Mock(Alien)
            def alien4 = Mock(Alien)

            alien1.getPosition() >> position
            alien2.getPosition() >> position
            alien3.getPosition() >> position
            alien4.getPosition() >> position

            List<Alien> aliens = new ArrayList<>()
            aliens.add(alien1)
            aliens.add(alien2)
            aliens.add(alien3)
            aliens.add(alien4)

            alienController.getArenaModifier() >> arenaModifier
            arena.getAliens() >> aliens

        when: 'every alien can move'
            alienController.canMoveAlien(alien1) >> true
            alienController.canMoveAlien(alien2) >> true
            alienController.canMoveAlien(alien3) >> true
            alienController.canMoveAlien(alien4) >> true

        then:
            alienController.canMoveAliens()
    }

    def "CanMoveAliens - False"() {
        given:

            def arenaModifier = Mock(ArenaModifier.class)
            def position = Mock(Position.class)
            def alien1 = Mock(Alien)
            def alien2 = Mock(Alien)
            def alien3 = Mock(Alien)
            def alien4 = Mock(Alien)

            alien1.getPosition() >> position
            alien2.getPosition() >> position
            alien3.getPosition() >> position
            alien4.getPosition() >> position

            List<Alien> aliens = new ArrayList<>()
            aliens.add(alien1)
            aliens.add(alien2)
            aliens.add(alien3)
            aliens.add(alien4)

            alienController.getArenaModifier() >> arenaModifier
            arena.getAliens() >> aliens

        when: 'Some aliens can not move'
            alienController.canMoveAlien(alien1) >> true
            alienController.canMoveAlien(alien2) >> true
            alienController.canMoveAlien(alien3) >> false
            alienController.canMoveAlien(alien4) >> false

        then:
            !alienController.canMoveAliens()
    }

    def "MoveAlien - Move Left"(){
        given:

            def alien = Mock(Alien.class)

            alienController.getMovementDirection() >> MovementDirection.LEFT
            alien.getPosition() >> new Position(50,10)

        when: 'Alien move left'
            alienController.moveAlien(alien)

        then:
            1 * alien.setPosition(new Position(49,10))
    }

    def "MoveAlien - Move Right"(){
        given:

            def alien = Mock(Alien.class)

            alienController.getMovementDirection() >> MovementDirection.RIGHT
            alien.getPosition() >> new Position(10,14)

        when: 'Alien move right'
            alienController.moveAlien(alien)

        then:
            1 * alien.setPosition(new Position(11,14))
    }

    def "MoveAlien - Move Down"(){
        given:

            def alien = Mock(Alien.class)

            alienController.getMovementDirection() >> MovementDirection.DOWN
            alien.getPosition() >> new Position(45,16)

        when: 'Alien move down'
            alienController.moveAlien(alien)

        then:
            1 * alien.setPosition(new Position(45,17))
    }


    def "MoveAliens"(){
        given:
            def arenaModifier = Mock(ArenaModifier.class)
            def position = Mock(Position.class)
            def alien1 = Mock(Alien)
            def alien2 = Mock(Alien)
            def alien3 = Mock(Alien)
            def alien4 = Mock(Alien)

            alien1.getPosition() >> position
            alien2.getPosition() >> position
            alien3.getPosition() >> position
            alien4.getPosition() >> position

            List<Alien> aliens = new ArrayList<>()
            aliens.add(alien1)
            aliens.add(alien2)
            aliens.add(alien3)
            aliens.add(alien4)

            alienController.getArenaModifier() >> arenaModifier
            arena.getAliens() >> aliens

        when: 'Move aliens'
            alienController.moveAliens()

        then:
            4 * alienController.moveAlien(_)
    }

    def "ShootProjectile"(){
        given:

            def arenaModifier = Mock(ArenaModifier.class)
            def alien = Mock(Alien.class)

            List<Alien> attackingAliens = new ArrayList<>()
            attackingAliens.add(alien)

            alienController.getArenaModifier() >> arenaModifier
            arena.getAttackingAliens() >> attackingAliens

        when: 'Shoot projectile'
            alienController.shootProjectile()

        then:
            1 * arenaModifier.addProjectile(_)
    }

    def "HitByProjectile"(){
        given:

            def ship = Mock(Ship.class)
            def alien = Mock(Alien.class)
            def projectile = Mock(Projectile.class)

            projectile.getElement() >> ship
            ship.getDamagePerShot() >> 50
            alien.getScore() >> 20

        when: 'Hit by projectile'
            alienController.hitByProjectile(alien,projectile)

        then:
            1 * alien.decreaseHealth(50)
            1 * arena.increaseScore(20)
    }

    def "RemoveDestroyedAliens - No alien to remove"(){
        given:

            def arenaModifier = Mock(ArenaModifier.class)
            def alien1 = Mock(Alien.class)
            def alien2 = Mock(Alien.class)
            def alien3 = Mock(Alien.class)
            def alien4 = Mock(Alien.class)
            def alien5 = Mock(Alien.class)

            List<Alien> aliens = new ArrayList<>()
            aliens.add(alien1)
            aliens.add(alien2)
            aliens.add(alien3)
            aliens.add(alien4)
            aliens.add(alien5)

            alienController.getArenaModifier() >> arenaModifier
            arena.getAliens() >> aliens

        when: 'No alien is destroyed'
            alien1.isDestroyed() >> false
            alien2.isDestroyed() >> false
            alien3.isDestroyed() >> false
            alien4.isDestroyed() >> false
            alien5.isDestroyed() >> false
            alienController.removeDestroyedAliens()

        then: 'No alien is removed'
            0 * arenaModifier.removeAlien(_)
    }

    def "RemoveDestroyedAliens - Aliens to remove"(){
        given:

            def arenaModifier = Mock(ArenaModifier.class)
            def alien1 = Mock(Alien.class)
            def alien2 = Mock(Alien.class)
            def alien3 = Mock(Alien.class)
            def alien4 = Mock(Alien.class)
            def alien5 = Mock(Alien.class)

            List<Alien> aliens = new ArrayList<>()
            aliens.add(alien1)
            aliens.add(alien2)
            aliens.add(alien3)
            aliens.add(alien4)
            aliens.add(alien5)

            alienController.getArenaModifier() >> arenaModifier
            arena.getAliens() >> aliens

        when: 'Some aliens are destroyed'
            alien1.isDestroyed() >> true
            alien2.isDestroyed() >> false
            alien3.isDestroyed() >> true
            alien4.isDestroyed() >> false
            alien5.isDestroyed() >> false
            alienController.removeDestroyedAliens()

        then: 'Some aliens are removed'
            1 * arenaModifier.removeAlien(alien1)
            0 * arenaModifier.removeAlien(alien2)
            1 * arenaModifier.removeAlien(alien3)
            0 * arenaModifier.removeAlien(alien4)
            0 * arenaModifier.removeAlien(alien5)
    }

    def "RemoveDestroyedAliens - Aliens to remove - Checking play sound invocation"(){
        given:
            def soundManager = Mockito.mock(SoundManager.class)
            def arenaModifier = Mock(ArenaModifier.class)
            def alien1 = Mock(Alien.class)
            def alien2 = Mock(Alien.class)
            def alien3 = Mock(Alien.class)
            def alien4 = Mock(Alien.class)
            def alien5 = Mock(Alien.class)

            List<Alien> aliens = new ArrayList<>()
            aliens.add(alien1)
            aliens.add(alien2)
            aliens.add(alien3)
            aliens.add(alien4)
            aliens.add(alien5)

            alienController.getArenaModifier() >> arenaModifier
            arena.getAliens() >> aliens

        try (MockedStatic<SoundManager> utilities = Mockito.mockStatic(SoundManager.class)) {
            utilities.when(SoundManager::getInstance).thenReturn(soundManager)

            when: 'Some aliens are destroyed'
                alien1.isDestroyed() >> true
                alien2.isDestroyed() >> true
                alien3.isDestroyed() >> true
                alien4.isDestroyed() >> false
                alien5.isDestroyed() >> false
                alienController.removeDestroyedAliens()

            then: 'Destruction sound plays'
                Mockito.verify(soundManager, Mockito.times(3)).playSound(Sound_Options.DESTRUCTION)
        }
    }

    def "AlienControllerStep - No movement or shot"(){
        given:
            def arena = Mockito.mock(Arena.class)
            def arenaModifier = Mockito.mock(ArenaModifier.class)
            def alienController = new AlienController(arena)
            def alienControllerSpy = Mockito.spy(alienController)
            def game = Mock(Game.class)
            def key = Mock(KeyStroke.class)
            long movementCoolDown = 300
            long shootingCoolDown = 800

            List<Alien> aliens = new ArrayList<>()

            Mockito.when(alienControllerSpy.getModel()).thenReturn(arena)
            Mockito.when(alienControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
            Mockito.when(arena.getAliens()).thenReturn(aliens)
            Mockito.when(arena.getAttackingAliens()).thenReturn(aliens)

            Mockito.doReturn(movementCoolDown).when(alienControllerSpy).movementCoolDown()
            Mockito.doReturn(shootingCoolDown).when(alienControllerSpy).shootingCoolDown()

            alienControllerSpy.setLastMovementTime(49700)
            alienControllerSpy.setLastShotTime(49750)

        when: 'have no movement or shot'
            alienControllerSpy.step(game,key,50000)

        then: 'no updates updates are made and no projectiles are shot'
            Mockito.verify(alienControllerSpy,Mockito.times(0)).updateMovementDirection()
            Mockito.verify(alienControllerSpy,Mockito.times(0)).moveAliens()
            Mockito.verify(alienControllerSpy,Mockito.times(0)).shootProjectile()
    }

    def "AlienControllerStep - Movement but no shot"(){
        given:
            def arena = Mockito.mock(Arena.class)
            def arenaModifier = Mockito.mock(ArenaModifier.class)
            def alienController = new AlienController(arena)
            def alienControllerSpy = Mockito.spy(alienController)
            def game = Mock(Game.class)
            def key = Mock(KeyStroke.class)
            long movementCoolDown = 300
            long shootingCoolDown = 800

            List<Alien> aliens = new ArrayList<>()

            Mockito.when(alienControllerSpy.getModel()).thenReturn(arena)
            Mockito.when(alienControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
            Mockito.when(arena.getAliens()).thenReturn(aliens)
            Mockito.when(arena.getAttackingAliens()).thenReturn(aliens)

            Mockito.doReturn(movementCoolDown).when(alienControllerSpy).movementCoolDown()
            Mockito.doReturn(shootingCoolDown).when(alienControllerSpy).shootingCoolDown()

            alienControllerSpy.setLastMovementTime(49699)
            alienControllerSpy.setLastShotTime(49200)

        when: 'have movement but no shot'
            alienControllerSpy.step(game,key,50000)

        then: 'the movement direction method is called'
            Mockito.verify(alienControllerSpy,Mockito.times(1)).updateMovementDirection()
            Mockito.verify(alienControllerSpy,Mockito.times(1)).moveAliens()
            Mockito.verify(alienControllerSpy,Mockito.times(0)).shootProjectile()
    }

    def "AlienControllerStep - Movement and shot"(){
        given:
            def arena = Mockito.mock(Arena.class)
            def arenaModifier = Mockito.mock(ArenaModifier.class)
            def alienController = new AlienController(arena)
            def alienControllerSpy = Mockito.spy(alienController)
            def game = Mock(Game.class)
            def key = Mock(KeyStroke.class)
            long movementCoolDown = 300
            long shootingCoolDown = 800

            List<Alien> aliens = new ArrayList<>()

            Mockito.when(alienControllerSpy.getModel()).thenReturn(arena)
            Mockito.when(alienControllerSpy.getArenaModifier()).thenReturn(arenaModifier)
            Mockito.when(arena.getAliens()).thenReturn(aliens)
            Mockito.when(arena.getAttackingAliens()).thenReturn(aliens)

            Mockito.doReturn(movementCoolDown).when(alienControllerSpy).movementCoolDown()
            Mockito.doReturn(shootingCoolDown).when(alienControllerSpy).shootingCoolDown()

            alienControllerSpy.setLastMovementTime(49500)
            alienControllerSpy.setLastShotTime(49199)

        when: 'have movement and shot'
            alienControllerSpy.step(game,key,50000)

        then: 'aliens move, shoot projectiles and change direction'
            Mockito.verify(alienControllerSpy,Mockito.times(1)).updateMovementDirection()
            Mockito.verify(alienControllerSpy,Mockito.times(1)).moveAliens()
            Mockito.verify(alienControllerSpy,Mockito.times(1)).shootProjectile()
    }

    def "updateMovementDirection left"(){
        given:
                def alien1 = new Alien(new Position(10,1), 1,1,1, AlienState.PASSIVE,1)
                def alien2 = new Alien(new Position(0,2), 1,1,1, AlienState.ATTACKING,1)
                def alien3 = new Alien(new Position(1,5), 1,1,1, AlienState.ATTACKING,1)

                def aliens = Arrays.asList(alien1,alien2, alien3)

                arena.getAliens() >> aliens
                arena.getWidth() >> 9

                alienController.setMovementDirection(MovementDirection.LEFT)

                alienController.canMoveAliens() >> false

        when: 'Aliens can´t move and movement direction is left'
            alienController.updateMovementDirection()

        then:
            alienController.getMovementDirection() == MovementDirection.DOWN

    }

    def "UpdateMovement right"(){
        given:
            def alien1 = new Alien(new Position(10,1), 1,1,1, AlienState.PASSIVE,1)
            def alien2 = new Alien(new Position(0,2), 1,1,1, AlienState.ATTACKING,1)
            def alien3 = new Alien(new Position(1,5), 1,1,1, AlienState.ATTACKING,1)

            def aliens = Arrays.asList(alien1,alien2, alien3)

            arena.getAliens() >> aliens
            arena.getWidth() >> 9

            alienController.setMovementDirection(MovementDirection.RIGHT)

            alienController.canMoveAliens() >> false

        when: 'Aliens can not move and movement direction is right'
        alienController.updateMovementDirection()

        then:
        alienController.getMovementDirection() == MovementDirection.LEFT

    }

    def "UpdateMovement Down"(){
        given:
            alienController.setMovementDirection(MovementDirection.DOWN)

        when: 'Aliens movement directions is down'
            alienController.updateMovementDirection()

        then:
            alienController.getMovementDirection() == MovementDirection.RIGHT
    }

    def "Movement cool down"(){
        given:
            arena.getRound() >> 2

        expect:
            alienController.movementCoolDown() == 250
    }

    def "Movement cool down less than 100"(){
        given:
            arena.getRound() >> 6

        expect:
            alienController.movementCoolDown() == 50
    }

    def "Movement cool down equal to 100"(){
        given:
            arena.getRound() >> 5

        expect:
            alienController.movementCoolDown() == 100
    }

    def "Shooting cool down"(){
        given:
            arena.getRound() >> 2

        expect:
            alienController.shootingCoolDown() == 700
    }

    def "Shooting cool down is equal to 200"(){
        given:
            arena.getRound() >> 7

        expect:
            alienController.shootingCoolDown() == 200
    }

    def "Shooting cool down is less than 200"(){
        given:
            arena.getRound() >> 8
        expect:
            alienController.shootingCoolDown() == 100
    }
}
