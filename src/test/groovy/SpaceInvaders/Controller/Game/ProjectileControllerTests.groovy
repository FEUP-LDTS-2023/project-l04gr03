package SpaceInvaders.Controller.Game

import SpaceInvaders.Controller.Game.ProjectileController
import SpaceInvaders.Game
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Position
import com.googlecode.lanterna.input.KeyStroke
import spock.lang.Specification

import java.lang.reflect.Array

class ProjectileControllerTests extends Specification{

    def "MoveProjectiles"(){
        given:
        def projectileController = Spy(ProjectileController.class)
        def arena = Mock(Arena.class)
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
        projectileController.getModel() >> arena
        when:
        projectileController.moveProjectiles()
        then:
        1 * projectile1.setPosition(new Position(27,26))
        1 * projectile2.setPosition(new Position(69,13))
        1 * projectile3.setPosition(new Position(66,25))
    }

    def "ProjectileControllerStep"(){
        def projectileController = Spy(ProjectileController.class)
        def arena = Mock(Arena.class)
        def game = Mock(Game.class)
        def key = Mock(KeyStroke.class)
        List<Projectile> projectiles = new ArrayList<>()
        projectileController.getModel() >> arena
        arena.getProjectiles() >> projectiles
        long time = 0
        when:
        projectileController.step(game,key,time)
        then:
        1 * projectileController.moveProjectiles()
    }
}
