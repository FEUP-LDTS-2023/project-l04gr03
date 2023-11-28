package SpaceInvaders.Viewer.Game

import SpaceInvaders.GUI.GUI
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Model.Game.ArenaBuilderByRound
import spock.lang.Specification

class GameViewerTests extends Specification{
    def "Draw Elements"(){
        given:
            def gameViewerMocked = Mock(GameViewer)
            def gui = Mock(GUI)
        when:
            gameViewerMocked.drawElements(gui,0)
        then:
            1 * gameViewerMocked.drawElements(_,_)
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

