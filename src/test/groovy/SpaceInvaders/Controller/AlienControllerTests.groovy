package SpaceInvaders.Controller

import SpaceInvaders.Controller.Game.AlienController
import SpaceInvaders.Game
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Model.Game.ArenaModifier
import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Position
import com.googlecode.lanterna.input.KeyStroke
import spock.lang.Specification

class AlienControllerTests extends Specification {

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
        arena.getAliens() >> aliens
        arena.getAttackingAliens() >> aliens
        when:
        alienController.step(game,key,850)
        then:
        1 * alienController.updateMovementDirection()
        1 * alienController.moveAliens()
        1 * alienController.shootProjectile()
    }
}
