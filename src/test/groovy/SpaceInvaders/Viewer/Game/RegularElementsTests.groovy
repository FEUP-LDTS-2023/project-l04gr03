package SpaceInvaders.Viewer.Game

import SpaceInvaders.GUI.GUI
import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.CoverWall
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Game.RegularGameElements.Wall
import SpaceInvaders.Viewer.Game.RegularElements.AlienViewer
import SpaceInvaders.Viewer.Game.RegularElements.CoverWallViewer
import SpaceInvaders.Viewer.Game.RegularElements.ProjectileViewer
import SpaceInvaders.Viewer.Game.RegularElements.ShipViewer
import SpaceInvaders.Viewer.Game.RegularElements.WallViewer
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

    def "Alien Draw Test character choice"(){
        given:
            def viewer = new AlienViewer(0)
            def gui = Mock(GUI)
            def alien = Mock(Alien)
            alien.getType() >> 0;
        when:
            viewer.draw(gui,alien)
        then:
            1 * gui.drawElement(_,'\u00ca',"#DB55DD")
        when:
            viewer = new AlienViewer(1)
            viewer.draw(gui,alien)
        then:
            1 * gui.drawElement(_, '\u00cb', "#DB55DD")
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

