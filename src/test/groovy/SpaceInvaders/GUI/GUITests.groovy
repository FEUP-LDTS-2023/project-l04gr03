package SpaceInvaders.GUI

import SpaceInvaders.Model.Position
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.graphics.TextGraphicsWriter
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import spock.lang.Specification

class GUITests extends Specification{

    Screen screen
    GUILanterna gui
    TextGraphics tg

    def setup(){
        screen = Mock(TerminalScreen.class)
        tg = Mock(TextGraphics.class)

        screen.newTextGraphics() >> tg
        gui = new GUILanterna(screen)
    }

    def "Draw element"(){
        when:
            gui.drawElement(new Position(1,1), 'a' as char, "#001000")
        then:
            1 * tg.putString(1,2,'a')
            1 * tg.setForegroundColor(_)

    }


    def "Draw text"(){
        when:
            gui.drawText(new Position(1,1), "lefjop", "#010101")
        then:
            1 * tg.putString(1,1,"lefjop")
            1 * tg.setForegroundColor(_)
    }

    def "getNextAction"(){
        given:
            def key = new KeyStroke(KeyType.Enter)
            screen.pollInput() >> key
        expect:
            gui.getNextAction() == key
    }

    def "Refresh"(){
        when:
            gui.refresh()
        then:
            1 * screen.refresh()
    }

    def "Close"(){
        when:
            gui.close()
        then:
            1 * screen.close()
    }

    def "clear"(){
        when:
            gui.clear()
        then:
            1 * screen.clear()
    }
}

