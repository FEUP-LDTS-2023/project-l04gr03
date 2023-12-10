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
                1 * gui.drawText(new Position(OnlyTextMenuViewer.getReference_x(), OnlyTextMenuViewer.getReference_y() + 1 +  1), "2 - " + OnlyTextMenuViewer.getModel().getText().get(1),_)
                (Menu.text.size()) * gui.drawText(_, _, _)

                }
            else{
                    1 * gui.drawText(new Position(OnlyTextMenuViewer.getReference_x(), OnlyTextMenuViewer.getReference_y() + 1 +  1), "2 - " + OnlyTextMenuViewer.getModel().getText().get(1),_)
                    5 * gui.drawText(_,_,_)
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
            1 * gui.drawText(new Position(OnlyTextMenuViewer.getReference_x(), OnlyTextMenuViewer.getReference_y() + 0 + 1),_,_)
            Menu.text.size() * gui.drawText(_,_,_)

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
            27 * gui.drawText(_,_,_)
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
            menu.getReference_x() == 35
            menu.getReference_y() == 13
    }

}