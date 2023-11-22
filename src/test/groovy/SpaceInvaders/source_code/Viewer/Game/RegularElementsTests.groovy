package SpaceInvaders.source_code.Viewer.Game

import SpaceInvaders.source_code.GUI.GUI
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Alien
import SpaceInvaders.source_code.Model.Game.RegularGameElements.CoverWall
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Ship
import SpaceInvaders.source_code.Model.Game.RegularGameElements.Wall
import SpaceInvaders.source_code.Viewer.Game.RegularElements.AlienViewer
import SpaceInvaders.source_code.Viewer.Game.RegularElements.CoverWallViewer
import SpaceInvaders.source_code.Viewer.Game.RegularElements.ProjectileViewer
import SpaceInvaders.source_code.Viewer.Game.RegularElements.ShipViewer
import SpaceInvaders.source_code.Viewer.Game.RegularElements.WallViewer
import spock.lang.Specification

class RegularElementsTests extends Specification{

    def "Alien Draw"(){
        given:
            def viewer = new AlienViewer(0);
            def gui = Mock(GUI)
            def alien = Mock(Alien)
        when:
            viewer.draw(gui,alien)
        then:
            1 * gui.drawElement(_,_,_)
    }

    def "Cover Wall Draw"(){
        given:
            def viewer = new CoverWallViewer()
            def gui = Mock(GUI)
            def coverWall = Mock(CoverWall)
        when:
            viewer.draw(gui,coverWall)
        then:
            1 * gui.drawElement(_,_,_)
    }

    def "Projectile Viewer"(){
        given:
            def viewer = new ProjectileViewer()
            def gui = Mock(GUI)
            def projectile = Mock(Projectile)
        when:
            viewer.draw(gui,projectile)
        then:
            1 * gui.drawElement(_,_,_)
    }

    def "Ship Viewer"(){
        given:
            def viewer = new ShipViewer()
            def gui = Mock(GUI)
            def ship = Mock(Ship)
        when:
            viewer.draw(gui,ship)
        then:
            1 * gui.drawElement(_,_,_)
    }

    def "Wall Viewer"(){
        given:
            def viewer = new WallViewer()
            def gui = Mock(GUI)
            def wall = Mock(Wall)
        when:
            viewer.draw(gui,wall)
        then:
            1 * gui.drawElement(_,_,_)
    }



}

