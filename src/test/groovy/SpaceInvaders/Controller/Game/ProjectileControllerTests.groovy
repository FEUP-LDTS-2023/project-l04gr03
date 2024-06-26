package SpaceInvaders.Controller.Game

import SpaceInvaders.Game
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Position
import com.googlecode.lanterna.input.KeyStroke
import spock.lang.Specification


class ProjectileControllerTests extends Specification{

    def arena = Mock(Arena.class)
    def projectileController = new ProjectileController(arena)

    def setup(){
        projectileController = Spy(projectileController)
        projectileController.getModel() >> arena
    }

    def "MoveProjectiles"(){
        given:
            def ship = Mock(Ship.class)
            def alien = Mock(Alien.class)
            def projectile1 = Mock(Projectile)
            def projectile2 = Mock(Projectile)
            def projectile3 = Mock(Projectile)

            projectile1.getElement() >> ship
            projectile1.getPosition() >> new Position(27,27)
            projectile2.getElement() >> alien
            projectile2.getPosition() >> new Position(69,12)
            projectile3.getElement() >> alien
            projectile3.getPosition() >> new Position(66,24)
            List<Projectile> projectiles = new ArrayList<>(Arrays.asList(projectile1,projectile2,projectile3))
            arena.getProjectiles() >> projectiles

        when:
            projectileController.moveProjectiles()

        then:
            1 * projectile1.setPosition(new Position(27,26))
            1 * projectile2.setPosition(new Position(69,13))
            1 * projectile3.setPosition(new Position(66,25))
    }

    def "ProjectileControllerStep"(){
        given:

            def game = Mock(Game.class)
            def key = Mock(KeyStroke.class)
            long time = 0

            List<Projectile> projectiles = new ArrayList<>()

            arena.getProjectiles() >> projectiles

        when:
            projectileController.step(game,key,time)

        then:
            1 * projectileController.moveProjectiles()
    }
}
