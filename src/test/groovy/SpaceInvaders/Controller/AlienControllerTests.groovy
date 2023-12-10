package SpaceInvaders.Controller

import SpaceInvaders.Controller.Game.AlienController
import SpaceInvaders.Controller.Game.MovementDirection
import SpaceInvaders.Game
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Model.Game.ArenaModifier
import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.AlienState
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Position
import com.googlecode.lanterna.input.KeyStroke
import spock.lang.Specification

class AlienControllerTests extends Specification {

    def "Get last Movement"(){
        given:
            def alienController = new AlienController(Mock(Arena))
            alienController.setLastMovementTime(10)
        expect:
            alienController.getLastMovementTime() == 10
    }

    def "last Shot time"(){
        given:
            def alienController = new AlienController(Mock(Arena))
            alienController.setLastShotTime(1)
        expect:
            alienController.getLastShotTime() == 1
    }

    def "CanMoveAlien - True Case Left"(){
        given:
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
        def alien = Mock(Alien.class)
        alienController.getModel() >> arena
        arena.getWidth() >> 74
        when:
        alienController.getMovementDirection() >> MovementDirection.LEFT
        alien.getPosition() >> new Position(15,10)
        then:
        alienController.canMoveAlien(alien)
    }

    def "CanMoveAlien - False Case Left"(){
        given:
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
        def alien = Mock(Alien.class)
        alienController.getModel() >> arena
        arena.getWidth() >> 74
        when:
        alienController.getMovementDirection() >> MovementDirection.LEFT
        alien.getPosition() >> new Position(3,14)
        then:
        !alienController.canMoveAlien(alien)
    }

    def "CanMoveAlien - True Case Right"(){
        given:
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
        def alien = Mock(Alien.class)
        alienController.getModel() >> arena
        arena.getWidth() >> 74
        when:
        alienController.getMovementDirection() >> MovementDirection.RIGHT
        alien.getPosition() >> new Position(57,16)
        then:
        alienController.canMoveAlien(alien)
    }

    def "CanMoveAlien - False Case Right"(){
        given:
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
        def alien = Mock(Alien.class)
        alienController.getModel() >> arena
        arena.getWidth() >> 74
        when:
        alienController.getMovementDirection() >> MovementDirection.RIGHT
        alien.getPosition() >> new Position(71,12)
        then:
        !alienController.canMoveAlien(alien)
    }

    def "CanMoveAlien - Case Down"(){
        given:
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
        def alien = Mock(Alien.class)
        alienController.getModel() >> arena
        when:
        alienController.getMovementDirection() >> MovementDirection.DOWN
        then:
        alienController.canMoveAlien(alien)
    }


    def "CanMoveAliens - True"(){
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
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
        alienController.getModel() >> arena
        alienController.getArenaModifier() >> arenaModifier
        aliens.add(alien1)
        aliens.add(alien2)
        aliens.add(alien3)
        aliens.add(alien4)
        arena.getAliens() >> aliens
        when:
        alienController.canMoveAlien(alien1) >> true
        alienController.canMoveAlien(alien2) >> true
        alienController.canMoveAlien(alien3) >> true
        alienController.canMoveAlien(alien4) >> true
        then:
        alienController.canMoveAliens()
    }
    def "CanMoveAliens - False"() {
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
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
        alienController.getModel() >> arena
        alienController.getArenaModifier() >> arenaModifier
        aliens.add(alien1)
        aliens.add(alien2)
        aliens.add(alien3)
        aliens.add(alien4)
        arena.getAliens() >> aliens
        when:
        alienController.canMoveAlien(alien1) >> true
        alienController.canMoveAlien(alien2) >> true
        alienController.canMoveAlien(alien3) >> false
        alienController.canMoveAlien(alien4) >> false
        then:
        !alienController.canMoveAliens()
    }

    def "MoveAlien - Move Left"(){
        given:
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
        def alien = Mock(Alien.class)
        alienController.getModel() >> arena
        alienController.getMovementDirection() >> MovementDirection.LEFT
        alien.getPosition() >> new Position(50,10)
        when:
        alienController.moveAlien(alien)
        then:
        1 * alien.setPosition(new Position(49,10))
    }

    def "MoveAlien - Move Right"(){
        given:
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
        def alien = Mock(Alien.class)
        alienController.getModel() >> arena
        alienController.getMovementDirection() >> MovementDirection.RIGHT
        alien.getPosition() >> new Position(10,14)
        when:
        alienController.moveAlien(alien)
        then:
        1 * alien.setPosition(new Position(11,14))
    }

    def "MoveAlien - Move Down"(){
        given:
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
        def alien = Mock(Alien.class)
        alienController.getModel() >> arena
        alienController.getMovementDirection() >> MovementDirection.DOWN
        alien.getPosition() >> new Position(45,16)
        when:
        alienController.moveAlien(alien)
        then:
        1 * alien.setPosition(new Position(45,17))
    }


    def "MoveAliens"(){
        given:
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
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
        alienController.getModel() >> arena
        alienController.getArenaModifier() >> arenaModifier
        aliens.add(alien1)
        aliens.add(alien2)
        aliens.add(alien3)
        aliens.add(alien4)
        arena.getAliens() >> aliens
        when:
        alienController.moveAliens()
        then:
        4 * alienController.moveAlien(_)
    }

    def "ShootProjectile"(){
        given:
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def alien = Mock(Alien.class)
        List<Alien> attackingAliens = new ArrayList<>()
        attackingAliens.add(alien)
        alienController.getModel() >> arena
        alienController.getArenaModifier() >> arenaModifier
        arena.getAttackingAliens() >> attackingAliens
        when:
        alienController.shootProjectile()
        then:
        1 * arenaModifier.addProjectile(_)
    }

    def "HitByProjectile"(){
        given:
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
        def ship = Mock(Ship.class)
        def alien = Mock(Alien.class)
        def projectile = Mock(Projectile.class)
        alienController.getModel() >> arena
        projectile.getElement() >> ship
        ship.getDamagePerShot() >> 50
        alien.getScore() >> 20
        when:
        alienController.hitByProjectile(alien,projectile)
        then:
        1 * alien.decreaseHealth(50)
        1 * arena.increaseScore(20)
    }

    def "RemoveDestroyedAliens - No alien to remove"(){
        given:
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
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
        alienController.getModel() >> arena
        alienController.getArenaModifier() >> arenaModifier
        arena.getAliens() >> aliens
        when:
        alien1.isDestroyed() >> false
        alien2.isDestroyed() >> false
        alien3.isDestroyed() >> false
        alien4.isDestroyed() >> false
        alien5.isDestroyed() >> false
        alienController.removeDestroyedAliens()
        then:
        0 * arenaModifier.removeAlien(_)
    }

    def "RemoveDestroyedAliens - Aliens to remove"(){
        given:
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
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
        alienController.getModel() >> arena
        alienController.getArenaModifier() >> arenaModifier
        arena.getAliens() >> aliens
        when:
        alien1.isDestroyed() >> true
        alien2.isDestroyed() >> false
        alien3.isDestroyed() >> true
        alien4.isDestroyed() >> false
        alien5.isDestroyed() >> false
        alienController.removeDestroyedAliens()
        then:
        1 * arenaModifier.removeAlien(alien1)
        0 * arenaModifier.removeAlien(alien2)
        1 * arenaModifier.removeAlien(alien3)
        0 * arenaModifier.removeAlien(alien4)
        0 * arenaModifier.removeAlien(alien5)
    }

    def "AlienControllerStep - No movement or shot"(){
        given:
        def alienController = Spy(AlienController.class)
        def game = Mock(Game.class)
        def key = Mock(KeyStroke.class)
        alienController.movementCoolDown() >> 300
        alienController.shootingCoolDown() >> 800
        when:
        alienController.step(game,key,300)
        then:
        0 * alienController.updateMovementDirection()
        0 * alienController.moveAliens()
        0 * alienController.shootProjectile()
    }

    def "AlienControllerStep - Movement but no shot"(){
        given:
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
        def game = Mock(Game.class)
        def key = Mock(KeyStroke.class)
        List<Alien> aliens = new ArrayList<>()
        alienController.getModel() >> arena
        alienController.movementCoolDown() >> 300
        alienController.shootingCoolDown() >> 800
        arena.getAliens() >> aliens
        when:
        alienController.step(game,key,600)
        then:
        1 * alienController.updateMovementDirection()
        1 * alienController.moveAliens()
        0 * alienController.shootProjectile()
    }

    def "AlienControllerStep - Movement and shot"(){
        given:
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
        def game = Mock(Game.class)
        def key = Mock(KeyStroke.class)
        List<Alien> aliens = new ArrayList<>()
        alienController.getModel() >> arena
        alienController.movementCoolDown() >> 300
        alienController.shootingCoolDown() >> 800
        arena.getAliens() >> aliens
        arena.getAttackingAliens() >> aliens
        when:
        alienController.step(game,key,850)
        then:
        1 * alienController.updateMovementDirection()
        1 * alienController.moveAliens()
        1 * alienController.shootProjectile()
    }

    def "updateMovementDirection left"(){
        given:
            def alienController = Spy(AlienController.class)
            def arena = Mock(Arena.class)
            def alien1 = new Alien(new Position(10,1), 1,1,1, AlienState.PASSIVE,1)
            def alien2 = new Alien(new Position(0,2), 1,1,1, AlienState.ATTACKING,1)
            def alien3 = new Alien(new Position(1,5), 1,1,1, AlienState.ATTACKING,1)
            def aliens = Arrays.asList(alien1,alien2, alien3)
            arena.getAliens() >> aliens
            arena.getWidth() >> 9
            alienController.getModel() >> arena
            alienController.setMovementDirection(MovementDirection.LEFT)
            alienController.canMoveAliens() >> false

        when:
            alienController.updateMovementDirection()

        then:
            alienController.getMovementDirection() == MovementDirection.DOWN

    }

    def "UpdateMovement right"(){
        given:
        def alienController = Spy(AlienController.class)
        def arena = Mock(Arena.class)
        def alien1 = new Alien(new Position(10,1), 1,1,1, AlienState.PASSIVE,1)
        def alien2 = new Alien(new Position(0,2), 1,1,1, AlienState.ATTACKING,1)
        def alien3 = new Alien(new Position(1,5), 1,1,1, AlienState.ATTACKING,1)
        def aliens = Arrays.asList(alien1,alien2, alien3)
        arena.getAliens() >> aliens
        arena.getWidth() >> 9
        alienController.getModel() >> arena
        alienController.setMovementDirection(MovementDirection.RIGHT)
        alienController.canMoveAliens() >> false

        when:
        alienController.updateMovementDirection()

        then:
        alienController.getMovementDirection() == MovementDirection.LEFT

    }

    def "UpdateMovement Down"(){
        given:
            def alienController = Spy(AlienController.class)
            alienController.setMovementDirection(MovementDirection.DOWN)
        when:
            alienController.updateMovementDirection()

        then:
            alienController.getMovementDirection() == MovementDirection.RIGHT
    }

    def "Movement cool down"(){
        given:
            def arena = Mock(Arena.class)
            def alienController = new AlienController(arena)
            arena.getRound() >> 2
        expect:
            alienController.movementCoolDown() == 250
    }

    def "Movement cool down less than 100"(){
        given:
            def arena = Mock(Arena.class)
            def alienController = new AlienController(arena)
            arena.getRound() >> 6
            expect:
            alienController.movementCoolDown() == 50
    }

    def "Movement cool down equal to 100"(){
        given:
            def arena = Mock(Arena.class)
            def alienController = new AlienController(arena)
            arena.getRound() >> 5
        expect:
            alienController.movementCoolDown() == 100
    }

    def "Shooting cool down"(){
        given:
            def arena = Mock(Arena.class)
            def alienController = new AlienController(arena)
            arena.getRound() >> 2
        expect:
            alienController.shootingCoolDown() == 700
    }

    def "Shooting cool down is equal to 200"(){
        given:
            def arena = Mock(Arena.class)
            def alienController = new AlienController(arena)
            arena.getRound() >> 7
        expect:
            alienController.shootingCoolDown() == 200
    }

    def "Shooting cool down is less than 200"(){
        given:
            def arena = Mock(Arena.class)
            def alienController = new AlienController(arena)
            arena.getRound() >> 8
        expect:
            alienController.shootingCoolDown() == 100
    }
}
