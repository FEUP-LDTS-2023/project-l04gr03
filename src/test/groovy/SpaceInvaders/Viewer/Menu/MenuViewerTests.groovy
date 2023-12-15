package SpaceInvaders.Viewer.Menu

import SpaceInvaders.GUI.GUI
import SpaceInvaders.Model.Menu.GameOverMenu
import SpaceInvaders.Model.Menu.PauseMenu
import SpaceInvaders.Model.Menu.StartMenu
import SpaceInvaders.Model.Position
import spock.lang.Specification

class MenuViewerTests extends Specification{


    def "Draw elements Start Menu"(){
        given:
            def menu = new StartMenu()
            def MViewer = new StartMenuViewer(menu)
            def gui = Mock(GUI.class)
        when:
            MViewer.drawElements(gui,0)
        then:
            5 * gui.drawText(_,_,_)

    }

    def "Draw elements GameOver Menu"(){
        given:
            def menu = new GameOverMenu(20)
            def MViewer = new GameOverMenuViewer(menu)
            def gui = Mock(GUI.class)
        when:
            MViewer.drawElements(gui,0)
        then:
            6 * gui.drawText(_,_,_)

    }

    def "Draw elements Pause Menu"(){
        given:
        def menu = new PauseMenu()
        def MViewer = new PauseMenuViewer(menu)
        def gui = Mock(GUI.class)
        when:
        MViewer.drawElements(gui,0)
        then:
            5 * gui.drawText(_,_,_)

    }

    def "Draw Options"(){
        given:
            def MenuViewer = Spy(PauseMenuViewer.class)
            def gui = Mock(GUI)
            def pauseMenu = new PauseMenu()
            pauseMenu.nextOption()
            MenuViewer.getModel() >> pauseMenu
        when:
            MenuViewer.drawOptions(gui)
        then:
            1 * MenuViewer.drawOptions(gui)
            1 * gui.drawText(new Position(35, 13  + 3 * 1), "->" + MenuViewer.getModel().getOption(1), _)
            1 * gui.drawText(new Position(35, 13  + 3 * 3),MenuViewer.getModel().getOption(3), _)
            2 * gui.drawText(_,_,_)

    }

    def "Draw Menu Title"(){
        given:
            def MenuViewer = Mock(MenuViewer)
            def gui = Mock(GUI)
            def position = Mock(Position)
        when:
            MenuViewer.drawMenuTitle(gui,"123","123",position)
        then:
            1 * MenuViewer.drawMenuTitle(_,_,_,_)
    }

    def"get reference_x"(){
        given:
            def MenuViewer = new PauseMenuViewer(Mock(PauseMenu))
        expect:
            MenuViewer.getReference_x() == 35
    }

    def"get reference_y"(){
        given:
        def MenuViewer = new PauseMenuViewer(Mock(PauseMenu))
        expect:
        MenuViewer.getReference_y() == 13
    }



}

