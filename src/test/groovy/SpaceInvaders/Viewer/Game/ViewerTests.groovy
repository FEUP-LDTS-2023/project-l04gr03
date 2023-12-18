package SpaceInvaders.Viewer.Game

import SpaceInvaders.GUI.GUI
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.CoverWall
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Game.RegularGameElements.Wall
import SpaceInvaders.Model.Position
import spock.lang.Specification

class ViewerTests extends Specification{

    def "draw"(){
        given:
            def viewer = Spy(GameViewer.class)
            def arena = Mock(Arena)
            def gui = Mock(GUI)

            viewer.getModel() >> arena
            arena.getAliens() >> Arrays.asList(Mock(Alien))
            arena.getCoverWalls() >> Arrays.asList(Mock(CoverWall))
            arena.getProjectiles() >> Arrays.asList(Mock(Projectile))
            arena.getWalls() >> Arrays.asList(Mock(Wall))
            arena.getShip() >> new Ship(Mock(Position), 10,10)

        when:
            viewer.draw(gui, 0)

        then:
            1 * gui.clear()
            1 * gui.refresh()
            1 * viewer.drawElements(_,_)
    }
}

