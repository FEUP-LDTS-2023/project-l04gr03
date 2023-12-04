package SpaceInvaders.Viewer.Menu

import SpaceInvaders.GUI.GUI
import SpaceInvaders.Model.Menu.Instructions
import SpaceInvaders.Model.Menu.Leaderboard
import SpaceInvaders.Model.Position
import spock.lang.Specification

class OnlyTextMenuViewerTests extends Specification{
    def "Draw elements Leaderboard"(){
        given:
            def Menu = new Leaderboard()
            def OnlyTextMenuViewer = new LeaderboardViewer(Menu)
            def gui = Mock(GUI)
        when:
            OnlyTextMenuViewer.drawElements(gui,0)
        then:
            if(Menu.text.size() <= 5) {
                (Menu.text.size() + 1) * gui.drawText(_, _, _)
                }
            else{
                    6 * gui.drawText(_,_,_)
                }
    }

    def "Draw elements Instructions"(){
        given:
        def Menu = new Instructions()
        def OnlyTextMenuViewer = new InstructionsViewer(Menu)
        def gui = Mock(GUI)
        when:
        OnlyTextMenuViewer.drawElements(gui,0)
        then:
        (Menu.text.size() + 1) * gui.drawText(_,_,_)
    }

    def "Draw FileText"(){
        given:
            def OnlyTextMenuViewer = Spy(InstructionsViewer)
            def gui = Mock(GUI)
            OnlyTextMenuViewer.getModel() >> new Instructions()
        when:
            OnlyTextMenuViewer.drawFileText(gui)
        then:
            1 * OnlyTextMenuViewer.drawFileText(gui)
            28 * gui.drawText(_,_,_)
    }

    def "Draw Menu Title"(){
        given:
        def OnlyTextMenuViewer = Mock(OnlyTextMenuViewer)
        def gui = Mock(GUI)
        def position = Mock(Position)
        when:
        OnlyTextMenuViewer.drawMenuTitle(gui,"123","123",position)
        then:
        1 * OnlyTextMenuViewer.drawMenuTitle(_,_,_,_)
    }

    def "reference_x and reference_y"(){
        given:
            def menu = new LeaderboardViewer(Mock(Leaderboard))
        expect:
            menu.getReference_x() == 5
            menu.getReference_y() == 3
    }

}