package SpaceInvaders.Viewer.Game

import SpaceInvaders.GUI.GUI
import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.AlienShip
import SpaceInvaders.Model.Game.RegularGameElements.CoverWall
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Game.RegularGameElements.Wall
import SpaceInvaders.Model.Position
import SpaceInvaders.Viewer.Game.RegularElements.AlienShipViewer
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

        when: 'Choice 0'
            viewer.draw(gui,alien)

        then:
            1 * gui.drawElement(_,'\u00ca',"#DB55DD")

        when: 'Choice 1'
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

    def "Cover Wall Test Char choices" () {
        given:
            def viewer = new CoverWallViewer()
            def gui = Mock(GUI)
            def coverWall = new CoverWall(Mock(Position), health)

        when:
            viewer.draw(gui, coverWall)

        then:
            1 * gui.drawElement(_,Char,_)

        where:
            Char | health
        '\u00d2' | 100
        '\u00d2' | 76
        '\u00d3' | 75
        '\u00d3' | 51
        '\u00d5' | 50
        '\u00d5' | 26
        '\u00d4' | 25
        '\u00d4' | 1
        '\u00d2' | 0
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

    def "AlienShip Viewer"(){
        given:
            def viewer = new AlienShipViewer();
            def gui = Mock(GUI)
            def alienShip = Mock(AlienShip)

        when:
            viewer.draw(gui,alienShip)

        then:
            1 * gui.drawElement(_,_,_)
    }


}

