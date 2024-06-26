package SpaceInvaders.Viewer.Game

import SpaceInvaders.GUI.GUI
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Model.Game.ArenaBuilderByRound
import SpaceInvaders.Model.Game.Collectables.Collectable
import SpaceInvaders.Model.Game.Collectables.DamageCollectable
import SpaceInvaders.Model.Game.Collectables.GodModeCollectable
import SpaceInvaders.Model.Game.Collectables.HealthCollectable
import SpaceInvaders.Model.Game.Collectables.MachineGunModeCollectable
import SpaceInvaders.Model.Game.Collectables.ScoreCollectable
import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.AlienMode
import SpaceInvaders.Model.Game.RegularGameElements.AlienShip
import SpaceInvaders.Model.Game.RegularGameElements.CoverWall
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Game.RegularGameElements.ShipMode
import SpaceInvaders.Model.Game.RegularGameElements.Wall
import SpaceInvaders.Model.Position
import SpaceInvaders.Viewer.Game.Collectables.GodModeCollectableViewer
import SpaceInvaders.Viewer.Game.RegularElements.AlienViewer
import org.mockito.Mockito
import spock.lang.Specification

class GameViewerTests extends Specification{
    def "Draw Elements"(){
        given:

            def gameViewerSpy = Spy(GameViewer)
            def gui = Mock(GUI)
            def arena = Mock(Arena)

            gameViewerSpy.getModel() >> arena
            arena.getShip() >> Mock(Ship)
            arena.getWalls() >> Arrays.asList(Mock(Wall))
            arena.getProjectiles() >> Arrays.asList(Mock(Projectile))
            arena.getCoverWalls() >> Arrays.asList(Mock(CoverWall))
            arena.getAliens() >> Arrays.asList((Mock(Alien)))
            arena.getActiveCollectable() >> Mock(Collectable)
            arena.getAlienShip() >> Mock(AlienShip)

        when:
            gameViewerSpy.drawElements(gui,0)

        then:
            6 * gui.drawElement(_,_,_)
            8 * gui.drawText(_,_,_)

        when: "No Char change (boundary limit)"
            gameViewerSpy.draw(gui, 300)
            gameViewerSpy.draw(gui,600)

        then:
            2 * gui.drawElement(_, '\u00ca', _)
            10 * gui.drawElement(_,_,_)
            16 * gui.drawText(_,_,_)

        when: "Char change"
            gameViewerSpy.draw(gui,1000 )
            gameViewerSpy.draw(gui,1001)

        then:
            1 * gui.drawElement(_, '\u00cb', _)
            11 * gui.drawElement(_,_,_)
            16 * gui.drawText(_,_,_)
    }

    def "Draw Elements Health collectable"() {
        given:

            def gameViewerSpy = Spy(GameViewer)
            def gui = Mock(GUI)
            def arena = Mock(Arena)

            gameViewerSpy.getModel() >> arena
            arena.getShip() >> Mock(Ship)
            arena.getWalls() >> Arrays.asList(Mock(Wall))
            arena.getProjectiles() >> Arrays.asList(Mock(Projectile))
            arena.getCoverWalls() >> Arrays.asList(Mock(CoverWall))
            arena.getAliens() >> Arrays.asList((Mock(Alien)))
            arena.getActiveCollectable() >> new HealthCollectable(Mock(Position), Mock(Ship))
            arena.getAlienShip() >> Mock(AlienShip)

        when:
            gameViewerSpy.drawElements(gui, 0)

        then:
            7 * gui.drawElement(_, _, _)
            8 * gui.drawText(_, _, _)
    }

    def "Draw Elements God Mode collectable"() {
        given:

            def gameViewerSpy = Spy(GameViewer)
            def gui = Mock(GUI)
            def arena = Mock(Arena)

            gameViewerSpy.getModel() >> arena
            arena.getShip() >> Mock(Ship)
            arena.getWalls() >> Arrays.asList(Mock(Wall))
            arena.getProjectiles() >> Arrays.asList(Mock(Projectile))
            arena.getCoverWalls() >> Arrays.asList(Mock(CoverWall))
            arena.getAliens() >> Arrays.asList((Mock(Alien)))
            arena.getActiveCollectable() >> new GodModeCollectable(Mock(Position), Mock(Ship))
            arena.getAlienShip() >> Mock(AlienShip)

        when:
            gameViewerSpy.drawElements(gui, 0)

        then:
            7 * gui.drawElement(_, _, _)
            8 * gui.drawText(_, _, _)
    }

    def "Draw Elements MachineGun collectable"() {
        given:

            def gameViewerSpy = Spy(GameViewer)
            def gui = Mock(GUI)
            def arena = Mock(Arena)

            gameViewerSpy.getModel() >> arena
            arena.getShip() >> Mock(Ship)
            arena.getWalls() >> Arrays.asList(Mock(Wall))
            arena.getProjectiles() >> Arrays.asList(Mock(Projectile))
            arena.getCoverWalls() >> Arrays.asList(Mock(CoverWall))
            arena.getAliens() >> Arrays.asList((Mock(Alien)))
            arena.getActiveCollectable() >> new MachineGunModeCollectable(Mock(Position), Mock(Ship))
            arena.getAlienShip() >> Mock(AlienShip)

        when:
            gameViewerSpy.drawElements(gui, 0)

        then:
            7 * gui.drawElement(_, _, _)
            8 * gui.drawText(_, _, _)
    }

    def "Draw Elements Score collectable"() {
        given:

            def gameViewerSpy = Spy(GameViewer)
            def gui = Mock(GUI)
            def arena = Mock(Arena)

            gameViewerSpy.getModel() >> arena
            arena.getShip() >> Mock(Ship)
            arena.getWalls() >> Arrays.asList(Mock(Wall))
            arena.getProjectiles() >> Arrays.asList(Mock(Projectile))
            arena.getCoverWalls() >> Arrays.asList(Mock(CoverWall))
            arena.getAliens() >> Arrays.asList((Mock(Alien)))
            arena.getActiveCollectable() >> new ScoreCollectable(Mock(Position), Mock(List<Alien>), 1)
            arena.getAlienShip() >> Mock(AlienShip)

        when:
            gameViewerSpy.drawElements(gui, 0)

        then:
            7 * gui.drawElement(_, _, _)
            8 * gui.drawText(_, _, _)
    }

    def "Draw Elements Damage collectable"() {
        given:

            def gameViewerSpy = Spy(GameViewer)
            def gui = Mock(GUI)
            def arena = Mock(Arena)

            gameViewerSpy.getModel() >> arena
            arena.getShip() >> Mock(Ship)
            arena.getWalls() >> Arrays.asList(Mock(Wall))
            arena.getProjectiles() >> Arrays.asList(Mock(Projectile))
            arena.getCoverWalls() >> Arrays.asList(Mock(CoverWall))
            arena.getAliens() >> Arrays.asList((Mock(Alien)))
            arena.getActiveCollectable() >> new DamageCollectable(Mock(Position), Mock(Ship), 1)
            arena.getAlienShip() >> Mock(AlienShip)

        when:
            gameViewerSpy.drawElements(gui, 0)

        then:
            7 * gui.drawElement(_, _, _)
            8 * gui.drawText(_, _, _)
    }
    def "Draw Elements test char choice when time equals zero"(){
        given:
            def arenaBuilder = new ArenaBuilderByRound(1)
            def arena = arenaBuilder.buildArena()
            def gameViewer = new GameViewer(arena)
            def gui = Mock(GUI)

        when:
            gameViewer.drawElements(gui, 0)

        then:
            gameViewer.getAlienCharChoice() == 0
            gameViewer.getLastCharChange() == 0
    }

    def "Draw Elements test char choice time-lastCharChange is less than 300"(){
        given:
            def arenaBuilder = new ArenaBuilderByRound(1)
            def arena = arenaBuilder.buildArena()
            def gameViewer = new GameViewer(arena)
            def gui = Mock(GUI)

        when: 'change char to 1'
            gameViewer.drawElements(gui, 301)

        then:
            gameViewer.getAlienCharChoice() == 1
            gameViewer.getLastCharChange() == 301

        when: 'change char to 2'
            gameViewer.drawElements(gui, 300)
        then:
            gameViewer.getAlienCharChoice() == 1
            gameViewer.getLastCharChange() == 301
    }

    def "Draw Elements test char choice when time-lastCharChange is bigger than 300" (){
        given:
            def arenaBuilder = new ArenaBuilderByRound(1)
            def arena = arenaBuilder.buildArena()
            def gameViewer = new GameViewer(arena)
            def gui = Mock(GUI)

        when: 'change char to 1'
            gameViewer.drawElements(gui, 301)

        then:
            gameViewer.getAlienCharChoice() == 1
            gameViewer.getLastCharChange() == 301

        when: 'change char to 2'
            gameViewer.drawElements(gui, 602)

        then:

            gameViewer.getAlienCharChoice() == 0
            gameViewer.getLastCharChange() == 602
    }



}

