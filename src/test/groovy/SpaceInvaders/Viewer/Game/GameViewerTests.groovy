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
import SpaceInvaders.Viewer.Game.Collectables.GodModeCollectableViewer
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

