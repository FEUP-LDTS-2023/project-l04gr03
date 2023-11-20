package SpaceInvaders.source_code.Viewer.Menu

import SpaceInvaders.source_code.GUI.GUI
import SpaceInvaders.source_code.GUI.GUILanterna
import SpaceInvaders.source_code.Model.Menu.Menu
import SpaceInvaders.source_code.Model.Menu.StartMenu
import SpaceInvaders.source_code.Model.Position
import spock.lang.Specification

class MenuViewerTests extends Specification{

    def "Draw elements"(){
        given:
            def MViewer = Mock(MenuViewer.class)
            def gui = Mock(GUI.class)
        when:
            MViewer.drawElements(gui)
        then:
            1 * MViewer.drawElements(_)
            1 * MViewer.drawOptions(_)

    }

    def "Draw Options"(){
        given:
            def MenuViewer = Mock(MenuViewer)
            def gui = Mock(GUI)
        when:
            MenuViewer.drawOptions(gui)
        then:
            1 * MenuViewer.drawOptions(gui)
    }

    def "Draw Menu Title"(){
        given:
            def Menu = new StartMenu()
            def MenuViewer = Mock(MenuViewer)
            def gui = Mock(GUI)
            def position = Mock(Position)
        when:
            MenuViewer.drawMenuTitle(gui,"123","123",position)
        then:
            1 * MenuViewer.drawMenuTitle(_,_,_,_)
    }



}

