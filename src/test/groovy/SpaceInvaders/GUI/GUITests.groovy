package SpaceInvaders.GUI

import SpaceInvaders.Model.Position
import spock.lang.Specification

class GUITests extends Specification{


    def "Draw element"(){
        given:
            def gui = Mock(GUI)
        when:
            gui.drawElement(Mock(Position), 'a' as char, "#djow")
        then:
            1 * gui.drawElement(_,_,_)
    }

    def "Draw text"(){
        given:
            def gui = Mock(GUI)
        when:
            gui.drawText(Mock(Position), "lefjop", "iphfO")
        then:
            1 * gui.drawText(_,_,_)
    }

    def "getNextAction"(){
        given:
            def gui = Mock(GUI)
        when:
            gui.getNextAction()
        then:
            1 * gui.getNextAction()
    }

    def "Refresh"(){
        given:
            def gui = Mock(GUI)
        when:
            gui.refresh()
        then:
            1 * gui.refresh()
    }

    def "Close"(){
        given:
            def gui = Mock(GUI)
        when:
            gui.close()
        then:
            1 * gui.close()
    }

    def "clear"(){
        given:
            def gui = Mock(GUI)
        when:
            gui.clear()
        then:
            1 * gui.clear()
    }
}

